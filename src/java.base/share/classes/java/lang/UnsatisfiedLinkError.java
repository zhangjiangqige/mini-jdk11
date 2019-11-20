package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class UnsatisfiedLinkError extends LinkageError {

    @SideEffectFree
    public UnsatisfiedLinkError() {
    }

    @SideEffectFree
    public UnsatisfiedLinkError(@Nullable String s) {
    }
}
