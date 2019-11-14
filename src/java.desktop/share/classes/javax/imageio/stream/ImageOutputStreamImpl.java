package javax.imageio.stream;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.ByteOrder;

@AnnotatedFor({ "signedness" })
public abstract class ImageOutputStreamImpl extends ImageInputStreamImpl implements ImageOutputStream {

    public ImageOutputStreamImpl() {
    }

    public abstract void write(int b) throws IOException;

    public void write(@PolySigned byte[] b) throws IOException;

    public abstract void write(@PolySigned byte[] b, int off, int len) throws IOException;

    public void writeBoolean(boolean v) throws IOException;

    public void writeByte(int v) throws IOException;

    public void writeShort(int v) throws IOException;

    public void writeChar(int v) throws IOException;

    public void writeInt(int v) throws IOException;

    public void writeLong(long v) throws IOException;

    public void writeFloat(float v) throws IOException;

    public void writeDouble(double v) throws IOException;

    public void writeBytes(String s) throws IOException;

    public void writeChars(String s) throws IOException;

    public void writeUTF(String s) throws IOException;

    public void writeShorts(short[] s, int off, int len) throws IOException;

    public void writeChars(char[] c, int off, int len) throws IOException;

    public void writeInts(int[] i, int off, int len) throws IOException;

    public void writeLongs(long[] l, int off, int len) throws IOException;

    public void writeFloats(float[] f, int off, int len) throws IOException;

    public void writeDoubles(double[] d, int off, int len) throws IOException;

    public void writeBit(int bit) throws IOException;

    public void writeBits(long bits, int numBits) throws IOException;

    protected final void flushBits() throws IOException;
}
