package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.SetsPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.training.Sets;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.exception.type.FileUploadFail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WorkoutService implements WorkoutUseCase {

    private final WorkoutPort workoutPort;
    private final SetsPort setsPort;


    @Value("${file.upload.path}") // application.properties 또는 application.yml에 설정된 경로를 가져옴
    private String fileUploadPath;
    @Override
    public EstimationResponse estimate(MultipartFile file, Long workoutId, Long setId) {
        try {
            String fileName = UUID.randomUUID().toString();
            Path filePath = Path.of(fileUploadPath, fileName);
            // 디렉토리가 존재하지 않으면 생성
            Files.createDirectories(filePath.getParent());

            // 파일 저장
            Files.copy(file.getInputStream(), filePath);

            // TODO 평가:모델과 연동
            // get acc & feedback
            Workout workout = workoutPort.getOne(workoutId);
            workout.getExercise().getId();
            // 요청 with 운동id, filepath
            // 응답 accuracy, feedback
            Float acc = 99.9f;
            String feedback = "짝짝짝";

            // DB에 결과 저장
            setsPort.save(Sets.builder()
                    .workout(workout)
                    .accuracy(acc)
                    .feedback(feedback)
                    .build()
            );

            // 평가 완료후 파일 삭제(EC2 용량 이슈)
            Files.delete(filePath);

            return EstimationResponse.builder()
                    .accuracy(acc)
                    .feedback(feedback)
                    .build();
        } catch (IOException e) {
            throw new FileUploadFail();
        }

    }
}
