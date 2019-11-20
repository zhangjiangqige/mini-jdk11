package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    protected AbstractList() {
    }

    public boolean add(@GuardSatisfied AbstractList<E> this, E e);

    @Pure
    public abstract E get(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index);

    public E set(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index, E element);

    public void add(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int index, E element);

    public E remove(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @Nullable Object o);

    public void clear(@GuardSatisfied AbstractList<E> this);

    public boolean addAll(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int index, Collection<? extends E> c);

    @SideEffectFree
    public Iterator<E> iterator();

    public ListIterator<E> listIterator();

    public ListIterator<E> listIterator(@IndexOrHigh({ "this" }) final int index);

    @SideEffectFree
    public List<E> subList(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    @Pure
    public boolean equals(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public int hashCode(@GuardSatisfied AbstractList<E> this);

    protected void removeRange(@IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    protected transient int modCount;
}
