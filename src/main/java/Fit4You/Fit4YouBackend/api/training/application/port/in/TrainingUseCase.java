package Fit4You.Fit4YouBackend.api.training.application.port.in;

import Fit4You.Fit4YouBackend.api.training.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.training.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.training.dto.response.TrainingResponse;

public interface TrainingUseCase {

    TrainingResponse createRecommend(RecommendCreate recommendCreate);
    TrainingResponse createTraining(TrainingCreate trainingCreate);
}
