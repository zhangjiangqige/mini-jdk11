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

    public static FileChannel open(FileDescriptor fd, String path, boolean readable, boolean writable, boolean direct, Object parent);

    public void setUninterruptible();

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

    public long transferTo(long position, long count, WritableByteChannel target) throws IOException;

    public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException;

    public int read(ByteBuffer dst, long position) throws IOException;

    public int write(ByteBuffer src, long position) throws IOException;

    public MappedByteBuffer map(MapMode mode, long position, long size) throws IOException;

    public static JavaNioAccess.BufferPool getMappedBufferPool();

    public FileLock lock(long position, long size, boolean shared) throws IOException;

    public FileLock tryLock(long position, long size, boolean shared) throws IOException;
}
