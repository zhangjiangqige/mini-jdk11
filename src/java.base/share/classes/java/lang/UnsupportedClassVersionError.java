package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class UnsupportedClassVersionError extends ClassFormatError {

    private static final long serialVersionUID = -7123279212883497373L;

    @SideEffectFree
    public UnsupportedClassVersionError() {
        super();
    }

    @SideEffectFree
    public UnsupportedClassVersionError(@Nullable String s) {
        super(s);
    }
}
