package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchFieldException extends ReflectiveOperationException {

    @SideEffectFree
    public NoSuchFieldException() {
    }

    @SideEffectFree
    public NoSuchFieldException(@Nullable String s) {
    }
}
