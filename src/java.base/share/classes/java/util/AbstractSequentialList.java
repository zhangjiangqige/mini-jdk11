package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness" })
public abstract class AbstractSequentialList<E> extends AbstractList<E> {

    protected AbstractSequentialList() {
    }

    @Pure
    public E get(@GuardSatisfied AbstractSequentialList<E> this, int index);

    public E set(@GuardSatisfied AbstractSequentialList<E> this, int index, E element);

    public void add(@GuardSatisfied AbstractSequentialList<E> this, int index, E element);

    public E remove(@GuardSatisfied AbstractSequentialList<E> this, int index);

    public boolean addAll(@GuardSatisfied AbstractSequentialList<E> this, int index, Collection<? extends E> c);

    @SideEffectFree
    public Iterator<E> iterator();

    public abstract ListIterator<E> listIterator(int index);
}
