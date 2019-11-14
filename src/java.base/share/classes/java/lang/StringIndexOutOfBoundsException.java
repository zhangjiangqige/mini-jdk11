package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class StringIndexOutOfBoundsException extends IndexOutOfBoundsException {

    private static final long serialVersionUID = -6762910422159637258L;

    @SideEffectFree
    public StringIndexOutOfBoundsException() {
        super();
    }

    @SideEffectFree
    public StringIndexOutOfBoundsException(@Nullable String s) {
        super(s);
    }

    @SideEffectFree
    public StringIndexOutOfBoundsException(int index) {
        super("String index out of range: " + index);
    }
}
