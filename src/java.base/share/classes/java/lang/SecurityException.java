package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class SecurityException extends RuntimeException {

    private static final long serialVersionUID = 6878364983674394167L;

    @SideEffectFree
    public SecurityException() {
        super();
    }

    @SideEffectFree
    public SecurityException(@Nullable String s) {
        super(s);
    }

    @SideEffectFree
    public SecurityException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public SecurityException(@Nullable Throwable cause) {
        super(cause);
    }
}
