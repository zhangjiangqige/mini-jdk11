package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public interface Collection<E> extends Iterable<E> {

    @NonNegative
    @Pure
    int size(@GuardSatisfied Collection<E> this);

    @Pure
    boolean isEmpty(@GuardSatisfied Collection<E> this);

    @CFComment({ "lock: not true, because map could contain nulls:  AssertParametersNonNull(\"get(#1)\")" })
    @Pure
    boolean contains(@GuardSatisfied Collection<E> this, @GuardSatisfied @Nullable Object o);

    @SideEffectFree
    Iterator<E> iterator();

    @CFComment({ "lock: The Nullness Checker does NOT use these signatures for either version", "of toArray; rather, the checker has hard-coded rules for those two", "methods, because the most useful type for toArray is not expressible", "in the surface syntax that the nullness annotations support." })
    @SideEffectFree
    @PolyNull
    Object[] toArray(Collection<@PolyNull E> this);

    @SideEffectFree
    @Nullable
    <T> T @PolyNull [] toArray(T @PolyNull [] a);

    default <T> T[] toArray(IntFunction<T[]> generator);

    boolean add(@GuardSatisfied Collection<E> this, E e);

    boolean remove(@GuardSatisfied Collection<E> this, @Nullable Object o);

    @Pure
    boolean containsAll(@GuardSatisfied Collection<E> this, @GuardSatisfied Collection<?> c);

    boolean addAll(@GuardSatisfied Collection<E> this, Collection<? extends E> c);

    boolean removeAll(@GuardSatisfied Collection<E> this, Collection<?> c);

    default boolean removeIf(Predicate<? super E> filter);

    boolean retainAll(@GuardSatisfied Collection<E> this, Collection<?> c);

    void clear(@GuardSatisfied Collection<E> this);

    @Pure
    boolean equals(@GuardSatisfied Collection<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    int hashCode(@GuardSatisfied Collection<E> this);

    @SideEffectFree
    @Override
    default Spliterator<E> spliterator();

    default Stream<E> stream();

    default Stream<E> parallelStream();
}
