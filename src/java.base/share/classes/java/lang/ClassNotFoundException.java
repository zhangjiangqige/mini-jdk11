package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ClassNotFoundException extends ReflectiveOperationException {

    private static final long serialVersionUID = 9176873029745254542L;

    private Throwable ex;

    @SideEffectFree
    public ClassNotFoundException() {
        super((Throwable) null);
    }

    @SideEffectFree
    public ClassNotFoundException(@Nullable String s) {
        super(s, null);
    }

    @SideEffectFree
    public ClassNotFoundException(@Nullable String s, @Nullable Throwable ex) {
        super(s, null);
        this.ex = ex;
    }

    public Throwable getException();

    public Throwable getCause();
}
