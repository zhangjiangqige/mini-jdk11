package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ArrayStoreException extends RuntimeException {

    @SideEffectFree
    public ArrayStoreException() {
    }

    @SideEffectFree
    public ArrayStoreException(@Nullable String s) {
    }
}
