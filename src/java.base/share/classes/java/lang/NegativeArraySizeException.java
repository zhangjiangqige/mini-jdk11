package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NegativeArraySizeException extends RuntimeException {

    @SideEffectFree
    public NegativeArraySizeException() {
    }

    @SideEffectFree
    public NegativeArraySizeException(@Nullable String s) {
    }
}
