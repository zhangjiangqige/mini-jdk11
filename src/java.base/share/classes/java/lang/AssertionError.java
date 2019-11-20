package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class AssertionError extends Error {

    @SideEffectFree
    public AssertionError() {
    }

    @SideEffectFree
    public AssertionError(@Nullable Object detailMessage) {
    }

    @SideEffectFree
    public AssertionError(boolean detailMessage) {
    }

    @SideEffectFree
    public AssertionError(char detailMessage) {
    }

    @SideEffectFree
    public AssertionError(int detailMessage) {
    }

    @SideEffectFree
    public AssertionError(long detailMessage) {
    }

    @SideEffectFree
    public AssertionError(float detailMessage) {
    }

    @SideEffectFree
    public AssertionError(double detailMessage) {
    }

    public AssertionError(String message, Throwable cause) {
    }
}
