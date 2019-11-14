package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchMethodError extends IncompatibleClassChangeError {

    private static final long serialVersionUID = -3765521442372831335L;

    @SideEffectFree
    public NoSuchMethodError() {
        super();
    }

    @SideEffectFree
    public NoSuchMethodError(@Nullable String s) {
        super(s);
    }
}
