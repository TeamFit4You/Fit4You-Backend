package Fit4You.Fit4YouBackend.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrainingResponse {

    private Long trainingId;

    @Builder
    public TrainingResponse(Long trainingId) {
        this.trainingId = trainingId;
    }
}
