package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class UnsupportedClassVersionError extends ClassFormatError {

    @SideEffectFree
    public UnsupportedClassVersionError() {
    }

    @SideEffectFree
    public UnsupportedClassVersionError(@Nullable String s) {
    }
}
