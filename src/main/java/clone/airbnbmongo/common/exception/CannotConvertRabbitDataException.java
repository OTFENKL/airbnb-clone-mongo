package clone.airbnbmongo.common.exception;

public class CannotConvertRabbitDataException extends RuntimeException {

    public CannotConvertRabbitDataException() {
        super();
    }

    public CannotConvertRabbitDataException(String message) {
        super(message);
    }

    public CannotConvertRabbitDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
