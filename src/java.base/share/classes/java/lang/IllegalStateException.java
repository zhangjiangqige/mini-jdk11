package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalStateException extends RuntimeException {

    @SideEffectFree
    public IllegalStateException() {
        super();
    }

    @SideEffectFree
    public IllegalStateException(@Nullable String s) {
        super(s);
    }

    @SideEffectFree
    public IllegalStateException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public IllegalStateException(@Nullable Throwable cause) {
        super(cause);
    }

    static final long serialVersionUID = -1848914673093119416L;
}
