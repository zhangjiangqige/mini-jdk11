package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.channels.FileChannel;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.JavaIOFileDescriptorAccess;
import sun.nio.ch.FileChannelImpl;

@AnnotatedFor({ "nullness", "index", "signedness" })
public class FileOutputStream extends OutputStream {

    public FileOutputStream(String name) throws FileNotFoundException {
    }

    public FileOutputStream(String name, boolean append) throws FileNotFoundException {
    }

    public FileOutputStream(File file) throws FileNotFoundException {
    }

    public FileOutputStream(File file, boolean append) throws FileNotFoundException {
    }

    public FileOutputStream(FileDescriptor fdObj) {
    }

    public void write(@PolySigned int b) throws IOException;

    public void write(@PolySigned byte[] b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void close() throws IOException;

    public final FileDescriptor getFD() throws IOException;

    public FileChannel getChannel();

    @Deprecated()
    protected void finalize() throws IOException;
}
