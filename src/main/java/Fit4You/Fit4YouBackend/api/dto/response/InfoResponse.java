package Fit4You.Fit4YouBackend.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InfoResponse {

    @Schema(description = "운동 이름")
    private String name;
    @Schema(description = "운동 상세 설명")
    private String detail;

    @Schema(description = "관련 부위")
    private String part;

    @Schema(description = "관련 질환명")
    private String diseaseName;

    @Builder
    public InfoResponse(String name, String detail, String part, String diseaseName) {
        this.name = name;
        this.detail = detail;
        this.part = part;
        this.diseaseName = diseaseName;
    }
}
