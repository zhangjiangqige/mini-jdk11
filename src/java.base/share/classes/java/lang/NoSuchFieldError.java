package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoSuchFieldError extends IncompatibleClassChangeError {

    private static final long serialVersionUID = -3456430195886129035L;

    @SideEffectFree
    public NoSuchFieldError() {
        super();
    }

    @SideEffectFree
    public NoSuchFieldError(@Nullable String s) {
        super(s);
    }
}
