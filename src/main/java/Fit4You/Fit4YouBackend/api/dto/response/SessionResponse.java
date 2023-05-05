package Fit4You.Fit4YouBackend.api.dto.response;

import lombok.Getter;

@Getter
public class SessionResponse {

    private final Long memberId;
    private final String authorization;


    public SessionResponse(Long memberId, String authorization) {
        this.memberId = memberId;
        this.authorization = authorization;
    }
}
