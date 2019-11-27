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

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "nullness" })
public class SynchronousQueue<E extends @NonNull Object> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {

    public SynchronousQueue() {
    }

    public SynchronousQueue(boolean fair) {
    }

    public void put(E e) throws InterruptedException;

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public boolean offer(E e);

    public E take() throws InterruptedException;

    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    public E poll();

    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    public int remainingCapacity();

    public void clear();

    @Pure
    public boolean contains(Object o);

    public boolean remove(Object o);

    @Pure
    public boolean containsAll(Collection<?> c);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public E peek();

    @SideEffectFree
    public Iterator<E> iterator();

    @SideEffectFree
    public Spliterator<E> spliterator();

    @SideEffectFree
    @PolyNull
    public Object[] toArray(SynchronousQueue<@PolyNull E> this);

    @SideEffectFree
    public <T> T[] toArray(T[] a);

    public String toString();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);
}
