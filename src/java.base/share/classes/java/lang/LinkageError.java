package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public class LinkageError extends Error {

    @SideEffectFree
    public LinkageError() {
    }

    @SideEffectFree
    public LinkageError(@Nullable String s) {
    }

    public LinkageError(String s, Throwable cause) {
    }
}
