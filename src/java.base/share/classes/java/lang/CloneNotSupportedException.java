package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class CloneNotSupportedException extends Exception {

    @SideEffectFree
    public CloneNotSupportedException() {
    }

    @SideEffectFree
    public CloneNotSupportedException(@Nullable String s) {
    }
}
