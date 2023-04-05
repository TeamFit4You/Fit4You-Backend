package Fit4You.Fit4YouBackend.member.application.ports.in;

import Fit4You.Fit4YouBackend.member.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.member.dto.response.TrainingResponse;

public interface TrainingUseCase {

    TrainingResponse createTraining(TrainingCreate trainingCreate);
}
