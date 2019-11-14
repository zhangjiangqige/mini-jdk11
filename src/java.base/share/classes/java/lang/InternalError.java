package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InternalError extends VirtualMachineError {

    private static final long serialVersionUID = -9062593416125562365L;

    @SideEffectFree
    public InternalError() {
        super();
    }

    @SideEffectFree
    public InternalError(@Nullable String message) {
        super(message);
    }

    @SideEffectFree
    public InternalError(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public InternalError(@Nullable Throwable cause) {
        super(cause);
    }
}
