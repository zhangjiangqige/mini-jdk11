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

    public InflaterOutputStream(OutputStream out) {
    }

    public InflaterOutputStream(OutputStream out, Inflater infl) {
    }

    public InflaterOutputStream(OutputStream out, Inflater infl, @Positive int bufLen) {
    }

    public void close() throws IOException;

    public void flush() throws IOException;

    public void finish() throws IOException;

    public void write(int b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;
}
