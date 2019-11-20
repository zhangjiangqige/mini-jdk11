package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullnesss" })
public class ClassCircularityError extends LinkageError {

    @SideEffectFree
    public ClassCircularityError() {
    }

    @SideEffectFree
    public ClassCircularityError(@Nullable String s) {
    }
}
