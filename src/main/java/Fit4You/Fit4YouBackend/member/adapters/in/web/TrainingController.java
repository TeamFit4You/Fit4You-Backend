package Fit4You.Fit4YouBackend.member.adapters.in.web;

import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import Fit4You.Fit4YouBackend.member.application.ports.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.member.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.member.dto.response.TrainingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingUseCase trainingUseCase;

    @Auth
    @PostMapping("/programs/create")
    public TrainingResponse startTraining(@RequestBody @Valid TrainingCreate request){
        return trainingUseCase.createTraining(request);
    }

}
