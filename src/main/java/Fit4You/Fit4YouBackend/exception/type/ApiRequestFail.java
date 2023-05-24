package Fit4You.Fit4YouBackend.exception.type;

public class ApiRequestFail extends CustomException {

    private static final String DEFAULT_MESSAGE = "내부 API 호출 실패";

    public ApiRequestFail() {
        super(DEFAULT_MESSAGE);
    }

    public ApiRequestFail(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
