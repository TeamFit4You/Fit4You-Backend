package Fit4You.Fit4YouBackend.api.application.ports.in;

import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.dto.response.ExercisesResponse;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ExerciseUseCase {

    ExercisesResponse getAll();

     Resource getVideo(Long workoutId);
}
