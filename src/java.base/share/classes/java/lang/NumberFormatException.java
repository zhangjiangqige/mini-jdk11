package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NumberFormatException extends IllegalArgumentException {

    public NumberFormatException() {
    }

    public NumberFormatException(@Nullable String s) {
    }
}
