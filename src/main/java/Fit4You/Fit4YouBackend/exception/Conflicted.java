package Fit4You.Fit4YouBackend.exception;


/**
 * status -> 409(Conflicted)
 * 고유값이 서버에 중복으로 요청이 들어온 경우 ex)회원가입시 로그인아이디 중복
 */
public class Conflicted extends CustomException{
    private static final String DEFAULT_MESSAGE = "이미 존재하는 값";

    public Conflicted() {
        super(DEFAULT_MESSAGE);
    }

    public Conflicted(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
