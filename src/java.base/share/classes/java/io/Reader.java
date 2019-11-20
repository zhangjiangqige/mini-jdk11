package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.CharBuffer;
import java.util.Objects;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public abstract class Reader implements Readable, Closeable {

    public static Reader nullReader();

    protected Object lock;

    protected Reader() {
    }

    protected Reader(Object lock) {
    }

    @GTENegativeOne
    public int read(@GuardSatisfied Reader this, java.nio.CharBuffer target) throws IOException;

    @GTENegativeOne
    public int read(@GuardSatisfied Reader this) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(@GuardSatisfied Reader this, char[] cbuf) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public abstract int read(@GuardSatisfied Reader this, char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public long skip(@GuardSatisfied Reader this, @NonNegative long n) throws IOException;

    public boolean ready() throws IOException;

    public boolean markSupported();

    public void mark(@GuardSatisfied Reader this, @NonNegative int readAheadLimit) throws IOException;

    public void reset(@GuardSatisfied Reader this) throws IOException;

    public abstract void close(@GuardSatisfied Reader this) throws IOException;

    public long transferTo(Writer out) throws IOException;
}
