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
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;

@CFComment({ "lock/nullness: This class permits null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
    }

    public E getFirst(@GuardSatisfied LinkedList<E> this);

    public E getLast(@GuardSatisfied LinkedList<E> this);

    public E removeFirst(@GuardSatisfied LinkedList<E> this);

    public E removeLast(@GuardSatisfied LinkedList<E> this);

    public void addFirst(@GuardSatisfied LinkedList<E> this, E e);

    public void addLast(@GuardSatisfied LinkedList<E> this, E e);

    @Pure
    public boolean contains(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied LinkedList<E> this);

    @ReleasesNoLocks
    public boolean add(@GuardSatisfied LinkedList<E> this, E e);

    @ReleasesNoLocks
    public boolean remove(@GuardSatisfied LinkedList<E> this, @Nullable Object o);

    public boolean addAll(@GuardSatisfied LinkedList<E> this, Collection<? extends E> c);

    public boolean addAll(@GuardSatisfied LinkedList<E> this, @NonNegative int index, Collection<? extends E> c);

    public void clear(@GuardSatisfied LinkedList<E> this);

    @Pure
    public E get(@GuardSatisfied LinkedList<E> this, @NonNegative int index);

    public E set(@GuardSatisfied LinkedList<E> this, @NonNegative int index, E element);

    public void add(@GuardSatisfied LinkedList<E> this, @NonNegative int index, E element);

    public E remove(@GuardSatisfied LinkedList<E> this, @NonNegative int index);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable Object o);

    @Nullable
    public E peek();

    public E element();

    @Nullable
    public E poll(@GuardSatisfied LinkedList<E> this);

    public E remove(@GuardSatisfied LinkedList<E> this);

    public boolean offer(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    @Nullable
    public E peekFirst();

    @Nullable
    public E peekLast();

    @Nullable
    public E pollFirst(@GuardSatisfied LinkedList<E> this);

    @Nullable
    public E pollLast(@GuardSatisfied LinkedList<E> this);

    public void push(@GuardSatisfied LinkedList<E> this, E e);

    public E pop(@GuardSatisfied LinkedList<E> this);

    public boolean removeFirstOccurrence(@GuardSatisfied LinkedList<E> this, Object o);

    public boolean removeLastOccurrence(@GuardSatisfied LinkedList<E> this, Object o);

    public ListIterator<E> listIterator(@NonNegative int index);

    public Iterator<E> descendingIterator();

    @SideEffectFree
    public Object clone(@GuardSatisfied LinkedList<E> this);

    @SideEffectFree
    @PolyNull
    public Object[] toArray(LinkedList<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T @PolyNull [] toArray(T @PolyNull [] a);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();
}
