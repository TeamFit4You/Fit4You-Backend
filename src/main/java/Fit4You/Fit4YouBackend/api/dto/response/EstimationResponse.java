package Fit4You.Fit4YouBackend.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EstimationResponse {

    private Float accuracy;
    private String feedback = "";
    private List<String> feedbackParts;
    @Builder
    public EstimationResponse(Float accuracy, List<String> feedbackParts) {
        this.accuracy = accuracy;
        this.feedbackParts = feedbackParts;
    }
}
