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

    volatile int status;

    private static final int DONE = 1 << 31;

    private static final int ABNORMAL = 1 << 18;

    private static final int THROWN = 1 << 17;

    private static final int SIGNAL = 1 << 16;

    private static final int SMASK = 0xffff;

    static boolean isExceptionalStatus(int s);

    private int setDone();

    private int abnormalCompletion(int completion);

    final int doExec();

    final void internalWait(long timeout);

    private int externalAwaitDone();

    private int externalInterruptibleAwaitDone() throws InterruptedException;

    private int tryExternalHelp();

    private int doJoin();

    private int doInvoke();

    private static final ExceptionNode[] exceptionTable = new ExceptionNode[32];

    private static final ReentrantLock exceptionTableLock = new ReentrantLock();

    private static final ReferenceQueue<ForkJoinTask<?>> exceptionTableRefQueue = new ReferenceQueue<>();

    static final class ExceptionNode extends WeakReference<ForkJoinTask<?>> {

        final Throwable ex;

        ExceptionNode next;

        final long thrower;

        final int hashCode;

        ExceptionNode(ForkJoinTask<?> task, Throwable ex, ExceptionNode next, ReferenceQueue<ForkJoinTask<?>> exceptionTableRefQueue) {
            super(task, exceptionTableRefQueue);
            this.ex = ex;
            this.next = next;
            this.thrower = Thread.currentThread().getId();
            this.hashCode = System.identityHashCode(task);
        }
    }

    final int recordExceptionalCompletion(Throwable ex);

    private int setExceptionalCompletion(Throwable ex);

    void internalPropagateException(Throwable ex);

    static final void cancelIgnoringExceptions(ForkJoinTask<?> t);

    private void clearExceptionalCompletion();

    private Throwable getThrowableException();

    private static void expungeStaleExceptions();

    static final void helpExpungeStaleExceptions();

    static void rethrow(Throwable ex);

    @SuppressWarnings("unchecked")
    static <T extends Throwable> void uncheckedThrow(Throwable t) throws T;

    private void reportException(int s);

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

    static final class AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {

        final Runnable runnable;

        T result;

        AdaptedRunnable(Runnable runnable, T result) {
            if (runnable == null)
                throw new NullPointerException();
            this.runnable = runnable;
            this.result = result;
        }

        public final T getRawResult();

        public final void setRawResult(T v);

        public final boolean exec();

        public final void run();

        public String toString();

        private static final long serialVersionUID = 5232453952276885070L;
    }

    static final class AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {

        final Runnable runnable;

        AdaptedRunnableAction(Runnable runnable) {
            if (runnable == null)
                throw new NullPointerException();
            this.runnable = runnable;
        }

        public final Void getRawResult();

        public final void setRawResult(Void v);

        public final boolean exec();

        public final void run();

        public String toString();

        private static final long serialVersionUID = 5232453952276885070L;
    }

    static final class RunnableExecuteAction extends ForkJoinTask<Void> {

        final Runnable runnable;

        RunnableExecuteAction(Runnable runnable) {
            if (runnable == null)
                throw new NullPointerException();
            this.runnable = runnable;
        }

        public final Void getRawResult();

        public final void setRawResult(Void v);

        public final boolean exec();

        void internalPropagateException(Throwable ex);

        private static final long serialVersionUID = 5232453952276885070L;
    }

    static final class AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {

        final Callable<? extends T> callable;

        T result;

        AdaptedCallable(Callable<? extends T> callable) {
            if (callable == null)
                throw new NullPointerException();
            this.callable = callable;
        }

        public final T getRawResult();

        public final void setRawResult(T v);

        public final boolean exec();

        public final void run();

        public String toString();

        private static final long serialVersionUID = 2838392045355241008L;
    }

    public static ForkJoinTask<?> adapt(Runnable runnable);

    public static <T> ForkJoinTask<T> adapt(Runnable runnable, T result);

    public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable);

    private static final long serialVersionUID = -7721805057305804111L;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    private static final VarHandle STATUS;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            STATUS = l.findVarHandle(ForkJoinTask.class, "status", int.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
