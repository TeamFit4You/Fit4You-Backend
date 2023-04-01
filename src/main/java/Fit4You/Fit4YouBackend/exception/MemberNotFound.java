package Fit4You.Fit4YouBackend.exception;

public class MemberNotFound extends CustomException {

    private static final String DEFAULT_MESSAGE = "존재하지 않는 회원";

    public MemberNotFound() {
        super(DEFAULT_MESSAGE);
    }

    public MemberNotFound(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
