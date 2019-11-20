package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ClassFormatError extends LinkageError {

    @SideEffectFree
    public ClassFormatError() {
    }

    @SideEffectFree
    public ClassFormatError(@Nullable String s) {
    }
}
