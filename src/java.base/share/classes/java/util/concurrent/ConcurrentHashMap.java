/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
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

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int initialCapacity) {
    }

    public ConcurrentHashMap(Map<? extends K, ? extends V> m) {
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor) {
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
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

    public void putAll(Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(Object key);

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

    public static class KeySetView<K, V> extends CollectionView<K, V, K> implements Set<K>, java.io.Serializable {

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
}
