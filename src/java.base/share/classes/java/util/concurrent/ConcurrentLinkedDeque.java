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

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class ConcurrentLinkedDeque<E extends @NonNull Object> extends AbstractCollection<E> implements Deque<E>, java.io.Serializable {

    public ConcurrentLinkedDeque() {
    }

    public ConcurrentLinkedDeque(Collection<? extends E> c) {
    }

    public void addFirst(E e);

    public void addLast(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    @Nullable
    public E peekFirst();

    @Nullable
    public E peekLast();

    public E getFirst();

    public E getLast();

    @Nullable
    public E pollFirst();

    @Nullable
    public E pollLast();

    public E removeFirst();

    public E removeLast();

    public boolean offer(E e);

    public boolean add(E e);

    @Nullable
    public E poll();

    @Nullable
    public E peek();

    public E remove();

    public E pop();

    public E element();

    public void push(E e);

    public boolean removeFirstOccurrence(Object o);

    public boolean removeLastOccurrence(Object o);

    @Pure
    public boolean contains(@Nullable Object o);

    @EnsuresNonNullIf(expression = { "peek()", "peekFirst()", "peekLast()", "poll()", "pollFirst()", "pollLast()" }, result = false)
    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    public boolean remove(Object o);

    public boolean addAll(Collection<? extends E> c);

    public void clear();

    public String toString();

    @SideEffectFree
    @PolyNull
    public Object[] toArray(ConcurrentLinkedDeque<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    @SideEffectFree
    public Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public void forEach(Consumer<? super E> action);
}
