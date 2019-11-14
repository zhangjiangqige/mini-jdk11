package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class ClassFormatError extends LinkageError {

    private static final long serialVersionUID = -8420114879011949195L;

    @SideEffectFree
    public ClassFormatError() {
        super();
    }

    @SideEffectFree
    public ClassFormatError(@Nullable String s) {
        super(s);
    }
}
