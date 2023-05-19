package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.exception.FileUploadFail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    @Value("${file.upload.path}") // application.properties 또는 application.yml에 설정된 경로를 가져옴
    private String fileUploadPath;

    @PostMapping(value = "/workouts/{workoutId}/estimation/{setId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EstimationResponse estimate(@RequestPart("file") MultipartFile file, @PathVariable Long workoutId, @PathVariable Long setId) {

        /*TODO 서비스 계층으로 분리*/
        try {
            String fileName = UUID.randomUUID().toString();
            Path filePath = Path.of(fileUploadPath, fileName);
            // 디렉토리가 존재하지 않으면 생성
            Files.createDirectories(filePath.getParent());

            // 파일 저장
            Files.copy(file.getInputStream(), filePath);

            //평가:모델과 연동

            //파일 삭제
            Files.delete(filePath);
            return EstimationResponse.builder()
                    .accuracy(99.9f)
                    .feedback("짝짝짝")
                    .build();
        } catch (IOException e) {
            throw new FileUploadFail();
        }
    }
}
