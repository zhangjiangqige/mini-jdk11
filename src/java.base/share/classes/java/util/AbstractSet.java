package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness" })
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {

    protected AbstractSet() {
    }

    @Pure
    public boolean equals(@GuardSatisfied AbstractSet<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public int hashCode(@GuardSatisfied AbstractSet<E> this);

    public boolean removeAll(@GuardSatisfied AbstractSet<E> this, Collection<?> c);
}
