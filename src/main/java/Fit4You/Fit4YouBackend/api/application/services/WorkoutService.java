package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.SetsPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.training.Sets;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutService implements WorkoutUseCase {

    private final WorkoutPort workoutPort;
    private final SetsPort setsPort;

    @Value("${ai.server.url}")
    private String serverUrl;

    @Override
    public EstimationResponse estimate(MultipartFile file, Long workoutId, Integer setNo) {

        // get acc & feedback
        Workout workout = workoutPort.getOne(workoutId);

        // 요청
        EstimationResponse response = null;
        try {
            response = requestAcc(file, workout.getExercise().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
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

        return response;
    }

    private EstimationResponse requestAcc(MultipartFile multipartFile, Long exerciseId) throws IOException {

        // API 엔드포인트 URL 설정
        String url = "http://{url}:5000/workouts/estimation/{exerciseId}";
        String apiUrl = url.replace("{exerciseId}",String.valueOf(exerciseId)).replace("{url}",serverUrl);

        //TODO
        System.out.println(apiUrl);

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
        file.delete();

        // 응답 결과 확인
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("동영상 업로드 성공");
        } else {
            System.out.println("동영상 업로드 실패");
        }

        String responseBody = responseEntity.getBody();
        // 응답 처리
        System.out.println("응답 결과: " + responseBody);

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

