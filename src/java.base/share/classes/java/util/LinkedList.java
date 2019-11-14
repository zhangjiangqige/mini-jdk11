package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;

@CFComment({ "lock/nullness: This class permits null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    transient int size = 0;

    transient Node<E> first;

    transient Node<E> last;

    public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    private void linkFirst(E e);

    void linkLast(E e);

    void linkBefore(E e, Node<E> succ);

    private E unlinkFirst(Node<E> f);

    private E unlinkLast(Node<E> l);

    E unlink(Node<E> x);

    public E getFirst(@GuardSatisfied LinkedList<E> this);

    public E getLast(@GuardSatisfied LinkedList<E> this);

    public E removeFirst(@GuardSatisfied LinkedList<E> this);

    public E removeLast(@GuardSatisfied LinkedList<E> this);

    public void addFirst(@GuardSatisfied LinkedList<E> this, E e);

    public void addLast(@GuardSatisfied LinkedList<E> this, E e);

    @Pure
    public boolean contains(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied LinkedList<E> this);

    @ReleasesNoLocks
    public boolean add(@GuardSatisfied LinkedList<E> this, E e);

    @ReleasesNoLocks
    public boolean remove(@GuardSatisfied LinkedList<E> this, @Nullable Object o);

    public boolean addAll(@GuardSatisfied LinkedList<E> this, Collection<? extends E> c);

    public boolean addAll(@GuardSatisfied LinkedList<E> this, @NonNegative int index, Collection<? extends E> c);

    public void clear(@GuardSatisfied LinkedList<E> this);

    @Pure
    public E get(@GuardSatisfied LinkedList<E> this, @NonNegative int index);

    public E set(@GuardSatisfied LinkedList<E> this, @NonNegative int index, E element);

    public void add(@GuardSatisfied LinkedList<E> this, @NonNegative int index, E element);

    public E remove(@GuardSatisfied LinkedList<E> this, @NonNegative int index);

    private boolean isElementIndex(@NonNegative int index);

    private boolean isPositionIndex(@NonNegative int index);

    private String outOfBoundsMsg(@NonNegative int index);

    private void checkElementIndex(@NonNegative int index);

    private void checkPositionIndex(@NonNegative int index);

    Node<E> node(@NonNegative int index);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable Object o);

    @Nullable
    public E peek();

    public E element();

    @Nullable
    public E poll(@GuardSatisfied LinkedList<E> this);

    public E remove(@GuardSatisfied LinkedList<E> this);

    public boolean offer(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    @Nullable
    public E peekFirst();

    @Nullable
    public E peekLast();

    @Nullable
    public E pollFirst(@GuardSatisfied LinkedList<E> this);

    @Nullable
    public E pollLast(@GuardSatisfied LinkedList<E> this);

    public void push(@GuardSatisfied LinkedList<E> this, E e);

    public E pop(@GuardSatisfied LinkedList<E> this);

    public boolean removeFirstOccurrence(@GuardSatisfied LinkedList<E> this, Object o);

    public boolean removeLastOccurrence(@GuardSatisfied LinkedList<E> this, Object o);

    public ListIterator<E> listIterator(@NonNegative int index);

    private class ListItr implements ListIterator<E> {

        private Node<E> lastReturned;

        private Node<E> next;

        private int nextIndex;

        private int expectedModCount = modCount;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext();

        public E next();

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void remove();

        public void set(E e);

        public void add(E e);

        public void forEachRemaining(Consumer<? super E> action);

        final void checkForComodification();
    }

    private static class Node<E> {

        E item;

        Node<E> next;

        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public Iterator<E> descendingIterator();

    private class DescendingIterator implements Iterator<E> {

        private final ListItr itr = new ListItr(size());

        public boolean hasNext();

        public E next();

        public void remove();
    }

    @SuppressWarnings("unchecked")
    private LinkedList<E> superClone();

    @SideEffectFree
    public Object clone(@GuardSatisfied LinkedList<E> this);

    @SideEffectFree
    @PolyNull
    public Object[] toArray(LinkedList<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    private static final long serialVersionUID = 876323262645176354L;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    static final class LLSpliterator<E> implements Spliterator<E> {

        static final int BATCH_UNIT = 1 << 10;

        static final int MAX_BATCH = 1 << 25;

        final LinkedList<E> list;

        Node<E> current;

        int est;

        int expectedModCount;

        int batch;

        LLSpliterator(LinkedList<E> list, int est, int expectedModCount) {
            this.list = list;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getEst();

        public long estimateSize();

        public Spliterator<E> trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public int characteristics();
    }
}
