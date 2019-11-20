package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchMethodException extends ReflectiveOperationException {

    @SideEffectFree
    public NoSuchMethodException() {
    }

    @SideEffectFree
    public NoSuchMethodException(@Nullable String s) {
    }
}
