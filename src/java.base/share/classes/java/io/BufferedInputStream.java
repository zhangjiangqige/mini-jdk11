package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "nullness", "index" })
public class BufferedInputStream extends FilterInputStream {

    protected volatile byte[] buf;

    protected int count;

    protected int pos;

    protected int markpos;

    protected int marklimit;

    public BufferedInputStream(InputStream in) {
    }

    public BufferedInputStream(InputStream in, @Positive int size) {
    }

    @GTENegativeOne
    public synchronized int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public synchronized int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public synchronized long skip(long n) throws IOException;

    @NonNegative
    public synchronized int available() throws IOException;

    public synchronized void mark(int readlimit);

    public synchronized void reset() throws IOException;

    public boolean markSupported();

    public void close() throws IOException;
}
