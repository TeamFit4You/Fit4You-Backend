package Fit4You.Fit4YouBackend.exception.type;

public class ExerciseNotFound extends CustomException {

    private static final String DEFAULT_MESSAGE = "존재하지 않는 운동; 잘못된 접근";

    public ExerciseNotFound() {
        super(DEFAULT_MESSAGE);
    }

    public ExerciseNotFound(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
