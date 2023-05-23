package Fit4You.Fit4YouBackend.exception.type;

public class WorkoutNotFound extends CustomException {

    private static final String DEFAULT_MESSAGE = "존재하지 않는 운동; 잘못된 접근";

    public WorkoutNotFound() {
        super(DEFAULT_MESSAGE);
    }

    public WorkoutNotFound(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
