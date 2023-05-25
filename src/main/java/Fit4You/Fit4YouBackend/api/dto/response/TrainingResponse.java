package Fit4You.Fit4YouBackend.api.dto.response;

import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TrainingResponse {

    private Long trainingId;
    private List<Long> workouts;

    @Builder
    public TrainingResponse(Long trainingId, List<Long> workouts) {
        this.trainingId = trainingId;
        this.workouts = workouts;
    }
}
