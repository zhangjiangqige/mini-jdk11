package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.Objects;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FileLock implements AutoCloseable {

    private final Channel channel;

    private final long position;

    private final long size;

    private final boolean shared;

    protected FileLock(FileChannel channel, long position, long size, boolean shared) {
        Objects.requireNonNull(channel, "Null channel");
        if (position < 0)
            throw new IllegalArgumentException("Negative position");
        if (size < 0)
            throw new IllegalArgumentException("Negative size");
        if (position + size < 0)
            throw new IllegalArgumentException("Negative position + size");
        this.channel = channel;
        this.position = position;
        this.size = size;
        this.shared = shared;
    }

    protected FileLock(AsynchronousFileChannel channel, long position, long size, boolean shared) {
        Objects.requireNonNull(channel, "Null channel");
        if (position < 0)
            throw new IllegalArgumentException("Negative position");
        if (size < 0)
            throw new IllegalArgumentException("Negative size");
        if (position + size < 0)
            throw new IllegalArgumentException("Negative position + size");
        this.channel = channel;
        this.position = position;
        this.size = size;
        this.shared = shared;
    }

    public final FileChannel channel();

    public Channel acquiredBy();

    public final long position();

    public final long size();

    public final boolean isShared();

    public final boolean overlaps(long position, long size);

    public abstract boolean isValid();

    public abstract void release() throws IOException;

    public final void close() throws IOException;

    public final String toString();
}
