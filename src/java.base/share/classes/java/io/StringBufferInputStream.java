package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index" })
@Deprecated
public class StringBufferInputStream extends InputStream {

    protected String buffer;

    protected int pos;

    protected int count;

    public StringBufferInputStream(String s) {
    }

    public synchronized int read();

    @SuppressWarnings("deprecation")
    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public synchronized int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    @NonNegative
    public synchronized long skip(long n);

    @NonNegative
    public synchronized int available();

    public synchronized void reset();
}
