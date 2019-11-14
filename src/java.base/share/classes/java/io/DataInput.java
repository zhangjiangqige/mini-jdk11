package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index" })
public interface DataInput {

    void readFully(byte[] b) throws IOException;

    void readFully(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    int skipBytes(int n) throws IOException;

    boolean readBoolean() throws IOException;

    byte readByte() throws IOException;

    @NonNegative
    int readUnsignedByte() throws IOException;

    short readShort() throws IOException;

    @NonNegative
    int readUnsignedShort() throws IOException;

    char readChar() throws IOException;

    int readInt() throws IOException;

    long readLong() throws IOException;

    float readFloat() throws IOException;

    double readDouble() throws IOException;

    @Nullable
    String readLine() throws IOException;

    String readUTF() throws IOException;
}
