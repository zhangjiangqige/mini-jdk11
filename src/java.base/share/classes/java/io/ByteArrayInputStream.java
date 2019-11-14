package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;
import java.util.Objects;

@AnnotatedFor({ "nullness", "index" })
public class ByteArrayInputStream extends InputStream {

    protected byte[] buf;

    protected int pos;

    protected int mark = 0;

    protected int count;

    public ByteArrayInputStream(byte[] buf) {
        this.buf = buf;
        this.pos = 0;
        this.count = buf.length;
    }

    public ByteArrayInputStream(byte[] buf, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length) {
        this.buf = buf;
        this.pos = offset;
        this.count = Math.min(offset + length, buf.length);
        this.mark = offset;
    }

    @GTENegativeOne
    public synchronized int read();

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public synchronized int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    public synchronized byte[] readAllBytes();

    public int readNBytes(byte[] b, int off, int len);

    public synchronized long transferTo(OutputStream out) throws IOException;

    @NonNegative
    public synchronized long skip(long n);

    @NonNegative
    public synchronized int available();

    public boolean markSupported();

    public void mark(@NonNegative int readAheadLimit);

    public synchronized void reset();

    public void close() throws IOException;
}
