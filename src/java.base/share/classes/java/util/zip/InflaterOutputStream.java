package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@AnnotatedFor({ "index", "signedness" })
public class InflaterOutputStream extends FilterOutputStream {

    protected final Inflater inf;

    protected final byte[] buf;

    private final byte[] wbuf = new byte[1];

    private boolean usesDefaultInflater = false;

    private boolean closed = false;

    private void ensureOpen() throws IOException;

    public InflaterOutputStream(OutputStream out) {
        this(out, new Inflater());
        usesDefaultInflater = true;
    }

    public InflaterOutputStream(OutputStream out, Inflater infl) {
        this(out, infl, 512);
    }

    public InflaterOutputStream(OutputStream out, Inflater infl, @Positive int bufLen) {
        super(out);
        if (out == null)
            throw new NullPointerException("Null output");
        if (infl == null)
            throw new NullPointerException("Null inflater");
        if (bufLen <= 0)
            throw new IllegalArgumentException("Buffer size < 1");
        inf = infl;
        buf = new byte[bufLen];
    }

    public void close() throws IOException;

    public void flush() throws IOException;

    public void finish() throws IOException;

    public void write(int b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;
}
