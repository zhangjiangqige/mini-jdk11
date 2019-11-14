package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.io.IOException;

@AnnotatedFor({ "lock", "nullness", "index" })
public class LinkedHashMap<K, V> extends HashMap<K, V> implements Map<K, V> {

    static class Entry<K, V> extends HashMap.Node<K, V> {

        Entry<K, V> before, after;

        Entry(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }
    }

    private static final long serialVersionUID = 3801124242820219131L;

    transient LinkedHashMap.Entry<K, V> head;

    transient LinkedHashMap.Entry<K, V> tail;

    final boolean accessOrder;

    private void linkNodeLast(LinkedHashMap.Entry<K, V> p);

    private void transferLinks(LinkedHashMap.Entry<K, V> src, LinkedHashMap.Entry<K, V> dst);

    void reinitialize();

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> e);

    Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next);

    TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next);

    TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next);

    void afterNodeRemoval(Node<K, V> e);

    void afterNodeInsertion(boolean evict);

    void afterNodeAccess(Node<K, V> e);

    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException;

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        accessOrder = false;
    }

    public LinkedHashMap(@NonNegative int initialCapacity) {
        super(initialCapacity);
        accessOrder = false;
    }

    public LinkedHashMap() {
        super();
        accessOrder = false;
    }

    public LinkedHashMap(Map<? extends K, ? extends V> m) {
        super();
        accessOrder = false;
        putMapEntries(m, false);
    }

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder;
    }

    @Pure
    public boolean containsValue(@GuardSatisfied LinkedHashMap<K, V> this, @GuardSatisfied @Nullable Object value);

    @Pure
    @Nullable
    public V get(@GuardSatisfied LinkedHashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    public V getOrDefault(Object key, V defaultValue);

    public void clear(@GuardSatisfied LinkedHashMap<K, V> this);

    protected boolean removeEldestEntry(@GuardSatisfied LinkedHashMap<K, V> this, Map.Entry<K, V> eldest);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet();

    final class LinkedKeySet extends AbstractSet<K> {

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

    public Collection<V> values();

    final class LinkedValues extends AbstractCollection<V> {

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
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied LinkedHashMap<K, V> this);

    final class LinkedEntrySet extends AbstractSet<Map.Entry<K, V>> {

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

    public void forEach(BiConsumer<? super K, ? super V> action);

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    abstract class LinkedHashIterator {

        LinkedHashMap.Entry<K, V> next;

        LinkedHashMap.Entry<K, V> current;

        int expectedModCount;

        LinkedHashIterator() {
            next = head;
            expectedModCount = modCount;
            current = null;
        }

        public final boolean hasNext();

        final LinkedHashMap.Entry<K, V> nextNode();

        public final void remove();
    }

    final class LinkedKeyIterator extends LinkedHashIterator implements Iterator<K> {

        public final K next();
    }

    final class LinkedValueIterator extends LinkedHashIterator implements Iterator<V> {

        public final V next();
    }

    final class LinkedEntryIterator extends LinkedHashIterator implements Iterator<Map.Entry<K, V>> {

        public final Map.Entry<K, V> next();
    }
}
