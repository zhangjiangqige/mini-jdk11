package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ArithmeticException extends RuntimeException {

    private static final long serialVersionUID = 2256477558314496007L;

    @SideEffectFree
    public ArithmeticException() {
        super();
    }

    @SideEffectFree
    public ArithmeticException(@Nullable String s) {
        super(s);
    }
}
