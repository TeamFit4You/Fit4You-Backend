package Fit4You.Fit4YouBackend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrainingCreate {

    @NotBlank
    private String email;
    @NotNull
    private Integer workoutEa;

    @Builder
    public TrainingCreate(String email, Integer workoutEa) {
        this.email = email;
        this.workoutEa = workoutEa;
    }
}
