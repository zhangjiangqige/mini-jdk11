package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class RuntimeException extends Exception {

    static final long serialVersionUID = -7034897190745766939L;

    @SideEffectFree
    public RuntimeException() {
        super();
    }

    @SideEffectFree
    public RuntimeException(@Nullable String message) {
        super(message);
    }

    @SideEffectFree
    public RuntimeException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public RuntimeException(@Nullable Throwable cause) {
        super(cause);
    }

    protected RuntimeException(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
