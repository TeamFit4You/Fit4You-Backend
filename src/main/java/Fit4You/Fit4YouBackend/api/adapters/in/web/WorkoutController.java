package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.application.ports.in.ExerciseUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.exception.type.InvalidFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutUseCase workoutUseCase;

    private final ExerciseUseCase exerciseUseCase;

    @PostMapping(value = "/workouts/estimation/{workoutId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "정확도 평가 요청", description = "운동 수행 영상의 정확도 평가 요청<br>" +
            "==Schema 참조==<br>" +
            "응답 - EstimationResponse<br>")
    @Parameters({
            @Parameter(name="file",description = "운동 수행 영상",required = true),
            @Parameter(name="workoutId",description = "해당 운동의 workout ID",required = true),
    })
    public EstimationResponse estimation(@RequestParam("file")MultipartFile file, @PathVariable Long workoutId) {
        // 파일이 비어있는지 확인
        if (file.isEmpty()) {
            throw new InvalidFile();
        }

        return workoutUseCase.estimate(file,workoutId);
    }

    @GetMapping("/workouts/video/{workoutId}")
    @Operation(summary = "전문가 데모 운동 영상 요청", description = "해당 운동의 전문가 영상 다운로드. <br>contentType = application/octet-stream <br>filename=demo_video.mp4")
    @Parameter(name = "workoutId",description = "수행하려는 workoutId")
    public ResponseEntity<Resource> downloadVideoByWorkout(@PathVariable Long workoutId) {

        Resource video = exerciseUseCase.getVideoByWorkout(workoutId);

        // 다운로드할 파일의 MIME 타입을 결정합니다.

        // 파일을 응답에 첨부하여 클라이언트에게 전송합니다.
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=demo_video.mp4")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(video);
    }
}
