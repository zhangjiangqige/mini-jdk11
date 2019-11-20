package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class UnknownError extends VirtualMachineError {

    @SideEffectFree
    public UnknownError() {
    }

    @SideEffectFree
    public UnknownError(@Nullable String s) {
    }
}
