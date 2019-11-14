package java.lang.reflect;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InvocationTargetException extends ReflectiveOperationException {

    private static final long serialVersionUID = 4085088731926701167L;

    private Throwable target;

    protected InvocationTargetException() {
        super((Throwable) null);
    }

    @SideEffectFree
    public InvocationTargetException(@Nullable Throwable target) {
        super((Throwable) null);
        this.target = target;
    }

    @SideEffectFree
    public InvocationTargetException(@Nullable Throwable target, @Nullable String s) {
        super(s, null);
        this.target = target;
    }

    @Nullable
    public Throwable getTargetException();

    @Nullable
    public Throwable getCause();
}
