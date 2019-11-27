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

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor) {
    }

    public LinkedHashMap(@NonNegative int initialCapacity) {
    }

    public LinkedHashMap() {
    }

    public LinkedHashMap(Map<? extends K, ? extends V> m) {
    }

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor, boolean accessOrder) {
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

    public Collection<V> values();

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied LinkedHashMap<K, V> this);

    public void forEach(BiConsumer<? super K, ? super V> action);

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);
}
