package java.lang;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public class Error extends Throwable {

    static final long serialVersionUID = 4980196508277280342L;

    @SideEffectFree
    public Error() {
        super();
    }

    @SideEffectFree
    public Error(@Nullable String message) {
        super(message);
    }

    @SideEffectFree
    public Error(@Nullable String message, @GuardSatisfied @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public Error(@Nullable Throwable cause) {
        super(cause);
    }

    @SideEffectFree
    protected Error(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
