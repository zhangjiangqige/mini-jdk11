package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class ConcurrentLinkedDeque<E extends @NonNull Object> extends AbstractCollection<E> implements Deque<E>, java.io.Serializable {

    public ConcurrentLinkedDeque() {
    }

    public ConcurrentLinkedDeque(Collection<? extends E> c) {
    }

    public void addFirst(E e);

    public void addLast(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    @Nullable
    public E peekFirst();

    @Nullable
    public E peekLast();

    public E getFirst();

    public E getLast();

    @Nullable
    public E pollFirst();

    @Nullable
    public E pollLast();

    public E removeFirst();

    public E removeLast();

    public boolean offer(E e);

    public boolean add(E e);

    @Nullable
    public E poll();

    @Nullable
    public E peek();

    public E remove();

    public E pop();

    public E element();

    public void push(E e);

    public boolean removeFirstOccurrence(Object o);

    public boolean removeLastOccurrence(Object o);

    @Pure
    public boolean contains(@Nullable Object o);

    @EnsuresNonNullIf(expression = { "peek()", "peekFirst()", "peekLast()", "poll()", "pollFirst()", "pollLast()" }, result = false)
    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    public boolean remove(Object o);

    public boolean addAll(Collection<? extends E> c);

    public void clear();

    public String toString();

    @SideEffectFree
    @PolyNull
    public Object[] toArray(ConcurrentLinkedDeque<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    @SideEffectFree
    public Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public void forEach(Consumer<? super E> action);
}
