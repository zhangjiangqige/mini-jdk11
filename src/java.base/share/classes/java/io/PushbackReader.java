package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index" })
public class PushbackReader extends FilterReader {

    private char[] buf;

    private int pos;

    public PushbackReader(Reader in, @Positive int size) {
        super(in);
        if (size <= 0) {
            throw new IllegalArgumentException("size <= 0");
        }
        this.buf = new char[size];
        this.pos = size;
    }

    public PushbackReader(Reader in) {
        this(in, 1);
    }

    private void ensureOpen() throws IOException;

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void unread(int c) throws IOException;

    public void unread(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void unread(char[] cbuf) throws IOException;

    public boolean ready() throws IOException;

    public void mark(@NonNegative int readAheadLimit) throws IOException;

    public void reset() throws IOException;

    public boolean markSupported();

    public void close() throws IOException;

    @NonNegative
    public long skip(@NonNegative long n) throws IOException;
}
