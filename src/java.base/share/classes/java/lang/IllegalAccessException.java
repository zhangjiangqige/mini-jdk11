package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalAccessException extends ReflectiveOperationException {

    @SideEffectFree
    public IllegalAccessException() {
    }

    @SideEffectFree
    public IllegalAccessException(@Nullable String s) {
    }
}
