package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "lock", "nullness", "index" })
public class ArrayDeque<E extends @NonNull Object> extends AbstractCollection<E> implements Deque<E>, Cloneable, Serializable {

    transient Object[] elements;

    transient int head;

    transient int tail;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int needed);

    private int newCapacity(int needed, int jump);

    public ArrayDeque() {
        elements = new Object[16];
    }

    public ArrayDeque(@NonNegative int numElements) {
        elements = new Object[(numElements < 1) ? 1 : (numElements == Integer.MAX_VALUE) ? Integer.MAX_VALUE : numElements + 1];
    }

    public ArrayDeque(Collection<? extends E> c) {
        this(c.size());
        copyElements(c);
    }

    static final int inc(int i, int modulus);

    static final int dec(int i, int modulus);

    static final int inc(int i, int distance, int modulus);

    static final int sub(int i, int j, int modulus);

    @SuppressWarnings("unchecked")
    static final <E> E elementAt(Object[] es, int i);

    static final <E> E nonNullElementAt(Object[] es, int i);

    public void addFirst(@GuardSatisfied ArrayDeque<E> this, E e);

    public void addLast(@GuardSatisfied ArrayDeque<E> this, E e);

    public boolean addAll(Collection<? extends E> c);

    private void copyElements(Collection<? extends E> c);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    public E removeFirst(@GuardSatisfied ArrayDeque<E> this);

    public E removeLast(@GuardSatisfied ArrayDeque<E> this);

    @Nullable
    public E pollFirst(@GuardSatisfied ArrayDeque<E> this);

    @Nullable
    public E pollLast(@GuardSatisfied ArrayDeque<E> this);

    public E getFirst(@GuardSatisfied ArrayDeque<E> this);

    public E getLast(@GuardSatisfied ArrayDeque<E> this);

    @Nullable
    public E peekFirst();

    @Nullable
    public E peekLast();

    public boolean removeFirstOccurrence(@GuardSatisfied ArrayDeque<E> this, Object o);

    public boolean removeLastOccurrence(@GuardSatisfied ArrayDeque<E> this, Object o);

    public boolean add(@GuardSatisfied ArrayDeque<E> this, E e);

    public boolean offer(@GuardSatisfied ArrayDeque<E> this, E e);

    public E remove(@GuardSatisfied ArrayDeque<E> this);

    @Nullable
    public E poll(@GuardSatisfied ArrayDeque<E> this);

    public E element();

    @Nullable
    public E peek();

    public void push(@GuardSatisfied ArrayDeque<E> this, E e);

    public E pop(@GuardSatisfied ArrayDeque<E> this);

    boolean delete(int i);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied ArrayDeque<E> this);

    @EnsuresNonNullIf(expression = { "peek()", "peekFirst()", "peekLast()", "poll()", "pollFirst()", "pollLast()" }, result = false)
    @Pure
    public boolean isEmpty(@GuardSatisfied ArrayDeque<E> this);

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    private class DeqIterator implements Iterator<E> {

        int cursor;

        int remaining = size();

        int lastRet = -1;

        DeqIterator() {
            cursor = head;
        }

        public final boolean hasNext();

        public E next();

        void postDelete(boolean leftShifted);

        public final void remove();

        public void forEachRemaining(Consumer<? super E> action);
    }

    private class DescendingIterator extends DeqIterator {

        DescendingIterator() {
            cursor = dec(tail, elements.length);
        }

        public final E next();

        void postDelete(boolean leftShifted);

        public final void forEachRemaining(Consumer<? super E> action);
    }

    public Spliterator<E> spliterator();

    final class DeqSpliterator implements Spliterator<E> {

        private int fence;

        private int cursor;

        DeqSpliterator() {
            this.fence = -1;
        }

        DeqSpliterator(int origin, int fence) {
            this.cursor = origin;
            this.fence = fence;
        }

        private int getFence();

        public DeqSpliterator trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    public void forEach(Consumer<? super E> action);

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    private boolean bulkRemove(Predicate<? super E> filter);

    private static long[] nBits(int n);

    private static void setBit(long[] bits, int i);

    private static boolean isClear(long[] bits, int i);

    private boolean bulkRemoveModified(Predicate<? super E> filter, final int beg);

    @Pure
    public boolean contains(@GuardSatisfied ArrayDeque<E> this, @GuardSatisfied @Nullable Object o);

    public boolean remove(@GuardSatisfied ArrayDeque<E> this, @Nullable Object o);

    public void clear(@GuardSatisfied ArrayDeque<E> this);

    private static void circularClear(Object[] es, int i, int end);

    @SideEffectFree
    public Object[] toArray();

    private <T> T[] toArray(Class<T[]> klazz);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @SideEffectFree
    public ArrayDeque<E> clone(@GuardSatisfied ArrayDeque<E> this);

    private static final long serialVersionUID = 2340985798034038923L;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    void checkInvariants();
}
