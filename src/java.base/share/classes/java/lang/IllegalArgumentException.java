package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalArgumentException extends RuntimeException {

    @SideEffectFree
    public IllegalArgumentException() {
    }

    @SideEffectFree
    public IllegalArgumentException(@Nullable String s) {
    }

    @SideEffectFree
    public IllegalArgumentException(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public IllegalArgumentException(@Nullable Throwable cause) {
    }
}
