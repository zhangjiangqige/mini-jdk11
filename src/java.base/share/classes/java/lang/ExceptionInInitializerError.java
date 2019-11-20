package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ExceptionInInitializerError extends LinkageError {

    @SideEffectFree
    public ExceptionInInitializerError() {
    }

    @SideEffectFree
    public ExceptionInInitializerError(@Nullable Throwable thrown) {
    }

    @SideEffectFree
    public ExceptionInInitializerError(@Nullable String s) {
    }

    @Nullable
    public Throwable getException();

    @Nullable
    public Throwable getCause();
}
