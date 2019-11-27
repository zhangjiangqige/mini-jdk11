/*
 * Copyright (c) 1994, 2018, Oracle and/or its affiliates. All rights reserved.
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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@CFComment({ "lock/nullness: permits nullable object" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    protected Object[] elementData;

    protected int elementCount;

    protected int capacityIncrement;

    public Vector(@NonNegative int initialCapacity, int capacityIncrement) {
    }

    public Vector(@NonNegative int initialCapacity) {
    }

    public Vector() {
    }

    public Vector(Collection<? extends E> c) {
    }

    public synchronized void copyInto(@Nullable Object[] anArray);

    public synchronized void trimToSize(@GuardSatisfied Vector<E> this);

    public synchronized void ensureCapacity(int minCapacity);

    public synchronized void setSize(@GuardSatisfied Vector<E> this, @NonNegative int newSize);

    @NonNegative
    public synchronized int capacity();

    @Pure
    @NonNegative
    public synchronized int size(@GuardSatisfied Vector<E> this);

    @Pure
    public synchronized boolean isEmpty(@GuardSatisfied Vector<E> this);

    public Enumeration<E> elements();

    @Pure
    public boolean contains(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public synchronized int indexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o, @NonNegative int index);

    @Pure
    @GTENegativeOne
    public synchronized int lastIndexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public synchronized int lastIndexOf(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o, @NonNegative int index);

    public synchronized E elementAt(@NonNegative int index);

    public synchronized E firstElement();

    public synchronized E lastElement();

    public synchronized void setElementAt(@GuardSatisfied Vector<E> this, E obj, @NonNegative int index);

    public synchronized void removeElementAt(@GuardSatisfied Vector<E> this, @NonNegative int index);

    public synchronized void insertElementAt(@GuardSatisfied Vector<E> this, E obj, @NonNegative int index);

    public synchronized void addElement(@GuardSatisfied Vector<E> this, E obj);

    public synchronized boolean removeElement(@GuardSatisfied Vector<E> this, Object obj);

    public synchronized void removeAllElements(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    public synchronized Object clone(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    @PolyNull
    public synchronized Object[] toArray(Vector<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public synchronized <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @Pure
    public synchronized E get(@GuardSatisfied Vector<E> this, @NonNegative int index);

    public synchronized E set(@GuardSatisfied Vector<E> this, @NonNegative int index, E element);

    public synchronized boolean add(@GuardSatisfied Vector<E> this, E e);

    public boolean remove(@GuardSatisfied Vector<E> this, @Nullable Object o);

    public void add(@GuardSatisfied Vector<E> this, @NonNegative int index, E element);

    public synchronized E remove(@GuardSatisfied Vector<E> this, @NonNegative int index);

    public void clear(@GuardSatisfied Vector<E> this);

    @Pure
    public synchronized boolean containsAll(@GuardSatisfied Vector<E> this, @GuardSatisfied Collection<?> c);

    public boolean addAll(@GuardSatisfied Vector<E> this, Collection<? extends E> c);

    public boolean removeAll(@GuardSatisfied Vector<E> this, Collection<?> c);

    public boolean retainAll(@GuardSatisfied Vector<E> this, Collection<?> c);

    @SuppressWarnings({ "unchecked" })
    @Override
    public boolean removeIf(Predicate<? super E> filter);

    public synchronized boolean addAll(@GuardSatisfied Vector<E> this, @NonNegative int index, Collection<? extends E> c);

    @Pure
    public synchronized boolean equals(@GuardSatisfied Vector<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public synchronized int hashCode(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    public synchronized String toString(@GuardSatisfied Vector<E> this);

    @SideEffectFree
    public synchronized List<E> subList(@GuardSatisfied Vector<E> this, int fromIndex, int toIndex);

    protected synchronized void removeRange(int fromIndex, int toIndex);

    public synchronized ListIterator<E> listIterator(@NonNegative int index);

    public synchronized ListIterator<E> listIterator();

    @SideEffectFree
    public synchronized Iterator<E> iterator();

    @Override
    public synchronized void forEach(Consumer<? super E> action);

    @SuppressWarnings({ "unchecked" })
    @Override
    public synchronized void replaceAll(UnaryOperator<E> operator);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void sort(Comparator<? super E> c);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();
}
