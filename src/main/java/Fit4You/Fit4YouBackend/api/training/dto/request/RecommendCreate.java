package Fit4You.Fit4YouBackend.api.training.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendCreate {

    @NotBlank
    @Schema(description = "이메일")
    private String email;

    @Builder
    public RecommendCreate(String email) {
        this.email = email;
    }
}
