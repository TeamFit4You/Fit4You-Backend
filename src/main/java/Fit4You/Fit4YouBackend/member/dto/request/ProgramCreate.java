package Fit4You.Fit4YouBackend.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramCreate {

    @NotBlank
    private String email;

    @NotNull
    private Integer pnum;
    @NotNull
    private Integer wnum;

    @Builder
    public ProgramCreate(String email, Integer pnum, Integer wnum) {
        this.email = email;
        this.pnum = pnum;
        this.wnum = wnum;
    }
}
