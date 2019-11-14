package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class UnsupportedOperationException extends RuntimeException {

    @SideEffectFree
    public UnsupportedOperationException() {
    }

    @SideEffectFree
    public UnsupportedOperationException(@Nullable String message) {
        super(message);
    }

    @SideEffectFree
    public UnsupportedOperationException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public UnsupportedOperationException(@Nullable Throwable cause) {
        super(cause);
    }

    static final long serialVersionUID = -1242599979055084673L;
}
