package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class UnknownError extends VirtualMachineError {

    private static final long serialVersionUID = 2524784860676771849L;

    @SideEffectFree
    public UnknownError() {
        super();
    }

    @SideEffectFree
    public UnknownError(@Nullable String s) {
        super(s);
    }
}
