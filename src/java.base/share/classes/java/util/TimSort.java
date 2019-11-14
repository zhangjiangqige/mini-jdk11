package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
class TimSort<T> {

    private static final int MIN_MERGE = 32;

    private final T[] a;

    private final Comparator<? super T> c;

    private static final int MIN_GALLOP = 7;

    private int minGallop = MIN_GALLOP;

    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;

    private T[] tmp;

    private int tmpBase;

    private int tmpLen;

    private int stackSize = 0;

    private final int[] runBase;

    private final int[] runLen;

    private TimSort(T[] a, Comparator<? super T> c, T[] work, int workBase, int workLen) {
        this.a = a;
        this.c = c;
        int len = a.length;
        int tlen = (len < 2 * INITIAL_TMP_STORAGE_LENGTH) ? len >>> 1 : INITIAL_TMP_STORAGE_LENGTH;
        if (work == null || workLen < tlen || workBase + tlen > work.length) {
            @SuppressWarnings({ "unchecked", "UnnecessaryLocalVariable" })
            T[] newArray = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), tlen);
            tmp = newArray;
            tmpBase = 0;
            tmpLen = tlen;
        } else {
            tmp = work;
            tmpBase = workBase;
            tmpLen = workLen;
        }
        int stackLen = (len < 120 ? 5 : len < 1542 ? 10 : len < 119151 ? 24 : 49);
        runBase = new int[stackLen];
        runLen = new int[stackLen];
    }

    static <T> void sort(T[] a, int lo, int hi, Comparator<? super T> c, T[] work, int workBase, int workLen);

    @SuppressWarnings("fallthrough")
    private static <T> void binarySort(T[] a, int lo, int hi, int start, Comparator<? super T> c);

    private static <T> int countRunAndMakeAscending(T[] a, int lo, int hi, Comparator<? super T> c);

    private static void reverseRange(Object[] a, int lo, int hi);

    private static int minRunLength(int n);

    private void pushRun(int runBase, int runLen);

    private void mergeCollapse();

    private void mergeForceCollapse();

    private void mergeAt(int i);

    private static <T> int gallopLeft(T key, T[] a, int base, int len, int hint, Comparator<? super T> c);

    private static <T> int gallopRight(T key, T[] a, int base, int len, int hint, Comparator<? super T> c);

    private void mergeLo(int base1, int len1, int base2, int len2);

    private void mergeHi(int base1, int len1, int base2, int len2);

    private T[] ensureCapacity(int minCapacity);
}
