package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.math.FloatingDecimal;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "nullness" })
public final class Float extends Number implements Comparable<Float> {

    public static final float POSITIVE_INFINITY;

    public static final float NEGATIVE_INFINITY;

    public static final float NaN;

    public static final float MAX_VALUE;

    public static final float MIN_NORMAL;

    public static final float MIN_VALUE;

    public static final int MAX_EXPONENT;

    public static final int MIN_EXPONENT;

    public static final int SIZE;

    public static final int BYTES;

    @SuppressWarnings("unchecked")
    public static final Class<Float> TYPE;

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

    @Deprecated()
    public Float(float value) {
    }

    @Deprecated()
    public Float(double value) {
    }

    @Deprecated()
    public Float(String s) throws NumberFormatException {
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
}
