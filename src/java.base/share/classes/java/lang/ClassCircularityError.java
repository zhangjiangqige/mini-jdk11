package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullnesss" })
public class ClassCircularityError extends LinkageError {

    private static final long serialVersionUID = 1054362542914539689L;

    @SideEffectFree
    public ClassCircularityError() {
        super();
    }

    @SideEffectFree
    public ClassCircularityError(@Nullable String s) {
        super(s);
    }
}
