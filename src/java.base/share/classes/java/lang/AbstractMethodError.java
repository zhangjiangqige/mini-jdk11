package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class AbstractMethodError extends IncompatibleClassChangeError {

    private static final long serialVersionUID = -1654391082989018462L;

    @SideEffectFree
    public AbstractMethodError() {
        super();
    }

    @SideEffectFree
    public AbstractMethodError(@Nullable String s) {
        super(s);
    }
}
