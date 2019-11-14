package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index" })
public class PipedReader extends Reader {

    boolean closedByWriter = false;

    boolean closedByReader = false;

    boolean connected = false;

    Thread readSide;

    Thread writeSide;

    private static final int DEFAULT_PIPE_SIZE = 1024;

    char[] buffer;

    int in = -1;

    int out = 0;

    public PipedReader(PipedWriter src) throws IOException {
        this(src, DEFAULT_PIPE_SIZE);
    }

    public PipedReader(PipedWriter src, @Positive int pipeSize) throws IOException {
        initPipe(pipeSize);
        connect(src);
    }

    public PipedReader() {
        initPipe(DEFAULT_PIPE_SIZE);
    }

    public PipedReader(@Positive int pipeSize) {
        initPipe(pipeSize);
    }

    private void initPipe(int pipeSize);

    public void connect(PipedWriter src) throws IOException;

    synchronized void receive(int c) throws IOException;

    synchronized void receive(char[] c, int off, int len) throws IOException;

    synchronized void receivedLast();

    @GTENegativeOne
    public synchronized int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public synchronized int read(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public synchronized boolean ready() throws IOException;

    public void close() throws IOException;
}
