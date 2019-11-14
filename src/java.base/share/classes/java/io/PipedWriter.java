package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index" })
public class PipedWriter extends Writer {

    private PipedReader sink;

    private boolean closed = false;

    public PipedWriter(PipedReader snk) throws IOException {
        connect(snk);
    }

    public PipedWriter() {
    }

    public synchronized void connect(PipedReader snk) throws IOException;

    public void write(int c) throws IOException;

    public void write(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public synchronized void flush() throws IOException;

    public void close() throws IOException;
}
