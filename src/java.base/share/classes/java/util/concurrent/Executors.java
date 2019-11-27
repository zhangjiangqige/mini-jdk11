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
import static java.lang.ref.Reference.reachabilityFence;
import java.security.AccessControlContext;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Executors {

    public static ExecutorService newFixedThreadPool(int nThreads);

    public static ExecutorService newWorkStealingPool(int parallelism);

    public static ExecutorService newWorkStealingPool();

    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory);

    public static ExecutorService newSingleThreadExecutor();

    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory);

    public static ExecutorService newCachedThreadPool();

    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory);

    public static ScheduledExecutorService newSingleThreadScheduledExecutor();

    public static ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory);

    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize);

    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory);

    public static ExecutorService unconfigurableExecutorService(ExecutorService executor);

    public static ScheduledExecutorService unconfigurableScheduledExecutorService(ScheduledExecutorService executor);

    public static ThreadFactory defaultThreadFactory();

    public static ThreadFactory privilegedThreadFactory();

    public static <T> Callable<T> callable(Runnable task, T result);

    public static Callable<Object> callable(Runnable task);

    public static Callable<Object> callable(final PrivilegedAction<?> action);

    public static Callable<Object> callable(final PrivilegedExceptionAction<?> action);

    public static <T> Callable<T> privilegedCallable(Callable<T> callable);

    public static <T> Callable<T> privilegedCallableUsingCurrentClassLoader(Callable<T> callable);
}
