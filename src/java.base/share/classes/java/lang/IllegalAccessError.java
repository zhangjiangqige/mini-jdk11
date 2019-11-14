package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalAccessError extends IncompatibleClassChangeError {

    private static final long serialVersionUID = -8988904074992417891L;

    @SideEffectFree
    public IllegalAccessError() {
        super();
    }

    @SideEffectFree
    public IllegalAccessError(@Nullable String s) {
        super(s);
    }
}
