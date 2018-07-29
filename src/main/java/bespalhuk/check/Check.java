package bespalhuk.check;

import org.joox.Content;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Check {

    public static final String NOT_BLANK = "Value can't be blank";

    public static final String NOT_NULL = "Value can't be null";

    public static final String DIDNT_MATCH = "Value didn't match";

    public static final String DONT_EXISTS = "Value don't exists";

    private Check() {
    }

    public static void argument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void argument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void content(Content content) {
        notNull(content);
        notNull(content.content(null));
    }

    public static void content(Content content, Object errorMessage) {
        notNull(content, errorMessage);
        notNull(content.content(null), errorMessage);
    }

    public static void content(Optional<Content> content) {
        notNull(content);
        content(content.orElseThrow(() -> new NoSuchElementException("No value present")));
    }

    public static void content(Optional<Content> content, Object errorMessage) {
        notNull(content, errorMessage);
        content(content.orElseThrow(() -> new NoSuchElementException("No value present")), errorMessage);
    }

    public static boolean isBlank(String value) {
        return isNull(value) || isEmpty(value.trim());
    }

    public static boolean isContentNull(Content content) {
        return isNull(content) || isNull(content.content(null));
    }

    public static boolean isEmpty(String value) {
        return value.isEmpty();
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNullOrEmpty(String value) {
        return isNull(value) || isEmpty(value);
    }

    public static void notBlank(String value) {
        notNull(value);
        if (isBlank(value)) {
            throw new IllegalArgumentException(NOT_BLANK);
        }
    }

    public static void notBlank(String value, Object errorMessage) {
        notNull(value, errorMessage);
        if (isBlank(value)) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void notEmpty(String value) {
        notNull(value);
        if (isEmpty(value)) {
            throw new IllegalArgumentException(NOT_BLANK);
        }
    }

    public static void notEmpty(String value, Object errorMessage) {
        notNull(value, errorMessage);
        if (isEmpty(value)) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void notNull(Nullable nullable) {
        if (isNull(nullable)) {
            throw new NullPointerException(NOT_NULL);
        }
        if (nullable.isNull()) {
            throw new NullableException();
        }
    }

    public static void notNull(Nullable nullable, Object errorMessage) {
        if (isNull(nullable)) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        if (nullable.isNull()) {
            throw new NullableException(String.valueOf(errorMessage));
        }
    }

    public static void notNull(Object value) {
        if (isNull(value)) {
            throw new NullPointerException(NOT_NULL);
        }
    }

    public static void notNull(Object value, Object errorMessage) {
        if (isNull(value)) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
    }

    public static void state(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void state(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

}