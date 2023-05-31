package Fit4You.Fit4YouBackend.api.training.apdater.in;

import Fit4You.Fit4YouBackend.api.training.application.port.in.ExerciseUseCase;
import Fit4You.Fit4YouBackend.api.training.application.port.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.training.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.InfoResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.ResultAllResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.ResultResponse;
import Fit4You.Fit4YouBackend.exception.type.InvalidFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;
import java.util.List;

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

    @GetMapping(value = "/workouts/estimation/{workoutId}/one")
    @Operation(summary = "하나로 합쳐진 정확도 평가 결과 요청", description = "운동 수행 영상의 정확도 평가 결과 요청<br>" +
            "==Schema 참조==<br>" +
            "응답 - EstimationResponse<br>")
    @Parameters({
            @Parameter(name="workoutId",description = "해당 운동의 workout ID",required = true),
    })
    public ResultAllResponse getEstimationResultAll(@PathVariable Long workoutId) {
        // 파일이 비어있는지 확인

        return workoutUseCase.getResultsByOne(workoutId);
    }

    @GetMapping(value = "/workouts/estimation/{workoutId}")
    @Operation(summary = "정확도 평가 결과 요청", description = "운동 수행 영상의 정확도 평가 결과 요청<br>" +
            "==Schema 참조==<br>" +
            "응답 - EstimationResponse<br>")
    @Parameters({
            @Parameter(name="workoutId",description = "해당 운동의 workout ID",required = true),
    })
    public List<ResultResponse> getEstimationResult(@PathVariable Long workoutId) {
        // 파일이 비어있는지 확인

        return workoutUseCase.getResults(workoutId);
    }

    @GetMapping("/workouts/info/{workoutId}")
    @Operation(summary = "수행할 운동정보 요청", description = "다음과 같은 운동정보 제공<br>운동명, 운동상세정보, 관련 질환, 관련부위<br>" +
            "==Schema 참조==<br>" +
            "응답 - InfoResponse<br>")
    @Parameter(name = "workoutId",description = "수행하려는 workoutId")
    public InfoResponse exerciseInfo(@PathVariable Long workoutId) {
        return workoutUseCase.getInfo(workoutId);
    }


    @GetMapping("/workouts/video/{workoutId}/stream")
    @Operation(summary = "전문가 데모 운동 영상 스트리밍 요청", description = "해당 운동의 전문가 영상 다운로드. <br>contentType = application/octet-stream <br>filename=demo_video.mp4")
    @Parameter(name = "workoutId",description = "수행하려는 workoutId")
    public ResponseEntity<StreamingResponseBody> streamVideoByWorkout(@PathVariable Long workoutId) {

        Resource video = exerciseUseCase.getVideoByWorkout(workoutId);

        // 스트리밍 응답 생성
        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream videoStream = video.getInputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = videoStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        };

        // Content-Type 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
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
