package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.lang.reflect.Array;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import jdk.internal.misc.SharedSecrets;

@CFComment({ "lock/nullness: This collection can only contain null values" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class IdentityHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, java.io.Serializable, Cloneable {

    private static final int DEFAULT_CAPACITY = 32;

    private static final int MINIMUM_CAPACITY = 4;

    private static final int MAXIMUM_CAPACITY = 1 << 29;

    transient Object[] table;

    int size;

    transient int modCount;

    static final Object NULL_KEY = new Object();

    private static Object maskNull(Object key);

    static final Object unmaskNull(Object key);

    public IdentityHashMap() {
        init(DEFAULT_CAPACITY);
    }

    public IdentityHashMap(@NonNegative int expectedMaxSize) {
        if (expectedMaxSize < 0)
            throw new IllegalArgumentException("expectedMaxSize is negative: " + expectedMaxSize);
        init(capacity(expectedMaxSize));
    }

    private static int capacity(int expectedMaxSize);

    private void init(int initCapacity);

    public IdentityHashMap(Map<? extends K, ? extends V> m) {
        this((int) ((1 + m.size()) * 1.1));
        putAll(m);
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied IdentityHashMap<K, V> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied IdentityHashMap<K, V> this);

    private static int hash(Object x, int length);

    private static int nextKeyIndex(int i, int len);

    @Pure
    @SuppressWarnings("unchecked")
    public V get(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object key);

    @Pure
    public boolean containsValue(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object value);

    private boolean containsMapping(Object key, Object value);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    public V put(@GuardSatisfied IdentityHashMap<K, V> this, K key, V value);

    private boolean resize(int newCapacity);

    public void putAll(@GuardSatisfied IdentityHashMap<K, V> this, Map<? extends K, ? extends V> m);

    public V remove(@GuardSatisfied IdentityHashMap<K, V> this, Object key);

    private boolean removeMapping(Object key, Object value);

    private void closeDeletion(int d);

    public void clear(@GuardSatisfied IdentityHashMap<K, V> this);

    @Pure
    public boolean equals(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object o);

    @Pure
    public int hashCode(@GuardSatisfied IdentityHashMap<K, V> this);

    @SideEffectFree
    public Object clone(@GuardSatisfied IdentityHashMap<K, V> this);

    private abstract class IdentityHashMapIterator<T> implements Iterator<T> {

        int index = (size != 0 ? 0 : table.length);

        int expectedModCount = modCount;

        int lastReturnedIndex = -1;

        boolean indexValid;

        Object[] traversalTable = table;

        public boolean hasNext();

        protected int nextIndex();

        public void remove();
    }

    private class KeyIterator extends IdentityHashMapIterator<K> {

        @SuppressWarnings("unchecked")
        public K next();
    }

    private class ValueIterator extends IdentityHashMapIterator<V> {

        @SuppressWarnings("unchecked")
        public V next();
    }

    private class EntryIterator extends IdentityHashMapIterator<Map.Entry<K, V>> {

        private Entry lastReturnedEntry;

        public Map.Entry<K, V> next();

        public void remove();

        private class Entry implements Map.Entry<K, V> {

            private int index;

            private Entry(int index) {
                this.index = index;
            }

            @SuppressWarnings("unchecked")
            public K getKey();

            @SuppressWarnings("unchecked")
            public V getValue();

            @SuppressWarnings("unchecked")
            public V setValue(V value);

            public boolean equals(Object o);

            public int hashCode();

            public String toString();

            private void checkIndexForEntryUse();
        }
    }

    private transient Set<Map.Entry<K, V>> entrySet;

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied IdentityHashMap<K, V> this);

    private class KeySet extends AbstractSet<K> {

        @SideEffectFree
        public Iterator<K> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        public boolean remove(Object o);

        public boolean removeAll(Collection<?> c);

        public void clear();

        public int hashCode();

        @SideEffectFree
        public Object[] toArray();

        @SideEffectFree
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        @SideEffectFree
        public Spliterator<K> spliterator();
    }

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied IdentityHashMap<K, V> this);

    private class Values extends AbstractCollection<V> {

        @SideEffectFree
        public Iterator<V> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        public boolean remove(Object o);

        public void clear();

        @SideEffectFree
        public Object[] toArray();

        @SideEffectFree
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        @SideEffectFree
        public Spliterator<V> spliterator();
    }

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied IdentityHashMap<K, V> this);

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        public boolean contains(Object o);

        public boolean remove(Object o);

        @NonNegative
        public int size();

        public void clear();

        public boolean removeAll(Collection<?> c);

        @SideEffectFree
        public Object[] toArray();

        @SideEffectFree
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        @SideEffectFree
        public Spliterator<Map.Entry<K, V>> spliterator();
    }

    private static final long serialVersionUID = 8188218128353913216L;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    private void putForCreate(K key, V value) throws java.io.StreamCorruptedException;

    @SuppressWarnings("unchecked")
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @SuppressWarnings("unchecked")
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    static class IdentityHashMapSpliterator<K, V> {

        final IdentityHashMap<K, V> map;

        int index;

        int fence;

        int est;

        int expectedModCount;

        IdentityHashMapSpliterator(IdentityHashMap<K, V> map, int origin, int fence, int est, int expectedModCount) {
            this.map = map;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence();

        public final long estimateSize();
    }

    static final class KeySpliterator<K, V> extends IdentityHashMapSpliterator<K, V> implements Spliterator<K> {

        KeySpliterator(IdentityHashMap<K, V> map, int origin, int fence, int est, int expectedModCount) {
            super(map, origin, fence, est, expectedModCount);
        }

        public KeySpliterator<K, V> trySplit();

        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super K> action);

        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends IdentityHashMapSpliterator<K, V> implements Spliterator<V> {

        ValueSpliterator(IdentityHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends IdentityHashMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {

        EntrySpliterator(IdentityHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public int characteristics();
    }
}
