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

    static final Unsafe UNSAFE = Unsafe.getUnsafe();

    static final int SPLITERATOR_CHARACTERISTICS = Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.ORDERED;

    private int mark = -1;

    private int position = 0;

    private int limit;

    private int capacity;

    long address;

    Buffer(@GTENegativeOne @LessThan({ "#2 + 1" }) int mark, @LessThan({ "#3 + 1" }) @NonNegative int pos, @LessThan({ "#4 + 1" }) @NonNegative int lim, @NonNegative int cap) {
        if (cap < 0)
            throw createCapacityException(cap);
        this.capacity = cap;
        limit(lim);
        position(pos);
        if (mark >= 0) {
            if (mark > pos)
                throw new IllegalArgumentException("mark > position: (" + mark + " > " + pos + ")");
            this.mark = mark;
        }
    }

    static IllegalArgumentException createSameBufferException();

    static IllegalArgumentException createCapacityException(int capacity);

    @NonNegative
    public final int capacity();

    @NonNegative
    public final int position();

    public Buffer position(@NonNegative int newPosition);

    private IllegalArgumentException createPositionException(int newPosition);

    @NonNegative
    public final int limit();

    public Buffer limit(@NonNegative int newLimit);

    private IllegalArgumentException createLimitException(int newLimit);

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

    abstract Object base();

    final int nextGetIndex();

    final int nextGetIndex(int nb);

    final int nextPutIndex();

    final int nextPutIndex(int nb);

    @HotSpotIntrinsicCandidate
    final int checkIndex(int i);

    final int checkIndex(int i, int nb);

    @GTENegativeOne
    final int markValue();

    final void truncate();

    final void discardMark();

    static void checkBounds(int off, int len, int size);

    static {
        SharedSecrets.setJavaNioAccess(new JavaNioAccess() {

            @Override
            public JavaNioAccess.BufferPool getDirectBufferPool() {
                return Bits.BUFFER_POOL;
            }

            @Override
            public ByteBuffer newDirectByteBuffer(long addr, int cap, Object ob) {
                return new DirectByteBuffer(addr, cap, ob);
            }

            @Override
            public void truncate(Buffer buf) {
                buf.truncate();
            }
        });
    }
}
