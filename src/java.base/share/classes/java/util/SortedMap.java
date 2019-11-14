package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public interface SortedMap<K, V> extends Map<K, V> {

    @SideEffectFree
    Comparator<? super K> comparator(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    SortedMap<K, V> subMap(@GuardSatisfied SortedMap<K, V> this, @GuardSatisfied K fromKey, @GuardSatisfied K toKey);

    @SideEffectFree
    SortedMap<K, V> headMap(@GuardSatisfied SortedMap<K, V> this, K toKey);

    @SideEffectFree
    SortedMap<K, V> tailMap(@GuardSatisfied SortedMap<K, V> this, K fromKey);

    @SideEffectFree
    K firstKey(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    K lastKey(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    Collection<V> values(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied SortedMap<K, V> this);
}
