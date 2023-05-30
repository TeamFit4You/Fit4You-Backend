package Fit4You.Fit4YouBackend.api.training.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class TrainingCreate {

    @NotBlank
    @Schema(description = "이메일")
    private String email;

    @Schema(description = "선택한 운동들의 ID")
    private List<Long> selects;

    @Builder
    public TrainingCreate(String email, List<Long> selects) {
        this.email = email;
        this.selects = selects;

    }
}
