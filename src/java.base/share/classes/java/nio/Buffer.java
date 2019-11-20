package java.nio;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.misc.JavaNioAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.Unsafe;
import java.util.Spliterator;

@AnnotatedFor({ "index" })
public abstract class Buffer {

    @NonNegative
    public final int capacity();

    @NonNegative
    public final int position();

    public Buffer position(@NonNegative int newPosition);

    @NonNegative
    public final int limit();

    public Buffer limit(@NonNegative int newLimit);

    public Buffer mark();

    public Buffer reset();

    public Buffer clear();

    public Buffer flip();

    public Buffer rewind();

    @NonNegative
    public final int remaining();

    public final boolean hasRemaining();

    public abstract boolean isReadOnly();

    public abstract boolean hasArray();

    public abstract Object array();

    @NonNegative
    public abstract int arrayOffset();

    public abstract boolean isDirect();

    public abstract Buffer slice();

    public abstract Buffer duplicate();
}
