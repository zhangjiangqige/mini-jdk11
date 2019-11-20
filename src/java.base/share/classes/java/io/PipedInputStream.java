package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index" })
public class PipedInputStream extends InputStream {

    protected static final int PIPE_SIZE;

    protected byte[] buffer;

    protected int in;

    protected int out;

    public PipedInputStream(PipedOutputStream src) throws IOException {
    }

    public PipedInputStream(PipedOutputStream src, @Positive int pipeSize) throws IOException {
    }

    public PipedInputStream() {
    }

    public PipedInputStream(@Positive int pipeSize) {
    }

    public void connect(PipedOutputStream src) throws IOException;

    protected synchronized void receive(int b) throws IOException;

    @GTENegativeOne
    public synchronized int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public synchronized int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public synchronized int available() throws IOException;

    public void close() throws IOException;
}
