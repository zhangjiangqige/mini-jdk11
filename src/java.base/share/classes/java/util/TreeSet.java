package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, java.io.Serializable {

    private transient NavigableMap<E, Object> m;

    private static final Object PRESENT = new Object();

    TreeSet(NavigableMap<E, Object> m) {
        this.m = m;
    }

    public TreeSet() {
        this(new TreeMap<>());
    }

    public TreeSet(Comparator<? super E> comparator) {
        this(new TreeMap<>(comparator));
    }

    public TreeSet(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    public TreeSet(SortedSet<E> s) {
        this(s.comparator());
        addAll(s);
    }

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    public NavigableSet<E> descendingSet();

    @Pure
    @NonNegative
    public int size(@GuardSatisfied TreeSet<E> this);

    @EnsuresNonNullIf(expression = { "pollFirst()", "pollLast()" }, result = false)
    @Pure
    public boolean isEmpty(@GuardSatisfied TreeSet<E> this);

    @Pure
    public boolean contains(@GuardSatisfied TreeSet<E> this, @GuardSatisfied Object o);

    public boolean add(@GuardSatisfied TreeSet<E> this, E e);

    public boolean remove(@GuardSatisfied TreeSet<E> this, Object o);

    public void clear(@GuardSatisfied TreeSet<E> this);

    public boolean addAll(@GuardSatisfied TreeSet<E> this, Collection<? extends E> c);

    @SideEffectFree
    public NavigableSet<E> subSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E fromElement, boolean fromInclusive, @GuardSatisfied E toElement, boolean toInclusive);

    @SideEffectFree
    public NavigableSet<E> headSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E toElement, boolean inclusive);

    @SideEffectFree
    public NavigableSet<E> tailSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E fromElement, boolean inclusive);

    @SideEffectFree
    public SortedSet<E> subSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E fromElement, @GuardSatisfied E toElement);

    @SideEffectFree
    public SortedSet<E> headSet(@GuardSatisfied TreeSet<E> this, E toElement);

    @SideEffectFree
    public SortedSet<E> tailSet(@GuardSatisfied TreeSet<E> this, E fromElement);

    @SideEffectFree
    public Comparator<? super E> comparator(@GuardSatisfied TreeSet<E> this);

    @SideEffectFree
    public E first(@GuardSatisfied TreeSet<E> this);

    @SideEffectFree
    public E last(@GuardSatisfied TreeSet<E> this);

    @Nullable
    public E lower(E e);

    @Nullable
    public E floor(E e);

    @Nullable
    public E ceiling(E e);

    @Nullable
    public E higher(E e);

    @Nullable
    public E pollFirst(@GuardSatisfied TreeSet<E> this);

    @Nullable
    public E pollLast(@GuardSatisfied TreeSet<E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public Object clone(@GuardSatisfied TreeSet<E> this);

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    public Spliterator<E> spliterator();

    private static final long serialVersionUID = -2479143000061671589L;
}
