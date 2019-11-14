package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock: Note that the @GuardSatisfied is for locks that are external to the implementation class." })
@AnnotatedFor({ "lock", "nullness", "index", "signedness" })
public class FilterOutputStream extends OutputStream {

    protected OutputStream out;

    private volatile boolean closed;

    private final Object closeLock = new Object();

    public FilterOutputStream(@Nullable OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(@GuardSatisfied FilterOutputStream this, @PolySigned int b) throws IOException;

    @Override
    public void write(@GuardSatisfied FilterOutputStream this, @PolySigned byte[] b) throws IOException;

    @Override
    public void write(@GuardSatisfied FilterOutputStream this, @PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Override
    public void flush(@GuardSatisfied FilterOutputStream this) throws IOException;

    @Override
    public void close(@GuardSatisfied FilterOutputStream this) throws IOException;
}
