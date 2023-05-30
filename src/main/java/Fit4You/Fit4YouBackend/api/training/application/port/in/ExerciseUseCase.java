package Fit4You.Fit4YouBackend.api.training.application.port.in;

import Fit4You.Fit4YouBackend.api.training.dto.request.ExerciseRequest;
import Fit4You.Fit4YouBackend.api.training.dto.response.ExerciseResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.InfoResponse;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ExerciseUseCase {

    List<ExerciseResponse> getAll(ExerciseRequest request);

    Resource getVideo(Long exerciseId);

    Resource getVideoByWorkout(Long workoutId);

    InfoResponse getInfo(Long exerciseId);
}
