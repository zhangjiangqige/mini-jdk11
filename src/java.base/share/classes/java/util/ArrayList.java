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
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import jdk.internal.misc.SharedSecrets;

@CFComment("lock/nullness: Permit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    private static final long serialVersionUID = 8683452581122892189L;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    public ArrayList(@NonNegative int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    public void trimToSize(@GuardSatisfied ArrayList<E> this);

    public void ensureCapacity(@GuardSatisfied ArrayList<E> this, int minCapacity);

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Object[] grow(int minCapacity);

    private Object[] grow();

    private int newCapacity(int minCapacity);

    private static int hugeCapacity(int minCapacity);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied ArrayList<E> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied ArrayList<E> this);

    @Pure
    public boolean contains(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable Object o);

    int indexOfRange(Object o, int start, int end);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable Object o);

    int lastIndexOfRange(Object o, int start, int end);

    @SideEffectFree
    public Object clone(@GuardSatisfied ArrayList<E> this);

    @SideEffectFree
    @PolyNull
    public Object[] toArray(ArrayList<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @SuppressWarnings("unchecked")
    E elementData(@NonNegative int index);

    @SuppressWarnings("unchecked")
    static <E> E elementAt(Object[] es, int index);

    @Pure
    public E get(@GuardSatisfied ArrayList<E> this, @NonNegative int index);

    public E set(@GuardSatisfied ArrayList<E> this, @NonNegative int index, E element);

    private void add(E e, Object[] elementData, int s);

    public boolean add(@GuardSatisfied ArrayList<E> this, E e);

    public void add(@GuardSatisfied ArrayList<E> this, @NonNegative int index, E element);

    public E remove(@GuardSatisfied ArrayList<E> this, @NonNegative int index);

    public boolean equals(Object o);

    boolean equalsRange(List<?> other, int from, int to);

    private boolean equalsArrayList(ArrayList<?> other);

    private void checkForComodification(final int expectedModCount);

    public int hashCode();

    int hashCodeRange(int from, int to);

    public boolean remove(@GuardSatisfied ArrayList<E> this, @Nullable Object o);

    private void fastRemove(Object[] es, int i);

    public void clear(@GuardSatisfied ArrayList<E> this);

    public boolean addAll(@GuardSatisfied ArrayList<E> this, Collection<? extends E> c);

    public boolean addAll(@GuardSatisfied ArrayList<E> this, @NonNegative int index, Collection<? extends E> c);

    protected void removeRange(int fromIndex, int toIndex);

    private void shiftTailOverGap(Object[] es, int lo, int hi);

    private void rangeCheckForAdd(@NonNegative int index);

    private String outOfBoundsMsg(@NonNegative int index);

    private static String outOfBoundsMsg(int fromIndex, int toIndex);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    boolean batchRemove(Collection<?> c, boolean complement, final int from, final int end);

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    public ListIterator<E> listIterator(@NonNegative int index);

    public ListIterator<E> listIterator();

    @SideEffectFree
    public Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        int cursor;

        int lastRet = -1;

        int expectedModCount = modCount;

        Itr() {
        }

        public boolean hasNext();

        @SuppressWarnings("unchecked")
        public E next();

        public void remove();

        @SuppressWarnings({ "unchecked" })
        @Override
        public void forEachRemaining(Consumer<? super E> action);

        final void checkForComodification();
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious();

        public int nextIndex();

        public int previousIndex();

        @SuppressWarnings("unchecked")
        public E previous();

        public void set(E e);

        public void add(E e);
    }

    public List<E> subList(@NonNegative int fromIndex, @NonNegative int toIndex);

    private static class SubList<E> extends AbstractList<E> implements RandomAccess {

        private final ArrayList<E> root;

        private final SubList<E> parent;

        private final int offset;

        private int size;

        public SubList(ArrayList<E> root, int fromIndex, int toIndex) {
            this.root = root;
            this.parent = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        private SubList(SubList<E> parent, int fromIndex, int toIndex) {
            this.root = parent.root;
            this.parent = parent;
            this.offset = parent.offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        public E set(@NonNegative int index, E element);

        public E get(@NonNegative int index);

        @NonNegative
        public int size();

        public void add(@NonNegative int index, E element);

        public E remove(@NonNegative int index);

        protected void removeRange(int fromIndex, int toIndex);

        public boolean addAll(Collection<? extends E> c);

        public boolean addAll(@NonNegative int index, Collection<? extends E> c);

        public void replaceAll(UnaryOperator<E> operator);

        public boolean removeAll(Collection<?> c);

        public boolean retainAll(Collection<?> c);

        private boolean batchRemove(Collection<?> c, boolean complement);

        public boolean removeIf(Predicate<? super E> filter);

        public Object[] toArray();

        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        public boolean equals(Object o);

        public int hashCode();

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public boolean contains(Object o);

        @SideEffectFree
        public Iterator<E> iterator();

        public ListIterator<E> listIterator(@NonNegative int index);

        public List<E> subList(int fromIndex, int toIndex);

        private void rangeCheckForAdd(@NonNegative int index);

        private String outOfBoundsMsg(@NonNegative int index);

        private void checkForComodification();

        private void updateSizeAndModCount(int sizeChange);

        @SideEffectFree
        public Spliterator<E> spliterator();
    }

    @Override
    public void forEach(Consumer<? super E> action);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    final class ArrayListSpliterator implements Spliterator<E> {

        private int index;

        private int fence;

        private int expectedModCount;

        ArrayListSpliterator(int origin, int fence, int expectedModCount) {
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence();

        public ArrayListSpliterator trySplit();

        public boolean tryAdvance(Consumer<? super E> action);

        public void forEachRemaining(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    private static long[] nBits(int n);

    private static void setBit(long[] bits, int i);

    private static boolean isClear(long[] bits, int i);

    @Override
    public boolean removeIf(Predicate<? super E> filter);

    boolean removeIf(Predicate<? super E> filter, int i, final int end);

    @SuppressWarnings({ "unchecked" })
    @Override
    public void replaceAll(UnaryOperator<E> operator);

    private void replaceAllRange(UnaryOperator<E> operator, int i, int end);

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c);

    void checkInvariants();
}
