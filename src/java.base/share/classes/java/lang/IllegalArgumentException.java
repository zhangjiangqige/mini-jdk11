package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalArgumentException extends RuntimeException {

    @SideEffectFree
    public IllegalArgumentException() {
        super();
    }

    @SideEffectFree
    public IllegalArgumentException(@Nullable String s) {
        super(s);
    }

    @SideEffectFree
    public IllegalArgumentException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public IllegalArgumentException(@Nullable Throwable cause) {
        super(cause);
    }

    private static final long serialVersionUID = -5365630128856068164L;
}
