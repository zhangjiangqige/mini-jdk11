package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;
import jdk.internal.misc.SharedSecrets;

@CFComment({ "lock: This collection can only contain nonnull values" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class Hashtable<K extends @NonNull Object, V extends @NonNull Object> extends Dictionary<K, V> implements Map<K, V>, Cloneable, java.io.Serializable {

    private transient Entry<?, ?>[] table;

    private transient int count;

    private int threshold;

    private float loadFactor;

    private transient int modCount = 0;

    private static final long serialVersionUID = 1421746759512286392L;

    public Hashtable(@NonNegative int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load: " + loadFactor);
        if (initialCapacity == 0)
            initialCapacity = 1;
        this.loadFactor = loadFactor;
        table = new Entry<?, ?>[initialCapacity];
        threshold = (int) Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
    }

    public Hashtable(@NonNegative int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public Hashtable() {
        this(11, 0.75f);
    }

    public Hashtable(Map<? extends K, ? extends V> t) {
        this(Math.max(2 * t.size(), 11), 0.75f);
        putAll(t);
    }

    Hashtable(Void dummy) {
    }

    @Pure
    @NonNegative
    public synchronized int size(@GuardSatisfied Hashtable<K, V> this);

    @Pure
    public synchronized boolean isEmpty(@GuardSatisfied Hashtable<K, V> this);

    public synchronized Enumeration<@KeyFor({ "this" }) K> keys();

    public synchronized Enumeration<V> elements();

    @Pure
    public synchronized boolean contains(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied Object value);

    @Pure
    public boolean containsValue(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @Nullable Object value);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public synchronized boolean containsKey(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @Nullable Object key);

    @Pure
    @SuppressWarnings("unchecked")
    @Nullable
    public synchronized V get(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied Object key);

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    @SuppressWarnings("unchecked")
    protected void rehash();

    private void addEntry(int hash, K key, V value, int index);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public synchronized V put(@GuardSatisfied Hashtable<K, V> this, K key, V value);

    @Nullable
    public synchronized V remove(@GuardSatisfied Hashtable<K, V> this, Object key);

    public synchronized void putAll(@GuardSatisfied Hashtable<K, V> this, Map<? extends K, ? extends V> t);

    public synchronized void clear(@GuardSatisfied Hashtable<K, V> this);

    @SideEffectFree
    public synchronized Object clone(@GuardSatisfied Hashtable<K, V> this);

    final Hashtable<?, ?> cloneHashtable();

    public synchronized String toString(@GuardSatisfied Hashtable<K, V> this);

    private <T> Enumeration<T> getEnumeration(int type);

    private <T> Iterator<T> getIterator(int type);

    private transient volatile Set<K> keySet;

    private transient volatile Set<Map.Entry<K, V>> entrySet;

    private transient volatile Collection<V> values;

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied Hashtable<K, V> this);

    private class KeySet extends AbstractSet<K> {

        @SideEffectFree
        public Iterator<K> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        public boolean remove(Object o);

        public void clear();
    }

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied Hashtable<K, V> this);

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        public boolean add(Map.Entry<K, V> o);

        public boolean contains(Object o);

        public boolean remove(Object o);

        @NonNegative
        public int size();

        public void clear();
    }

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied Hashtable<K, V> this);

    private class ValueCollection extends AbstractCollection<V> {

        @SideEffectFree
        public Iterator<V> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        public void clear();
    }

    @Pure
    public synchronized boolean equals(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public synchronized int hashCode(@GuardSatisfied Hashtable<K, V> this);

    @Override
    public synchronized V getOrDefault(Object key, V defaultValue);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void forEach(BiConsumer<? super K, ? super V> action);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Override
    public synchronized V putIfAbsent(K key, V value);

    @Override
    public synchronized boolean remove(Object key, Object value);

    @Override
    public synchronized boolean replace(K key, V oldValue, V newValue);

    @Override
    public synchronized V replace(K key, V value);

    @Override
    public synchronized V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

    @Override
    public synchronized V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public synchronized V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public synchronized V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

    private void writeObject(java.io.ObjectOutputStream s) throws IOException;

    void writeHashtable(java.io.ObjectOutputStream s) throws IOException;

    final void defaultWriteHashtable(java.io.ObjectOutputStream s, int length, float loadFactor) throws IOException;

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException;

    void readHashtable(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException;

    private void reconstitutionPut(Entry<?, ?>[] tab, K key, V value) throws StreamCorruptedException;

    private static class Entry<K, V> implements Map.Entry<K, V> {

        final int hash;

        final K key;

        V value;

        Entry<K, V> next;

        protected Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @SideEffectFree
        @SuppressWarnings("unchecked")
        protected Object clone();

        public K getKey();

        public V getValue();

        public V setValue(V value);

        public boolean equals(Object o);

        public int hashCode();

        public String toString();
    }

    private static final int KEYS = 0;

    private static final int VALUES = 1;

    private static final int ENTRIES = 2;

    private class Enumerator<T> implements Enumeration<T>, Iterator<T> {

        final Entry<?, ?>[] table = Hashtable.this.table;

        int index = table.length;

        Entry<?, ?> entry;

        Entry<?, ?> lastReturned;

        final int type;

        final boolean iterator;

        protected int expectedModCount = Hashtable.this.modCount;

        Enumerator(int type, boolean iterator) {
            this.type = type;
            this.iterator = iterator;
        }

        public boolean hasMoreElements();

        @SuppressWarnings("unchecked")
        public T nextElement();

        public boolean hasNext();

        public T next();

        public void remove();
    }
}
