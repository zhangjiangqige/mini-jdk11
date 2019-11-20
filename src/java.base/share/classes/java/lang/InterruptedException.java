package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InterruptedException extends Exception {

    @SideEffectFree
    public InterruptedException() {
    }

    @SideEffectFree
    public InterruptedException(@Nullable String s) {
    }
}
