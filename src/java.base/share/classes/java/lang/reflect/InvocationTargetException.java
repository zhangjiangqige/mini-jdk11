package java.lang.reflect;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InvocationTargetException extends ReflectiveOperationException {

    protected InvocationTargetException() {
    }

    @SideEffectFree
    public InvocationTargetException(@Nullable Throwable target) {
    }

    @SideEffectFree
    public InvocationTargetException(@Nullable Throwable target, @Nullable String s) {
    }

    @Nullable
    public Throwable getTargetException();

    @Nullable
    public Throwable getCause();
}
