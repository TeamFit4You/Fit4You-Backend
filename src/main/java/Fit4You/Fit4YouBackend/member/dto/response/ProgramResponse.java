package Fit4You.Fit4YouBackend.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramResponse {

    private Long programId;

    @Builder
    public ProgramResponse(Long programId) {
        this.programId = programId;
    }
}
