package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.SetsPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.domains.training.Sets;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.exception.type.ApiRequestFail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutService implements WorkoutUseCase {

    private final WorkoutPort workoutPort;
    private final SetsPort setsPort;

    @Value("${ai.server.url}")
    private String serverUrl;

    @Override
    @Transactional
    public EstimationResponse estimate(MultipartFile file, Long workoutId, Integer setNo){

        // get acc & feedback
        Workout workout = workoutPort.getOne(workoutId);

        // 요청
        EstimationResponse response;
        try {
            response = requestAcc(file, workout.getExercise().getId());
        } catch (IOException e) {
            throw new ApiRequestFail();
        }

        // 응답저장
        Float acc = response.getAccuracy();
        String feedback = response.getFeedback();

        // DB에 결과 저장
        setsPort.save(Sets.builder()
                .workout(workout)
                .setNo(setNo)
                .accuracy(acc)
                .feedback(feedback)
                .build());

        //결과 Condtion에 반영
        Member member = workout.getTraining().getMember();
        Conditions condition = member.getConditions();
        String part = workout.getExercise().getDisease().getRelatedPart();

        //변화 정도 설정 1. 정확도 2. 과거병력
        float delta = 0f;
        boolean hasHist = false;
        for (MedicalHist hist : member.getMedicalHists()) {
            String histPart = hist.getDisease().getRelatedPart();
            if (histPart.equals(part)) {
                hasHist = true;
                break;
            }
        }
        if (hasHist) {
            if (acc > 80) {
                delta = -0.1f;
            } else if (acc > 60) {
                delta = 0.1f;
            } else {
                delta = 0.3f;
            }
        } else {
            if (acc > 80) {
                delta = -0.3f;
            } else if (acc < 60) {
                delta = 0.3f;
            }
        }
        condition.change(delta, part);

        return response;
    }

    private EstimationResponse requestAcc(MultipartFile multipartFile, Long exerciseId) throws IOException {

        // 정확도 측정 API URL 설정
        String url = "http://{url}:5000/workouts/estimation/{exerciseId}";
        String apiUrl = url.replace("{exerciseId}", String.valueOf(exerciseId)).replace("{url}", serverUrl);
        
        log.info(apiUrl);

        // 멀티파트 요청을 위한 HttpHeaders 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        File file = convertMultipartFileToFile(multipartFile);
        // 요청 바디 설정 (파일 첨부)
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));

        // 멀티파트 POST 요청 보내기
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        //파일삭제
        if(file.delete()){

        }

        // 응답 결과 확인
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("동영상 업로드 성공");
        } else {
            log.warn("동영상 업로드 실패");
        }

        String responseBody = responseEntity.getBody();
        // 응답 처리
        log.info("응답 결과: " + responseBody);

        ObjectMapper objectMapper = new ObjectMapper();
        EstimationResponse response = null;
        try {
            response = objectMapper.readValue(responseBody, EstimationResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(UUID.randomUUID().toString());
        Path filePath = file.toPath();

        // MultipartFile을 File로 복사
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return file;
    }
}

