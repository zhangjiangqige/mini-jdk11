package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class Exception extends Throwable {

    static final long serialVersionUID = -3387516993124229948L;

    @SideEffectFree
    public Exception() {
        super();
    }

    @SideEffectFree
    public Exception(@Nullable String message) {
        super(message);
    }

    @SideEffectFree
    public Exception(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public Exception(@Nullable Throwable cause) {
        super(cause);
    }

    protected Exception(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
