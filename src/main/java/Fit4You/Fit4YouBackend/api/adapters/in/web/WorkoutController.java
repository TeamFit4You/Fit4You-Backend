package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.application.ports.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    public EstimationResponse estimation(@RequestParam("file")MultipartFile file, @PathVariable Long workoutId, @PathVariable Integer setNo) {
        // 파일이 비어있는지 확인
//        if (file.isEmpty()) {
//            // 파일이 없으면 처리 로직 작성
////            return "redirect:/error";
//        }
        return workoutUseCase.estimate(file,workoutId,setNo);
    }
}
