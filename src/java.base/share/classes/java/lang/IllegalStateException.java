package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalStateException extends RuntimeException {

    @SideEffectFree
    public IllegalStateException() {
    }

    @SideEffectFree
    public IllegalStateException(@Nullable String s) {
    }

    @SideEffectFree
    public IllegalStateException(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public IllegalStateException(@Nullable Throwable cause) {
    }
}
