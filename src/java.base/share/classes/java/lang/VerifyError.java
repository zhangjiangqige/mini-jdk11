package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class VerifyError extends LinkageError {

    private static final long serialVersionUID = 7001962396098498785L;

    @SideEffectFree
    public VerifyError() {
        super();
    }

    @SideEffectFree
    public VerifyError(@Nullable String s) {
        super(s);
    }
}
