package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InstantiationException extends ReflectiveOperationException {

    private static final long serialVersionUID = -8441929162975509110L;

    @SideEffectFree
    public InstantiationException() {
        super();
    }

    @SideEffectFree
    public InstantiationException(@Nullable String s) {
        super(s);
    }
}
