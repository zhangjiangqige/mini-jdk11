package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IncompatibleClassChangeError extends LinkageError {

    private static final long serialVersionUID = -4914975503642802119L;

    @SideEffectFree
    public IncompatibleClassChangeError() {
        super();
    }

    @SideEffectFree
    public IncompatibleClassChangeError(@Nullable String s) {
        super(s);
    }
}
