package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {

    @SideEffectFree
    public ArrayIndexOutOfBoundsException() {
    }

    @SideEffectFree
    public ArrayIndexOutOfBoundsException(@Nullable String s) {
    }

    @SideEffectFree
    public ArrayIndexOutOfBoundsException(int index) {
    }
}
