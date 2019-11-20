package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.channels.FileChannel;
import sun.nio.ch.FileChannelImpl;

@AnnotatedFor({ "nullness", "index" })
public class FileInputStream extends InputStream {

    public FileInputStream(String name) throws FileNotFoundException {
    }

    public FileInputStream(File file) throws FileNotFoundException {
    }

    public FileInputStream(FileDescriptor fdObj) {
    }

    @GTENegativeOne
    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public long skip(long n) throws IOException;

    @NonNegative
    public int available() throws IOException;

    public void close() throws IOException;

    public final FileDescriptor getFD() throws IOException;

    public FileChannel getChannel();

    @Deprecated()
    protected void finalize() throws IOException;
}
