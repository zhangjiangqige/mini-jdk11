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

    boolean closedByWriter;

    volatile boolean closedByReader;

    boolean connected;

    Thread readSide;

    Thread writeSide;

    private static final int DEFAULT_PIPE_SIZE = 1024;

    protected static final int PIPE_SIZE = DEFAULT_PIPE_SIZE;

    protected byte[] buffer;

    protected int in = -1;

    protected int out = 0;

    public PipedInputStream(PipedOutputStream src) throws IOException {
        this(src, DEFAULT_PIPE_SIZE);
    }

    public PipedInputStream(PipedOutputStream src, @Positive int pipeSize) throws IOException {
        initPipe(pipeSize);
        connect(src);
    }

    public PipedInputStream() {
        initPipe(DEFAULT_PIPE_SIZE);
    }

    public PipedInputStream(@Positive int pipeSize) {
        initPipe(pipeSize);
    }

    private void initPipe(int pipeSize);

    public void connect(PipedOutputStream src) throws IOException;

    protected synchronized void receive(int b) throws IOException;

    synchronized void receive(byte[] b, int off, int len) throws IOException;

    private void checkStateForReceive() throws IOException;

    private void awaitSpace() throws IOException;

    synchronized void receivedLast();

    @GTENegativeOne
    public synchronized int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public synchronized int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public synchronized int available() throws IOException;

    public void close() throws IOException;
}
