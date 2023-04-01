package Fit4You.Fit4YouBackend.exception;

public class ExpirationToken extends CustomException{

    private static final String DEFAULT_MESSAGE = "만료된 토큰";

    public ExpirationToken() {
        super(DEFAULT_MESSAGE);
    }

    public ExpirationToken(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
