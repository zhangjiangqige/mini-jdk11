package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class ConcurrentLinkedQueue<E extends @NonNull Object> extends AbstractQueue<E> implements Queue<E>, java.io.Serializable {

    public ConcurrentLinkedQueue() {
    }

    public ConcurrentLinkedQueue(Collection<? extends E> c) {
    }

    public boolean add(E e);

    public boolean offer(E e);

    @Nullable
    public E poll();

    @Nullable
    public E peek();

    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    @Pure
    public boolean contains(Object o);

    public boolean remove(@Nullable Object o);

    public boolean addAll(Collection<? extends E> c);

    public String toString();

    @SideEffectFree
    @PolyNull
    public Object[] toArray(ConcurrentLinkedQueue<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public void clear();

    public void forEach(Consumer<? super E> action);
}
