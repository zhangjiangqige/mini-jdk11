package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NumberFormatException extends IllegalArgumentException {

    static final long serialVersionUID = -2848938806368998894L;

    public NumberFormatException() {
        super();
    }

    public NumberFormatException(@Nullable String s) {
        super(s);
    }

    static NumberFormatException forInputString(String s);

    static NumberFormatException forCharSequence(CharSequence s, int beginIndex, int endIndex, int errorIndex);
}
