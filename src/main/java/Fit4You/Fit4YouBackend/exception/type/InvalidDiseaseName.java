package Fit4You.Fit4YouBackend.exception.type;

public class InvalidDiseaseName extends CustomException {

    private static final String DEFAULT_MESSAGE = "DB에 존재하지 않는 질병명";

    public InvalidDiseaseName() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidDiseaseName(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
