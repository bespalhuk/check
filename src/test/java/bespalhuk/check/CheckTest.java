package bespalhuk.check;

import org.joox.Content;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckTest {

    private static final String error = "Error";

    //    @Test
//    public void checkConstructor()
//            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Constructor<Check> constructor = Check.class.getDeclaredConstructor();
//        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
//        constructor.setAccessible(true);
//        constructor.newInstance();
//        constructor.setAccessible(false);
//    }
//
    @Test
    public void messages() {
        assertThat(Check.NOT_BLANK).isEqualTo("Value can't be blank");
        assertThat(Check.NOT_NULL).isEqualTo("Value can't be null");
        assertThat(Check.DIDNT_MATCH).isEqualTo("Value didn't match");
        assertThat(Check.DONT_EXISTS).isEqualTo("Value don't exists");
    }

    @Test
    public void argumentTrue() {
        Check.argument(true);
        Check.argument(true, error);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void argumentFalse() {
        Check.argument(false);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = error)
    public void argumentFalseMessage() {
        Check.argument(false, error);
    }

    @Test
    public void content() {
        Check.content(context -> "");
        Check.content(context -> "", error);
        Check.content(Optional.of(context -> ""));
        Check.content(Optional.of(context -> ""), error);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void contentNull() {
        Content content = null;
        Check.content(content);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = error)
    public void contentNullMessage() {
        Content content = null;
        Check.content(content, error);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void contentContextNull() {
        Content content = context -> null;
        Check.content(content);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = error)
    public void contentContextNullMessage() {
        Content content = context -> null;
        Check.content(content, error);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void contentOptionalNull() {
        Optional<Content> content = null;
        Check.content(content);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = error)
    public void contentOptionalNullMessage() {
        Optional<Content> content = null;
        Check.content(content, error);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void contentOptionalContextNull() {
        Optional<Content> content = Optional.ofNullable(context -> null);
        Check.content(content);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = error)
    public void contentOptionalContextNullMessage() {
        Optional<Content> content = Optional.ofNullable(context -> null);
        Check.content(content, error);
    }

    @Test
    public void isBlank() {
        assertThat(Check.isBlank(null)).isTrue();
        assertThat(Check.isBlank("")).isTrue();
        assertThat(Check.isBlank(" ")).isTrue();
        assertThat(Check.isBlank("z")).isFalse();
    }

    @Test
    public void isContentNull() {
        assertThat(Check.isContentNull(null)).isTrue();
        assertThat(Check.isContentNull(context -> null)).isTrue();
        assertThat(Check.isContentNull(context -> "")).isFalse();
    }

    @Test
    public void isEmpty() {
        assertThat(Check.isEmpty("")).isTrue();
        assertThat(Check.isEmpty(" ")).isFalse();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void isEmptyNull() {
        Check.isEmpty(null);
    }

    @Test
    public void isNull() {
        assertThat(Check.isNull(null)).isTrue();
        assertThat(Check.isNull("")).isFalse();
    }

    @Test
    public void isNullOrEmpty() {
        assertThat(Check.isNullOrEmpty(null)).isTrue();
        assertThat(Check.isNullOrEmpty("")).isTrue();
        assertThat(Check.isNullOrEmpty(" ")).isFalse();
    }

    @Test
    public void notBlank() {
        Check.notBlank("z");
        Check.notBlank("z", error);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = Check.NOT_NULL)
    public void notBlankNull() {
        Check.notBlank(null);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = error)
    public void notBlankNullMessage() {
        Check.notBlank(null, error);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = Check.NOT_BLANK)
    public void notBlankEmpty() {
        Check.notBlank("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = error)
    public void notBlankEmptyMessage() {
        Check.notBlank("", error);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = Check.NOT_BLANK)
    public void notBlankSpace() {
        Check.notBlank(" ");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = error)
    public void notBlankSpaceMessage() {
        Check.notBlank(" ", error);
    }

    @Test
    public void notEmpty() {
        Check.notEmpty(" ");
        Check.notEmpty(" ", error);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = Check.NOT_NULL)
    public void notEmptyNull() {
        Check.notEmpty(null);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = error)
    public void notEmptyNullMessage() {
        Check.notEmpty(null, error);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = Check.NOT_BLANK)
    public void notEmptyEmpty() {
        Check.notEmpty("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = error)
    public void notEmptyEmptyMessage() {
        Check.notEmpty("", error);
    }

    @Test
    public void notNull() {
        Check.notNull("");
        Check.notNull("", error);
        Check.notNull(() -> false);
        Check.notNull(() -> false, error);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = Check.NOT_NULL)
    public void notNullNull() {
        Check.notNull(null);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = error)
    public void notNullNullMessage() {
        Check.notNull(null, error);
    }

    @Test(expectedExceptions = NullableException.class, expectedExceptionsMessageRegExp = NullableException.NOT_USABLE)
    public void notNullNullableTrue() {
        Check.notNull(() -> true);
    }

    @Test(expectedExceptions = NullableException.class, expectedExceptionsMessageRegExp = error)
    public void notNullNullableTrueMessage() {
        Check.notNull(() -> true, error);
    }

    @Test
    public void stateTrue() {
        Check.state(true);
        Check.state(true, error);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void stateFalse() {
        Check.state(false);
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = error)
    public void stateFalseMessage() {
        Check.state(false, error);
    }

}