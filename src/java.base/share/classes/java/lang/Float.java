package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.math.FloatingDecimal;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "nullness" })
public final class Float extends Number implements Comparable<Float> {

    public static final float POSITIVE_INFINITY = 1.0f / 0.0f;

    public static final float NEGATIVE_INFINITY = -1.0f / 0.0f;

    public static final float NaN = 0.0f / 0.0f;

    public static final float MAX_VALUE = 0x1.fffffeP+127f;

    public static final float MIN_NORMAL = 0x1.0p-126f;

    public static final float MIN_VALUE = 0x0.000002P-126f;

    public static final int MAX_EXPONENT = 127;

    public static final int MIN_EXPONENT = -126;

    public static final int SIZE = 32;

    public static final int BYTES = SIZE / Byte.SIZE;

    @SuppressWarnings("unchecked")
    public static final Class<Float> TYPE = (Class<Float>) Class.getPrimitiveClass("float");

    @SideEffectFree
    public static String toString(float f);

    @SideEffectFree
    public static String toHexString(float f);

    @SideEffectFree
    public static Float valueOf(String s) throws NumberFormatException;

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public static Float valueOf(float f);

    @Pure
    public static float parseFloat(String s) throws NumberFormatException;

    @Pure
    public static boolean isNaN(float v);

    @Pure
    public static boolean isInfinite(float v);

    public static boolean isFinite(float f);

    private final float value;

    @Deprecated(since = "9")
    public Float(float value) {
        this.value = value;
    }

    @Deprecated(since = "9")
    public Float(double value) {
        this.value = (float) value;
    }

    @Deprecated(since = "9")
    public Float(String s) throws NumberFormatException {
        value = parseFloat(s);
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
    @HotSpotIntrinsicCandidate
    public float floatValue();

    @Pure
    public double doubleValue();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(float value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    @HotSpotIntrinsicCandidate
    public static int floatToIntBits(float value);

    @HotSpotIntrinsicCandidate
    public static native int floatToRawIntBits(float value);

    @HotSpotIntrinsicCandidate
    public static native float intBitsToFloat(int bits);

    @Pure
    public int compareTo(Float anotherFloat);

    @Pure
    public static int compare(float f1, float f2);

    public static float sum(float a, float b);

    public static float max(float a, float b);

    public static float min(float a, float b);

    private static final long serialVersionUID = -2671257302660747028L;
}
