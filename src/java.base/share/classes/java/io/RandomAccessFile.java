package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.Unsigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import jdk.internal.misc.JavaIORandomAccessFileAccess;
import jdk.internal.misc.SharedSecrets;
import sun.nio.ch.FileChannelImpl;

@AnnotatedFor({ "index", "interning", "nullness", "signedness" })
@UsesObjectEquals
public class RandomAccessFile implements DataOutput, DataInput, Closeable {

    private FileDescriptor fd;

    private volatile FileChannel channel;

    private boolean rw;

    private final String path;

    private final AtomicBoolean closed = new AtomicBoolean(false);

    private static final int O_RDONLY = 1;

    private static final int O_RDWR = 2;

    private static final int O_SYNC = 4;

    private static final int O_DSYNC = 8;

    private static final int O_TEMPORARY = 16;

    public RandomAccessFile(String name, String mode) throws FileNotFoundException {
        this(name != null ? new File(name) : null, mode);
    }

    public RandomAccessFile(File file, String mode) throws FileNotFoundException {
        this(file, mode, false);
    }

    private RandomAccessFile(File file, String mode, boolean openAndDelete) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        int imode = -1;
        if (mode.equals("r"))
            imode = O_RDONLY;
        else if (mode.startsWith("rw")) {
            imode = O_RDWR;
            rw = true;
            if (mode.length() > 2) {
                if (mode.equals("rws"))
                    imode |= O_SYNC;
                else if (mode.equals("rwd"))
                    imode |= O_DSYNC;
                else
                    imode = -1;
            }
        }
        if (openAndDelete)
            imode |= O_TEMPORARY;
        if (imode < 0)
            throw new IllegalArgumentException("Illegal mode \"" + mode + "\" must be one of " + "\"r\", \"rw\", \"rws\"," + " or \"rwd\"");
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
            if (rw) {
                security.checkWrite(name);
            }
        }
        if (name == null) {
            throw new NullPointerException();
        }
        if (file.isInvalid()) {
            throw new FileNotFoundException("Invalid file path");
        }
        fd = new FileDescriptor();
        fd.attach(this);
        path = name;
        open(name, imode);
        FileCleanable.register(fd);
    }

    public final FileDescriptor getFD() throws IOException;

    public final FileChannel getChannel();

    private native void open0(String name, int mode) throws FileNotFoundException;

    private void open(String name, int mode) throws FileNotFoundException;

    @GTENegativeOne
    public int read() throws IOException;

    private native int read0() throws IOException;

    private native int readBytes(@PolySigned byte[] b, int off, int len) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(@PolySigned byte[] b) throws IOException;

    public final void readFully(@PolySigned byte[] b) throws IOException;

    public final void readFully(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public int skipBytes(@NonNegative int n) throws IOException;

    public void write(@PolySigned int b) throws IOException;

    private native void write0(@PolySigned int b) throws IOException;

    private native void writeBytes(@PolySigned byte[] b, int off, int len) throws IOException;

    public void write(@PolySigned byte[] b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public native long getFilePointer() throws IOException;

    public void seek(@NonNegative long pos) throws IOException;

    private native void seek0(long pos) throws IOException;

    @NonNegative
    public native long length() throws IOException;

    public native void setLength(@NonNegative long newLength) throws IOException;

    public void close() throws IOException;

    public final boolean readBoolean() throws IOException;

    public final byte readByte() throws IOException;

    @NonNegative
    @Unsigned
    public final int readUnsignedByte() throws IOException;

    public final short readShort() throws IOException;

    @NonNegative
    @Unsigned
    public final int readUnsignedShort() throws IOException;

    public final char readChar() throws IOException;

    public final int readInt() throws IOException;

    public final long readLong() throws IOException;

    public final float readFloat() throws IOException;

    public final double readDouble() throws IOException;

    @Nullable
    public final String readLine() throws IOException;

    public final String readUTF() throws IOException;

    public final void writeBoolean(boolean v) throws IOException;

    public final void writeByte(@PolySigned int v) throws IOException;

    public final void writeShort(@PolySigned int v) throws IOException;

    public final void writeChar(@PolySigned int v) throws IOException;

    public final void writeInt(@PolySigned int v) throws IOException;

    public final void writeLong(@PolySigned long v) throws IOException;

    public final void writeFloat(float v) throws IOException;

    public final void writeDouble(double v) throws IOException;

    @SuppressWarnings("deprecation")
    public final void writeBytes(String s) throws IOException;

    public final void writeChars(String s) throws IOException;

    public final void writeUTF(String str) throws IOException;

    private static native void initIDs();

    static {
        initIDs();
        SharedSecrets.setJavaIORandomAccessFileAccess(new JavaIORandomAccessFileAccess() {

            public RandomAccessFile openAndDelete(File file, String mode) throws IOException {
                return new RandomAccessFile(file, mode, true);
            }
        });
    }
}
