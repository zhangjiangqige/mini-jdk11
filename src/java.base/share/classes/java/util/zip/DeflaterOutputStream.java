package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;

@AnnotatedFor({ "index", "signedness" })
public class DeflaterOutputStream extends FilterOutputStream {

    protected Deflater def;

    protected byte[] buf;

    private boolean closed = false;

    private final boolean syncFlush;

    public DeflaterOutputStream(OutputStream out, Deflater def, @Positive int size, boolean syncFlush) {
        super(out);
        if (out == null || def == null) {
            throw new NullPointerException();
        } else if (size <= 0) {
            throw new IllegalArgumentException("buffer size <= 0");
        }
        this.def = def;
        this.buf = new byte[size];
        this.syncFlush = syncFlush;
    }

    public DeflaterOutputStream(OutputStream out, Deflater def, @Positive int size) {
        this(out, def, size, false);
    }

    public DeflaterOutputStream(OutputStream out, Deflater def, boolean syncFlush) {
        this(out, def, 512, syncFlush);
    }

    public DeflaterOutputStream(OutputStream out, Deflater def) {
        this(out, def, 512, false);
    }

    boolean usesDefaultDeflater = false;

    public DeflaterOutputStream(OutputStream out, boolean syncFlush) {
        this(out, new Deflater(), 512, syncFlush);
        usesDefaultDeflater = true;
    }

    public DeflaterOutputStream(OutputStream out) {
        this(out, false);
        usesDefaultDeflater = true;
    }

    public void write(@NonNegative int b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void finish() throws IOException;

    public void close() throws IOException;

    protected void deflate() throws IOException;

    public void flush() throws IOException;
}
