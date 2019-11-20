package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullable" })
public class InstantiationError extends IncompatibleClassChangeError {

    @SideEffectFree
    public InstantiationError() {
    }

    @SideEffectFree
    public InstantiationError(@Nullable String s) {
    }
}
