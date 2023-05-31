package Fit4You.Fit4YouBackend.api.training.application.port.in;

import Fit4You.Fit4YouBackend.api.training.dto.response.EstimationResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.InfoResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.ResultAllResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.ResultResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkoutUseCase {

    EstimationResponse estimate(MultipartFile file, Long workoutId);

    InfoResponse getInfo(Long workoutId);

    List<ResultResponse> getResults(Long workoutId);
    ResultAllResponse getResultsByOne(Long workoutId);

}
