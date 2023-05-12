package Fit4You.Fit4YouBackend.api.dto.response;

import lombok.Getter;

@Getter
public class SessionResponse {

<<<<<<< HEAD
    private final Long memberId;
    private final String authorization;


    public SessionResponse(Long memberId, String authorization) {
        this.memberId = memberId;
=======
    private final String authorization;

    public SessionResponse(String authorization) {
>>>>>>> 00b6878d02ccec6a17d98a3ea189e9f8accc85dd
        this.authorization = authorization;
    }
}
