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

    public DeflaterOutputStream(OutputStream out, Deflater def, @Positive int size, boolean syncFlush) {
    }

    public DeflaterOutputStream(OutputStream out, Deflater def, @Positive int size) {
    }

    public DeflaterOutputStream(OutputStream out, Deflater def, boolean syncFlush) {
    }

    public DeflaterOutputStream(OutputStream out, Deflater def) {
    }

    public DeflaterOutputStream(OutputStream out, boolean syncFlush) {
    }

    public DeflaterOutputStream(OutputStream out) {
    }

    public void write(@NonNegative int b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void finish() throws IOException;

    public void close() throws IOException;

    protected void deflate() throws IOException;

    public void flush() throws IOException;
}
