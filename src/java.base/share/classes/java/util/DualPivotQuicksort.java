package java.util;

import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
final class DualPivotQuicksort {

    private DualPivotQuicksort() {
    }

    private static final int MAX_RUN_COUNT = 67;

    private static final int QUICKSORT_THRESHOLD = 286;

    private static final int INSERTION_SORT_THRESHOLD = 47;

    private static final int COUNTING_SORT_THRESHOLD_FOR_BYTE = 29;

    private static final int COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR = 3200;

    static void sort(int[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, int[] work, int workBase, int workLen);

    private static void sort(int[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, boolean leftmost);

    static void sort(long[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, long[] work, int workBase, int workLen);

    private static void sort(long[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, boolean leftmost);

    static void sort(short[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, short[] work, int workBase, int workLen);

    private static final int NUM_SHORT_VALUES = 1 << 16;

    private static void doSort(short[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, short[] work, int workBase, int workLen);

    private static void sort(short[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, boolean leftmost);

    static void sort(char[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, char[] work, int workBase, int workLen);

    private static final int NUM_CHAR_VALUES = 1 << 16;

    private static void doSort(char[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, char[] work, int workBase, int workLen);

    private static void sort(char[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, boolean leftmost);

    private static final int NUM_BYTE_VALUES = 1 << 8;

    static void sort(byte[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right);

    static void sort(float[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, float[] work, int workBase, int workLen);

    private static void doSort(float[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, float[] work, int workBase, int workLen);

    private static void sort(float[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, boolean leftmost);

    static void sort(double[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, double[] work, int workBase, int workLen);

    private static void doSort(double[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, double[] work, int workBase, int workLen);

    private static void sort(double[] a, @IndexOrHigh({ "#1" }) int left, @IndexFor({ "#1" }) int right, boolean leftmost);
}
