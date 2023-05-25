package Fit4You.Fit4YouBackend.exception.type;

public class InvalidFile extends CustomException {

    private static final String DEFAULT_MESSAGE = "유효하지 않은 파일";

    public InvalidFile() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidFile(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
