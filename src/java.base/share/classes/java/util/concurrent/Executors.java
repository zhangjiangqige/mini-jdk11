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

    private static final class RunnableAdapter<T> implements Callable<T> {

        private final Runnable task;

        private final T result;

        RunnableAdapter(Runnable task, T result) {
            this.task = task;
            this.result = result;
        }

        public T call();

        public String toString();
    }

    private static final class PrivilegedCallable<T> implements Callable<T> {

        final Callable<T> task;

        final AccessControlContext acc;

        PrivilegedCallable(Callable<T> task) {
            this.task = task;
            this.acc = AccessController.getContext();
        }

        public T call() throws Exception;

        public String toString();
    }

    private static final class PrivilegedCallableUsingCurrentClassLoader<T> implements Callable<T> {

        final Callable<T> task;

        final AccessControlContext acc;

        final ClassLoader ccl;

        PrivilegedCallableUsingCurrentClassLoader(Callable<T> task) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
                sm.checkPermission(new RuntimePermission("setContextClassLoader"));
            }
            this.task = task;
            this.acc = AccessController.getContext();
            this.ccl = Thread.currentThread().getContextClassLoader();
        }

        public T call() throws Exception;

        public String toString();
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup group;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r);
    }

    private static class PrivilegedThreadFactory extends DefaultThreadFactory {

        final AccessControlContext acc;

        final ClassLoader ccl;

        PrivilegedThreadFactory() {
            super();
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
                sm.checkPermission(new RuntimePermission("setContextClassLoader"));
            }
            this.acc = AccessController.getContext();
            this.ccl = Thread.currentThread().getContextClassLoader();
        }

        public Thread newThread(final Runnable r);
    }

    private static class DelegatedExecutorService implements ExecutorService {

        private final ExecutorService e;

        DelegatedExecutorService(ExecutorService executor) {
            e = executor;
        }

        public void execute(Runnable command);

        public void shutdown();

        public List<Runnable> shutdownNow();

        public boolean isShutdown();

        public boolean isTerminated();

        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;

        public Future<?> submit(Runnable task);

        public <T> Future<T> submit(Callable<T> task);

        public <T> Future<T> submit(Runnable task, T result);

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
    }

    private static class FinalizableDelegatedExecutorService extends DelegatedExecutorService {

        FinalizableDelegatedExecutorService(ExecutorService executor) {
            super(executor);
        }

        @SuppressWarnings("deprecation")
        protected void finalize();
    }

    private static class DelegatedScheduledExecutorService extends DelegatedExecutorService implements ScheduledExecutorService {

        private final ScheduledExecutorService e;

        DelegatedScheduledExecutorService(ScheduledExecutorService executor) {
            super(executor);
            e = executor;
        }

        public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit);

        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit);

        public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);
    }

    private Executors() {
    }
}
