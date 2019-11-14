package java.util;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.SearchIndexFor;
import org.checkerframework.checker.interning.qual.PolyInterned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.util.ArraysSupport;
import java.lang.reflect.Array;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "index", "interning", "lock", "nullness", "signedness" })
public class Arrays {

    private static final int MIN_ARRAY_SORT_GRAN = 1 << 13;

    private Arrays() {
    }

    static final class NaturalOrder implements Comparator<Object> {

        @SuppressWarnings("unchecked")
        public int compare(Object first, Object second);

        static final NaturalOrder INSTANCE = new NaturalOrder();
    }

    static void rangeCheck(int arrayLength, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(int[] a);

    public static void sort(int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(long[] a);

    public static void sort(long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(short[] a);

    public static void sort(short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(char[] a);

    public static void sort(char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(byte[] a);

    public static void sort(byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(float[] a);

    public static void sort(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(double[] a);

    public static void sort(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(byte[] a);

    public static void parallelSort(byte[] a, int fromIndex, int toIndex);

    public static void parallelSort(char[] a);

    public static void parallelSort(char[] a, int fromIndex, int toIndex);

    public static void parallelSort(short[] a);

    public static void parallelSort(short[] a, int fromIndex, int toIndex);

    public static void parallelSort(int[] a);

    public static void parallelSort(int[] a, int fromIndex, int toIndex);

    public static void parallelSort(long[] a);

    public static void parallelSort(long[] a, int fromIndex, int toIndex);

    public static void parallelSort(float[] a);

    public static void parallelSort(float[] a, int fromIndex, int toIndex);

    public static void parallelSort(double[] a);

    public static void parallelSort(double[] a, int fromIndex, int toIndex);

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void parallelSort(T[] a);

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void parallelSort(T[] a, int fromIndex, int toIndex);

    @SuppressWarnings("unchecked")
    public static <T> void parallelSort(T[] a, @Nullable Comparator<? super T> cmp);

    @SuppressWarnings("unchecked")
    public static <T> void parallelSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> cmp);

    static final class LegacyMergeSort {

        private static final boolean userRequested = java.security.AccessController.doPrivileged(new sun.security.action.GetBooleanAction("java.util.Arrays.useLegacyMergeSort")).booleanValue();
    }

    public static void sort(@PolyInterned Object[] a);

    private static void legacyMergeSort(Object[] a);

    public static void sort(@PolyInterned Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    private static void legacyMergeSort(Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    private static final int INSERTIONSORT_THRESHOLD = 7;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void mergeSort(Object[] src, Object[] dest, int low, int high, int off);

    private static void swap(Object[] x, int a, int b);

    public static <T> void sort(T[] a, @Nullable Comparator<? super T> c);

    private static <T> void legacyMergeSort(T[] a, @Nullable Comparator<? super T> c);

    public static <T> void sort(T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, Comparator<? super T> c);

    private static <T> void legacyMergeSort(T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, Comparator<? super T> c);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void mergeSort(Object[] src, Object[] dest, int low, int high, int off, Comparator c);

    public static <T> void parallelPrefix(T[] array, BinaryOperator<T> op);

    public static <T> void parallelPrefix(T[] array, int fromIndex, int toIndex, BinaryOperator<T> op);

    public static void parallelPrefix(long[] array, LongBinaryOperator op);

    public static void parallelPrefix(long[] array, int fromIndex, int toIndex, LongBinaryOperator op);

    public static void parallelPrefix(double[] array, DoubleBinaryOperator op);

    public static void parallelPrefix(double[] array, int fromIndex, int toIndex, DoubleBinaryOperator op);

    public static void parallelPrefix(int[] array, IntBinaryOperator op);

    public static void parallelPrefix(int[] array, int fromIndex, int toIndex, IntBinaryOperator op);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(long[] a, long key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, long key);

    private static int binarySearch0(long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, long key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(int[] a, int key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, int key);

    @SearchIndexFor({ "#1" })
    private static int binarySearch0(int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, int key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(short[] a, short key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, short key);

    private static int binarySearch0(short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, short key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(char[] a, char key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, char key);

    private static int binarySearch0(char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, char key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(byte[] a, byte key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, byte key);

    private static int binarySearch0(byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, byte key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(double[] a, double key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, double key);

    private static int binarySearch0(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, double key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(float[] a, float key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, float key);

    private static int binarySearch0(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, float key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(@Nullable @PolyInterned Object[] a, @Nullable @PolyInterned Object key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(@Nullable @PolyInterned Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @Nullable @PolyInterned Object key);

    private static int binarySearch0(Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, Object key);

    @SearchIndexFor({ "#1" })
    public static <T> int binarySearch(T[] a, T key, @Nullable Comparator<? super T> c);

    @SearchIndexFor({ "#1" })
    public static <T> int binarySearch(T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, T key, @Nullable Comparator<? super T> c);

    private static <T> int binarySearch0(T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, T key, @Nullable Comparator<? super T> c);

    @Pure
    public static boolean equals(@PolySigned long @Nullable [] a, @PolySigned long @Nullable [] a2);

    public static boolean equals(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex);

    @Pure
    public static boolean equals(@PolySigned int @Nullable [] a, @PolySigned int @Nullable [] a2);

    public static boolean equals(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex);

    @Pure
    public static boolean equals(@PolySigned short @Nullable [] a, @PolySigned short @Nullable [] a2);

    public static boolean equals(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex);

    @Pure
    @HotSpotIntrinsicCandidate
    public static boolean equals(@PolySigned char @Nullable [] a, @PolySigned char @Nullable [] a2);

    public static boolean equals(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex);

    @Pure
    @HotSpotIntrinsicCandidate
    public static boolean equals(@PolySigned byte @Nullable [] a, @PolySigned byte @Nullable [] a2);

    public static boolean equals(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex);

    @Pure
    public static boolean equals(boolean @Nullable [] a, boolean @Nullable [] a2);

    public static boolean equals(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex);

    @Pure
    public static boolean equals(double @Nullable [] a, double @Nullable [] a2);

    public static boolean equals(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex);

    @Pure
    public static boolean equals(float @Nullable [] a, float @Nullable [] a2);

    public static boolean equals(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex);

    @Pure
    public static boolean equals(@PolyNull @PolyInterned Object @GuardSatisfied @Nullable [] a, @PolyNull @PolyInterned Object @GuardSatisfied @Nullable [] a2);

    public static boolean equals(Object[] a, int aFromIndex, int aToIndex, Object[] b, int bFromIndex, int bToIndex);

    public static <T> boolean equals(T[] a, T[] a2, Comparator<? super T> cmp);

    public static <T> boolean equals(T[] a, int aFromIndex, int aToIndex, T[] b, int bFromIndex, int bToIndex, Comparator<? super T> cmp);

    public static void fill(@PolySigned long[] a, @PolySigned long val);

    public static void fill(@PolySigned long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned long val);

    public static void fill(@PolySigned int[] a, @PolySigned int val);

    public static void fill(@PolySigned int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned int val);

    public static void fill(@PolySigned short[] a, @PolySigned short val);

    public static void fill(@PolySigned short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned short val);

    public static void fill(@PolySigned char[] a, @PolySigned char val);

    public static void fill(@PolySigned char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned char val);

    public static void fill(@PolySigned byte[] a, @PolySigned byte val);

    public static void fill(@PolySigned byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned byte val);

    public static void fill(boolean[] a, boolean val);

    public static void fill(boolean[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, boolean val);

    public static void fill(double[] a, double val);

    public static void fill(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, double val);

    public static void fill(float[] a, float val);

    public static void fill(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, float val);

    public static void fill(@PolyNull @PolyInterned Object[] a, @PolyNull @PolyInterned Object val);

    public static void fill(@PolyNull @PolyInterned Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolyNull @PolyInterned Object val);

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T[] copyOf(T[] original, @NonNegative int newLength);

    @HotSpotIntrinsicCandidate
    @Nullable
    public static <T, U> T[] copyOf(U[] original, @NonNegative int newLength, Class<? extends T[]> newType);

    @PolySigned
    public static byte[] copyOf(@PolySigned byte[] original, @NonNegative int newLength);

    @PolySigned
    public static short[] copyOf(@PolySigned short[] original, @NonNegative int newLength);

    @PolySigned
    public static int[] copyOf(@PolySigned int[] original, @NonNegative int newLength);

    @PolySigned
    public static long[] copyOf(@PolySigned long[] original, @NonNegative int newLength);

    @PolySigned
    public static char[] copyOf(@PolySigned char[] original, @NonNegative int newLength);

    public static float[] copyOf(float[] original, @NonNegative int newLength);

    public static double[] copyOf(double[] original, @NonNegative int newLength);

    public static boolean[] copyOf(boolean[] original, @NonNegative int newLength);

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T[] copyOfRange(T[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @HotSpotIntrinsicCandidate
    @Nullable
    public static <T, U> T[] copyOfRange(U[] original, @IndexOrHigh({ "#1" }) int from, int to, Class<? extends T[]> newType);

    @PolySigned
    public static byte[] copyOfRange(@PolySigned byte[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @PolySigned
    public static short[] copyOfRange(@PolySigned short[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @PolySigned
    public static int[] copyOfRange(@PolySigned int[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @PolySigned
    public static long[] copyOfRange(@PolySigned long[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @PolySigned
    public static char[] copyOfRange(@PolySigned char[] original, @IndexOrHigh({ "#1" }) int from, int to);

    public static float[] copyOfRange(float[] original, @IndexOrHigh({ "#1" }) int from, int to);

    public static double[] copyOfRange(double[] original, @IndexOrHigh({ "#1" }) int from, int to);

    public static boolean[] copyOfRange(boolean[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> asList(T... a);

    private static class ArrayList<E> extends AbstractList<E> implements RandomAccess, java.io.Serializable {

        private static final long serialVersionUID = -2764017481108945198L;

        private final E[] a;

        ArrayList(E[] array) {
            a = Objects.requireNonNull(array);
        }

        @Override
        @NonNegative
        public int size();

        @SideEffectFree
        @Override
        @PolyNull
        public Object[] toArray(Arrays.ArrayList<@PolyNull E> this);

        @SideEffectFree
        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        @Override
        public E get(int index);

        @Override
        public E set(int index, E element);

        @Override
        public int indexOf(Object o);

        @Override
        public boolean contains(Object o);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        @Override
        public Iterator<E> iterator();
    }

    private static class ArrayItr<E> implements Iterator<E> {

        private int cursor;

        private final E[] a;

        ArrayItr(E[] a) {
            this.a = a;
        }

        @Override
        public boolean hasNext();

        @Override
        public E next();
    }

    @Pure
    public static int hashCode(@PolySigned long @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned int @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned short @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned char @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned byte @Nullable [] a);

    @Pure
    public static int hashCode(boolean @Nullable [] a);

    @Pure
    public static int hashCode(float @Nullable [] a);

    @Pure
    public static int hashCode(double @Nullable [] a);

    @Pure
    public static int hashCode(@PolyNull @PolyInterned Object @GuardSatisfied @Nullable [] a);

    @Pure
    public static int deepHashCode(@PolyNull @PolyInterned Object @GuardSatisfied @Nullable [] a);

    private static int primitiveArrayHashCode(Object a, Class<?> cl);

    @Pure
    public static boolean deepEquals(@PolyNull @PolyInterned Object @GuardSatisfied @Nullable [] a1, @PolyNull @PolyInterned Object @GuardSatisfied @Nullable [] a2);

    static boolean deepEquals0(Object e1, Object e2);

    @SideEffectFree
    public static String toString(long @Nullable [] a);

    @SideEffectFree
    public static String toString(int @Nullable [] a);

    @SideEffectFree
    public static String toString(short @Nullable [] a);

    @SideEffectFree
    public static String toString(char @Nullable [] a);

    @SideEffectFree
    public static String toString(byte @Nullable [] a);

    @SideEffectFree
    public static String toString(boolean @Nullable [] a);

    @SideEffectFree
    public static String toString(float @Nullable [] a);

    @SideEffectFree
    public static String toString(double @Nullable [] a);

    @SideEffectFree
    public static String toString(@PolyNull @PolyInterned Object @Nullable [] a);

    @SideEffectFree
    public static String deepToString(@PolyNull @PolyInterned Object @Nullable [] a);

    private static void deepToString(Object[] a, StringBuilder buf, Set<Object[]> dejaVu);

    public static <T> void setAll(T[] array, IntFunction<? extends T> generator);

    public static <T> void parallelSetAll(T[] array, IntFunction<? extends T> generator);

    public static void setAll(int[] array, IntUnaryOperator generator);

    public static void parallelSetAll(int[] array, IntUnaryOperator generator);

    public static void setAll(long[] array, IntToLongFunction generator);

    public static void parallelSetAll(long[] array, IntToLongFunction generator);

    public static void setAll(double[] array, IntToDoubleFunction generator);

    public static void parallelSetAll(double[] array, IntToDoubleFunction generator);

    @SideEffectFree
    public static <T> Spliterator<T> spliterator(T[] array);

    @SideEffectFree
    public static <T> Spliterator<T> spliterator(T[] array, int startInclusive, int endExclusive);

    @SideEffectFree
    public static Spliterator.OfInt spliterator(int[] array);

    @SideEffectFree
    public static Spliterator.OfInt spliterator(int[] array, int startInclusive, int endExclusive);

    @SideEffectFree
    public static Spliterator.OfLong spliterator(long[] array);

    @SideEffectFree
    public static Spliterator.OfLong spliterator(long[] array, int startInclusive, int endExclusive);

    @SideEffectFree
    public static Spliterator.OfDouble spliterator(double[] array);

    @SideEffectFree
    public static Spliterator.OfDouble spliterator(double[] array, int startInclusive, int endExclusive);

    public static <T> Stream<T> stream(T[] array);

    public static <T> Stream<T> stream(T[] array, int startInclusive, int endExclusive);

    public static IntStream stream(int[] array);

    public static IntStream stream(int[] array, int startInclusive, int endExclusive);

    public static LongStream stream(long[] array);

    public static LongStream stream(long[] array, int startInclusive, int endExclusive);

    public static DoubleStream stream(double[] array);

    public static DoubleStream stream(double[] array, int startInclusive, int endExclusive);

    public static int compare(boolean[] a, boolean[] b);

    public static int compare(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex);

    public static int compare(byte[] a, byte[] b);

    public static int compare(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(byte[] a, byte[] b);

    public static int compareUnsigned(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex);

    public static int compare(short[] a, short[] b);

    public static int compare(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(short[] a, short[] b);

    public static int compareUnsigned(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex);

    public static int compare(char[] a, char[] b);

    public static int compare(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex);

    public static int compare(int[] a, int[] b);

    public static int compare(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(int[] a, int[] b);

    public static int compareUnsigned(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex);

    public static int compare(long[] a, long[] b);

    public static int compare(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(long[] a, long[] b);

    public static int compareUnsigned(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex);

    public static int compare(float[] a, float[] b);

    public static int compare(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex);

    public static int compare(double[] a, double[] b);

    public static int compare(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex);

    public static <T extends Comparable<? super T>> int compare(T[] a, T[] b);

    public static <T extends Comparable<? super T>> int compare(T[] a, int aFromIndex, int aToIndex, T[] b, int bFromIndex, int bToIndex);

    public static <T> int compare(T[] a, T[] b, Comparator<? super T> cmp);

    public static <T> int compare(T[] a, int aFromIndex, int aToIndex, T[] b, int bFromIndex, int bToIndex, Comparator<? super T> cmp);

    public static int mismatch(boolean[] a, boolean[] b);

    public static int mismatch(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex);

    public static int mismatch(byte[] a, byte[] b);

    public static int mismatch(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex);

    public static int mismatch(char[] a, char[] b);

    public static int mismatch(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex);

    public static int mismatch(short[] a, short[] b);

    public static int mismatch(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex);

    public static int mismatch(int[] a, int[] b);

    public static int mismatch(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex);

    public static int mismatch(long[] a, long[] b);

    public static int mismatch(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex);

    public static int mismatch(float[] a, float[] b);

    public static int mismatch(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex);

    public static int mismatch(double[] a, double[] b);

    public static int mismatch(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex);

    public static int mismatch(Object[] a, Object[] b);

    public static int mismatch(Object[] a, int aFromIndex, int aToIndex, Object[] b, int bFromIndex, int bToIndex);

    public static <T> int mismatch(T[] a, T[] b, Comparator<? super T> cmp);

    public static <T> int mismatch(T[] a, int aFromIndex, int aToIndex, T[] b, int bFromIndex, int bToIndex, Comparator<? super T> cmp);
}
