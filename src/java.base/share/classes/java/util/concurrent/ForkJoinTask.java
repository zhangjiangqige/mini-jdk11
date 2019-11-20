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
