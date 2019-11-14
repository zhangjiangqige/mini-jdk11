package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public interface SortedSet<E> extends Set<E> {

    @SideEffectFree
    Comparator<? super E> comparator(@GuardSatisfied SortedSet<E> this);

    @SideEffectFree
    SortedSet<E> subSet(@GuardSatisfied SortedSet<E> this, @GuardSatisfied E fromElement, @GuardSatisfied E toElement);

    @SideEffectFree
    SortedSet<E> headSet(@GuardSatisfied SortedSet<E> this, E toElement);

    @SideEffectFree
    SortedSet<E> tailSet(@GuardSatisfied SortedSet<E> this, E fromElement);

    @SideEffectFree
    E first(@GuardSatisfied SortedSet<E> this);

    @SideEffectFree
    E last(@GuardSatisfied SortedSet<E> this);

    @Override
    default Spliterator<E> spliterator();
}
