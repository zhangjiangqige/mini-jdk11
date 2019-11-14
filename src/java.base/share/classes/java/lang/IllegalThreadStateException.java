package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalThreadStateException extends IllegalArgumentException {

    private static final long serialVersionUID = -7626246362397460174L;

    @SideEffectFree
    public IllegalThreadStateException() {
        super();
    }

    @SideEffectFree
    public IllegalThreadStateException(@Nullable String s) {
        super(s);
    }
}
