package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchMethodError extends IncompatibleClassChangeError {

    @SideEffectFree
    public NoSuchMethodError() {
    }

    @SideEffectFree
    public NoSuchMethodError(@Nullable String s) {
    }
}
