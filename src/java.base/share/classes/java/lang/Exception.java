package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class Exception extends Throwable {

    @SideEffectFree
    public Exception() {
    }

    @SideEffectFree
    public Exception(@Nullable String message) {
    }

    @SideEffectFree
    public Exception(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public Exception(@Nullable Throwable cause) {
    }

    protected Exception(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
