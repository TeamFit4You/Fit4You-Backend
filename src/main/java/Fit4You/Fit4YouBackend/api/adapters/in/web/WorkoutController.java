package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.application.ports.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.exception.type.InvalidFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutUseCase workoutUseCase;


    @PostMapping(value = "/workouts/{workoutId}/estimation/{setNo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "정확도 평가 요청", description = "운동 수행 영상의 정확도 평가 요청")
    @Parameters({
            @Parameter(name="file",description = "운동 수행 영상",required = true),
            @Parameter(name="workoutId",description = "해당 운동의 ID",required = true),
            @Parameter(name="setNo",description = "해당 운동의 몇번 째 횟수인지",required = true)
    })
    public EstimationResponse estimation(@RequestParam("file")MultipartFile file, @PathVariable Long workoutId, @PathVariable Integer setNo) {
        // 파일이 비어있는지 확인
        if (file.isEmpty()) {
            throw new InvalidFile();
        }

        return workoutUseCase.estimate(file,workoutId,setNo);
    }
}
