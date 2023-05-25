package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import Fit4You.Fit4YouBackend.api.application.ports.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingUseCase trainingUseCase;

    @Auth
    @GetMapping("/trainings/recommend")
    @Operation(summary = "추천 운동 리스트 요청", description = "사용자의 현재 상태를 바탕으로 추천 운동 리스트(3개) 요청")
    public TrainingResponse startRecommend(@RequestBody @Valid RecommendCreate request){
        return trainingUseCase.createRecommend(request);
    }

    @Auth
    @GetMapping("/trainings/create")
    @Operation(summary = "운동 리스트 요청", description = "사용자가 선택한 운동(id)들로 운동리스트를 구성")
    public TrainingResponse startTraining(@RequestBody @Valid TrainingCreate request){
        return trainingUseCase.createTraining(request);
    }
}
