package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public abstract class VirtualMachineError extends Error {

    private static final long serialVersionUID = 4161983926571568670L;

    @SideEffectFree
    public VirtualMachineError() {
        super();
    }

    @SideEffectFree
    public VirtualMachineError(@Nullable String message) {
        super(message);
    }

    @SideEffectFree
    public VirtualMachineError(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    @SideEffectFree
    public VirtualMachineError(@Nullable Throwable cause) {
        super(cause);
    }
}
