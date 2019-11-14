package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ExceptionInInitializerError extends LinkageError {

    private static final long serialVersionUID = 1521711792217232256L;

    private Throwable exception;

    @SideEffectFree
    public ExceptionInInitializerError() {
        initCause(null);
    }

    @SideEffectFree
    public ExceptionInInitializerError(@Nullable Throwable thrown) {
        initCause(null);
        this.exception = thrown;
    }

    @SideEffectFree
    public ExceptionInInitializerError(@Nullable String s) {
        super(s);
        initCause(null);
    }

    @Nullable
    public Throwable getException();

    @Nullable
    public Throwable getCause();
}
