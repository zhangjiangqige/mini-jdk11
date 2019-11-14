package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness", "index" })
public interface ListIterator<E> extends Iterator<E> {

    boolean hasNext();

    E next(@GuardSatisfied ListIterator<E> this);

    boolean hasPrevious();

    E previous(@GuardSatisfied ListIterator<E> this);

    @NonNegative
    int nextIndex();

    @GTENegativeOne
    int previousIndex();

    void remove(@GuardSatisfied ListIterator<E> this);

    void set(@GuardSatisfied ListIterator<E> this, E e);

    void add(@GuardSatisfied ListIterator<E> this, E e);
}
