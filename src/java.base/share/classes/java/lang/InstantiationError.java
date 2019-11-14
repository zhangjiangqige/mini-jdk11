package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullable" })
public class InstantiationError extends IncompatibleClassChangeError {

    private static final long serialVersionUID = -4885810657349421204L;

    @SideEffectFree
    public InstantiationError() {
        super();
    }

    @SideEffectFree
    public InstantiationError(@Nullable String s) {
        super(s);
    }
}
