package Fit4You.Fit4YouBackend.api.dto.response;

import lombok.Getter;

@Getter
public class SessionResponse {

    private final String authorization;

    public SessionResponse(String authorization) {
        this.authorization = authorization;
    }
}
