package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchFieldError extends IncompatibleClassChangeError {

    @SideEffectFree
    public NoSuchFieldError() {
    }

    @SideEffectFree
    public NoSuchFieldError(@Nullable String s) {
    }
}
