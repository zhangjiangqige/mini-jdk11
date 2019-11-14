package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ArrayStoreException extends RuntimeException {

    private static final long serialVersionUID = -4522193890499838241L;

    @SideEffectFree
    public ArrayStoreException() {
        super();
    }

    @SideEffectFree
    public ArrayStoreException(@Nullable String s) {
        super(s);
    }
}
