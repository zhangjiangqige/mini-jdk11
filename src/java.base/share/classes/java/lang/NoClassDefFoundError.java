package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class NoClassDefFoundError extends LinkageError {

    private static final long serialVersionUID = 9095859863287012458L;

    @SideEffectFree
    public NoClassDefFoundError() {
        super();
    }

    @SideEffectFree
    public NoClassDefFoundError(@Nullable String s) {
        super(s);
    }
}
