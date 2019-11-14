package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index" })
public class PushbackInputStream extends FilterInputStream {

    protected byte[] buf;

    protected int pos;

    private void ensureOpen() throws IOException;

    public PushbackInputStream(InputStream in, @Positive int size) {
        super(in);
        if (size <= 0) {
            throw new IllegalArgumentException("size <= 0");
        }
        this.buf = new byte[size];
        this.pos = size;
    }

    public PushbackInputStream(InputStream in) {
        this(in, 1);
    }

    @GTENegativeOne
    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void unread(int b) throws IOException;

    public void unread(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void unread(byte[] b) throws IOException;

    @NonNegative
    public int available() throws IOException;

    @NonNegative
    public long skip(long n) throws IOException;

    public boolean markSupported();

    public synchronized void mark(@NonNegative int readlimit);

    public synchronized void reset() throws IOException;

    public synchronized void close() throws IOException;
}
