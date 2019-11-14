package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jdk.internal.misc.JavaIOFileDescriptorAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.ref.PhantomCleanable;

@AnnotatedFor({ "nullness", "index" })
public final class FileDescriptor {

    private int fd;

    private long handle;

    private Closeable parent;

    private List<Closeable> otherParents;

    private boolean closed;

    private boolean append;

    static {
        initIDs();
    }

    static {
        SharedSecrets.setJavaIOFileDescriptorAccess(new JavaIOFileDescriptorAccess() {

            public void set(FileDescriptor fdo, int fd) {
                fdo.set(fd);
            }

            public int get(FileDescriptor fdo) {
                return fdo.fd;
            }

            public void setAppend(FileDescriptor fdo, boolean append) {
                fdo.append = append;
            }

            public boolean getAppend(FileDescriptor fdo) {
                return fdo.append;
            }

            public void close(FileDescriptor fdo) throws IOException {
                fdo.close();
            }

            public void registerCleanup(FileDescriptor fdo) {
                FileCleanable.register(fdo);
            }

            public void registerCleanup(FileDescriptor fdo, PhantomCleanable<FileDescriptor> cleanup) {
                fdo.registerCleanup(cleanup);
            }

            public void unregisterCleanup(FileDescriptor fdo) {
                fdo.unregisterCleanup();
            }

            public void setHandle(FileDescriptor fdo, long handle) {
                fdo.setHandle(handle);
            }

            public long getHandle(FileDescriptor fdo) {
                return fdo.handle;
            }
        });
    }

    private PhantomCleanable<FileDescriptor> cleanup;

    public FileDescriptor() {
        fd = -1;
        handle = -1;
    }

    private FileDescriptor(int fd) {
        this.fd = fd;
        this.handle = getHandle(fd);
        this.append = getAppend(fd);
    }

    public static final FileDescriptor in = new FileDescriptor(0);

    public static final FileDescriptor out = new FileDescriptor(1);

    public static final FileDescriptor err = new FileDescriptor(2);

    public boolean valid();

    public native void sync() throws SyncFailedException;

    private static native void initIDs();

    private static native long getHandle(int d);

    private static native boolean getAppend(int fd);

    @SuppressWarnings("unchecked")
    synchronized void set(int fd);

    @SuppressWarnings("unchecked")
    void setHandle(long handle);

    @SuppressWarnings("unchecked")
    synchronized void registerCleanup(PhantomCleanable<FileDescriptor> cleanable);

    synchronized void unregisterCleanup();

    @SuppressWarnings("unchecked")
    synchronized void close() throws IOException;

    private native void close0() throws IOException;

    synchronized void attach(Closeable c);

    @SuppressWarnings("try")
    synchronized void closeAll(Closeable releaser) throws IOException;
}
