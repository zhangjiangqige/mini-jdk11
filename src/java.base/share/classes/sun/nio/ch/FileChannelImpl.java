package sun.nio.ch;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.ref.Cleaner.Cleanable;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.FileLockInterruptionException;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;
import jdk.internal.misc.JavaIOFileDescriptorAccess;
import jdk.internal.misc.JavaNioAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.ref.Cleaner;
import jdk.internal.ref.CleanerFactory;

@AnnotatedFor({ "index" })
public class FileChannelImpl extends FileChannel {

    private static final long allocationGranularity;

    private static final JavaIOFileDescriptorAccess fdAccess = SharedSecrets.getJavaIOFileDescriptorAccess();

    private final FileDispatcher nd;

    private final FileDescriptor fd;

    private final boolean writable;

    private final boolean readable;

    private final Object parent;

    private final String path;

    private final NativeThreadSet threads = new NativeThreadSet(2);

    private final Object positionLock = new Object();

    private volatile boolean uninterruptible;

    private final boolean direct;

    private final int alignment;

    private final Cleanable closer;

    private static class Closer implements Runnable {

        private final FileDescriptor fd;

        Closer(FileDescriptor fd) {
            this.fd = fd;
        }

        public void run();
    }

    private FileChannelImpl(FileDescriptor fd, String path, boolean readable, boolean writable, boolean direct, Object parent) {
        this.fd = fd;
        this.readable = readable;
        this.writable = writable;
        this.parent = parent;
        this.path = path;
        this.direct = direct;
        this.nd = new FileDispatcherImpl();
        if (direct) {
            assert path != null;
            this.alignment = nd.setDirectIO(fd, path);
        } else {
            this.alignment = -1;
        }
        this.closer = parent != null ? null : CleanerFactory.cleaner().register(this, new Closer(fd));
    }

    public static FileChannel open(FileDescriptor fd, String path, boolean readable, boolean writable, boolean direct, Object parent);

    private void ensureOpen() throws IOException;

    public void setUninterruptible();

    private void beginBlocking();

    private void endBlocking(boolean completed) throws AsynchronousCloseException;

    protected void implCloseChannel() throws IOException;

    public int read(ByteBuffer dst) throws IOException;

    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException;

    public int write(ByteBuffer src) throws IOException;

    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException;

    public long position() throws IOException;

    public FileChannel position(long newPosition) throws IOException;

    public long size() throws IOException;

    public FileChannel truncate(long newSize) throws IOException;

    public void force(boolean metaData) throws IOException;

    private static volatile boolean transferSupported = true;

    private static volatile boolean pipeSupported = true;

    private static volatile boolean fileSupported = true;

    private long transferToDirectlyInternal(long position, int icount, WritableByteChannel target, FileDescriptor targetFD) throws IOException;

    private long transferToDirectly(long position, int icount, WritableByteChannel target) throws IOException;

    private static final long MAPPED_TRANSFER_SIZE = 8L * 1024L * 1024L;

    private long transferToTrustedChannel(long position, long count, WritableByteChannel target) throws IOException;

    private long transferToArbitraryChannel(long position, int icount, WritableByteChannel target) throws IOException;

    public long transferTo(long position, long count, WritableByteChannel target) throws IOException;

    private long transferFromFileChannel(FileChannelImpl src, long position, long count) throws IOException;

    private static final int TRANSFER_SIZE = 8192;

    private long transferFromArbitraryChannel(ReadableByteChannel src, long position, long count) throws IOException;

    public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException;

    public int read(ByteBuffer dst, long position) throws IOException;

    private int readInternal(ByteBuffer dst, long position) throws IOException;

    public int write(ByteBuffer src, long position) throws IOException;

    private int writeInternal(ByteBuffer src, long position) throws IOException;

    private static class Unmapper implements Runnable {

        private static final NativeDispatcher nd = new FileDispatcherImpl();

        static volatile int count;

        static volatile long totalSize;

        static volatile long totalCapacity;

        private volatile long address;

        private final long size;

        private final int cap;

        private final FileDescriptor fd;

        private Unmapper(long address, long size, int cap, FileDescriptor fd) {
            assert (address != 0);
            this.address = address;
            this.size = size;
            this.cap = cap;
            this.fd = fd;
            synchronized (Unmapper.class) {
                count++;
                totalSize += size;
                totalCapacity += cap;
            }
        }

        public void run();
    }

    private static void unmap(MappedByteBuffer bb);

    private static final int MAP_RO = 0;

    private static final int MAP_RW = 1;

    private static final int MAP_PV = 2;

    public MappedByteBuffer map(MapMode mode, long position, long size) throws IOException;

    public static JavaNioAccess.BufferPool getMappedBufferPool();

    private volatile FileLockTable fileLockTable;

    private FileLockTable fileLockTable() throws IOException;

    public FileLock lock(long position, long size, boolean shared) throws IOException;

    public FileLock tryLock(long position, long size, boolean shared) throws IOException;

    void release(FileLockImpl fli) throws IOException;

    private native long map0(int prot, long position, long length) throws IOException;

    private static native int unmap0(long address, long length);

    private native long transferTo0(FileDescriptor src, long position, long count, FileDescriptor dst);

    private static native long initIDs();

    static {
        IOUtil.load();
        allocationGranularity = initIDs();
    }
}
