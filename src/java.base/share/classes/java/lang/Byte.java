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

    public static final byte MIN_VALUE;

    @Positive
    public static final byte MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static final Class<Byte> TYPE;

    public static String toString(byte b);

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

    @Deprecated()
    @PolyIndex
    public Byte(@PolyIndex byte value) {
    }

    @Deprecated()
    public Byte(String s) throws NumberFormatException {
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
    public static final int SIZE;

    public static final int BYTES;
}
