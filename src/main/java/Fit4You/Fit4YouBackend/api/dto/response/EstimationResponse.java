package Fit4You.Fit4YouBackend.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EstimationResponse {

    @Schema(description = "정확도")
    private Float accuracy;
    @Schema(description = "피드백")
    private String feedback = "";
    @Schema(description = "피드백 부위 - 정확하지 못했던 부위들")
    private List<String> feedbackParts;
    @Builder
    public EstimationResponse(Float accuracy, List<String> feedbackParts) {
        this.accuracy = accuracy;
        this.feedbackParts = feedbackParts;
    }
}
