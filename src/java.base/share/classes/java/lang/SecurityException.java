package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class SecurityException extends RuntimeException {

    @SideEffectFree
    public SecurityException() {
    }

    @SideEffectFree
    public SecurityException(@Nullable String s) {
    }

    @SideEffectFree
    public SecurityException(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public SecurityException(@Nullable Throwable cause) {
    }
}
