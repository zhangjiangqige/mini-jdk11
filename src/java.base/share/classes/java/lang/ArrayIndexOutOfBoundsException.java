package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {

    private static final long serialVersionUID = -5116101128118950844L;

    @SideEffectFree
    public ArrayIndexOutOfBoundsException() {
        super();
    }

    @SideEffectFree
    public ArrayIndexOutOfBoundsException(@Nullable String s) {
        super(s);
    }

    @SideEffectFree
    public ArrayIndexOutOfBoundsException(int index) {
        super("Array index out of range: " + index);
    }
}
