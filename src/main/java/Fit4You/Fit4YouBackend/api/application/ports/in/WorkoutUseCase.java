package Fit4You.Fit4YouBackend.api.application.ports.in;

import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.api.dto.response.InfoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface WorkoutUseCase {

    EstimationResponse estimate(MultipartFile file, Long workoutId);

    InfoResponse getInfo(Long workoutId);
}
