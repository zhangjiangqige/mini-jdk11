package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.lang.ref.WeakReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@CFComment({ "lock: permits null keys and values" })
@AnnotatedFor({ "lock", "index" })
public class WeakHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry<K, V>[] table;

    private int size;

    private int threshold;

    private final float loadFactor;

    private final ReferenceQueue<Object> queue = new ReferenceQueue<>();

    int modCount;

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] newTable(int n);

    public WeakHashMap(@NonNegative int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Initial Capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
        int capacity = 1;
        while (capacity < initialCapacity) capacity <<= 1;
        table = newTable(capacity);
        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
    }

    public WeakHashMap(@NonNegative int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public WeakHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public WeakHashMap(Map<? extends K, ? extends V> m) {
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    private static final Object NULL_KEY = new Object();

    private static Object maskNull(Object key);

    static Object unmaskNull(Object key);

    private static boolean eq(Object x, Object y);

    final int hash(Object k);

    private static int indexFor(int h, int length);

    private void expungeStaleEntries();

    private Entry<K, V>[] getTable();

    @Pure
    @NonNegative
    public int size(@GuardSatisfied WeakHashMap<K, V> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied WeakHashMap<K, V> this);

    @Pure
    @Nullable
    public V get(@GuardSatisfied WeakHashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied WeakHashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    Entry<K, V> getEntry(Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied WeakHashMap<K, V> this, K key, V value);

    void resize(int newCapacity);

    private void transfer(Entry<K, V>[] src, Entry<K, V>[] dest);

    public void putAll(@GuardSatisfied WeakHashMap<K, V> this, Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(@GuardSatisfied WeakHashMap<K, V> this, @Nullable Object key);

    boolean removeMapping(Object o);

    public void clear(@GuardSatisfied WeakHashMap<K, V> this);

    @Pure
    public boolean containsValue(@GuardSatisfied WeakHashMap<K, V> this, @GuardSatisfied @Nullable Object value);

    private boolean containsNullValue();

    private static class Entry<K, V> extends WeakReference<Object> implements Map.Entry<K, V> {

        V value;

        final int hash;

        Entry<K, V> next;

        Entry(Object key, V value, ReferenceQueue<Object> queue, int hash, Entry<K, V> next) {
            super(key, queue);
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @SuppressWarnings("unchecked")
        public K getKey();

        public V getValue();

        public V setValue(V newValue);

        public boolean equals(Object o);

        public int hashCode();

        public String toString();
    }

    private abstract class HashIterator<T> implements Iterator<T> {

        private int index;

        private Entry<K, V> entry;

        private Entry<K, V> lastReturned;

        private int expectedModCount = modCount;

        private Object nextKey;

        private Object currentKey;

        HashIterator() {
            index = isEmpty() ? 0 : table.length;
        }

        public boolean hasNext();

        protected Entry<K, V> nextEntry();

        public void remove();
    }

    private class ValueIterator extends HashIterator<V> {

        public V next();
    }

    private class KeyIterator extends HashIterator<K> {

        public K next();
    }

    private class EntryIterator extends HashIterator<Map.Entry<K, V>> {

        public Map.Entry<K, V> next();
    }

    private transient Set<Map.Entry<K, V>> entrySet;

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied WeakHashMap<K, V> this);

    private class KeySet extends AbstractSet<K> {

        @SideEffectFree
        public Iterator<K> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        public boolean remove(Object o);

        public void clear();

        @SideEffectFree
        public Spliterator<K> spliterator();
    }

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied WeakHashMap<K, V> this);

    private class Values extends AbstractCollection<V> {

        @SideEffectFree
        public Iterator<V> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        public void clear();

        @SideEffectFree
        public Spliterator<V> spliterator();
    }

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied WeakHashMap<K, V> this);

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        public boolean contains(Object o);

        public boolean remove(Object o);

        @NonNegative
        public int size();

        public void clear();

        private List<Map.Entry<K, V>> deepCopy();

        @SideEffectFree
        public Object[] toArray();

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        @SideEffectFree
        public Spliterator<Map.Entry<K, V>> spliterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @SuppressWarnings("unchecked")
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    static class WeakHashMapSpliterator<K, V> {

        final WeakHashMap<K, V> map;

        WeakHashMap.Entry<K, V> current;

        int index;

        int fence;

        int est;

        int expectedModCount;

        WeakHashMapSpliterator(WeakHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence();

        public final long estimateSize();
    }

    static final class KeySpliterator<K, V> extends WeakHashMapSpliterator<K, V> implements Spliterator<K> {

        KeySpliterator(WeakHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends WeakHashMapSpliterator<K, V> implements Spliterator<V> {

        ValueSpliterator(WeakHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends WeakHashMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {

        EntrySpliterator(WeakHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public int characteristics();
    }
}
