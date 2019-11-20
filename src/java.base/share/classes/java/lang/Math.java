package java.lang;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.PolyLowerBound;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigDecimal;
import java.util.Random;
import jdk.internal.math.FloatConsts;
import jdk.internal.math.DoubleConsts;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public final class Math {

    public static final double E;

    public static final double PI;

    @Pure
    @HotSpotIntrinsicCandidate
    public static double sin(double a);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double cos(double a);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double tan(double a);

    @Pure
    public static double asin(double a);

    @Pure
    public static double acos(double a);

    @Pure
    public static double atan(double a);

    @Pure
    public static double toRadians(double angdeg);

    @Pure
    public static double toDegrees(double angrad);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double exp(double a);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double log(double a);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double log10(double a);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double sqrt(double a);

    @Pure
    public static double cbrt(double a);

    @Pure
    public static double IEEEremainder(double f1, double f2);

    @Pure
    public static double ceil(double a);

    @Pure
    public static double floor(double a);

    @Pure
    public static double rint(double a);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double atan2(double y, double x);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double pow(double a, double b);

    @Pure
    public static int round(float a);

    @Pure
    public static long round(double a);

    @Pure
    public static double random();

    @HotSpotIntrinsicCandidate
    public static int addExact(int x, int y);

    @HotSpotIntrinsicCandidate
    public static long addExact(long x, long y);

    @HotSpotIntrinsicCandidate
    public static int subtractExact(int x, int y);

    @HotSpotIntrinsicCandidate
    public static long subtractExact(long x, long y);

    @HotSpotIntrinsicCandidate
    public static int multiplyExact(int x, int y);

    public static long multiplyExact(long x, int y);

    @HotSpotIntrinsicCandidate
    public static long multiplyExact(long x, long y);

    @HotSpotIntrinsicCandidate
    public static int incrementExact(int a);

    @HotSpotIntrinsicCandidate
    public static long incrementExact(long a);

    @HotSpotIntrinsicCandidate
    public static int decrementExact(int a);

    @HotSpotIntrinsicCandidate
    public static long decrementExact(long a);

    @HotSpotIntrinsicCandidate
    public static int negateExact(int a);

    @HotSpotIntrinsicCandidate
    public static long negateExact(long a);

    public static int toIntExact(long value);

    public static long multiplyFull(int x, int y);

    @HotSpotIntrinsicCandidate
    public static long multiplyHigh(long x, long y);

    public static int floorDiv(int x, int y);

    public static long floorDiv(long x, int y);

    public static long floorDiv(long x, long y);

    public static int floorMod(int x, int y);

    public static int floorMod(long x, int y);

    public static long floorMod(long x, long y);

    @Pure
    @NonNegative
    public static int abs(int a);

    @Pure
    @NonNegative
    public static long abs(long a);

    @Pure
    public static float abs(float a);

    @Pure
    @HotSpotIntrinsicCandidate
    public static double abs(double a);

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyUpperBound
    public static int max(@PolyUpperBound int a, @PolyUpperBound int b);

    @Pure
    @PolyUpperBound
    public static long max(@PolyUpperBound long a, @PolyUpperBound long b);

    @Pure
    public static float max(float a, float b);

    @Pure
    public static double max(double a, double b);

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyLowerBound
    public static int min(@PolyLowerBound int a, @PolyLowerBound int b);

    @Pure
    @PolyLowerBound
    public static long min(@PolyLowerBound long a, @PolyLowerBound long b);

    @Pure
    public static float min(float a, float b);

    @Pure
    public static double min(double a, double b);

    @HotSpotIntrinsicCandidate
    public static double fma(double a, double b, double c);

    @HotSpotIntrinsicCandidate
    public static float fma(float a, float b, float c);

    @Pure
    public static double ulp(double d);

    @Pure
    public static float ulp(float f);

    @Pure
    public static double signum(double d);

    @Pure
    public static float signum(float f);

    @Pure
    public static double sinh(double x);

    @Pure
    public static double cosh(double x);

    @Pure
    public static double tanh(double x);

    @Pure
    public static double hypot(double x, double y);

    @Pure
    public static double expm1(double x);

    @Pure
    public static double log1p(double x);

    @Pure
    public static double copySign(double magnitude, double sign);

    @Pure
    public static float copySign(float magnitude, float sign);

    @Pure
    public static int getExponent(float f);

    @Pure
    public static int getExponent(double d);

    @Pure
    public static double nextAfter(double start, double direction);

    @Pure
    public static float nextAfter(float start, double direction);

    @Pure
    public static double nextUp(double d);

    @Pure
    public static float nextUp(float f);

    public static double nextDown(double d);

    public static float nextDown(float f);

    @Pure
    public static double scalb(double d, int scaleFactor);

    @Pure
    public static float scalb(float f, int scaleFactor);
}
