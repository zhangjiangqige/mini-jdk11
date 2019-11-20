package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalAccessError extends IncompatibleClassChangeError {

    @SideEffectFree
    public IllegalAccessError() {
    }

    @SideEffectFree
    public IllegalAccessError(@Nullable String s) {
    }
}
