package Fit4You.Fit4YouBackend.exception;

public class FileUploadFail extends CustomException {

    private static final String DEFAULT_MESSAGE = "파일 업로드 실패";

    public FileUploadFail() {
        super(DEFAULT_MESSAGE);
    }

    public FileUploadFail(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
