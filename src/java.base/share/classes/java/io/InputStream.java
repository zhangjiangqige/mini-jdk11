package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AnnotatedFor({ "index" })
public abstract class InputStream implements Closeable {

    public static InputStream nullInputStream();

    @GTENegativeOne
    public abstract int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public byte[] readAllBytes() throws IOException;

    public byte[] readNBytes(int len) throws IOException;

    public int readNBytes(byte[] b, int off, int len) throws IOException;

    @NonNegative
    public long skip(long n) throws IOException;

    @NonNegative
    public int available() throws IOException;

    public void close() throws IOException;

    public synchronized void mark(@NonNegative int readlimit);

    public synchronized void reset() throws IOException;

    public boolean markSupported();

    public long transferTo(OutputStream out) throws IOException;
}
