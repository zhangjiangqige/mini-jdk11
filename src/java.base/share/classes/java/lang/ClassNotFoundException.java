package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ClassNotFoundException extends ReflectiveOperationException {

    @SideEffectFree
    public ClassNotFoundException() {
    }

    @SideEffectFree
    public ClassNotFoundException(@Nullable String s) {
    }

    @SideEffectFree
    public ClassNotFoundException(@Nullable String s, @Nullable Throwable ex) {
    }

    public Throwable getException();

    public Throwable getCause();
}
