package Fit4You.Fit4YouBackend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendCreate {

    @NotBlank
    private String email;

    @Builder
    public RecommendCreate(String email) {
        this.email = email;
    }
}
