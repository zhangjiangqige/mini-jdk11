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

    public TreeMap() {
    }

    public TreeMap(Comparator<? super K> comparator) {
    }

    public TreeMap(Map<? extends K, ? extends V> m) {
    }

    public TreeMap(SortedMap<K, ? extends V> m) {
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
}
