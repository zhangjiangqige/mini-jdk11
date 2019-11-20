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

    public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
    }

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

    public Iterator<E> descendingIterator();

    @SideEffectFree
    public Object clone(@GuardSatisfied LinkedList<E> this);

    @SideEffectFree
    @PolyNull
    public Object[] toArray(LinkedList<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();
}
