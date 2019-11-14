package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;
import java.util.function.Predicate;
import jdk.internal.misc.SharedSecrets;

@CFComment({ "lock/nullness: This class doesn't permits null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
@SuppressWarnings("unchecked")
public class PriorityQueue<E extends @NonNull Object> extends AbstractQueue<E> implements java.io.Serializable {

    private static final long serialVersionUID = -7720805057305804111L;

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    transient Object[] queue;

    int size;

    private final Comparator<? super E> comparator;

    transient int modCount;

    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public PriorityQueue(@Positive int initialCapacity) {
        this(initialCapacity, null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public PriorityQueue(@Positive int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    public PriorityQueue(Collection<? extends E> c) {
        if (c instanceof SortedSet<?>) {
            SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
            this.comparator = (Comparator<? super E>) ss.comparator();
            initElementsFromCollection(ss);
        } else if (c instanceof PriorityQueue<?>) {
            PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;
            this.comparator = (Comparator<? super E>) pq.comparator();
            initFromPriorityQueue(pq);
        } else {
            this.comparator = null;
            initFromCollection(c);
        }
    }

    public PriorityQueue(PriorityQueue<? extends E> c) {
        this.comparator = (Comparator<? super E>) c.comparator();
        initFromPriorityQueue(c);
    }

    public PriorityQueue(SortedSet<? extends E> c) {
        this.comparator = (Comparator<? super E>) c.comparator();
        initElementsFromCollection(c);
    }

    private static Object[] ensureNonEmpty(Object[] es);

    private void initFromPriorityQueue(PriorityQueue<? extends E> c);

    private void initElementsFromCollection(Collection<? extends E> c);

    private void initFromCollection(Collection<? extends E> c);

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity);

    private static int hugeCapacity(int minCapacity);

    public boolean add(@GuardSatisfied PriorityQueue<E> this, E e);

    public boolean offer(E e);

    @Nullable
    public E peek(@GuardSatisfied PriorityQueue<E> this);

    private int indexOf(Object o);

    public boolean remove(@GuardSatisfied PriorityQueue<E> this, @Nullable Object o);

    void removeEq(Object o);

    @Pure
    public boolean contains(@GuardSatisfied PriorityQueue<E> this, @GuardSatisfied @Nullable Object o);

    @SideEffectFree
    public Object[] toArray();

    @SideEffectFree
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @SideEffectFree
    public Iterator<E> iterator();

    private final class Itr implements Iterator<E> {

        private int cursor;

        private int lastRet = -1;

        private ArrayDeque<E> forgetMeNot;

        private E lastRetElt;

        private int expectedModCount = modCount;

        Itr() {
        }

        public boolean hasNext();

        public E next();

        public void remove();
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied PriorityQueue<E> this);

    public void clear(@GuardSatisfied PriorityQueue<E> this);

    @Nullable
    public E poll(@GuardSatisfied PriorityQueue<E> this);

    E removeAt(int i);

    private void siftUp(int k, E x);

    private static <T> void siftUpComparable(int k, T x, Object[] es);

    private static <T> void siftUpUsingComparator(int k, T x, Object[] es, Comparator<? super T> cmp);

    private void siftDown(int k, E x);

    private static <T> void siftDownComparable(int k, T x, Object[] es, int n);

    private static <T> void siftDownUsingComparator(int k, T x, Object[] es, int n, Comparator<? super T> cmp);

    private void heapify();

    @SideEffectFree
    public Comparator<? super E> comparator(@GuardSatisfied PriorityQueue<E> this);

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    public final Spliterator<E> spliterator();

    final class PriorityQueueSpliterator implements Spliterator<E> {

        private int index;

        private int fence;

        private int expectedModCount;

        PriorityQueueSpliterator(int origin, int fence, int expectedModCount) {
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence();

        public PriorityQueueSpliterator trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    private static long[] nBits(int n);

    private static void setBit(long[] bits, int i);

    private static boolean isClear(long[] bits, int i);

    private boolean bulkRemove(Predicate<? super E> filter);

    public void forEach(Consumer<? super E> action);
}
