package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public interface Queue<E> extends Collection<E> {

    boolean add(@GuardSatisfied Queue<E> this, E e);

    boolean offer(E e);

    E remove(@GuardSatisfied Queue<E> this);

    @Nullable
    E poll(@GuardSatisfied Queue<E> this);

    E element();

    @Nullable
    E peek();
}
