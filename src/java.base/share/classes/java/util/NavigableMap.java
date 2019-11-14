package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public interface NavigableMap<K, V> extends SortedMap<K, V> {

    Map.@Nullable Entry<K, V> lowerEntry(K key);

    @Nullable
    K lowerKey(K key);

    Map.@Nullable Entry<K, V> floorEntry(K key);

    @Nullable
    K floorKey(K key);

    Map.@Nullable Entry<K, V> ceilingEntry(K key);

    @Nullable
    K ceilingKey(K key);

    Map.@Nullable Entry<K, V> higherEntry(K key);

    @Nullable
    K higherKey(K key);

    Map.@Nullable Entry<K, V> firstEntry();

    Map.@Nullable Entry<K, V> lastEntry();

    Map.@Nullable Entry<K, V> pollFirstEntry(@GuardSatisfied NavigableMap<K, V> this);

    Map.@Nullable Entry<K, V> pollLastEntry(@GuardSatisfied NavigableMap<K, V> this);

    NavigableMap<K, V> descendingMap();

    NavigableSet<@KeyFor({ "this" }) K> navigableKeySet();

    NavigableSet<@KeyFor({ "this" }) K> descendingKeySet();

    NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

    NavigableMap<K, V> headMap(K toKey, boolean inclusive);

    NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);

    SortedMap<K, V> subMap(K fromKey, K toKey);

    SortedMap<K, V> headMap(K toKey);

    SortedMap<K, V> tailMap(K fromKey);
}
