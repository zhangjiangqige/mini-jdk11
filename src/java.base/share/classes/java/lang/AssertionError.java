package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class AssertionError extends Error {

    private static final long serialVersionUID = -5013299493970297370L;

    @SideEffectFree
    public AssertionError() {
    }

    @SideEffectFree
    private AssertionError(@Nullable String detailMessage) {
        super(detailMessage);
    }

    @SideEffectFree
    public AssertionError(@Nullable Object detailMessage) {
        this(String.valueOf(detailMessage));
        if (detailMessage instanceof Throwable)
            initCause((Throwable) detailMessage);
    }

    @SideEffectFree
    public AssertionError(boolean detailMessage) {
        this(String.valueOf(detailMessage));
    }

    @SideEffectFree
    public AssertionError(char detailMessage) {
        this(String.valueOf(detailMessage));
    }

    @SideEffectFree
    public AssertionError(int detailMessage) {
        this(String.valueOf(detailMessage));
    }

    @SideEffectFree
    public AssertionError(long detailMessage) {
        this(String.valueOf(detailMessage));
    }

    @SideEffectFree
    public AssertionError(float detailMessage) {
        this(String.valueOf(detailMessage));
    }

    @SideEffectFree
    public AssertionError(double detailMessage) {
        this(String.valueOf(detailMessage));
    }

    public AssertionError(String message, Throwable cause) {
        super(message, cause);
    }
}
