package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Random;
import jdk.internal.math.DoubleConsts;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public final class StrictMath {

    private StrictMath() {
    }

    public static final double E = 2.7182818284590452354;

    public static final double PI = 3.14159265358979323846;

    private static final double DEGREES_TO_RADIANS = 0.017453292519943295;

    private static final double RADIANS_TO_DEGREES = 57.29577951308232;

    public static native double sin(double a);

    public static native double cos(double a);

    public static native double tan(double a);

    public static native double asin(double a);

    public static native double acos(double a);

    public static native double atan(double a);

    public static strictfp double toRadians(double angdeg);

    public static strictfp double toDegrees(double angrad);

    public static double exp(double a);

    public static native double log(double a);

    public static native double log10(double a);

    @HotSpotIntrinsicCandidate
    public static native double sqrt(double a);

    public static double cbrt(double a);

    public static native double IEEEremainder(double f1, double f2);

    public static double ceil(double a);

    public static double floor(double a);

    private static double floorOrCeil(double a, double negativeBoundary, double positiveBoundary, double sign);

    public static double rint(double a);

    public static native double atan2(double y, double x);

    public static double pow(double a, double b);

    public static int round(float a);

    public static long round(double a);

    private static final class RandomNumberGeneratorHolder {

        static final Random randomNumberGenerator = new Random();
    }

    public static double random();

    public static int addExact(int x, int y);

    public static long addExact(long x, long y);

    public static int subtractExact(int x, int y);

    public static long subtractExact(long x, long y);

    public static int multiplyExact(int x, int y);

    public static long multiplyExact(long x, int y);

    public static long multiplyExact(long x, long y);

    public static int toIntExact(long value);

    public static long multiplyFull(int x, int y);

    public static long multiplyHigh(long x, long y);

    public static int floorDiv(int x, int y);

    public static long floorDiv(long x, int y);

    public static long floorDiv(long x, long y);

    public static int floorMod(int x, int y);

    public static int floorMod(long x, int y);

    public static long floorMod(long x, long y);

    public static int abs(int a);

    public static long abs(long a);

    public static float abs(float a);

    public static double abs(double a);

    @HotSpotIntrinsicCandidate
    public static int max(int a, int b);

    public static long max(long a, long b);

    public static float max(float a, float b);

    public static double max(double a, double b);

    @HotSpotIntrinsicCandidate
    public static int min(int a, int b);

    public static long min(long a, long b);

    public static float min(float a, float b);

    public static double min(double a, double b);

    public static double fma(double a, double b, double c);

    public static float fma(float a, float b, float c);

    public static double ulp(double d);

    public static float ulp(float f);

    public static double signum(double d);

    public static float signum(float f);

    public static native double sinh(double x);

    public static native double cosh(double x);

    public static native double tanh(double x);

    public static double hypot(double x, double y);

    public static native double expm1(double x);

    public static native double log1p(double x);

    public static double copySign(double magnitude, double sign);

    public static float copySign(float magnitude, float sign);

    public static int getExponent(float f);

    public static int getExponent(double d);

    public static double nextAfter(double start, double direction);

    public static float nextAfter(float start, double direction);

    public static double nextUp(double d);

    public static float nextUp(float f);

    public static double nextDown(double d);

    public static float nextDown(float f);

    public static double scalb(double d, int scaleFactor);

    public static float scalb(float f, int scaleFactor);
}
