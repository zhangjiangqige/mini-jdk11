package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class OutOfMemoryError extends VirtualMachineError {

    private static final long serialVersionUID = 8228564086184010517L;

    @SideEffectFree
    public OutOfMemoryError() {
        super();
    }

    @SideEffectFree
    public OutOfMemoryError(@Nullable String s) {
        super(s);
    }
}
