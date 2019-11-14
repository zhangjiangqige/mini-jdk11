package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NullPointerException extends RuntimeException {

    private static final long serialVersionUID = 5162710183389028792L;

    @SideEffectFree
    public NullPointerException() {
        super();
    }

    @SideEffectFree
    public NullPointerException(@Nullable String s) {
        super(s);
    }
}
