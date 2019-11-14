package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public interface NavigableSet<E> extends SortedSet<E> {

    @Nullable
    E lower(E e);

    @Nullable
    E floor(E e);

    @Nullable
    E ceiling(E e);

    @Nullable
    E higher(E e);

    @Nullable
    E pollFirst(@GuardSatisfied NavigableSet<E> this);

    @Nullable
    E pollLast(@GuardSatisfied NavigableSet<E> this);

    @SideEffectFree
    Iterator<E> iterator();

    NavigableSet<E> descendingSet();

    Iterator<E> descendingIterator();

    @SideEffectFree
    NavigableSet<E> subSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E fromElement, boolean fromInclusive, @GuardSatisfied E toElement, boolean toInclusive);

    @SideEffectFree
    NavigableSet<E> headSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E toElement, boolean inclusive);

    @SideEffectFree
    NavigableSet<E> tailSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E fromElement, boolean inclusive);

    @SideEffectFree
    SortedSet<E> subSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E fromElement, @GuardSatisfied E toElement);

    @SideEffectFree
    SortedSet<E> headSet(@GuardSatisfied NavigableSet<E> this, E toElement);

    @SideEffectFree
    SortedSet<E> tailSet(@GuardSatisfied NavigableSet<E> this, E fromElement);
}
