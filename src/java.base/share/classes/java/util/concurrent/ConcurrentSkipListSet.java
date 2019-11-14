package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.Field;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;

@AnnotatedFor({ "nullness" })
public class ConcurrentSkipListSet<E extends @NonNull Object> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, java.io.Serializable {

    private static final long serialVersionUID = -2479143111061671589L;

    private final ConcurrentNavigableMap<E, Object> m;

    public ConcurrentSkipListSet() {
        m = new ConcurrentSkipListMap<E, Object>();
    }

    public ConcurrentSkipListSet(@Nullable Comparator<? super E> comparator) {
        m = new ConcurrentSkipListMap<E, Object>(comparator);
    }

    public ConcurrentSkipListSet(Collection<? extends E> c) {
        m = new ConcurrentSkipListMap<E, Object>();
        addAll(c);
    }

    public ConcurrentSkipListSet(SortedSet<E> s) {
        m = new ConcurrentSkipListMap<E, Object>(s.comparator());
        addAll(s);
    }

    ConcurrentSkipListSet(ConcurrentNavigableMap<E, Object> m) {
        this.m = m;
    }

    @SideEffectFree
    public ConcurrentSkipListSet<E> clone();

    @Pure
    public int size();

    @Pure
    public boolean isEmpty();

    @Pure
    public boolean contains(Object o);

    public boolean add(E e);

    public boolean remove(Object o);

    public void clear();

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    public boolean equals(Object o);

    public boolean removeAll(Collection<?> c);

    public E lower(E e);

    public E floor(E e);

    public E ceiling(E e);

    public E higher(E e);

    @Nullable
    public E pollFirst();

    @Nullable
    public E pollLast();

    public Comparator<? super E> comparator();

    public E first();

    public E last();

    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

    public NavigableSet<E> headSet(E toElement, boolean inclusive);

    public NavigableSet<E> tailSet(E fromElement, boolean inclusive);

    public NavigableSet<E> subSet(E fromElement, E toElement);

    public NavigableSet<E> headSet(E toElement);

    public NavigableSet<E> tailSet(E fromElement);

    public NavigableSet<E> descendingSet();

    @SuppressWarnings({ "unchecked" })
    @SideEffectFree
    public Spliterator<E> spliterator();

    private void setMap(ConcurrentNavigableMap<E, Object> map);
}
