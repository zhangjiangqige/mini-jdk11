package java.math;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.math.BigDecimal.INFLATED;
import static java.math.BigInteger.LONG_MASK;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class MutableBigInteger {

    int[] value;

    int intLen;

    int offset = 0;

    static final MutableBigInteger ONE = new MutableBigInteger(1);

    static final int KNUTH_POW2_THRESH_LEN = 6;

    static final int KNUTH_POW2_THRESH_ZEROS = 3;

    MutableBigInteger() {
        value = new int[1];
        intLen = 0;
    }

    MutableBigInteger(int val) {
        value = new int[1];
        intLen = 1;
        value[0] = val;
    }

    MutableBigInteger(int[] val) {
        value = val;
        intLen = val.length;
    }

    MutableBigInteger(BigInteger b) {
        intLen = b.mag.length;
        value = Arrays.copyOf(b.mag, intLen);
    }

    MutableBigInteger(MutableBigInteger val) {
        intLen = val.intLen;
        value = Arrays.copyOfRange(val.value, val.offset, val.offset + intLen);
    }

    private void ones(int n);

    private int[] getMagnitudeArray();

    private long toLong();

    BigInteger toBigInteger(int sign);

    BigInteger toBigInteger();

    BigDecimal toBigDecimal(int sign, int scale);

    long toCompactValue(int sign);

    void clear();

    void reset();

    final int compare(MutableBigInteger b);

    private int compareShifted(MutableBigInteger b, int ints);

    final int compareHalf(MutableBigInteger b);

    private final int getLowestSetBit();

    private final int getInt(int index);

    private final long getLong(int index);

    final void normalize();

    private final void ensureCapacity(int len);

    int[] toIntArray();

    void setInt(int index, int val);

    void setValue(int[] val, int length);

    void copyValue(MutableBigInteger src);

    void copyValue(int[] val);

    boolean isOne();

    boolean isZero();

    boolean isEven();

    boolean isOdd();

    boolean isNormal();

    public String toString();

    void safeRightShift(int n);

    void rightShift(int n);

    void safeLeftShift(int n);

    void leftShift(int n);

    private int divadd(int[] a, int[] result, int offset);

    private int mulsub(int[] q, int[] a, int x, int len, int offset);

    private int mulsubBorrow(int[] q, int[] a, int x, int len, int offset);

    private final void primitiveRightShift(int n);

    private final void primitiveLeftShift(int n);

    private BigInteger getLower(int n);

    private void keepLower(int n);

    void add(MutableBigInteger addend);

    void addShifted(MutableBigInteger addend, int n);

    void addDisjoint(MutableBigInteger addend, int n);

    void addLower(MutableBigInteger addend, int n);

    int subtract(MutableBigInteger b);

    private int difference(MutableBigInteger b);

    void multiply(MutableBigInteger y, MutableBigInteger z);

    void mul(int y, MutableBigInteger z);

    int divideOneWord(int divisor, MutableBigInteger quotient);

    MutableBigInteger divide(MutableBigInteger b, MutableBigInteger quotient);

    MutableBigInteger divide(MutableBigInteger b, MutableBigInteger quotient, boolean needRemainder);

    MutableBigInteger divideKnuth(MutableBigInteger b, MutableBigInteger quotient);

    MutableBigInteger divideKnuth(MutableBigInteger b, MutableBigInteger quotient, boolean needRemainder);

    MutableBigInteger divideAndRemainderBurnikelZiegler(MutableBigInteger b, MutableBigInteger quotient);

    private MutableBigInteger divide2n1n(MutableBigInteger b, MutableBigInteger quotient);

    private MutableBigInteger divide3n2n(MutableBigInteger b, MutableBigInteger quotient);

    private MutableBigInteger getBlock(int index, int numBlocks, int blockLength);

    long bitLength();

    long divide(long v, MutableBigInteger quotient);

    private static void copyAndShift(int[] src, int srcFrom, int srcLen, int[] dst, int dstFrom, int shift);

    private MutableBigInteger divideMagnitude(MutableBigInteger div, MutableBigInteger quotient, boolean needRemainder);

    private MutableBigInteger divideLongMagnitude(long ldivisor, MutableBigInteger quotient);

    private int divaddLong(int dh, int dl, int[] result, int offset);

    private int mulsubLong(int[] q, int dh, int dl, int x, int offset);

    private boolean unsignedLongCompare(long one, long two);

    static long divWord(long n, int d);

    MutableBigInteger sqrt();

    MutableBigInteger hybridGCD(MutableBigInteger b);

    private MutableBigInteger binaryGCD(MutableBigInteger v);

    static int binaryGcd(int a, int b);

    MutableBigInteger mutableModInverse(MutableBigInteger p);

    MutableBigInteger modInverseMP2(int k);

    static int inverseMod32(int val);

    static long inverseMod64(long val);

    static MutableBigInteger modInverseBP2(MutableBigInteger mod, int k);

    private MutableBigInteger modInverse(MutableBigInteger mod);

    static MutableBigInteger fixup(MutableBigInteger c, MutableBigInteger p, int k);

    MutableBigInteger euclidModInverse(int k);
}
