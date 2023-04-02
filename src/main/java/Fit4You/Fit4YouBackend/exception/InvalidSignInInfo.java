package Fit4You.Fit4YouBackend.exception;

public class InvalidSignInInfo extends CustomException {

    private static final String DEFAULT_MESSAGE = "이메일/비밀번호가 일치하지 않습니다";

    public InvalidSignInInfo() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidSignInInfo(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
