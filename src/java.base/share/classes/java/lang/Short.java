package java.lang;

import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "nullness", "index" })
public final class Short extends Number implements Comparable<Short> {

    public static final short MIN_VALUE = -32768;

    @Positive
    public static final short MAX_VALUE = 32767;

    @SuppressWarnings("unchecked")
    public static final Class<Short> TYPE = (Class<Short>) Class.getPrimitiveClass("short");

    @SideEffectFree
    public static String toString(short s);

    @Pure
    public static short parseShort(String s, @Positive int radix) throws NumberFormatException;

    @Pure
    public static short parseShort(String s) throws NumberFormatException;

    @SideEffectFree
    public static Short valueOf(String s, @Positive int radix) throws NumberFormatException;

    @SideEffectFree
    public static Short valueOf(String s) throws NumberFormatException;

    private static class ShortCache {

        private ShortCache() {
        }

        static final Short[] cache = new Short[-(-128) + 127 + 1];

        static {
            for (int i = 0; i < cache.length; i++) cache[i] = new Short((short) (i - 128));
        }
    }

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public static Short valueOf(@PolyIndex short s);

    @SideEffectFree
    public static Short decode(String nm) throws NumberFormatException;

    private final short value;

    @SideEffectFree
    @Deprecated(since = "9")
    @PolyIndex
    public Short(@PolyIndex short value) {
        this.value = value;
    }

    @SideEffectFree
    @Deprecated(since = "9")
    public Short(String s) throws NumberFormatException {
        this.value = parseShort(s, 10);
    }

    @Pure
    @PolyIndex
    public byte byteValue(@PolyIndex Short this);

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public short shortValue(@PolyIndex Short this);

    @Pure
    @PolyIndex
    public int intValue(@PolyIndex Short this);

    @Pure
    @PolyIndex
    public long longValue(@PolyIndex Short this);

    @Pure
    public float floatValue();

    @Pure
    public double doubleValue();

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(short value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    public int compareTo(Short anotherShort);

    public static int compare(short x, short y);

    public static int compareUnsigned(short x, short y);

    @Positive
    public static final int SIZE = 16;

    public static final int BYTES = SIZE / Byte.SIZE;

    @Pure
    @HotSpotIntrinsicCandidate
    public static short reverseBytes(short i);

    public static int toUnsignedInt(short x);

    public static long toUnsignedLong(short x);

    private static final long serialVersionUID = 7515723908773894738L;
}
