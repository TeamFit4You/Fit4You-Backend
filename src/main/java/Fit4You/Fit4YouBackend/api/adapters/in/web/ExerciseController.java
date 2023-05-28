package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.application.ports.in.ExerciseUseCase;
import Fit4You.Fit4YouBackend.api.dto.request.ExerciseRequest;
import Fit4You.Fit4YouBackend.api.dto.response.ExerciseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Operation(summary = "전체 운동 리스트 요청",
            description = "데이터 베이스에 존재하는 해당 부위 모든 운동 컬렉션 반환<br>" +
                    "부위리스트 = 전체, 목, 어깨, 허리, 손목, 팔꿈치, 무릎<br>" +
                    "==Schema 참조==<br>" +
                    "요청 - ExerciseRequest<br>" +
                    "응답 - ExerciseResponse<br>")
    public List<ExerciseResponse> getExercises(@RequestBody ExerciseRequest request){
        return exerciseUseCase.getAll(request);
    }


    @GetMapping("/exercises/video")
    @Operation(summary = "전문가 데모 운동 영상 요청", description = "해당 운동의 전문가 영상 다운로드. <br>contentType = application/octet-stream <br>filename=demo_video.mp4")
    @Parameter(name = "workoutId",description = "수행하려는 운동ID")
    public ResponseEntity<Resource> downloadVideo(Long workoutId) {

    @GetMapping("/exercises/video/{exerciseId}")
    @Operation(summary = "전문가 데모 운동 영상 요청", description = "해당 운동의 전문가 영상 다운로드. <br>contentType = application/octet-stream <br>filename=demo_video.mp4")
    @Parameter(name = "exerciseId",description = "수행할 운동의 exerciseId")
    public ResponseEntity<Resource> downloadVideo(@PathVariable Long exerciseId) {

        Resource video = exerciseUseCase.getVideo(exerciseId);

        // 다운로드할 파일의 MIME 타입을 결정합니다.

        // 파일을 응답에 첨부하여 클라이언트에게 전송합니다.
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=demo_video.mp4")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(video);
    }


}
