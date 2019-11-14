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

    private static final long serialVersionUID = -3042686055658047285L;

    private transient Object backtrace;

    private String detailMessage;

    private static class SentinelHolder {

        public static final StackTraceElement STACK_TRACE_ELEMENT_SENTINEL = new StackTraceElement("", "", null, Integer.MIN_VALUE);

        public static final StackTraceElement[] STACK_TRACE_SENTINEL = new StackTraceElement[] { STACK_TRACE_ELEMENT_SENTINEL };
    }

    private static final StackTraceElement[] UNASSIGNED_STACK = new StackTraceElement[0];

    private Throwable cause = this;

    private StackTraceElement[] stackTrace = UNASSIGNED_STACK;

    private transient int depth;

    private static final List<Throwable> SUPPRESSED_SENTINEL = Collections.emptyList();

    private List<Throwable> suppressedExceptions = SUPPRESSED_SENTINEL;

    private static final String NULL_CAUSE_MESSAGE = "Cannot suppress a null exception.";

    private static final String SELF_SUPPRESSION_MESSAGE = "Self-suppression not permitted";

    private static final String CAUSE_CAPTION = "Caused by: ";

    private static final String SUPPRESSED_CAPTION = "Suppressed: ";

    @SideEffectFree
    public Throwable() {
        fillInStackTrace();
    }

    @SideEffectFree
    public Throwable(@Nullable String message) {
        fillInStackTrace();
        detailMessage = message;
    }

    @SideEffectFree
    public Throwable(@Nullable String message, @Nullable Throwable cause) {
        fillInStackTrace();
        detailMessage = message;
        this.cause = cause;
    }

    @SideEffectFree
    public Throwable(@Nullable Throwable cause) {
        fillInStackTrace();
        detailMessage = (cause == null ? null : cause.toString());
        this.cause = cause;
    }

    @SideEffectFree
    protected Throwable(@Nullable String message, @Nullable Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        if (writableStackTrace) {
            fillInStackTrace();
        } else {
            stackTrace = null;
        }
        detailMessage = message;
        this.cause = cause;
        if (!enableSuppression)
            suppressedExceptions = null;
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

    private void printStackTrace(PrintStreamOrWriter s);

    private void printEnclosedStackTrace(PrintStreamOrWriter s, StackTraceElement[] enclosingTrace, String caption, String prefix, Set<Throwable> dejaVu);

    public void printStackTrace(PrintWriter s);

    private abstract static class PrintStreamOrWriter {

        abstract Object lock();

        abstract void println(Object o);
    }

    private static class WrappedPrintStream extends PrintStreamOrWriter {

        private final PrintStream printStream;

        WrappedPrintStream(PrintStream printStream) {
            this.printStream = printStream;
        }

        Object lock();

        void println(Object o);
    }

    private static class WrappedPrintWriter extends PrintStreamOrWriter {

        private final PrintWriter printWriter;

        WrappedPrintWriter(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        Object lock();

        void println(Object o);
    }

    public synchronized Throwable fillInStackTrace();

    private native Throwable fillInStackTrace(int dummy);

    public StackTraceElement[] getStackTrace();

    private synchronized StackTraceElement[] getOurStackTrace();

    public void setStackTrace(StackTraceElement[] stackTrace);

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    private synchronized void writeObject(ObjectOutputStream s) throws IOException;

    public final synchronized void addSuppressed(Throwable exception);

    private static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];

    public final synchronized Throwable[] getSuppressed();
}
