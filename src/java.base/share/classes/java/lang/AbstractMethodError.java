package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class AbstractMethodError extends IncompatibleClassChangeError {

    @SideEffectFree
    public AbstractMethodError() {
    }

    @SideEffectFree
    public AbstractMethodError(@Nullable String s) {
    }
}
