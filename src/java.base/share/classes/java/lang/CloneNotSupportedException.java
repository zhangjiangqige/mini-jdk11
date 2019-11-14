package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class CloneNotSupportedException extends Exception {

    private static final long serialVersionUID = 5195511250079656443L;

    @SideEffectFree
    public CloneNotSupportedException() {
        super();
    }

    @SideEffectFree
    public CloneNotSupportedException(@Nullable String s) {
        super(s);
    }
}
