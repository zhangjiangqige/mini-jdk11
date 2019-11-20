package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IncompatibleClassChangeError extends LinkageError {

    @SideEffectFree
    public IncompatibleClassChangeError() {
    }

    @SideEffectFree
    public IncompatibleClassChangeError(@Nullable String s) {
    }
}
