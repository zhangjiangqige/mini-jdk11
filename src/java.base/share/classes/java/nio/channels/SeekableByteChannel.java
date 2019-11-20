package java.nio.channels;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.ByteBuffer;
import java.io.IOException;

@AnnotatedFor({ "index" })
public interface SeekableByteChannel extends ByteChannel {
}
