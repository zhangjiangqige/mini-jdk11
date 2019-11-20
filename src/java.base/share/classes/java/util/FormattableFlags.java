package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public class FormattableFlags {

    public static final int LEFT_JUSTIFY;

    public static final int UPPERCASE;

    public static final int ALTERNATE;
}
