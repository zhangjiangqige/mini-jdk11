package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InternalError extends VirtualMachineError {

    @SideEffectFree
    public InternalError() {
    }

    @SideEffectFree
    public InternalError(@Nullable String message) {
    }

    @SideEffectFree
    public InternalError(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public InternalError(@Nullable Throwable cause) {
    }
}
