package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "nullness" })
public class ConcurrentHashMap<K extends @NonNull Object, V extends @NonNull Object> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {

    private static final long serialVersionUID = 7249069246763182397L;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final int DEFAULT_CAPACITY = 16;

    static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;

    private static final float LOAD_FACTOR = 0.75f;

    static final int TREEIFY_THRESHOLD = 8;

    static final int UNTREEIFY_THRESHOLD = 6;

    static final int MIN_TREEIFY_CAPACITY = 64;

    private static final int MIN_TRANSFER_STRIDE = 16;

    private static final int RESIZE_STAMP_BITS = 16;

    private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;

    private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;

    static final int MOVED = -1;

    static final int TREEBIN = -2;

    static final int RESERVED = -3;

    static final int HASH_BITS = 0x7fffffff;

    static final int NCPU = Runtime.getRuntime().availableProcessors();

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("segments", Segment[].class), new ObjectStreamField("segmentMask", Integer.TYPE), new ObjectStreamField("segmentShift", Integer.TYPE) };

    static class Node<K, V> implements Map.Entry<K, V> {

        final int hash;

        final K key;

        volatile V val;

        volatile Node<K, V> next;

        Node(int hash, K key, V val) {
            this.hash = hash;
            this.key = key;
            this.val = val;
        }

        Node(int hash, K key, V val, Node<K, V> next) {
            this(hash, key, val);
            this.next = next;
        }

        public final K getKey();

        public final V getValue();

        public final int hashCode();

        public final String toString();

        public final V setValue(V value);

        public final boolean equals(Object o);

        Node<K, V> find(int h, Object k);
    }

    static final int spread(int h);

    private static final int tableSizeFor(int c);

    static Class<?> comparableClassFor(Object x);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static int compareComparables(Class<?> kc, Object k, Object x);

    @SuppressWarnings("unchecked")
    static final <K, V> Node<K, V> tabAt(Node<K, V>[] tab, int i);

    static final <K, V> boolean casTabAt(Node<K, V>[] tab, int i, Node<K, V> c, Node<K, V> v);

    static final <K, V> void setTabAt(Node<K, V>[] tab, int i, Node<K, V> v);

    transient volatile Node<K, V>[] table;

    private transient volatile Node<K, V>[] nextTable;

    private transient volatile long baseCount;

    private transient volatile int sizeCtl;

    private transient volatile int transferIndex;

    private transient volatile int cellsBusy;

    private transient volatile CounterCell[] counterCells;

    private transient KeySetView<K, V> keySet;

    private transient ValuesView<K, V> values;

    private transient EntrySetView<K, V> entrySet;

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int initialCapacity) {
        this(initialCapacity, LOAD_FACTOR, 1);
    }

    public ConcurrentHashMap(Map<? extends K, ? extends V> m) {
        this.sizeCtl = DEFAULT_CAPACITY;
        putAll(m);
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, 1);
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        if (!(loadFactor > 0.0f) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalArgumentException();
        if (initialCapacity < concurrencyLevel)
            initialCapacity = concurrencyLevel;
        long size = (long) (1.0 + (long) initialCapacity / loadFactor);
        int cap = (size >= (long) MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : tableSizeFor((int) size);
        this.sizeCtl = cap;
    }

    @Pure
    public int size();

    @Pure
    public boolean isEmpty();

    @Pure
    @Nullable
    public V get(Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(Object key);

    @Pure
    public boolean containsValue(Object value);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(K key, V value);

    final V putVal(K key, V value, boolean onlyIfAbsent);

    public void putAll(Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(Object key);

    final V replaceNode(Object key, V value, Object cv);

    public void clear();

    @SideEffectFree
    public KeySetView<@KeyFor({ "this" }) K, V> keySet();

    @SideEffectFree
    public Collection<V> values();

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet();

    public int hashCode();

    public String toString();

    public boolean equals(Object o);

    static class Segment<K, V> extends ReentrantLock implements Serializable {

        private static final long serialVersionUID = 2249069246763182397L;

        final float loadFactor;

        Segment(float lf) {
            this.loadFactor = lf;
        }
    }

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V putIfAbsent(K key, V value);

    public boolean remove(Object key, Object value);

    public boolean replace(K key, V oldValue, V newValue);

    @Nullable
    public V replace(K key, V value);

    public V getOrDefault(Object key, V defaultValue);

    public void forEach(BiConsumer<? super K, ? super V> action);

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    boolean removeEntryIf(Predicate<? super Entry<K, V>> function);

    boolean removeValueIf(Predicate<? super V> function);

    @PolyNull
    public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

    @PolyNull
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

    @PolyNull
    public V compute(K key, BiFunction<? super K, ? super @Nullable V, ? extends @PolyNull V> remappingFunction);

    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

    @Pure
    public boolean contains(Object value);

    @SideEffectFree
    public Enumeration<@KeyFor({ "this" }) K> keys();

    @SideEffectFree
    public Enumeration<V> elements();

    public long mappingCount();

    public static <K> KeySetView<K, Boolean> newKeySet();

    public static <K> KeySetView<K, Boolean> newKeySet(int initialCapacity);

    public KeySetView<K, V> keySet(V mappedValue);

    static final class ForwardingNode<K, V> extends Node<K, V> {

        final Node<K, V>[] nextTable;

        ForwardingNode(Node<K, V>[] tab) {
            super(MOVED, null, null);
            this.nextTable = tab;
        }

        Node<K, V> find(int h, Object k);
    }

    static final class ReservationNode<K, V> extends Node<K, V> {

        ReservationNode() {
            super(RESERVED, null, null);
        }

        Node<K, V> find(int h, Object k);
    }

    static final int resizeStamp(int n);

    private final Node<K, V>[] initTable();

    private final void addCount(long x, int check);

    final Node<K, V>[] helpTransfer(Node<K, V>[] tab, Node<K, V> f);

    private final void tryPresize(int size);

    private final void transfer(Node<K, V>[] tab, Node<K, V>[] nextTab);

    @jdk.internal.vm.annotation.Contended
    static final class CounterCell {

        volatile long value;

        CounterCell(long x) {
            value = x;
        }
    }

    final long sumCount();

    private final void fullAddCount(long x, boolean wasUncontended);

    private final void treeifyBin(Node<K, V>[] tab, int index);

    static <K, V> Node<K, V> untreeify(Node<K, V> b);

    static final class TreeNode<K, V> extends Node<K, V> {

        TreeNode<K, V> parent;

        TreeNode<K, V> left;

        TreeNode<K, V> right;

        TreeNode<K, V> prev;

        boolean red;

        TreeNode(int hash, K key, V val, Node<K, V> next, TreeNode<K, V> parent) {
            super(hash, key, val, next);
            this.parent = parent;
        }

        Node<K, V> find(int h, Object k);

        final TreeNode<K, V> findTreeNode(int h, Object k, Class<?> kc);
    }

    static final class TreeBin<K, V> extends Node<K, V> {

        TreeNode<K, V> root;

        volatile TreeNode<K, V> first;

        volatile Thread waiter;

        volatile int lockState;

        static final int WRITER = 1;

        static final int WAITER = 2;

        static final int READER = 4;

        static int tieBreakOrder(Object a, Object b);

        TreeBin(TreeNode<K, V> b) {
            super(TREEBIN, null, null);
            this.first = b;
            TreeNode<K, V> r = null;
            for (TreeNode<K, V> x = b, next; x != null; x = next) {
                next = (TreeNode<K, V>) x.next;
                x.left = x.right = null;
                if (r == null) {
                    x.parent = null;
                    x.red = false;
                    r = x;
                } else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (TreeNode<K, V> p = r; ; ) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h)
                            dir = -1;
                        else if (ph < h)
                            dir = 1;
                        else if ((kc == null && (kc = comparableClassFor(k)) == null) || (dir = compareComparables(kc, k, pk)) == 0)
                            dir = tieBreakOrder(k, pk);
                        TreeNode<K, V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if (dir <= 0)
                                xp.left = x;
                            else
                                xp.right = x;
                            r = balanceInsertion(r, x);
                            break;
                        }
                    }
                }
            }
            this.root = r;
            assert checkInvariants(root);
        }

        private final void lockRoot();

        private final void unlockRoot();

        private final void contendedLock();

        final Node<K, V> find(int h, Object k);

        final TreeNode<K, V> putTreeVal(int h, K k, V v);

        final boolean removeTreeNode(TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> boolean checkInvariants(TreeNode<K, V> t);

        private static final Unsafe U = Unsafe.getUnsafe();

        private static final long LOCKSTATE = U.objectFieldOffset(TreeBin.class, "lockState");
    }

    static final class TableStack<K, V> {

        int length;

        int index;

        Node<K, V>[] tab;

        TableStack<K, V> next;
    }

    static class Traverser<K, V> {

        Node<K, V>[] tab;

        Node<K, V> next;

        TableStack<K, V> stack, spare;

        int index;

        int baseIndex;

        int baseLimit;

        final int baseSize;

        Traverser(Node<K, V>[] tab, int size, int index, int limit) {
            this.tab = tab;
            this.baseSize = size;
            this.baseIndex = this.index = index;
            this.baseLimit = limit;
            this.next = null;
        }

        final Node<K, V> advance();

        private void pushState(Node<K, V>[] t, int i, int n);

        private void recoverState(int n);
    }

    static class BaseIterator<K, V> extends Traverser<K, V> {

        final ConcurrentHashMap<K, V> map;

        Node<K, V> lastReturned;

        BaseIterator(Node<K, V>[] tab, int size, int index, int limit, ConcurrentHashMap<K, V> map) {
            super(tab, size, index, limit);
            this.map = map;
            advance();
        }

        public final boolean hasNext();

        public final boolean hasMoreElements();

        public final void remove();
    }

    static final class KeyIterator<K, V> extends BaseIterator<K, V> implements Iterator<K>, Enumeration<K> {

        KeyIterator(Node<K, V>[] tab, int size, int index, int limit, ConcurrentHashMap<K, V> map) {
            super(tab, size, index, limit, map);
        }

        public final K next();

        public final K nextElement();
    }

    static final class ValueIterator<K, V> extends BaseIterator<K, V> implements Iterator<V>, Enumeration<V> {

        ValueIterator(Node<K, V>[] tab, int size, int index, int limit, ConcurrentHashMap<K, V> map) {
            super(tab, size, index, limit, map);
        }

        public final V next();

        public final V nextElement();
    }

    static final class EntryIterator<K, V> extends BaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        EntryIterator(Node<K, V>[] tab, int size, int index, int limit, ConcurrentHashMap<K, V> map) {
            super(tab, size, index, limit, map);
        }

        public final Map.Entry<K, V> next();
    }

    static final class MapEntry<K, V> implements Map.Entry<K, V> {

        final K key;

        V val;

        final ConcurrentHashMap<K, V> map;

        MapEntry(K key, V val, ConcurrentHashMap<K, V> map) {
            this.key = key;
            this.val = val;
            this.map = map;
        }

        public K getKey();

        public V getValue();

        public int hashCode();

        public String toString();

        public boolean equals(Object o);

        public V setValue(V value);
    }

    static final class KeySpliterator<K, V> extends Traverser<K, V> implements Spliterator<K> {

        long est;

        KeySpliterator(Node<K, V>[] tab, int size, int index, int limit, long est) {
            super(tab, size, index, limit);
            this.est = est;
        }

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public long estimateSize();

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends Traverser<K, V> implements Spliterator<V> {

        long est;

        ValueSpliterator(Node<K, V>[] tab, int size, int index, int limit, long est) {
            super(tab, size, index, limit);
            this.est = est;
        }

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public long estimateSize();

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends Traverser<K, V> implements Spliterator<Map.Entry<K, V>> {

        final ConcurrentHashMap<K, V> map;

        long est;

        EntrySpliterator(Node<K, V>[] tab, int size, int index, int limit, long est, ConcurrentHashMap<K, V> map) {
            super(tab, size, index, limit);
            this.map = map;
            this.est = est;
        }

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public long estimateSize();

        public int characteristics();
    }

    final int batchFor(long b);

    public void forEach(long parallelismThreshold, BiConsumer<? super K, ? super V> action);

    public <U> void forEach(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> transformer, Consumer<? super U> action);

    public <U> U search(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> searchFunction);

    public <U> U reduce(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceToDouble(long parallelismThreshold, ToDoubleBiFunction<? super K, ? super V> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceToLong(long parallelismThreshold, ToLongBiFunction<? super K, ? super V> transformer, long basis, LongBinaryOperator reducer);

    public int reduceToInt(long parallelismThreshold, ToIntBiFunction<? super K, ? super V> transformer, int basis, IntBinaryOperator reducer);

    public void forEachKey(long parallelismThreshold, Consumer<? super K> action);

    public <U> void forEachKey(long parallelismThreshold, Function<? super K, ? extends U> transformer, Consumer<? super U> action);

    public <U> U searchKeys(long parallelismThreshold, Function<? super K, ? extends U> searchFunction);

    public K reduceKeys(long parallelismThreshold, BiFunction<? super K, ? super K, ? extends K> reducer);

    public <U> U reduceKeys(long parallelismThreshold, Function<? super K, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceKeysToDouble(long parallelismThreshold, ToDoubleFunction<? super K> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceKeysToLong(long parallelismThreshold, ToLongFunction<? super K> transformer, long basis, LongBinaryOperator reducer);

    public int reduceKeysToInt(long parallelismThreshold, ToIntFunction<? super K> transformer, int basis, IntBinaryOperator reducer);

    public void forEachValue(long parallelismThreshold, Consumer<? super V> action);

    public <U> void forEachValue(long parallelismThreshold, Function<? super V, ? extends U> transformer, Consumer<? super U> action);

    public <U> U searchValues(long parallelismThreshold, Function<? super V, ? extends U> searchFunction);

    public V reduceValues(long parallelismThreshold, BiFunction<? super V, ? super V, ? extends V> reducer);

    public <U> U reduceValues(long parallelismThreshold, Function<? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceValuesToDouble(long parallelismThreshold, ToDoubleFunction<? super V> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceValuesToLong(long parallelismThreshold, ToLongFunction<? super V> transformer, long basis, LongBinaryOperator reducer);

    public int reduceValuesToInt(long parallelismThreshold, ToIntFunction<? super V> transformer, int basis, IntBinaryOperator reducer);

    public void forEachEntry(long parallelismThreshold, Consumer<? super Map.Entry<K, V>> action);

    public <U> void forEachEntry(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> transformer, Consumer<? super U> action);

    public <U> U searchEntries(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> searchFunction);

    public Map.Entry<K, V> reduceEntries(long parallelismThreshold, BiFunction<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer);

    public <U> U reduceEntries(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceEntriesToDouble(long parallelismThreshold, ToDoubleFunction<Map.Entry<K, V>> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceEntriesToLong(long parallelismThreshold, ToLongFunction<Map.Entry<K, V>> transformer, long basis, LongBinaryOperator reducer);

    public int reduceEntriesToInt(long parallelismThreshold, ToIntFunction<Map.Entry<K, V>> transformer, int basis, IntBinaryOperator reducer);

    abstract static class CollectionView<K, V, E> implements Collection<E>, java.io.Serializable {

        private static final long serialVersionUID = 7249069246763182397L;

        final ConcurrentHashMap<K, V> map;

        CollectionView(ConcurrentHashMap<K, V> map) {
            this.map = map;
        }

        public ConcurrentHashMap<K, V> getMap();

        public final void clear();

        public final int size();

        public final boolean isEmpty();

        @SideEffectFree
        public abstract Iterator<E> iterator();

        public abstract boolean contains(Object o);

        public abstract boolean remove(Object o);

        private static final String OOME_MSG = "Required array size too large";

        @SideEffectFree
        public final Object[] toArray();

        @SideEffectFree
        @SuppressWarnings("unchecked")
        public final <T> T[] toArray(T[] a);

        public final String toString();

        public final boolean containsAll(Collection<?> c);

        public boolean removeAll(Collection<?> c);

        public final boolean retainAll(Collection<?> c);
    }

    public static class KeySetView<K, V> extends CollectionView<K, V, K> implements Set<K>, java.io.Serializable {

        private static final long serialVersionUID = 7249069246763182397L;

        private final V value;

        KeySetView(ConcurrentHashMap<K, V> map, V value) {
            super(map);
            this.value = value;
        }

        public V getMappedValue();

        public boolean contains(Object o);

        public boolean remove(Object o);

        @SideEffectFree
        public Iterator<K> iterator();

        public boolean add(K e);

        public boolean addAll(Collection<? extends K> c);

        public int hashCode();

        public boolean equals(Object o);

        @SideEffectFree
        public Spliterator<K> spliterator();

        public void forEach(Consumer<? super K> action);
    }

    static final class ValuesView<K, V> extends CollectionView<K, V, V> implements Collection<V>, java.io.Serializable {

        private static final long serialVersionUID = 2249069246763182397L;

        ValuesView(ConcurrentHashMap<K, V> map) {
            super(map);
        }

        public final boolean contains(Object o);

        public final boolean remove(Object o);

        @SideEffectFree
        public final Iterator<V> iterator();

        public final boolean add(V e);

        public final boolean addAll(Collection<? extends V> c);

        @Override
        public boolean removeAll(Collection<?> c);

        public boolean removeIf(Predicate<? super V> filter);

        @SideEffectFree
        public Spliterator<V> spliterator();

        public void forEach(Consumer<? super V> action);
    }

    static final class EntrySetView<K, V> extends CollectionView<K, V, Map.Entry<K, V>> implements Set<Map.Entry<K, V>>, java.io.Serializable {

        private static final long serialVersionUID = 2249069246763182397L;

        EntrySetView(ConcurrentHashMap<K, V> map) {
            super(map);
        }

        public boolean contains(Object o);

        public boolean remove(Object o);

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        public boolean add(Entry<K, V> e);

        public boolean addAll(Collection<? extends Entry<K, V>> c);

        public boolean removeIf(Predicate<? super Entry<K, V>> filter);

        public final int hashCode();

        public final boolean equals(Object o);

        @SideEffectFree
        public Spliterator<Map.Entry<K, V>> spliterator();

        public void forEach(Consumer<? super Map.Entry<K, V>> action);
    }

    @SuppressWarnings("serial")
    abstract static class BulkTask<K, V, R> extends CountedCompleter<R> {

        Node<K, V>[] tab;

        Node<K, V> next;

        TableStack<K, V> stack, spare;

        int index;

        int baseIndex;

        int baseLimit;

        final int baseSize;

        int batch;

        BulkTask(BulkTask<K, V, ?> par, int b, int i, int f, Node<K, V>[] t) {
            super(par);
            this.batch = b;
            this.index = this.baseIndex = i;
            if ((this.tab = t) == null)
                this.baseSize = this.baseLimit = 0;
            else if (par == null)
                this.baseSize = this.baseLimit = t.length;
            else {
                this.baseLimit = f;
                this.baseSize = par.baseSize;
            }
        }

        final Node<K, V> advance();

        private void pushState(Node<K, V>[] t, int i, int n);

        private void recoverState(int n);
    }

    @SuppressWarnings("serial")
    static final class ForEachKeyTask<K, V> extends BulkTask<K, V, Void> {

        final Consumer<? super K> action;

        ForEachKeyTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Consumer<? super K> action) {
            super(p, b, i, f, t);
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachValueTask<K, V> extends BulkTask<K, V, Void> {

        final Consumer<? super V> action;

        ForEachValueTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Consumer<? super V> action) {
            super(p, b, i, f, t);
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachEntryTask<K, V> extends BulkTask<K, V, Void> {

        final Consumer<? super Entry<K, V>> action;

        ForEachEntryTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Consumer<? super Entry<K, V>> action) {
            super(p, b, i, f, t);
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachMappingTask<K, V> extends BulkTask<K, V, Void> {

        final BiConsumer<? super K, ? super V> action;

        ForEachMappingTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, BiConsumer<? super K, ? super V> action) {
            super(p, b, i, f, t);
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedKeyTask<K, V, U> extends BulkTask<K, V, Void> {

        final Function<? super K, ? extends U> transformer;

        final Consumer<? super U> action;

        ForEachTransformedKeyTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super K, ? extends U> transformer, Consumer<? super U> action) {
            super(p, b, i, f, t);
            this.transformer = transformer;
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedValueTask<K, V, U> extends BulkTask<K, V, Void> {

        final Function<? super V, ? extends U> transformer;

        final Consumer<? super U> action;

        ForEachTransformedValueTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super V, ? extends U> transformer, Consumer<? super U> action) {
            super(p, b, i, f, t);
            this.transformer = transformer;
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedEntryTask<K, V, U> extends BulkTask<K, V, Void> {

        final Function<Map.Entry<K, V>, ? extends U> transformer;

        final Consumer<? super U> action;

        ForEachTransformedEntryTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<Map.Entry<K, V>, ? extends U> transformer, Consumer<? super U> action) {
            super(p, b, i, f, t);
            this.transformer = transformer;
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedMappingTask<K, V, U> extends BulkTask<K, V, Void> {

        final BiFunction<? super K, ? super V, ? extends U> transformer;

        final Consumer<? super U> action;

        ForEachTransformedMappingTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, BiFunction<? super K, ? super V, ? extends U> transformer, Consumer<? super U> action) {
            super(p, b, i, f, t);
            this.transformer = transformer;
            this.action = action;
        }

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchKeysTask<K, V, U> extends BulkTask<K, V, U> {

        final Function<? super K, ? extends U> searchFunction;

        final AtomicReference<U> result;

        SearchKeysTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super K, ? extends U> searchFunction, AtomicReference<U> result) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction;
            this.result = result;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchValuesTask<K, V, U> extends BulkTask<K, V, U> {

        final Function<? super V, ? extends U> searchFunction;

        final AtomicReference<U> result;

        SearchValuesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super V, ? extends U> searchFunction, AtomicReference<U> result) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction;
            this.result = result;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchEntriesTask<K, V, U> extends BulkTask<K, V, U> {

        final Function<Entry<K, V>, ? extends U> searchFunction;

        final AtomicReference<U> result;

        SearchEntriesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<Entry<K, V>, ? extends U> searchFunction, AtomicReference<U> result) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction;
            this.result = result;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchMappingsTask<K, V, U> extends BulkTask<K, V, U> {

        final BiFunction<? super K, ? super V, ? extends U> searchFunction;

        final AtomicReference<U> result;

        SearchMappingsTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, BiFunction<? super K, ? super V, ? extends U> searchFunction, AtomicReference<U> result) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction;
            this.result = result;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ReduceKeysTask<K, V> extends BulkTask<K, V, K> {

        final BiFunction<? super K, ? super K, ? extends K> reducer;

        K result;

        ReduceKeysTask<K, V> rights, nextRight;

        ReduceKeysTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, ReduceKeysTask<K, V> nextRight, BiFunction<? super K, ? super K, ? extends K> reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.reducer = reducer;
        }

        public final K getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ReduceValuesTask<K, V> extends BulkTask<K, V, V> {

        final BiFunction<? super V, ? super V, ? extends V> reducer;

        V result;

        ReduceValuesTask<K, V> rights, nextRight;

        ReduceValuesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, ReduceValuesTask<K, V> nextRight, BiFunction<? super V, ? super V, ? extends V> reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.reducer = reducer;
        }

        public final V getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ReduceEntriesTask<K, V> extends BulkTask<K, V, Map.Entry<K, V>> {

        final BiFunction<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer;

        Map.Entry<K, V> result;

        ReduceEntriesTask<K, V> rights, nextRight;

        ReduceEntriesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, ReduceEntriesTask<K, V> nextRight, BiFunction<Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.reducer = reducer;
        }

        public final Map.Entry<K, V> getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysTask<K, V, U> extends BulkTask<K, V, U> {

        final Function<? super K, ? extends U> transformer;

        final BiFunction<? super U, ? super U, ? extends U> reducer;

        U result;

        MapReduceKeysTask<K, V, U> rights, nextRight;

        MapReduceKeysTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysTask<K, V, U> nextRight, Function<? super K, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.reducer = reducer;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesTask<K, V, U> extends BulkTask<K, V, U> {

        final Function<? super V, ? extends U> transformer;

        final BiFunction<? super U, ? super U, ? extends U> reducer;

        U result;

        MapReduceValuesTask<K, V, U> rights, nextRight;

        MapReduceValuesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesTask<K, V, U> nextRight, Function<? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.reducer = reducer;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesTask<K, V, U> extends BulkTask<K, V, U> {

        final Function<Map.Entry<K, V>, ? extends U> transformer;

        final BiFunction<? super U, ? super U, ? extends U> reducer;

        U result;

        MapReduceEntriesTask<K, V, U> rights, nextRight;

        MapReduceEntriesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesTask<K, V, U> nextRight, Function<Map.Entry<K, V>, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.reducer = reducer;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsTask<K, V, U> extends BulkTask<K, V, U> {

        final BiFunction<? super K, ? super V, ? extends U> transformer;

        final BiFunction<? super U, ? super U, ? extends U> reducer;

        U result;

        MapReduceMappingsTask<K, V, U> rights, nextRight;

        MapReduceMappingsTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsTask<K, V, U> nextRight, BiFunction<? super K, ? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.reducer = reducer;
        }

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        final ToDoubleFunction<? super K> transformer;

        final DoubleBinaryOperator reducer;

        final double basis;

        double result;

        MapReduceKeysToDoubleTask<K, V> rights, nextRight;

        MapReduceKeysToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysToDoubleTask<K, V> nextRight, ToDoubleFunction<? super K> transformer, double basis, DoubleBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        final ToDoubleFunction<? super V> transformer;

        final DoubleBinaryOperator reducer;

        final double basis;

        double result;

        MapReduceValuesToDoubleTask<K, V> rights, nextRight;

        MapReduceValuesToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesToDoubleTask<K, V> nextRight, ToDoubleFunction<? super V> transformer, double basis, DoubleBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        final ToDoubleFunction<Map.Entry<K, V>> transformer;

        final DoubleBinaryOperator reducer;

        final double basis;

        double result;

        MapReduceEntriesToDoubleTask<K, V> rights, nextRight;

        MapReduceEntriesToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesToDoubleTask<K, V> nextRight, ToDoubleFunction<Map.Entry<K, V>> transformer, double basis, DoubleBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        final ToDoubleBiFunction<? super K, ? super V> transformer;

        final DoubleBinaryOperator reducer;

        final double basis;

        double result;

        MapReduceMappingsToDoubleTask<K, V> rights, nextRight;

        MapReduceMappingsToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsToDoubleTask<K, V> nextRight, ToDoubleBiFunction<? super K, ? super V> transformer, double basis, DoubleBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysToLongTask<K, V> extends BulkTask<K, V, Long> {

        final ToLongFunction<? super K> transformer;

        final LongBinaryOperator reducer;

        final long basis;

        long result;

        MapReduceKeysToLongTask<K, V> rights, nextRight;

        MapReduceKeysToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysToLongTask<K, V> nextRight, ToLongFunction<? super K> transformer, long basis, LongBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesToLongTask<K, V> extends BulkTask<K, V, Long> {

        final ToLongFunction<? super V> transformer;

        final LongBinaryOperator reducer;

        final long basis;

        long result;

        MapReduceValuesToLongTask<K, V> rights, nextRight;

        MapReduceValuesToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesToLongTask<K, V> nextRight, ToLongFunction<? super V> transformer, long basis, LongBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesToLongTask<K, V> extends BulkTask<K, V, Long> {

        final ToLongFunction<Map.Entry<K, V>> transformer;

        final LongBinaryOperator reducer;

        final long basis;

        long result;

        MapReduceEntriesToLongTask<K, V> rights, nextRight;

        MapReduceEntriesToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesToLongTask<K, V> nextRight, ToLongFunction<Map.Entry<K, V>> transformer, long basis, LongBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsToLongTask<K, V> extends BulkTask<K, V, Long> {

        final ToLongBiFunction<? super K, ? super V> transformer;

        final LongBinaryOperator reducer;

        final long basis;

        long result;

        MapReduceMappingsToLongTask<K, V> rights, nextRight;

        MapReduceMappingsToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsToLongTask<K, V> nextRight, ToLongBiFunction<? super K, ? super V> transformer, long basis, LongBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysToIntTask<K, V> extends BulkTask<K, V, Integer> {

        final ToIntFunction<? super K> transformer;

        final IntBinaryOperator reducer;

        final int basis;

        int result;

        MapReduceKeysToIntTask<K, V> rights, nextRight;

        MapReduceKeysToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysToIntTask<K, V> nextRight, ToIntFunction<? super K> transformer, int basis, IntBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Integer getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesToIntTask<K, V> extends BulkTask<K, V, Integer> {

        final ToIntFunction<? super V> transformer;

        final IntBinaryOperator reducer;

        final int basis;

        int result;

        MapReduceValuesToIntTask<K, V> rights, nextRight;

        MapReduceValuesToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesToIntTask<K, V> nextRight, ToIntFunction<? super V> transformer, int basis, IntBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Integer getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesToIntTask<K, V> extends BulkTask<K, V, Integer> {

        final ToIntFunction<Map.Entry<K, V>> transformer;

        final IntBinaryOperator reducer;

        final int basis;

        int result;

        MapReduceEntriesToIntTask<K, V> rights, nextRight;

        MapReduceEntriesToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesToIntTask<K, V> nextRight, ToIntFunction<Map.Entry<K, V>> transformer, int basis, IntBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Integer getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsToIntTask<K, V> extends BulkTask<K, V, Integer> {

        final ToIntBiFunction<? super K, ? super V> transformer;

        final IntBinaryOperator reducer;

        final int basis;

        int result;

        MapReduceMappingsToIntTask<K, V> rights, nextRight;

        MapReduceMappingsToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsToIntTask<K, V> nextRight, ToIntBiFunction<? super K, ? super V> transformer, int basis, IntBinaryOperator reducer) {
            super(p, b, i, f, t);
            this.nextRight = nextRight;
            this.transformer = transformer;
            this.basis = basis;
            this.reducer = reducer;
        }

        public final Integer getRawResult();

        public final void compute();
    }

    private static final Unsafe U = Unsafe.getUnsafe();

    private static final long SIZECTL;

    private static final long TRANSFERINDEX;

    private static final long BASECOUNT;

    private static final long CELLSBUSY;

    private static final long CELLVALUE;

    private static final int ABASE;

    private static final int ASHIFT;

    static {
        SIZECTL = U.objectFieldOffset(ConcurrentHashMap.class, "sizeCtl");
        TRANSFERINDEX = U.objectFieldOffset(ConcurrentHashMap.class, "transferIndex");
        BASECOUNT = U.objectFieldOffset(ConcurrentHashMap.class, "baseCount");
        CELLSBUSY = U.objectFieldOffset(ConcurrentHashMap.class, "cellsBusy");
        CELLVALUE = U.objectFieldOffset(CounterCell.class, "value");
        ABASE = U.arrayBaseOffset(Node[].class);
        int scale = U.arrayIndexScale(Node[].class);
        if ((scale & (scale - 1)) != 0)
            throw new ExceptionInInitializerError("array index scale not a power of two");
        ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
        Class<?> ensureLoaded = LockSupport.class;
        ensureLoaded = ReservationNode.class;
    }
}
