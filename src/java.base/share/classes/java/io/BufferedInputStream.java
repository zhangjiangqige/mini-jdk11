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

    private static int DEFAULT_BUFFER_SIZE = 8192;

    private static int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    private static final Unsafe U = Unsafe.getUnsafe();

    private static final long BUF_OFFSET = U.objectFieldOffset(BufferedInputStream.class, "buf");

    protected volatile byte[] buf;

    protected int count;

    protected int pos;

    protected int markpos = -1;

    protected int marklimit;

    private InputStream getInIfOpen() throws IOException;

    private byte[] getBufIfOpen() throws IOException;

    public BufferedInputStream(InputStream in) {
        this(in, DEFAULT_BUFFER_SIZE);
    }

    public BufferedInputStream(InputStream in, @Positive int size) {
        super(in);
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        buf = new byte[size];
    }

    private void fill() throws IOException;

    @GTENegativeOne
    public synchronized int read() throws IOException;

    private int read1(byte[] b, int off, int len) throws IOException;

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
