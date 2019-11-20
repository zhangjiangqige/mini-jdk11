package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class VerifyError extends LinkageError {

    @SideEffectFree
    public VerifyError() {
    }

    @SideEffectFree
    public VerifyError(@Nullable String s) {
    }
}
