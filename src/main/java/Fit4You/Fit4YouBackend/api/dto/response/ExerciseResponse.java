package Fit4You.Fit4YouBackend.api.dto.response;

import Fit4You.Fit4YouBackend.api.domains.Exercise;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExerciseResponse {

    @Schema(description = "운동(Exercise) ID")
    private Long exerciseId;

    @Schema(description = "운동 상세 설명")
    private String detail;

    @Schema(description = "관련 부위")
    private String part;

    @Schema(description = "관련 질환명")
    private String diseaseName;

    @Builder
    public ExerciseResponse(Long exerciseId, String detail, String part, String diseaseName) {
        this.exerciseId = exerciseId;
        this.detail = detail;
        this.part = part;
        this.diseaseName = diseaseName;
    }
}
