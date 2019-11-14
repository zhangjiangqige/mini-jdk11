package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class StackOverflowError extends VirtualMachineError {

    private static final long serialVersionUID = 8609175038441759607L;

    @SideEffectFree
    public StackOverflowError() {
        super();
    }

    @SideEffectFree
    public StackOverflowError(@Nullable String s) {
        super(s);
    }
}
