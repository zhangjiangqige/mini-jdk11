/*
 * Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.
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

    public IdentityHashMap() {
    }

    public IdentityHashMap(@NonNegative int expectedMaxSize) {
    }

    public IdentityHashMap(Map<? extends K, ? extends V> m) {
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied IdentityHashMap<K, V> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied IdentityHashMap<K, V> this);

    @Pure
    @SuppressWarnings("unchecked")
    public V get(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object key);

    @Pure
    public boolean containsValue(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object value);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    public V put(@GuardSatisfied IdentityHashMap<K, V> this, K key, V value);

    public void putAll(@GuardSatisfied IdentityHashMap<K, V> this, Map<? extends K, ? extends V> m);

    public V remove(@GuardSatisfied IdentityHashMap<K, V> this, Object key);

    public void clear(@GuardSatisfied IdentityHashMap<K, V> this);

    @Pure
    public boolean equals(@GuardSatisfied IdentityHashMap<K, V> this, @GuardSatisfied Object o);

    @Pure
    public int hashCode(@GuardSatisfied IdentityHashMap<K, V> this);

    @SideEffectFree
    public Object clone(@GuardSatisfied IdentityHashMap<K, V> this);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied IdentityHashMap<K, V> this);

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied IdentityHashMap<K, V> this);

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied IdentityHashMap<K, V> this);

    @SuppressWarnings("unchecked")
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @SuppressWarnings("unchecked")
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);
}
