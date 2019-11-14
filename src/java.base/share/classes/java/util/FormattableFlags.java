package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public class FormattableFlags {

    private FormattableFlags() {
    }

    public static final int LEFT_JUSTIFY = 1 << 0;

    public static final int UPPERCASE = 1 << 1;

    public static final int ALTERNATE = 1 << 2;
}
