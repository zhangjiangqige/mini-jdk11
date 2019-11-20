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

    public PriorityQueue() {
    }

    public PriorityQueue(@Positive int initialCapacity) {
    }

    public PriorityQueue(Comparator<? super E> comparator) {
    }

    public PriorityQueue(@Positive int initialCapacity, Comparator<? super E> comparator) {
    }

    public PriorityQueue(Collection<? extends E> c) {
    }

    public PriorityQueue(PriorityQueue<? extends E> c) {
    }

    public PriorityQueue(SortedSet<? extends E> c) {
    }

    public boolean add(@GuardSatisfied PriorityQueue<E> this, E e);

    public boolean offer(E e);

    @Nullable
    public E peek(@GuardSatisfied PriorityQueue<E> this);

    public boolean remove(@GuardSatisfied PriorityQueue<E> this, @Nullable Object o);

    @Pure
    public boolean contains(@GuardSatisfied PriorityQueue<E> this, @GuardSatisfied @Nullable Object o);

    @SideEffectFree
    public Object[] toArray();

    @SideEffectFree
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @SideEffectFree
    public Iterator<E> iterator();

    @Pure
    @NonNegative
    public int size(@GuardSatisfied PriorityQueue<E> this);

    public void clear(@GuardSatisfied PriorityQueue<E> this);

    @Nullable
    public E poll(@GuardSatisfied PriorityQueue<E> this);

    @SideEffectFree
    public Comparator<? super E> comparator(@GuardSatisfied PriorityQueue<E> this);

    public final Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public void forEach(Consumer<? super E> action);
}
