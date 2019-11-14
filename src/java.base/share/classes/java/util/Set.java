package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public interface Set<E> extends Collection<E> {

    @NonNegative
    @Pure
    int size(@GuardSatisfied Set<E> this);

    @Pure
    boolean isEmpty(@GuardSatisfied Set<E> this);

    @Pure
    boolean contains(@GuardSatisfied Set<E> this, @GuardSatisfied @Nullable Object o);

    @SideEffectFree
    Iterator<E> iterator();

    @SideEffectFree
    Object[] toArray();

    @SideEffectFree
    @Nullable
    <T> T @PolyNull [] toArray(T @PolyNull [] a);

    boolean add(@GuardSatisfied Set<E> this, E e);

    boolean remove(@GuardSatisfied Set<E> this, @Nullable Object o);

    @Pure
    boolean containsAll(@GuardSatisfied Set<E> this, @GuardSatisfied Collection<?> c);

    boolean addAll(@GuardSatisfied Set<E> this, Collection<? extends E> c);

    boolean retainAll(@GuardSatisfied Set<E> this, Collection<?> c);

    boolean removeAll(@GuardSatisfied Set<E> this, Collection<?> c);

    void clear(@GuardSatisfied Set<E> this);

    @Pure
    boolean equals(@GuardSatisfied Set<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    int hashCode(@GuardSatisfied Set<E> this);

    @Override
    default Spliterator<E> spliterator();

    static <E> Set<E> of();

    static <E> Set<E> of(E e1);

    static <E> Set<E> of(E e1, E e2);

    static <E> Set<E> of(E e1, E e2, E e3);

    static <E> Set<E> of(E e1, E e2, E e3, E e4);

    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5);

    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6);

    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7);

    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8);

    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9);

    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10);

    @SafeVarargs
    @SuppressWarnings("varargs")
    static <E> Set<E> of(E... elements);

    @SuppressWarnings("unchecked")
    static <E> Set<E> copyOf(Collection<? extends E> coll);
}
