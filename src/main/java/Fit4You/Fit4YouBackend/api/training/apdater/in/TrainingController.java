package Fit4You.Fit4YouBackend.api.training.apdater.in;

import Fit4You.Fit4YouBackend.api.training.application.port.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.api.training.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.training.dto.response.TrainingResponse;
import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingUseCase trainingUseCase;

    @Auth
    @GetMapping("/trainings/recommend")
    @Operation(summary = "추천 운동 리스트 요청", description = "사용자의 현재 상태를 바탕으로 오늘의 운동 -(운동 추천3개) 제공 요청<br>" +
            "==Schema 참조==<br>" +
            "응답 - TrainingResponse<br>")
    @Parameter(name = "email",description = "이메일 - xxx@xxx.xx 양식 필수")
    public TrainingResponse startRecommend(@RequestParam @Valid String email){
        RecommendCreate request = RecommendCreate.builder()
                .email(email)
                .build();
        return trainingUseCase.createRecommend(request);
    }

//    @Auth
//    @GetMapping("/trainings/create")
//    @Operation(summary = "운동 리스트 요청", description = "사용자가 선택한 운동(id)들로 운동리스트를 구성")
//    public TrainingResponse startTraining(@RequestBody @Valid TrainingCreate request){
//        return trainingUseCase.createTraining(request);
//    }

}
