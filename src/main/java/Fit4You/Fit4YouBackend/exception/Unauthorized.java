package Fit4You.Fit4YouBackend.exception;

public class Unauthorized extends CustomException{

    private static final String DEFAULT_MESSAGE = "인증실패";

    public Unauthorized() {
        super(DEFAULT_MESSAGE);
    }

    public Unauthorized(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
