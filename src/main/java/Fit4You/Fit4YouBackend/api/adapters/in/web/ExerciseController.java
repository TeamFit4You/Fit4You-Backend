package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.application.ports.in.ExerciseUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.dto.response.ExercisesResponse;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExerciseController {

    private final ExerciseUseCase exerciseUseCase;

    @GetMapping("/exercises")
    @Operation(summary = "전체 운동 리스트 요청", description = "데이터 베이스에 존재하는 모든 운동 컬렉션 반환")
    public ExercisesResponse getExercises(){
        return exerciseUseCase.getAll();
    }

    @GetMapping("/exercises/video")
    @Operation(summary = "전문가 데모 운동 영상 요청", description = "해당 운동의 전문가 영상 다운로드. <br>contentType = application/octet-stream <br>filename=demo_video.mp4")
    @Parameter(name = "workoutId",description = "수행하려는 운동ID")
    public ResponseEntity<Resource> downloadVideo(Long workoutId) {

        Resource video = exerciseUseCase.getVideo(workoutId);

        // 다운로드할 파일의 MIME 타입을 결정합니다.

        // 파일을 응답에 첨부하여 클라이언트에게 전송합니다.
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=demo_video.mp4")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(video);
    }


}
