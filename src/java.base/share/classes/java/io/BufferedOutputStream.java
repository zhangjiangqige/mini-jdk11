package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index", "signedness" })
public class BufferedOutputStream extends FilterOutputStream {

    protected byte[] buf;

    protected int count;

    public BufferedOutputStream(OutputStream out) {
    }

    public BufferedOutputStream(OutputStream out, @Positive int size) {
    }

    @Override
    public synchronized void write(@PolySigned int b) throws IOException;

    @Override
    public synchronized void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Override
    public synchronized void flush() throws IOException;
}
