package java.nio.channels;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.ByteBuffer;
import java.io.IOException;

@AnnotatedFor({ "index" })
public interface SeekableByteChannel extends ByteChannel {

    @Override
    @GTENegativeOne
    int read(ByteBuffer dst) throws IOException;

    @Override
    int write(ByteBuffer src) throws IOException;

    @NonNegative
    long position() throws IOException;

    SeekableByteChannel position(@NonNegative long newPosition) throws IOException;

    @NonNegative
    long size() throws IOException;

    SeekableByteChannel truncate(@NonNegative long size) throws IOException;
}
