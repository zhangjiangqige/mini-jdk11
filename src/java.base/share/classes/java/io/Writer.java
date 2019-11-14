package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "index", "interning", "nullness" })
@UsesObjectEquals
public abstract class Writer implements Appendable, Closeable, Flushable {

    private char[] writeBuffer;

    private static final int WRITE_BUFFER_SIZE = 1024;

    public static Writer nullWriter();

    protected Object lock;

    protected Writer() {
        this.lock = this;
    }

    protected Writer(Object lock) {
        if (lock == null) {
            throw new NullPointerException();
        }
        this.lock = lock;
    }

    public void write(int c) throws IOException;

    public void write(char[] cbuf) throws IOException;

    public abstract void write(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void write(String str) throws IOException;

    public void write(String str, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public Writer append(@Nullable CharSequence csq) throws IOException;

    public Writer append(@Nullable CharSequence csq, @IndexOrHigh({ "#1" }) int start, @IndexOrHigh({ "#1" }) int end) throws IOException;

    public Writer append(char c) throws IOException;

    public abstract void flush() throws IOException;

    public abstract void close() throws IOException;
}
