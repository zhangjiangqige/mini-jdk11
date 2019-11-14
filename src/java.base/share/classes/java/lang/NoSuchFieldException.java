package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchFieldException extends ReflectiveOperationException {

    private static final long serialVersionUID = -6143714805279938260L;

    @SideEffectFree
    public NoSuchFieldException() {
        super();
    }

    @SideEffectFree
    public NoSuchFieldException(@Nullable String s) {
        super(s);
    }
}
