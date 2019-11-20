package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class OutOfMemoryError extends VirtualMachineError {

    @SideEffectFree
    public OutOfMemoryError() {
    }

    @SideEffectFree
    public OutOfMemoryError(@Nullable String s) {
    }
}
