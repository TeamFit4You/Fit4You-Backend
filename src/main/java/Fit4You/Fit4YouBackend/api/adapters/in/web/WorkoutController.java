package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.application.ports.in.WorkoutUseCase;
import Fit4You.Fit4YouBackend.api.dto.response.EstimationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutUseCase workoutUseCase;


    @PostMapping(value = "/workouts/{workoutId}/estimation/{setId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EstimationResponse estimation(@RequestPart("file") MultipartFile file, @PathVariable Long workoutId, @PathVariable Long setId) {
        return workoutUseCase.estimate(file, workoutId, setId);
    }
}
