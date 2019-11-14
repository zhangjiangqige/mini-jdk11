package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;

@AnnotatedFor({ "index" })
public class DeflaterInputStream extends FilterInputStream {

    protected final Deflater def;

    protected final byte[] buf;

    private byte[] rbuf = new byte[1];

    private boolean usesDefaultDeflater = false;

    private boolean reachEOF = false;

    private void ensureOpen() throws IOException;

    public DeflaterInputStream(InputStream in) {
        this(in, new Deflater());
        usesDefaultDeflater = true;
    }

    public DeflaterInputStream(InputStream in, Deflater defl) {
        this(in, defl, 512);
    }

    public DeflaterInputStream(InputStream in, Deflater defl, @Positive int bufLen) {
        super(in);
        if (in == null)
            throw new NullPointerException("Null input");
        if (defl == null)
            throw new NullPointerException("Null deflater");
        if (bufLen < 1)
            throw new IllegalArgumentException("Buffer size < 1");
        def = defl;
        buf = new byte[bufLen];
    }

    public void close() throws IOException;

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public long skip(long n) throws IOException;

    public int available() throws IOException;

    public boolean markSupported();

    public void mark(@NonNegative int limit);

    public void reset() throws IOException;
}
