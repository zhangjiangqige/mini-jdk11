package java.lang;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.SignedPositive;
import org.checkerframework.checker.signedness.qual.SignednessGlb;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.checker.signedness.qual.Unsigned;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Native;
import java.util.Objects;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.misc.VM;
import static java.lang.String.COMPACT_STRINGS;
import static java.lang.String.LATIN1;
import static java.lang.String.UTF16;

@AnnotatedFor({ "nullness", "index", "signedness" })
public final class Integer extends Number implements Comparable<Integer> {

    @Native
    public static final int MIN_VALUE = 0x80000000;

    @Native
    @SignedPositive
    public static final int MAX_VALUE = 0x7fffffff;

    @SuppressWarnings("unchecked")
    public static final Class<Integer> TYPE = (Class<Integer>) Class.getPrimitiveClass("int");

    static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    @SideEffectFree
    public static String toString(int i, @Positive int radix);

    private static String toStringUTF16(int i, int radix);

    public static String toUnsignedString(@Unsigned int i, @Positive int radix);

    @SideEffectFree
    public static String toHexString(@Unsigned int i);

    @SideEffectFree
    public static String toOctalString(@Unsigned int i);

    @SideEffectFree
    public static String toBinaryString(@Unsigned int i);

    private static String toUnsignedString0(@Unsigned int val, @IntVal({ 1, 2, 3, 4 }) int shift);

    static void formatUnsignedInt(int val, int shift, char[] buf, int offset, int len);

    static void formatUnsignedInt(int val, int shift, byte[] buf, int offset, int len);

    private static void formatUnsignedIntUTF16(int val, int shift, byte[] buf, int offset, int len);

    static final byte[] DigitTens = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9' };

    static final byte[] DigitOnes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public static String toString(int i);

    public static String toUnsignedString(@Unsigned int i);

    static int getChars(int i, int index, byte[] buf);

    static final int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };

    static int stringSize(int x);

    @Pure
    public static int parseInt(String s, @Positive int radix) throws NumberFormatException;

    public static int parseInt(CharSequence s, int beginIndex, int endIndex, int radix) throws NumberFormatException;

    @Pure
    public static int parseInt(String s) throws NumberFormatException;

    @Unsigned
    public static int parseUnsignedInt(String s, @Positive int radix) throws NumberFormatException;

    public static int parseUnsignedInt(CharSequence s, int beginIndex, int endIndex, int radix) throws NumberFormatException;

    @Unsigned
    public static int parseUnsignedInt(String s) throws NumberFormatException;

    @SideEffectFree
    public static Integer valueOf(String s, @Positive int radix) throws NumberFormatException;

    @SideEffectFree
    public static Integer valueOf(String s) throws NumberFormatException;

    private static class IntegerCache {

        static final int low = -128;

        static final int high;

        static final Integer[] cache;

        static {
            int h = 127;
            String integerCacheHighPropValue = VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null) {
                try {
                    int i = parseInt(integerCacheHighPropValue);
                    i = Math.max(i, 127);
                    h = Math.min(i, Integer.MAX_VALUE - (-low) - 1);
                } catch (NumberFormatException nfe) {
                }
            }
            high = h;
            cache = new Integer[(high - low) + 1];
            int j = low;
            for (int k = 0; k < cache.length; k++) cache[k] = new Integer(j++);
            assert IntegerCache.high >= 127;
        }

        private IntegerCache() {
        }
    }

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public static Integer valueOf(@PolyIndex int i);

    private final int value;

    @SideEffectFree
    @Deprecated(since = "9")
    @PolyIndex
    public Integer(@PolyIndex int value) {
        this.value = value;
    }

    @SideEffectFree
    @Deprecated(since = "9")
    public Integer(String s) throws NumberFormatException {
        this.value = parseInt(s, 10);
    }

    @Pure
    @PolyIndex
    public byte byteValue(@PolyIndex Integer this);

    @Pure
    @PolyIndex
    public short shortValue(@PolyIndex Integer this);

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public int intValue(@PolyIndex Integer this);

    @Pure
    @PolyIndex
    public long longValue(@PolyIndex Integer this);

    @Pure
    public float floatValue();

    @Pure
    public double doubleValue();

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(int value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @SideEffectFree
    @Nullable
    public static Integer getInteger(@Nullable String nm);

    @SideEffectFree
    public static Integer getInteger(@Nullable String nm, int val);

    @SideEffectFree
    @PolyNull
    public static Integer getInteger(@Nullable String nm, @PolyNull Integer val);

    @SideEffectFree
    public static Integer decode(String nm) throws NumberFormatException;

    @Pure
    public int compareTo(Integer anotherInteger);

    public static int compare(int x, int y);

    public static int compareUnsigned(@Unsigned int x, @Unsigned int y);

    @SignedPositive
    public static long toUnsignedLong(int x);

    @Unsigned
    public static int divideUnsigned(@Unsigned int dividend, @Unsigned int divisor);

    @Unsigned
    public static int remainderUnsigned(@Unsigned int dividend, @Unsigned int divisor);

    @Native
    @SignedPositive
    public static final int SIZE = 32;

    @SignedPositive
    public static final int BYTES = SIZE / Byte.SIZE;

    @Pure
    public static int highestOneBit(@UnknownSignedness int i);

    @Pure
    public static int lowestOneBit(@UnknownSignedness int i);

    @Pure
    @HotSpotIntrinsicCandidate
    @NonNegative
    public static int numberOfLeadingZeros(@UnknownSignedness int i);

    @Pure
    @HotSpotIntrinsicCandidate
    @NonNegative
    public static int numberOfTrailingZeros(@UnknownSignedness int i);

    @Pure
    @HotSpotIntrinsicCandidate
    @NonNegative
    public static int bitCount(@UnknownSignedness int i);

    @Pure
    @PolySigned
    public static int rotateLeft(@PolySigned int i, int distance);

    @Pure
    @PolySigned
    public static int rotateRight(@PolySigned int i, int distance);

    @Pure
    @SignednessGlb
    public static int reverse(@PolySigned int i);

    @Pure
    @GTENegativeOne
    public static int signum(int i);

    @Pure
    @HotSpotIntrinsicCandidate
    @SignednessGlb
    public static int reverseBytes(@PolySigned int i);

    public static int sum(int a, int b);

    public static int max(int a, int b);

    public static int min(int a, int b);

    @Native
    private static final long serialVersionUID = 1360826667806852920L;
}
