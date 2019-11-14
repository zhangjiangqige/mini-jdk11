package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.math.FloatingDecimal;
import jdk.internal.math.DoubleConsts;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "nullness", "index" })
public final class Double extends Number implements Comparable<Double> {

    public static final double POSITIVE_INFINITY = 1.0 / 0.0;

    public static final double NEGATIVE_INFINITY = -1.0 / 0.0;

    public static final double NaN = 0.0d / 0.0;

    public static final double MAX_VALUE = 0x1.fffffffffffffP+1023;

    public static final double MIN_NORMAL = 0x1.0p-1022;

    public static final double MIN_VALUE = 0x0.0000000000001P-1022;

    public static final int MAX_EXPONENT = 1023;

    public static final int MIN_EXPONENT = -1022;

    public static final int SIZE = 64;

    public static final int BYTES = SIZE / Byte.SIZE;

    @SuppressWarnings("unchecked")
    public static final Class<Double> TYPE = (Class<Double>) Class.getPrimitiveClass("double");

    @SideEffectFree
    public static String toString(double d);

    @SideEffectFree
    public static String toHexString(double d);

    @SideEffectFree
    public static Double valueOf(String s) throws NumberFormatException;

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public static Double valueOf(double d);

    @Pure
    public static double parseDouble(String s) throws NumberFormatException;

    @Pure
    public static boolean isNaN(double v);

    @Pure
    public static boolean isInfinite(double v);

    public static boolean isFinite(double d);

    private final double value;

    @Deprecated(since = "9")
    public Double(double value) {
        this.value = value;
    }

    @Deprecated(since = "9")
    public Double(String s) throws NumberFormatException {
        value = parseDouble(s);
    }

    @Pure
    public boolean isNaN();

    @Pure
    public boolean isInfinite();

    @SideEffectFree
    public String toString();

    @Pure
    public byte byteValue();

    @Pure
    public short shortValue();

    @Pure
    public int intValue();

    @Pure
    public long longValue();

    @Pure
    public float floatValue();

    @Pure
    @HotSpotIntrinsicCandidate
    public double doubleValue();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(double value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    @HotSpotIntrinsicCandidate
    public static long doubleToLongBits(double value);

    @Pure
    @HotSpotIntrinsicCandidate
    public static native long doubleToRawLongBits(double value);

    @Pure
    @HotSpotIntrinsicCandidate
    public static native double longBitsToDouble(long bits);

    @Pure
    public int compareTo(Double anotherDouble);

    @Pure
    public static int compare(double d1, double d2);

    public static double sum(double a, double b);

    public static double max(double a, double b);

    public static double min(double a, double b);

    private static final long serialVersionUID = -9172774392245257468L;
}
