package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;

@AnnotatedFor({ "nullness", "index", "signedness" })
public class PipedOutputStream extends OutputStream {

    private PipedInputStream sink;

    public PipedOutputStream(PipedInputStream snk) throws IOException {
        connect(snk);
    }

    public PipedOutputStream() {
    }

    public synchronized void connect(PipedInputStream snk) throws IOException;

    public void write(@PolySigned int b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public synchronized void flush() throws IOException;

    public void close() throws IOException;
}
