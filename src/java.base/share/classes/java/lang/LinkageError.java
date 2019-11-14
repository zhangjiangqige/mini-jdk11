package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public class LinkageError extends Error {

    private static final long serialVersionUID = 3579600108157160122L;

    @SideEffectFree
    public LinkageError() {
        super();
    }

    @SideEffectFree
    public LinkageError(@Nullable String s) {
        super(s);
    }

    public LinkageError(String s, Throwable cause) {
        super(s, cause);
    }
}
