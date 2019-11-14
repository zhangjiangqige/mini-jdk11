package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@CFComment({ "lock/nullness: This permits null element when using a custom comparator which allows null" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class TreeMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, java.io.Serializable {

    private final Comparator<? super K> comparator;

    private transient Entry<K, V> root;

    private transient int size = 0;

    private transient int modCount = 0;

    public TreeMap() {
        comparator = null;
    }

    public TreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    public TreeMap(Map<? extends K, ? extends V> m) {
        comparator = null;
        putAll(m);
    }

    public TreeMap(SortedMap<K, ? extends V> m) {
        comparator = m.comparator();
        try {
            buildFromSorted(m.size(), m.entrySet().iterator(), null, null);
        } catch (java.io.IOException | ClassNotFoundException cannotHappen) {
        }
    }

    @NonNegative
    public int size(@GuardSatisfied TreeMap<K, V> this);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    public boolean containsKey(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied @Nullable Object key);

    public boolean containsValue(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied @Nullable Object value);

    @Nullable
    public V get(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied @Nullable Object key);

    public Comparator<? super K> comparator(@GuardSatisfied TreeMap<K, V> this);

    public K firstKey();

    public K lastKey();

    public void putAll(@GuardSatisfied TreeMap<K, V> this, Map<? extends K, ? extends V> map);

    final Entry<K, V> getEntry(Object key);

    final Entry<K, V> getEntryUsingComparator(Object key);

    final Entry<K, V> getCeilingEntry(K key);

    final Entry<K, V> getFloorEntry(K key);

    final Entry<K, V> getHigherEntry(K key);

    final Entry<K, V> getLowerEntry(K key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied TreeMap<K, V> this, K key, V value);

    @Nullable
    public V remove(@GuardSatisfied TreeMap<K, V> this, @Nullable Object key);

    public void clear(@GuardSatisfied TreeMap<K, V> this);

    public Object clone(@GuardSatisfied TreeMap<K, V> this);

    public Map.@Nullable Entry<K, V> firstEntry();

    public Map.@Nullable Entry<K, V> lastEntry();

    public Map.@Nullable Entry<K, V> pollFirstEntry(@GuardSatisfied TreeMap<K, V> this);

    public Map.@Nullable Entry<K, V> pollLastEntry(@GuardSatisfied TreeMap<K, V> this);

    public Map.@Nullable Entry<K, V> lowerEntry(K key);

    @Nullable
    public K lowerKey(K key);

    public Map.@Nullable Entry<K, V> floorEntry(K key);

    @Nullable
    public K floorKey(K key);

    public Map.@Nullable Entry<K, V> ceilingEntry(K key);

    @Nullable
    public K ceilingKey(K key);

    public Map.@Nullable Entry<K, V> higherEntry(K key);

    @Nullable
    public K higherKey(K key);

    private transient EntrySet entrySet;

    private transient KeySet<K> navigableKeySet;

    private transient NavigableMap<K, V> descendingMap;

    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied TreeMap<K, V> this);

    public NavigableSet<@KeyFor({ "this" }) K> navigableKeySet(@GuardSatisfied TreeMap<K, V> this);

    public NavigableSet<@KeyFor({ "this" }) K> descendingKeySet(@GuardSatisfied TreeMap<K, V> this);

    public Collection<V> values(@GuardSatisfied TreeMap<K, V> this);

    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied TreeMap<K, V> this);

    public NavigableMap<K, V> descendingMap(@GuardSatisfied TreeMap<K, V> this);

    public NavigableMap<K, V> subMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K fromKey, boolean fromInclusive, @GuardSatisfied K toKey, boolean toInclusive);

    public NavigableMap<K, V> headMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K toKey, boolean inclusive);

    public NavigableMap<K, V> tailMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K fromKey, boolean inclusive);

    public SortedMap<K, V> subMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K fromKey, @GuardSatisfied K toKey);

    public SortedMap<K, V> headMap(@GuardSatisfied TreeMap<K, V> this, K toKey);

    public SortedMap<K, V> tailMap(@GuardSatisfied TreeMap<K, V> this, K fromKey);

    @Override
    public boolean replace(K key, V oldValue, V newValue);

    @Override
    public V replace(K key, V value);

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    class Values extends AbstractCollection<V> {

        @SideEffectFree
        public Iterator<V> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        public boolean remove(Object o);

        public void clear();

        @SideEffectFree
        public Spliterator<V> spliterator();
    }

    class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        public boolean contains(Object o);

        public boolean remove(Object o);

        @NonNegative
        public int size();

        public void clear();

        @SideEffectFree
        public Spliterator<Map.Entry<K, V>> spliterator();
    }

    Iterator<K> keyIterator();

    Iterator<K> descendingKeyIterator();

    static final class KeySet<E> extends AbstractSet<E> implements NavigableSet<E> {

        private final NavigableMap<E, ?> m;

        KeySet(NavigableMap<E, ?> map) {
            m = map;
        }

        @SideEffectFree
        public Iterator<E> iterator();

        public Iterator<E> descendingIterator();

        @NonNegative
        public int size();

        public boolean isEmpty();

        public boolean contains(Object o);

        public void clear();

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E first();

        public E last();

        public Comparator<? super E> comparator();

        public E pollFirst();

        public E pollLast();

        public boolean remove(Object o);

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);

        public NavigableSet<E> descendingSet();

        @SideEffectFree
        public Spliterator<E> spliterator();
    }

    abstract class PrivateEntryIterator<T> implements Iterator<T> {

        Entry<K, V> next;

        Entry<K, V> lastReturned;

        int expectedModCount;

        PrivateEntryIterator(Entry<K, V> first) {
            expectedModCount = modCount;
            lastReturned = null;
            next = first;
        }

        public final boolean hasNext();

        final Entry<K, V> nextEntry();

        final Entry<K, V> prevEntry();

        public void remove();
    }

    final class EntryIterator extends PrivateEntryIterator<Map.Entry<K, V>> {

        EntryIterator(Entry<K, V> first) {
            super(first);
        }

        public Map.Entry<K, V> next();
    }

    final class ValueIterator extends PrivateEntryIterator<V> {

        ValueIterator(Entry<K, V> first) {
            super(first);
        }

        public V next();
    }

    final class KeyIterator extends PrivateEntryIterator<K> {

        KeyIterator(Entry<K, V> first) {
            super(first);
        }

        public K next();
    }

    final class DescendingKeyIterator extends PrivateEntryIterator<K> {

        DescendingKeyIterator(Entry<K, V> first) {
            super(first);
        }

        public K next();

        public void remove();
    }

    @SuppressWarnings("unchecked")
    final int compare(Object k1, Object k2);

    static final boolean valEquals(Object o1, Object o2);

    static <K, V> Map.Entry<K, V> exportEntry(TreeMap.Entry<K, V> e);

    static <K, V> K keyOrNull(TreeMap.Entry<K, V> e);

    static <K> K key(Entry<K, ?> e);

    private static final Object UNBOUNDED = new Object();

    abstract static class NavigableSubMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, java.io.Serializable {

        private static final long serialVersionUID = -2102997345730753016L;

        final TreeMap<K, V> m;

        final K lo, hi;

        final boolean fromStart, toEnd;

        final boolean loInclusive, hiInclusive;

        NavigableSubMap(TreeMap<K, V> m, boolean fromStart, K lo, boolean loInclusive, boolean toEnd, K hi, boolean hiInclusive) {
            if (!fromStart && !toEnd) {
                if (m.compare(lo, hi) > 0)
                    throw new IllegalArgumentException("fromKey > toKey");
            } else {
                if (!fromStart)
                    m.compare(lo, lo);
                if (!toEnd)
                    m.compare(hi, hi);
            }
            this.m = m;
            this.fromStart = fromStart;
            this.lo = lo;
            this.loInclusive = loInclusive;
            this.toEnd = toEnd;
            this.hi = hi;
            this.hiInclusive = hiInclusive;
        }

        final boolean tooLow(Object key);

        final boolean tooHigh(Object key);

        final boolean inRange(Object key);

        final boolean inClosedRange(Object key);

        final boolean inRange(Object key, boolean inclusive);

        final TreeMap.Entry<K, V> absLowest();

        final TreeMap.Entry<K, V> absHighest();

        final TreeMap.Entry<K, V> absCeiling(K key);

        final TreeMap.Entry<K, V> absHigher(K key);

        final TreeMap.Entry<K, V> absFloor(K key);

        final TreeMap.Entry<K, V> absLower(K key);

        final TreeMap.Entry<K, V> absHighFence();

        final TreeMap.Entry<K, V> absLowFence();

        abstract TreeMap.Entry<K, V> subLowest();

        abstract TreeMap.Entry<K, V> subHighest();

        abstract TreeMap.Entry<K, V> subCeiling(K key);

        abstract TreeMap.Entry<K, V> subHigher(K key);

        abstract TreeMap.Entry<K, V> subFloor(K key);

        abstract TreeMap.Entry<K, V> subLower(K key);

        abstract Iterator<K> keyIterator();

        abstract Spliterator<K> keySpliterator();

        abstract Iterator<K> descendingKeyIterator();

        public boolean isEmpty();

        @NonNegative
        public int size();

        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public final boolean containsKey(Object key);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public final V put(K key, V value);

        public final V get(Object key);

        public final V remove(Object key);

        public final Map.Entry<K, V> ceilingEntry(K key);

        public final K ceilingKey(K key);

        public final Map.Entry<K, V> higherEntry(K key);

        public final K higherKey(K key);

        public final Map.Entry<K, V> floorEntry(K key);

        public final K floorKey(K key);

        public final Map.Entry<K, V> lowerEntry(K key);

        public final K lowerKey(K key);

        public final K firstKey();

        public final K lastKey();

        public final Map.Entry<K, V> firstEntry();

        public final Map.Entry<K, V> lastEntry();

        public final Map.Entry<K, V> pollFirstEntry();

        public final Map.Entry<K, V> pollLastEntry();

        transient NavigableMap<K, V> descendingMapView;

        transient EntrySetView entrySetView;

        transient KeySet<K> navigableKeySetView;

        public final NavigableSet<K> navigableKeySet();

        public final Set<K> keySet();

        public NavigableSet<K> descendingKeySet();

        public final SortedMap<K, V> subMap(K fromKey, K toKey);

        public final SortedMap<K, V> headMap(K toKey);

        public final SortedMap<K, V> tailMap(K fromKey);

        abstract class EntrySetView extends AbstractSet<Map.Entry<K, V>> {

            private transient int size = -1, sizeModCount;

            public int size();

            public boolean isEmpty();

            public boolean contains(Object o);

            public boolean remove(Object o);
        }

        abstract class SubMapIterator<T> implements Iterator<T> {

            TreeMap.Entry<K, V> lastReturned;

            TreeMap.Entry<K, V> next;

            final Object fenceKey;

            int expectedModCount;

            SubMapIterator(TreeMap.Entry<K, V> first, TreeMap.Entry<K, V> fence) {
                expectedModCount = m.modCount;
                lastReturned = null;
                next = first;
                fenceKey = fence == null ? UNBOUNDED : fence.key;
            }

            public final boolean hasNext();

            final TreeMap.Entry<K, V> nextEntry();

            final TreeMap.Entry<K, V> prevEntry();

            final void removeAscending();

            final void removeDescending();
        }

        final class SubMapEntryIterator extends SubMapIterator<Map.Entry<K, V>> {

            SubMapEntryIterator(TreeMap.Entry<K, V> first, TreeMap.Entry<K, V> fence) {
                super(first, fence);
            }

            public Map.Entry<K, V> next();

            public void remove();
        }

        final class DescendingSubMapEntryIterator extends SubMapIterator<Map.Entry<K, V>> {

            DescendingSubMapEntryIterator(TreeMap.Entry<K, V> last, TreeMap.Entry<K, V> fence) {
                super(last, fence);
            }

            public Map.Entry<K, V> next();

            public void remove();
        }

        final class SubMapKeyIterator extends SubMapIterator<K> implements Spliterator<K> {

            SubMapKeyIterator(TreeMap.Entry<K, V> first, TreeMap.Entry<K, V> fence) {
                super(first, fence);
            }

            public K next();

            public void remove();

            public Spliterator<K> trySplit();

            public void forEachRemaining(Consumer<? super K> action);

            public boolean tryAdvance(Consumer<? super K> action);

            public long estimateSize();

            public int characteristics();

            public final Comparator<? super K> getComparator();
        }

        final class DescendingSubMapKeyIterator extends SubMapIterator<K> implements Spliterator<K> {

            DescendingSubMapKeyIterator(TreeMap.Entry<K, V> last, TreeMap.Entry<K, V> fence) {
                super(last, fence);
            }

            public K next();

            public void remove();

            public Spliterator<K> trySplit();

            public void forEachRemaining(Consumer<? super K> action);

            public boolean tryAdvance(Consumer<? super K> action);

            public long estimateSize();

            public int characteristics();
        }
    }

    static final class AscendingSubMap<K, V> extends NavigableSubMap<K, V> {

        private static final long serialVersionUID = 912986545866124060L;

        AscendingSubMap(TreeMap<K, V> m, boolean fromStart, K lo, boolean loInclusive, boolean toEnd, K hi, boolean hiInclusive) {
            super(m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive);
        }

        public Comparator<? super K> comparator();

        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);

        public NavigableMap<K, V> descendingMap();

        Iterator<K> keyIterator();

        Spliterator<K> keySpliterator();

        Iterator<K> descendingKeyIterator();

        final class AscendingEntrySetView extends EntrySetView {

            public Iterator<Map.Entry<K, V>> iterator();
        }

        public Set<Map.Entry<K, V>> entrySet();

        TreeMap.Entry<K, V> subLowest();

        TreeMap.Entry<K, V> subHighest();

        TreeMap.Entry<K, V> subCeiling(K key);

        TreeMap.Entry<K, V> subHigher(K key);

        TreeMap.Entry<K, V> subFloor(K key);

        TreeMap.Entry<K, V> subLower(K key);
    }

    static final class DescendingSubMap<K, V> extends NavigableSubMap<K, V> {

        private static final long serialVersionUID = 912986545866120460L;

        DescendingSubMap(TreeMap<K, V> m, boolean fromStart, K lo, boolean loInclusive, boolean toEnd, K hi, boolean hiInclusive) {
            super(m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive);
        }

        private final Comparator<? super K> reverseComparator = Collections.reverseOrder(m.comparator);

        public Comparator<? super K> comparator();

        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);

        public NavigableMap<K, V> descendingMap();

        Iterator<K> keyIterator();

        Spliterator<K> keySpliterator();

        Iterator<K> descendingKeyIterator();

        final class DescendingEntrySetView extends EntrySetView {

            public Iterator<Map.Entry<K, V>> iterator();
        }

        public Set<Map.Entry<K, V>> entrySet();

        TreeMap.Entry<K, V> subLowest();

        TreeMap.Entry<K, V> subHighest();

        TreeMap.Entry<K, V> subCeiling(K key);

        TreeMap.Entry<K, V> subHigher(K key);

        TreeMap.Entry<K, V> subFloor(K key);

        TreeMap.Entry<K, V> subLower(K key);
    }

    private class SubMap extends AbstractMap<K, V> implements SortedMap<K, V>, java.io.Serializable {

        private static final long serialVersionUID = -6520786458950516097L;

        private boolean fromStart = false, toEnd = false;

        private K fromKey, toKey;

        private Object readResolve();

        public Set<Map.Entry<K, V>> entrySet();

        public K lastKey();

        public K firstKey();

        public SortedMap<K, V> subMap(K fromKey, K toKey);

        public SortedMap<K, V> headMap(K toKey);

        public SortedMap<K, V> tailMap(K fromKey);

        public Comparator<? super K> comparator();
    }

    private static final boolean RED = false;

    private static final boolean BLACK = true;

    static final class Entry<K, V> implements Map.Entry<K, V> {

        K key;

        V value;

        Entry<K, V> left;

        Entry<K, V> right;

        Entry<K, V> parent;

        boolean color = BLACK;

        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey();

        public V getValue();

        public V setValue(V value);

        public boolean equals(Object o);

        public int hashCode();

        public String toString();
    }

    final Entry<K, V> getFirstEntry();

    final Entry<K, V> getLastEntry();

    static <K, V> TreeMap.Entry<K, V> successor(Entry<K, V> t);

    static <K, V> Entry<K, V> predecessor(Entry<K, V> t);

    private static <K, V> boolean colorOf(Entry<K, V> p);

    private static <K, V> Entry<K, V> parentOf(Entry<K, V> p);

    private static <K, V> void setColor(Entry<K, V> p, boolean c);

    private static <K, V> Entry<K, V> leftOf(Entry<K, V> p);

    private static <K, V> Entry<K, V> rightOf(Entry<K, V> p);

    private void rotateLeft(Entry<K, V> p);

    private void rotateRight(Entry<K, V> p);

    private void fixAfterInsertion(Entry<K, V> x);

    private void deleteEntry(Entry<K, V> p);

    private void fixAfterDeletion(Entry<K, V> x);

    private static final long serialVersionUID = 919286545866124006L;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(final java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    void readTreeSet(int size, java.io.ObjectInputStream s, V defaultVal) throws java.io.IOException, ClassNotFoundException;

    void addAllForTreeSet(SortedSet<? extends K> set, V defaultVal);

    private void buildFromSorted(int size, Iterator<?> it, java.io.ObjectInputStream str, V defaultVal) throws java.io.IOException, ClassNotFoundException;

    @SuppressWarnings("unchecked")
    private final Entry<K, V> buildFromSorted(int level, int lo, int hi, int redLevel, Iterator<?> it, java.io.ObjectInputStream str, V defaultVal) throws java.io.IOException, ClassNotFoundException;

    private static int computeRedLevel(int size);

    static <K> Spliterator<K> keySpliteratorFor(NavigableMap<K, ?> m);

    final Spliterator<K> keySpliterator();

    final Spliterator<K> descendingKeySpliterator();

    static class TreeMapSpliterator<K, V> {

        final TreeMap<K, V> tree;

        TreeMap.Entry<K, V> current;

        TreeMap.Entry<K, V> fence;

        int side;

        int est;

        int expectedModCount;

        TreeMapSpliterator(TreeMap<K, V> tree, TreeMap.Entry<K, V> origin, TreeMap.Entry<K, V> fence, int side, int est, int expectedModCount) {
            this.tree = tree;
            this.current = origin;
            this.fence = fence;
            this.side = side;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getEstimate();

        public final long estimateSize();
    }

    static final class KeySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<K> {

        KeySpliterator(TreeMap<K, V> tree, TreeMap.Entry<K, V> origin, TreeMap.Entry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();

        public final Comparator<? super K> getComparator();
    }

    static final class DescendingKeySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<K> {

        DescendingKeySpliterator(TreeMap<K, V> tree, TreeMap.Entry<K, V> origin, TreeMap.Entry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        public DescendingKeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<V> {

        ValueSpliterator(TreeMap<K, V> tree, TreeMap.Entry<K, V> origin, TreeMap.Entry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {

        EntrySpliterator(TreeMap<K, V> tree, TreeMap.Entry<K, V> origin, TreeMap.Entry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public int characteristics();

        @Override
        public Comparator<Map.Entry<K, V>> getComparator();
    }
}
