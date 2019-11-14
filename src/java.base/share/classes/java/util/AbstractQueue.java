package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {

    protected AbstractQueue() {
    }

    public boolean add(@GuardSatisfied AbstractQueue<E> this, E e);

    public E remove(@GuardSatisfied AbstractQueue<E> this);

    public E element();

    public void clear(@GuardSatisfied AbstractQueue<E> this);

    public boolean addAll(@GuardSatisfied AbstractQueue<E> this, Collection<? extends E> c);
}
