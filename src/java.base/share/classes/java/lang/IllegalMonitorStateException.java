package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IllegalMonitorStateException extends RuntimeException {

    private static final long serialVersionUID = 3713306369498869069L;

    @SideEffectFree
    public IllegalMonitorStateException() {
        super();
    }

    @SideEffectFree
    public IllegalMonitorStateException(@Nullable String s) {
        super(s);
    }
}
