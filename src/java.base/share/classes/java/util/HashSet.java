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

    static final long serialVersionUID = -5024744406713321676L;

    private transient HashMap<E, Object> map;

    private static final Object PRESENT = new Object();

    public HashSet() {
        map = new HashMap<>();
    }

    public HashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size() / .75f) + 1, 16));
        addAll(c);
    }

    public HashSet(@NonNegative int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    public HashSet(@NonNegative int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
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

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    public Spliterator<E> spliterator();
}
