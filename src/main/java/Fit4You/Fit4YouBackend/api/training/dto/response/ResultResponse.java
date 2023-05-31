package Fit4You.Fit4YouBackend.api.training.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResultResponse {

    @Schema(description = "정확도")
    private Float accuracy;
    @Schema(description = "피드백")
    private String feedback = "";
    @Schema(description = "수행 횟수 번호 ex)3번 중 count 째")
    private Integer count;
    @Builder
    public ResultResponse(Float accuracy, String feedback, Integer count) {
        this.accuracy = accuracy;
        this.feedback = feedback;
        this.count = count;
    }
}
