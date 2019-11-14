package java.nio.channels;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

@AnnotatedFor({ "nullness", "index" })
public abstract class FileChannel extends AbstractInterruptibleChannel implements SeekableByteChannel, GatheringByteChannel, ScatteringByteChannel {

    protected FileChannel() {
    }

    public static FileChannel open(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static final FileAttribute<?>[] NO_ATTRIBUTES = new FileAttribute[0];

    public static FileChannel open(Path path, OpenOption... options) throws IOException;

    @GTENegativeOne
    public abstract int read(ByteBuffer dst) throws IOException;

    @GTENegativeOne
    public abstract long read(ByteBuffer[] dsts, @IndexOrHigh({ "#1" }) int offset, @IndexOrHigh({ "#1" }) int length) throws IOException;

    @GTENegativeOne
    public final long read(ByteBuffer[] dsts) throws IOException;

    @NonNegative
    public abstract int write(ByteBuffer src) throws IOException;

    @NonNegative
    public abstract long write(ByteBuffer[] srcs, @IndexOrHigh({ "#1" }) int offset, @IndexOrHigh({ "#1" }) int length) throws IOException;

    @NonNegative
    public final long write(ByteBuffer[] srcs) throws IOException;

    @NonNegative
    public abstract long position() throws IOException;

    public abstract FileChannel position(@NonNegative long newPosition) throws IOException;

    @NonNegative
    public abstract long size() throws IOException;

    public abstract FileChannel truncate(@NonNegative long size) throws IOException;

    public abstract void force(boolean metaData) throws IOException;

    @NonNegative
    public abstract long transferTo(@NonNegative long position, @NonNegative long count, WritableByteChannel target) throws IOException;

    @NonNegative
    public abstract long transferFrom(ReadableByteChannel src, @NonNegative long position, @NonNegative long count) throws IOException;

    @GTENegativeOne
    public abstract int read(ByteBuffer dst, @NonNegative long position) throws IOException;

    @NonNegative
    public abstract int write(ByteBuffer src, @NonNegative long position) throws IOException;

    public static class MapMode {

        public static final MapMode READ_ONLY = new MapMode("READ_ONLY");

        public static final MapMode READ_WRITE = new MapMode("READ_WRITE");

        public static final MapMode PRIVATE = new MapMode("PRIVATE");

        private final String name;

        private MapMode(String name) {
            this.name = name;
        }

        public String toString();
    }

    public abstract MappedByteBuffer map(MapMode mode, @NonNegative long position, @NonNegative long size) throws IOException;

    public abstract FileLock lock(@NonNegative long position, @NonNegative long size, boolean shared) throws IOException;

    public final FileLock lock() throws IOException;

    @Nullable
    public abstract FileLock tryLock(@NonNegative long position, @NonNegative long size, boolean shared) throws IOException;

    @Nullable
    public final FileLock tryLock() throws IOException;
}
