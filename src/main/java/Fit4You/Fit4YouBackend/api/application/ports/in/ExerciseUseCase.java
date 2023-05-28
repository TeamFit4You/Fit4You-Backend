package Fit4You.Fit4YouBackend.api.application.ports.in;

import Fit4You.Fit4YouBackend.api.dto.request.ExerciseRequest;
import Fit4You.Fit4YouBackend.api.dto.response.ExerciseResponse;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ExerciseUseCase {

    List<ExerciseResponse> getAll(ExerciseRequest request);

    Resource getVideo(Long exerciseId);

    Resource getVideoByWorkout(Long workoutId);

}
