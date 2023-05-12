package Fit4You.Fit4YouBackend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class TrainingCreate {

    @NotBlank
    private String email;

    private List<Long> selects;

    @Builder
    public TrainingCreate(String email, List<Long> selects) {
        this.email = email;
        this.selects = selects;

    }
}
