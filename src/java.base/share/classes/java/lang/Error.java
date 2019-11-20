package java.lang;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public class Error extends Throwable {

    @SideEffectFree
    public Error() {
    }

    @SideEffectFree
    public Error(@Nullable String message) {
    }

    @SideEffectFree
    public Error(@Nullable String message, @GuardSatisfied @Nullable Throwable cause) {
    }

    @SideEffectFree
    public Error(@Nullable Throwable cause) {
    }

    @SideEffectFree
    protected Error(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
