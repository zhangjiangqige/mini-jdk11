package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index", "signedness" })
public class DataInputStream extends FilterInputStream implements DataInput {

    public DataInputStream(InputStream in) {
        super(in);
    }

    private byte[] bytearr = new byte[80];

    private char[] chararr = new char[80];

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public final int read(@PolySigned byte @Nullable [] b) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public final int read(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public final void readFully(@PolySigned byte[] b) throws IOException;

    public final void readFully(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public final int skipBytes(int n) throws IOException;

    public final boolean readBoolean() throws IOException;

    public final byte readByte() throws IOException;

    @NonNegative
    public final int readUnsignedByte() throws IOException;

    public final short readShort() throws IOException;

    @NonNegative
    public final int readUnsignedShort() throws IOException;

    public final char readChar() throws IOException;

    public final int readInt() throws IOException;

    private byte[] readBuffer = new byte[8];

    public final long readLong() throws IOException;

    public final float readFloat() throws IOException;

    public final double readDouble() throws IOException;

    private char[] lineBuffer;

    @Deprecated
    @Nullable
    public final String readLine() throws IOException;

    public final String readUTF() throws IOException;

    public static final String readUTF(DataInput in) throws IOException;
}
