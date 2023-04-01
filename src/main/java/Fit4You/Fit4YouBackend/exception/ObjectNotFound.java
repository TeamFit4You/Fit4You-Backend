package Fit4You.Fit4YouBackend.exception;

public class ObjectNotFound extends CustomException {

    private static final String DEFAULT_MESSAGE = "존재하지 않는 객체";

    public ObjectNotFound() {
        super(DEFAULT_MESSAGE);
    }

    public ObjectNotFound(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
