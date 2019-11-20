package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InvalidObjectException;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "lock", "nullness", "index" })
public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable {

    public HashSet() {
    }

    public HashSet(Collection<? extends E> c) {
    }

    public HashSet(@NonNegative int initialCapacity, float loadFactor) {
    }

    public HashSet(@NonNegative int initialCapacity) {
    }

    @SideEffectFree
    public Iterator<E> iterator();

    @Pure
    @NonNegative
    public int size(@GuardSatisfied HashSet<E> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied HashSet<E> this);

    @Pure
    public boolean contains(@GuardSatisfied HashSet<E> this, @GuardSatisfied @Nullable Object o);

    public boolean add(@GuardSatisfied HashSet<E> this, E e);

    public boolean remove(@GuardSatisfied HashSet<E> this, @Nullable Object o);

    public void clear(@GuardSatisfied HashSet<E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public Object clone(@GuardSatisfied HashSet<E> this);

    public Spliterator<E> spliterator();
}
