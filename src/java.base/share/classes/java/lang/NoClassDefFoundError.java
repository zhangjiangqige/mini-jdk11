package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoClassDefFoundError extends LinkageError {

    @SideEffectFree
    public NoClassDefFoundError() {
    }

    @SideEffectFree
    public NoClassDefFoundError(@Nullable String s) {
    }
}
