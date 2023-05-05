package Fit4You.Fit4YouBackend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConditionCreate {

    @NotBlank
    private String email;
    @NotNull
    private Integer workoutEa;

    @Builder
    public ConditionCreate(String email, Integer workoutEa) {
        this.email = email;
        this.workoutEa = workoutEa;
    }
}
