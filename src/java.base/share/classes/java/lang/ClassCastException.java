package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ClassCastException extends RuntimeException {

    private static final long serialVersionUID = -9223365651070458532L;

    @SideEffectFree
    public ClassCastException() {
        super();
    }

    @SideEffectFree
    public ClassCastException(@Nullable String s) {
        super(s);
    }
}
