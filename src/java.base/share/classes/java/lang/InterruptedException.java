package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InterruptedException extends Exception {

    private static final long serialVersionUID = 6700697376100628473L;

    @SideEffectFree
    public InterruptedException() {
        super();
    }

    @SideEffectFree
    public InterruptedException(@Nullable String s) {
        super(s);
    }
}
