package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.UnaryOperator;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public interface List<E> extends Collection<E> {

    @NonNegative
    @Pure
    int size(@GuardSatisfied List<E> this);

    @Pure
    boolean isEmpty(@GuardSatisfied List<E> this);

    @Pure
    boolean contains(@GuardSatisfied List<E> this, @Nullable Object o);

    @SideEffectFree
    Iterator<E> iterator();

    @SideEffectFree
    @PolyNull
    Object[] toArray(List<@PolyNull E> this);

    @SideEffectFree
    @Nullable
    <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @ReleasesNoLocks
    boolean add(@GuardSatisfied List<E> this, E e);

    boolean remove(@GuardSatisfied List<E> this, @Nullable Object o);

    @Pure
    boolean containsAll(@GuardSatisfied List<E> this, Collection<?> c);

    boolean addAll(@GuardSatisfied List<E> this, Collection<? extends E> c);

    boolean addAll(@GuardSatisfied List<E> this, @IndexOrHigh({ "this" }) int index, Collection<? extends E> c);

    boolean removeAll(@GuardSatisfied List<E> this, Collection<?> c);

    boolean retainAll(@GuardSatisfied List<E> this, Collection<?> c);

    default void replaceAll(UnaryOperator<E> operator);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    default void sort(Comparator<? super E> c);

    void clear(@GuardSatisfied List<E> this);

    @Pure
    boolean equals(@GuardSatisfied List<E> this, @Nullable Object o);

    @Pure
    int hashCode(@GuardSatisfied List<E> this);

    @Pure
    E get(@GuardSatisfied List<E> this, @IndexFor({ "this" }) int index);

    E set(@GuardSatisfied List<E> this, @IndexFor({ "this" }) int index, E element);

    @ReleasesNoLocks
    void add(@GuardSatisfied List<E> this, @IndexOrHigh({ "this" }) int index, E element);

    @ReleasesNoLocks
    E remove(@GuardSatisfied List<E> this, @IndexFor({ "this" }) int index);

    @GTENegativeOne
    @Pure
    int indexOf(@GuardSatisfied List<E> this, @Nullable Object o);

    @GTENegativeOne
    @Pure
    int lastIndexOf(@GuardSatisfied List<E> this, @Nullable Object o);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(@IndexOrHigh({ "this" }) int index);

    @SideEffectFree
    List<E> subList(@GuardSatisfied List<E> this, @IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    @SideEffectFree
    @Override
    default Spliterator<E> spliterator();

    static <E> List<E> of();

    static <E> List<E> of(E e1);

    static <E> List<E> of(E e1, E e2);

    static <E> List<E> of(E e1, E e2, E e3);

    static <E> List<E> of(E e1, E e2, E e3, E e4);

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5);

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6);

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7);

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8);

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9);

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10);

    @SafeVarargs
    @SuppressWarnings("varargs")
    static <E> List<E> of(E... elements);

    static <E> List<E> copyOf(Collection<? extends E> coll);
}
