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

    private final FileDescriptor fd;

    private final String path;

    private volatile FileChannel channel;

    private final Object closeLock = new Object();

    private volatile boolean closed;

    private final Object altFinalizer;

    public FileInputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null);
    }

    public FileInputStream(File file) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
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
        open(name);
        altFinalizer = getFinalizer(this);
        if (altFinalizer == null) {
            FileCleanable.register(fd);
        }
    }

    public FileInputStream(FileDescriptor fdObj) {
        SecurityManager security = System.getSecurityManager();
        if (fdObj == null) {
            throw new NullPointerException();
        }
        if (security != null) {
            security.checkRead(fdObj);
        }
        fd = fdObj;
        path = null;
        altFinalizer = null;
        fd.attach(this);
    }

    private native void open0(String name) throws FileNotFoundException;

    private void open(String name) throws FileNotFoundException;

    @GTENegativeOne
    public int read() throws IOException;

    private native int read0() throws IOException;

    private native int readBytes(byte[] b, int off, int len) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public long skip(long n) throws IOException;

    private native long skip0(long n) throws IOException;

    @NonNegative
    public int available() throws IOException;

    private native int available0() throws IOException;

    public void close() throws IOException;

    public final FileDescriptor getFD() throws IOException;

    public FileChannel getChannel();

    private static native void initIDs();

    static {
        initIDs();
    }

    @Deprecated(since = "9", forRemoval = true)
    protected void finalize() throws IOException;

    private static Object getFinalizer(FileInputStream fis);

    static class AltFinalizer {

        private final FileInputStream fis;

        AltFinalizer(FileInputStream fis) {
            this.fis = fis;
        }

        @Override
        @SuppressWarnings("deprecation")
        protected final void finalize();
    }
}
