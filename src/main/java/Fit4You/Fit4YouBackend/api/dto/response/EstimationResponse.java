package Fit4You.Fit4YouBackend.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EstimationResponse {

    private Float accuracy;
    private String feedback;

    @Builder
    public EstimationResponse(Float accuracy, String feedback) {
        this.accuracy = accuracy;
        this.feedback = feedback;
    }
}
