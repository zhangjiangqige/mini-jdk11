package java.util;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
class ComparableTimSort {

    private static final int MIN_MERGE = 32;

    private final Object[] a;

    private static final int MIN_GALLOP = 7;

    private int minGallop = MIN_GALLOP;

    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;

    private Object[] tmp;

    private int tmpBase;

    private int tmpLen;

    private int stackSize = 0;

    private final int[] runBase;

    private final int[] runLen;

    private ComparableTimSort(Object[] a, Object[] work, int workBase, int workLen) {
        this.a = a;
        int len = a.length;
        int tlen = (len < 2 * INITIAL_TMP_STORAGE_LENGTH) ? len >>> 1 : INITIAL_TMP_STORAGE_LENGTH;
        if (work == null || workLen < tlen || workBase + tlen > work.length) {
            tmp = new Object[tlen];
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

    static void sort(Object[] a, @IndexOrHigh({ "#1" }) int lo, @IndexOrHigh({ "#1" }) int hi, Object[] work, int workBase, int workLen);

    @SuppressWarnings({ "fallthrough", "rawtypes", "unchecked" })
    private static void binarySort(Object[] a, int lo, int hi, int start);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static int countRunAndMakeAscending(Object[] a, int lo, int hi);

    private static void reverseRange(Object[] a, int lo, int hi);

    private static int minRunLength(int n);

    private void pushRun(int runBase, int runLen);

    private void mergeCollapse();

    private void mergeForceCollapse();

    @SuppressWarnings("unchecked")
    private void mergeAt(int i);

    private static int gallopLeft(Comparable<Object> key, Object[] a, int base, int len, int hint);

    private static int gallopRight(Comparable<Object> key, Object[] a, int base, int len, int hint);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void mergeLo(int base1, int len1, int base2, int len2);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void mergeHi(int base1, int len1, int base2, int len2);

    private Object[] ensureCapacity(int minCapacity);
}
