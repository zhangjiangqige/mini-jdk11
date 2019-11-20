package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.OutputStream;
import java.io.IOException;
import org.checkerframework.framework.qual.CFComment;

@AnnotatedFor({ "index", "signedness" })
public class GZIPOutputStream extends DeflaterOutputStream {

    protected CRC32 crc;

    public GZIPOutputStream(OutputStream out, @Positive int size) throws IOException {
    }

    public GZIPOutputStream(OutputStream out, @Positive int size, boolean syncFlush) throws IOException {
    }

    public GZIPOutputStream(OutputStream out) throws IOException {
    }

    public GZIPOutputStream(OutputStream out, boolean syncFlush) throws IOException {
    }

    public synchronized void write(@PolySigned byte[] buf, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void finish() throws IOException;
}
