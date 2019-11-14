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

    private static final JavaIOFileDescriptorAccess fdAccess = SharedSecrets.getJavaIOFileDescriptorAccess();

    private final FileDescriptor fd;

    private volatile FileChannel channel;

    private final String path;

    private final Object closeLock = new Object();

    private volatile boolean closed;

    private final Object altFinalizer;

    public FileOutputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null, false);
    }

    public FileOutputStream(String name, boolean append) throws FileNotFoundException {
        this(name != null ? new File(name) : null, append);
    }

    public FileOutputStream(File file) throws FileNotFoundException {
        this(file, false);
    }

    public FileOutputStream(File file, boolean append) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(name);
        }
        if (name == null) {
            throw new NullPointerException();
        }
        if (file.isInvalid()) {
            throw new FileNotFoundException("Invalid file path");
        }
        this.fd = new FileDescriptor();
        fd.attach(this);
        this.path = name;
        open(name, append);
        altFinalizer = getFinalizer(this);
        if (altFinalizer == null) {
            FileCleanable.register(fd);
        }
    }

    public FileOutputStream(FileDescriptor fdObj) {
        SecurityManager security = System.getSecurityManager();
        if (fdObj == null) {
            throw new NullPointerException();
        }
        if (security != null) {
            security.checkWrite(fdObj);
        }
        this.fd = fdObj;
        this.path = null;
        this.altFinalizer = null;
        fd.attach(this);
    }

    private native void open0(String name, boolean append) throws FileNotFoundException;

    private void open(String name, boolean append) throws FileNotFoundException;

    private native void write(int b, boolean append) throws IOException;

    public void write(@PolySigned int b) throws IOException;

    private native void writeBytes(@PolySigned byte[] b, int off, int len, boolean append) throws IOException;

    public void write(@PolySigned byte[] b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void close() throws IOException;

    public final FileDescriptor getFD() throws IOException;

    public FileChannel getChannel();

    @Deprecated(since = "9", forRemoval = true)
    protected void finalize() throws IOException;

    private static native void initIDs();

    static {
        initIDs();
    }

    private static Object getFinalizer(FileOutputStream fos);

    static class AltFinalizer {

        private final FileOutputStream fos;

        AltFinalizer(FileOutputStream fos) {
            this.fos = fos;
        }

        @Override
        @SuppressWarnings("deprecation")
        protected final void finalize();
    }
}
