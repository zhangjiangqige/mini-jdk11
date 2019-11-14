package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NegativeArraySizeException extends RuntimeException {

    private static final long serialVersionUID = -8960118058596991861L;

    @SideEffectFree
    public NegativeArraySizeException() {
        super();
    }

    @SideEffectFree
    public NegativeArraySizeException(@Nullable String s) {
        super(s);
    }
}
