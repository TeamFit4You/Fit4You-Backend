package Fit4You.Fit4YouBackend.member.dto.request;

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
    private Integer numOfWork;
    @NotNull
    private Integer numOfSet;
    @Builder
    public TrainingCreate(String email, Integer numOfWork, Integer numOfSet) {
        this.email = email;
        this.numOfWork = numOfWork;
        this.numOfSet = numOfSet;
    }
}
