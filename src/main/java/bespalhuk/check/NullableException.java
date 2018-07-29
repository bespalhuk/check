package bespalhuk.check;

public class NullableException extends RuntimeException {

    public static final String NOT_USABLE = "Null object can't be used";

    public NullableException() {
        super(NOT_USABLE);
    }

    public NullableException(String message) {
        super(message);
    }

}