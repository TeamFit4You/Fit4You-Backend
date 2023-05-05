package Fit4You.Fit4YouBackend.api.application.ports.in;

import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;

public interface TrainingUseCase {

    TrainingResponse createRecommend(RecommendCreate recommendCreate);
    TrainingResponse createTraining(TrainingCreate trainingCreate);
}
