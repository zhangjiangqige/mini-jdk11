package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@CFComment({ "lock/nullness: permits nullable object" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    protected Object[] elementData;

    protected int elementCount;

    protected int capacityIncrement;

    private static final long serialVersionUID = -2767605614048989439L;

    public Vector(@NonNegative int initialCapacity, int capacityIncrement) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    public Vector(@NonNegative int initialCapacity) {
        this(initialCapacity, 0);
    }

    public Vector() {
        this(10);
    }

    public Vector(Collection<? extends E> c) {
        elementData = c.toArray();
        elementCount = elementData.length;
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
    }

    public synchronized void copyInto(@Nullable Object[] anArray);

    public synchronized void trimToSize(@GuardSatisfied Vector<E> this);

    public synchronized void ensureCapacity(int minCapacity);

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Object[] grow(int minCapacity);

    private Object[] grow();

    private int newCapacity(int minCapacity);

    private static int hugeCapacity(int minCapacity);

    public synchronized void setSize(@GuardSatisfied Vector<E> this, @NonNegative int newSize);

    @NonNegative
    public synchronized int capacity();

    @Pure
    @NonNegative
    public synchronized int size(@GuardSatisfied Vector<E> this);

    @Pure
    public synchronized boolean isEmpty(@GuardSatisfied Vector<E> this);

    public Enumeration<E> elements();

    @Pure
    public boolean contains(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public synchronized int indexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o, @NonNegative int index);

    @Pure
    @GTENegativeOne
    public synchronized int lastIndexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public synchronized int lastIndexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o, @NonNegative int index);

    public synchronized E elementAt(@NonNegative int index);

    public synchronized E firstElement();

    public synchronized E lastElement();

    public synchronized void setElementAt(@GuardSatisfied Vector<E> this, E obj, @NonNegative int index);

    public synchronized void removeElementAt(@GuardSatisfied Vector<E> this, @NonNegative int index);

    public synchronized void insertElementAt(@GuardSatisfied Vector<E> this, E obj, @NonNegative int index);

    public synchronized void addElement(@GuardSatisfied Vector<E> this, E obj);

    public synchronized boolean removeElement(@GuardSatisfied Vector<E> this, Object obj);

    public synchronized void removeAllElements(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    public synchronized Object clone(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    @PolyNull
    public synchronized Object[] toArray(Vector<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public synchronized <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @SuppressWarnings("unchecked")
    E elementData(int index);

    @SuppressWarnings("unchecked")
    static <E> E elementAt(Object[] es, int index);

    @Pure
    public synchronized E get(@GuardSatisfied Vector<E> this, @NonNegative int index);

    public synchronized E set(@GuardSatisfied Vector<E> this, @NonNegative int index, E element);

    private void add(E e, Object[] elementData, int s);

    public synchronized boolean add(@GuardSatisfied Vector<E> this, E e);

    public boolean remove(@GuardSatisfied Vector<E> this, @Nullable Object o);

    public void add(@GuardSatisfied Vector<E> this, @NonNegative int index, E element);

    public synchronized E remove(@GuardSatisfied Vector<E> this, @NonNegative int index);

    public void clear(@GuardSatisfied Vector<E> this);

    @Pure
    public synchronized boolean containsAll(@GuardSatisfied Vector<E> this, @GuardSatisfied Collection<?> c);

    public boolean addAll(@GuardSatisfied Vector<E> this, Collection<? extends E> c);

    public boolean removeAll(@GuardSatisfied Vector<E> this, Collection<?> c);

    public boolean retainAll(@GuardSatisfied Vector<E> this, Collection<?> c);

    @SuppressWarnings({ "unchecked" })
    @Override
    public boolean removeIf(Predicate<? super E> filter);

    private static long[] nBits(int n);

    private static void setBit(long[] bits, int i);

    private static boolean isClear(long[] bits, int i);

    private synchronized boolean bulkRemove(Predicate<? super E> filter);

    public synchronized boolean addAll(@GuardSatisfied Vector<E> this, @NonNegative int index, Collection<? extends E> c);

    @Pure
    public synchronized boolean equals(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public synchronized int hashCode(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    public synchronized String toString(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    public synchronized List<E> subList(@GuardSatisfied Vector<E> this, int fromIndex, int toIndex);

    protected synchronized void removeRange(int fromIndex, int toIndex);

    private void shiftTailOverGap(Object[] es, int lo, int hi);

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    public synchronized ListIterator<E> listIterator(@NonNegative int index);

    public synchronized ListIterator<E> listIterator();

    @SideEffectFree
    public synchronized Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        int cursor;

        int lastRet = -1;

        int expectedModCount = modCount;

        public boolean hasNext();

        public E next();

        public void remove();

        @Override
        public void forEachRemaining(Consumer<? super E> action);

        final void checkForComodification();
    }

    final class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious();

        public int nextIndex();

        public int previousIndex();

        public E previous();

        public void set(E e);

        public void add(E e);
    }

    @Override
    public synchronized void forEach(Consumer<? super E> action);

    @SuppressWarnings({ "unchecked" })
    @Override
    public synchronized void replaceAll(UnaryOperator<E> operator);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void sort(Comparator<? super E> c);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    final class VectorSpliterator implements Spliterator<E> {

        private Object[] array;

        private int index;

        private int fence;

        private int expectedModCount;

        VectorSpliterator(Object[] array, int origin, int fence, int expectedModCount) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence();

        public Spliterator<E> trySplit();

        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super E> action);

        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    void checkInvariants();
}
