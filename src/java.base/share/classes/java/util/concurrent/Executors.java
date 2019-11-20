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
