package java.lang;

import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.*;

@AnnotatedFor({ "interning", "lock", "nullable" })
@UsesObjectEquals
public class Throwable implements Serializable {

    @SideEffectFree
    public Throwable() {
    }

    @SideEffectFree
    public Throwable(@Nullable String message) {
    }

    @SideEffectFree
    public Throwable(@Nullable String message, @Nullable Throwable cause) {
    }

    @SideEffectFree
    public Throwable(@Nullable Throwable cause) {
    }

    @SideEffectFree
    protected Throwable(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }

    @Pure
    @Nullable
    public String getMessage(@GuardSatisfied Throwable this);

    @SideEffectFree
    @Nullable
    public String getLocalizedMessage(@GuardSatisfied Throwable this);

    @Pure
    @Nullable
    public synchronized Throwable getCause(@GuardSatisfied Throwable this);

    @UnknownInitialization
    public synchronized Throwable initCause(@UnknownInitialization Throwable this, @Nullable Throwable cause);

    @SideEffectFree
    public String toString(@GuardSatisfied Throwable this);

    public void printStackTrace();

    public void printStackTrace(PrintStream s);

    public void printStackTrace(PrintWriter s);

    public synchronized Throwable fillInStackTrace();

    public StackTraceElement[] getStackTrace();

    public void setStackTrace(StackTraceElement[] stackTrace);

    public final synchronized void addSuppressed(Throwable exception);

    public final synchronized Throwable[] getSuppressed();
}
