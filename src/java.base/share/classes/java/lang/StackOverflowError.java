package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class StackOverflowError extends VirtualMachineError {

    @SideEffectFree
    public StackOverflowError() {
    }

    @SideEffectFree
    public StackOverflowError(@Nullable String s) {
    }
}
