/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
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

    public HashMap(@NonNegative int initialCapacity, float loadFactor) {
    }

    public HashMap(@NonNegative int initialCapacity) {
    }

    public HashMap() {
    }

    public HashMap(Map<? extends K, ? extends V> m) {
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied HashMap<K, V> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied HashMap<K, V> this);

    @Pure
    @Nullable
    public V get(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied HashMap<K, V> this, K key, V value);

    public void putAll(@GuardSatisfied HashMap<K, V> this, Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(@GuardSatisfied HashMap<K, V> this, @Nullable Object key);

    public void clear(@GuardSatisfied HashMap<K, V> this);

    @Pure
    public boolean containsValue(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable Object value);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied HashMap<K, V> this);

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied HashMap<K, V> this);

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied HashMap<K, V> this);

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
}
