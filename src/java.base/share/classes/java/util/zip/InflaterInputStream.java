package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;

@AnnotatedFor({ "nullness", "index" })
public class InflaterInputStream extends FilterInputStream {

    protected Inflater inf;

    protected byte[] buf;

    protected int len;

    public InflaterInputStream(InputStream in, Inflater inf, @Positive int size) {
    }

    public InflaterInputStream(InputStream in, Inflater inf) {
    }

    public InflaterInputStream(InputStream in) {
    }

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public int available() throws IOException;

    public long skip(long n) throws IOException;

    public void close() throws IOException;

    protected void fill() throws IOException;

    public boolean markSupported();

    public synchronized void mark(int readlimit);

    public synchronized void reset() throws IOException;
}
