package constant;

public enum ErrorMessage {
    RETRY_OR_QUIT_INVALID("재시도는 R, 종료는 Q를 입력하세요."),
    MOVE_INVALID("이동할 칸은 위(U) 또는 아래(D)를 입력하세요."),
    BRIDGE_SIZE_NOT_NUMBER("다리 길이는 숫자만 입력 가능합니다."),
    BRIDGE_SIZE_INVALID_RANGE("다리 길이는 3부터 20 사이의 숫자여야 합니다.");

    private final String ERROR_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ERROR_PREFIX + errorMessage;
    }
}

