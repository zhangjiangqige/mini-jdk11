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

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ForkJoinTask<V> implements Future<V>, Serializable {

    public final ForkJoinTask<V> fork();

    public final V join();

    public final V invoke();

    public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2);

    public static void invokeAll(ForkJoinTask<?>... tasks);

    public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> tasks);

    public boolean cancel(boolean mayInterruptIfRunning);

    public final boolean isDone();

    public final boolean isCancelled();

    public final boolean isCompletedAbnormally();

    public final boolean isCompletedNormally();

    public final Throwable getException();

    public void completeExceptionally(Throwable ex);

    public void complete(V value);

    public final void quietlyComplete();

    public final V get() throws InterruptedException, ExecutionException;

    public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    public final void quietlyJoin();

    public final void quietlyInvoke();

    public static void helpQuiesce();

    public void reinitialize();

    public static ForkJoinPool getPool();

    public static boolean inForkJoinPool();

    public boolean tryUnfork();

    public static int getQueuedTaskCount();

    public static int getSurplusQueuedTaskCount();

    public abstract V getRawResult();

    protected abstract void setRawResult(V value);

    protected abstract boolean exec();

    protected static ForkJoinTask<?> peekNextLocalTask();

    protected static ForkJoinTask<?> pollNextLocalTask();

    protected static ForkJoinTask<?> pollTask();

    protected static ForkJoinTask<?> pollSubmission();

    public final short getForkJoinTaskTag();

    public final short setForkJoinTaskTag(short newValue);

    public final boolean compareAndSetForkJoinTaskTag(short expect, short update);

    public static ForkJoinTask<?> adapt(Runnable runnable);

    public static <T> ForkJoinTask<T> adapt(Runnable runnable, T result);

    public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable);
}
