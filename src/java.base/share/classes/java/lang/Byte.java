package java.lang;

import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "index", "interning", "nullness" })
public final class Byte extends Number implements Comparable<Byte> {

    public static final byte MIN_VALUE = -128;

    @Positive
    public static final byte MAX_VALUE = 127;

    @SuppressWarnings("unchecked")
    public static final Class<Byte> TYPE = (Class<Byte>) Class.getPrimitiveClass("byte");

    public static String toString(byte b);

    private static class ByteCache {

        private ByteCache() {
        }

        static final Byte[] cache = new Byte[-(-128) + 127 + 1];

        static {
            for (int i = 0; i < cache.length; i++) cache[i] = new Byte((byte) (i - 128));
        }
    }

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    @Interned
    public static Byte valueOf(@PolyIndex byte b);

    @Pure
    public static byte parseByte(String s, @Positive int radix) throws NumberFormatException;

    @Pure
    public static byte parseByte(String s) throws NumberFormatException;

    @Pure
    @Interned
    public static Byte valueOf(String s, @Positive int radix) throws NumberFormatException;

    @Pure
    @Interned
    public static Byte valueOf(String s) throws NumberFormatException;

    @Pure
    public static Byte decode(String nm) throws NumberFormatException;

    private final byte value;

    @Deprecated(since = "9")
    @PolyIndex
    public Byte(@PolyIndex byte value) {
        this.value = value;
    }

    @Deprecated(since = "9")
    public Byte(String s) throws NumberFormatException {
        this.value = parseByte(s, 10);
    }

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public byte byteValue(@PolyIndex Byte this);

    @Pure
    @PolyIndex
    public short shortValue(@PolyIndex Byte this);

    @Pure
    @PolyIndex
    public int intValue(@PolyIndex Byte this);

    @Pure
    @PolyIndex
    public long longValue(@PolyIndex Byte this);

    @Pure
    public float floatValue();

    @Pure
    public double doubleValue();

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(byte value);

    public boolean equals(@Nullable Object obj);

    public int compareTo(Byte anotherByte);

    public static int compare(byte x, byte y);

    public static int compareUnsigned(byte x, byte y);

    public static int toUnsignedInt(byte x);

    public static long toUnsignedLong(byte x);

    @Positive
    public static final int SIZE = 8;

    public static final int BYTES = SIZE / Byte.SIZE;

    private static final long serialVersionUID = -7183698231559129828L;
}
