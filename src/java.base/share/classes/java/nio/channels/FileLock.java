package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.Objects;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FileLock implements AutoCloseable {

    protected FileLock(FileChannel channel, long position, long size, boolean shared) {
    }

    protected FileLock(AsynchronousFileChannel channel, long position, long size, boolean shared) {
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
