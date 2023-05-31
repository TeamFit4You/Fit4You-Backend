package Fit4You.Fit4YouBackend.api.training.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultAllResponse {

    @Schema(description = "정확도")
    private Float accuracy1;
    @Schema(description = "피드백")
    private String feedback1 = "";
    @Schema(description = "수행 횟수 번호 ex)3번 중 count 째")
    private Integer count1;
    @Schema(description = "정확도")
    private Float accuracy2;
    @Schema(description = "피드백")
    private String feedback2 = "";
    @Schema(description = "수행 횟수 번호 ex)3번 중 count 째")
    private Integer count2;
    @Schema(description = "정확도")
    private Float accuracy3;
    @Schema(description = "피드백")
    private String feedback3 = "";
    @Schema(description = "수행 횟수 번호 ex)3번 중 count 째")
    private Integer count3;

    @Schema(description = "평균 정확도")
    private Float avgAcc;

    @Builder
    public ResultAllResponse(ResultResponse response1,ResultResponse response2,ResultResponse response3) {
        this.accuracy1 = response1.getAccuracy();
        this.feedback1 = response1.getFeedback();
        this.count1 = response1.getCount();
        this.accuracy2 = response2.getAccuracy();
        this.feedback2 = response2.getFeedback();
        this.count2 = response2.getCount();
        this.accuracy3 = response3.getAccuracy();
        this.feedback3 = response3.getFeedback();
        this.count3 = response3.getCount();
        this.avgAcc = (accuracy1+accuracy2+accuracy3)/3;
    }
}
