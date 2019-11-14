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
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "lock", "nullness", "index" })
public class HashMap<K extends @Nullable Object, V extends @Nullable Object> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int TREEIFY_THRESHOLD = 8;

    static final int UNTREEIFY_THRESHOLD = 6;

    static final int MIN_TREEIFY_CAPACITY = 64;

    static class Node<K, V> implements Map.Entry<K, V> {

        final int hash;

        final K key;

        V value;

        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey();

        public final V getValue();

        public final String toString();

        public final int hashCode();

        public final V setValue(V newValue);

        public final boolean equals(Object o);
    }

    static final int hash(Object key);

    static Class<?> comparableClassFor(Object x);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static int compareComparables(Class<?> kc, Object k, Object x);

    static final int tableSizeFor(int cap);

    transient Node<K, V>[] table;

    transient Set<Map.Entry<K, V>> entrySet;

    transient int size;

    transient int modCount;

    int threshold;

    final float loadFactor;

    public HashMap(@NonNegative int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public HashMap(@NonNegative int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied HashMap<K, V> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied HashMap<K, V> this);

    @Pure
    @Nullable
    public V get(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    final Node<K, V> getNode(int hash, Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied HashMap<K, V> this, K key, V value);

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict);

    @SuppressWarnings("cast.unsafe")
    final Node<K, V>[] resize();

    final void treeifyBin(Node<K, V>[] tab, int hash);

    public void putAll(@GuardSatisfied HashMap<K, V> this, Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(@GuardSatisfied HashMap<K, V> this, @Nullable Object key);

    final Node<K, V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable);

    public void clear(@GuardSatisfied HashMap<K, V> this);

    @Pure
    public boolean containsValue(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable Object value);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied HashMap<K, V> this);

    final class KeySet extends AbstractSet<K> {

        @NonNegative
        public final int size();

        public final void clear();

        @SideEffectFree
        public final Iterator<K> iterator();

        public final boolean contains(Object o);

        public final boolean remove(Object key);

        @SideEffectFree
        public final Spliterator<K> spliterator();

        public final void forEach(Consumer<? super K> action);
    }

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied HashMap<K, V> this);

    final class Values extends AbstractCollection<V> {

        @NonNegative
        public final int size();

        public final void clear();

        @SideEffectFree
        public final Iterator<V> iterator();

        public final boolean contains(Object o);

        @SideEffectFree
        public final Spliterator<V> spliterator();

        public final void forEach(Consumer<? super V> action);
    }

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied HashMap<K, V> this);

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @NonNegative
        public final int size();

        public final void clear();

        @SideEffectFree
        public final Iterator<Map.Entry<K, V>> iterator();

        public final boolean contains(Object o);

        public final boolean remove(Object o);

        @SideEffectFree
        public final Spliterator<Map.Entry<K, V>> spliterator();

        public final void forEach(Consumer<? super Map.Entry<K, V>> action);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Override
    public V putIfAbsent(K key, V value);

    @Override
    public boolean remove(Object key, Object value);

    @Override
    public boolean replace(K key, V oldValue, V newValue);

    @Override
    public V replace(K key, V value);

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Override
    public Object clone(@GuardSatisfied HashMap<K, V> this);

    final float loadFactor();

    final int capacity();

    private void writeObject(java.io.ObjectOutputStream s) throws IOException;

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException;

    abstract class HashIterator {

        Node<K, V> next;

        Node<K, V> current;

        int expectedModCount;

        int index;

        HashIterator() {
            expectedModCount = modCount;
            Node<K, V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext();

        final Node<K, V> nextNode();

        public final void remove();
    }

    final class KeyIterator extends HashIterator implements Iterator<K> {

        public final K next();
    }

    final class ValueIterator extends HashIterator implements Iterator<V> {

        public final V next();
    }

    final class EntryIterator extends HashIterator implements Iterator<Map.Entry<K, V>> {

        public final Map.Entry<K, V> next();
    }

    static class HashMapSpliterator<K, V> {

        final HashMap<K, V> map;

        Node<K, V> current;

        int index;

        int fence;

        int est;

        int expectedModCount;

        HashMapSpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence();

        public final long estimateSize();
    }

    static final class KeySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<K> {

        KeySpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<V> {

        ValueSpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {

        EntrySpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public int characteristics();
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next);

    Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next);

    TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next);

    TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next);

    void reinitialize();

    void afterNodeAccess(Node<K, V> p);

    void afterNodeInsertion(boolean evict);

    void afterNodeRemoval(Node<K, V> p);

    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException;

    static final class TreeNode<K, V> extends LinkedHashMap.Entry<K, V> {

        TreeNode<K, V> parent;

        TreeNode<K, V> left;

        TreeNode<K, V> right;

        TreeNode<K, V> prev;

        boolean red;

        TreeNode(int hash, K key, V val, Node<K, V> next) {
            super(hash, key, val, next);
        }

        final TreeNode<K, V> root();

        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root);

        final TreeNode<K, V> find(int h, Object k, Class<?> kc);

        final TreeNode<K, V> getTreeNode(int h, Object k);

        static int tieBreakOrder(Object a, Object b);

        final void treeify(Node<K, V>[] tab);

        final Node<K, V> untreeify(HashMap<K, V> map);

        final TreeNode<K, V> putTreeVal(HashMap<K, V> map, Node<K, V>[] tab, int h, K k, V v);

        final void removeTreeNode(HashMap<K, V> map, Node<K, V>[] tab, boolean movable);

        final void split(HashMap<K, V> map, Node<K, V>[] tab, int index, int bit);

        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> boolean checkInvariants(TreeNode<K, V> t);
    }
}
