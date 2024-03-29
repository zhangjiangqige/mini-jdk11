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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import jdk.internal.misc.SharedSecrets;

@CFComment("lock/nullness: Permit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    public ArrayList(@NonNegative int initialCapacity) {
    }

    public ArrayList() {
    }

    public ArrayList(Collection<? extends E> c) {
    }

    public void trimToSize(@GuardSatisfied ArrayList<E> this);

    public void ensureCapacity(@GuardSatisfied ArrayList<E> this, int minCapacity);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied ArrayList<E> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied ArrayList<E> this);

    @Pure
    public boolean contains(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable Object o);

    @SideEffectFree
    public Object clone(@GuardSatisfied ArrayList<E> this);

    @SideEffectFree
    @PolyNull
    public Object[] toArray(ArrayList<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @Pure
    public E get(@GuardSatisfied ArrayList<E> this, @NonNegative int index);

    public E set(@GuardSatisfied ArrayList<E> this, @NonNegative int index, E element);

    public boolean add(@GuardSatisfied ArrayList<E> this, E e);

    public void add(@GuardSatisfied ArrayList<E> this, @NonNegative int index, E element);

    public E remove(@GuardSatisfied ArrayList<E> this, @NonNegative int index);

    public boolean equals(Object o);

    public int hashCode();

    public boolean remove(@GuardSatisfied ArrayList<E> this, @Nullable Object o);

    public void clear(@GuardSatisfied ArrayList<E> this);

    public boolean addAll(@GuardSatisfied ArrayList<E> this, Collection<? extends E> c);

    public boolean addAll(@GuardSatisfied ArrayList<E> this, @NonNegative int index, Collection<? extends E> c);

    protected void removeRange(int fromIndex, int toIndex);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public ListIterator<E> listIterator(@NonNegative int index);

    public ListIterator<E> listIterator();

    @SideEffectFree
    public Iterator<E> iterator();

    public List<E> subList(@NonNegative int fromIndex, @NonNegative int toIndex);

    @Override
    public void forEach(Consumer<? super E> action);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    @Override
    public boolean removeIf(Predicate<? super E> filter);

    @SuppressWarnings({ "unchecked" })
    @Override
    public void replaceAll(UnaryOperator<E> operator);

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c);
}
