package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness", "index", "signedness" })
public class DataOutputStream extends FilterOutputStream implements DataOutput {

    protected int written;

    public DataOutputStream(OutputStream out) {
    }

    public synchronized void write(@PolySigned int b) throws IOException;

    public synchronized void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void flush() throws IOException;

    public final void writeBoolean(boolean v) throws IOException;

    public final void writeByte(int v) throws IOException;

    public final void writeShort(int v) throws IOException;

    public final void writeChar(int v) throws IOException;

    public final void writeInt(int v) throws IOException;

    public final void writeLong(long v) throws IOException;

    public final void writeFloat(float v) throws IOException;

    public final void writeDouble(double v) throws IOException;

    public final void writeBytes(String s) throws IOException;

    public final void writeChars(String s) throws IOException;

    public final void writeUTF(String str) throws IOException;

    @Pure
    @NonNegative
    public final int size(@GuardSatisfied DataOutputStream this);
}
