package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public abstract class VirtualMachineError extends Error {

    @SideEffectFree
    public VirtualMachineError() {
    }

    @SideEffectFree
    public VirtualMachineError(@Nullable String message) {
    }

    @SideEffectFree
    public VirtualMachineError(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public VirtualMachineError(@Nullable Throwable cause) {
    }
}
