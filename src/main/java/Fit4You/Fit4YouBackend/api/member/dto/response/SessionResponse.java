package Fit4You.Fit4YouBackend.api.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SessionResponse {

    @Schema(description = "인증토큰")
    private final String authorization;


    public SessionResponse(String authorization) {
        this.authorization = authorization;
    }
}
