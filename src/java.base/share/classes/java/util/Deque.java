package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public interface Deque<E> extends Queue<E> {

    void addFirst(@GuardSatisfied Deque<E> this, E e);

    void addLast(@GuardSatisfied Deque<E> this, E e);

    boolean offerFirst(E e);

    boolean offerLast(E e);

    E removeFirst(@GuardSatisfied Deque<E> this);

    E removeLast(@GuardSatisfied Deque<E> this);

    @Nullable
    E pollFirst(@GuardSatisfied Deque<E> this);

    @Nullable
    E pollLast(@GuardSatisfied Deque<E> this);

    E getFirst(@GuardSatisfied Deque<E> this);

    E getLast(@GuardSatisfied Deque<E> this);

    @Nullable
    E peekFirst();

    @Nullable
    E peekLast();

    boolean removeFirstOccurrence(@GuardSatisfied Deque<E> this, Object o);

    boolean removeLastOccurrence(@GuardSatisfied Deque<E> this, Object o);

    boolean add(@GuardSatisfied Deque<E> this, E e);

    boolean offer(E e);

    E remove(@GuardSatisfied Deque<E> this);

    @Nullable
    E poll(@GuardSatisfied Deque<E> this);

    E element();

    @Nullable
    E peek();

    boolean addAll(Collection<? extends E> c);

    void push(@GuardSatisfied Deque<E> this, E e);

    E pop(@GuardSatisfied Deque<E> this);

    boolean remove(@GuardSatisfied Deque<E> this, @Nullable Object o);

    @Pure
    boolean contains(@GuardSatisfied Deque<E> this, @Nullable Object o);

    @NonNegative
    @Pure
    int size(@GuardSatisfied Deque<E> this);

    @SideEffectFree
    Iterator<E> iterator();

    Iterator<E> descendingIterator();
}
