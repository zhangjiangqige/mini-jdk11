package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchMethodException extends ReflectiveOperationException {

    private static final long serialVersionUID = 5034388446362600923L;

    @SideEffectFree
    public NoSuchMethodException() {
        super();
    }

    @SideEffectFree
    public NoSuchMethodException(@Nullable String s) {
        super(s);
    }
}
