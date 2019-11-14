package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class AbstractCollection<E> implements Collection<E> {

    protected AbstractCollection() {
    }

    @Override
    @SideEffectFree
    public abstract Iterator<E> iterator();

    @Override
    @Pure
    @NonNegative
    public abstract int size(@GuardSatisfied AbstractCollection<E> this);

    @Override
    @Pure
    public boolean isEmpty(@GuardSatisfied AbstractCollection<E> this);

    @Override
    @Pure
    public boolean contains(@GuardSatisfied AbstractCollection<E> this, @GuardSatisfied @Nullable Object o);

    @Override
    @SideEffectFree
    public Object[] toArray();

    @Override
    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T @PolyNull [] toArray(@Nullable T @PolyNull [] a);

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    @SuppressWarnings("unchecked")
    private static <T> T[] finishToArray(T[] r, Iterator<?> it);

    private static int hugeCapacity(int minCapacity);

    @Override
    public boolean add(@GuardSatisfied AbstractCollection<E> this, E e);

    @Override
    public boolean remove(@GuardSatisfied AbstractCollection<E> this, @Nullable Object o);

    @Override
    @Pure
    public boolean containsAll(@GuardSatisfied AbstractCollection<E> this, @GuardSatisfied Collection<?> c);

    @Override
    public boolean addAll(@GuardSatisfied AbstractCollection<E> this, Collection<? extends E> c);

    @Override
    public boolean removeAll(@GuardSatisfied AbstractCollection<E> this, Collection<?> c);

    @Override
    public boolean retainAll(@GuardSatisfied AbstractCollection<E> this, Collection<?> c);

    @Override
    public void clear(@GuardSatisfied AbstractCollection<E> this);

    @Override
    @SideEffectFree
    public String toString(@GuardSatisfied AbstractCollection<E> this);
}
