package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class RuntimeException extends Exception {

    @SideEffectFree
    public RuntimeException() {
    }

    @SideEffectFree
    public RuntimeException(@Nullable String message) {
    }

    @SideEffectFree
    public RuntimeException(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public RuntimeException(@Nullable Throwable cause) {
    }

    protected RuntimeException(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
