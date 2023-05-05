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

    @Builder
    public TrainingResponse(Long trainingId) {
        this.trainingId = trainingId;
    }
}
