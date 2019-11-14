package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness", "index", "signedness" })
public class ByteArrayOutputStream extends OutputStream {

    protected byte[] buf;

    @IndexOrHigh({ "this.buf" })
    protected int count;

    public ByteArrayOutputStream() {
        this(32);
    }

    public ByteArrayOutputStream(@NonNegative int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: " + size);
        }
        buf = new byte[size];
    }

    private void ensureCapacity(int minCapacity);

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity);

    private static int hugeCapacity(int minCapacity);

    public synchronized void write(@PolySigned int b);

    public synchronized void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    public void writeBytes(byte[] b);

    public synchronized void writeTo(OutputStream out) throws IOException;

    public synchronized void reset();

    @PolySigned
    public synchronized byte[] toByteArray();

    @Pure
    @IndexOrHigh({ "this.buf" })
    public synchronized int size(@GuardSatisfied ByteArrayOutputStream this);

    @SideEffectFree
    public synchronized String toString(@GuardSatisfied ByteArrayOutputStream this);

    @SideEffectFree
    public synchronized String toString(@GuardSatisfied ByteArrayOutputStream this, String charsetName) throws UnsupportedEncodingException;

    public synchronized String toString(Charset charset);

    @SideEffectFree
    @Deprecated
    public synchronized String toString(@GuardSatisfied ByteArrayOutputStream this, int hibyte);

    public void close() throws IOException;
}
