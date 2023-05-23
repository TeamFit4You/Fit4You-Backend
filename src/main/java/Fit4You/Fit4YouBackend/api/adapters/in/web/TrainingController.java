package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import Fit4You.Fit4YouBackend.api.application.ports.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
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
    public TrainingResponse startRecommend(@RequestBody @Valid RecommendCreate request){
        return trainingUseCase.createRecommend(request);
    }

    @Auth
    @GetMapping("/trainings/create")
    public TrainingResponse startTraining(@RequestBody @Valid TrainingCreate request){
        return trainingUseCase.createTraining(request);
    }
}
