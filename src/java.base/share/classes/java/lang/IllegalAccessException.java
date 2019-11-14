package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalAccessException extends ReflectiveOperationException {

    private static final long serialVersionUID = 6616958222490762034L;

    @SideEffectFree
    public IllegalAccessException() {
        super();
    }

    @SideEffectFree
    public IllegalAccessException(@Nullable String s) {
        super(s);
    }
}
