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

    public PipedReader(PipedWriter src) throws IOException {
    }

    public PipedReader(PipedWriter src, @Positive int pipeSize) throws IOException {
    }

    public PipedReader() {
    }

    public PipedReader(@Positive int pipeSize) {
    }

    public void connect(PipedWriter src) throws IOException;

    @GTENegativeOne
    public synchronized int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public synchronized int read(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public synchronized boolean ready() throws IOException;

    public void close() throws IOException;
}
