package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.LockSupport;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class FutureTask<V> implements RunnableFuture<V> {

    private volatile int state;

    private static final int NEW = 0;

    private static final int COMPLETING = 1;

    private static final int NORMAL = 2;

    private static final int EXCEPTIONAL = 3;

    private static final int CANCELLED = 4;

    private static final int INTERRUPTING = 5;

    private static final int INTERRUPTED = 6;

    private Callable<V> callable;

    private Object outcome;

    private volatile Thread runner;

    private volatile WaitNode waiters;

    @SuppressWarnings("unchecked")
    private V report(int s) throws ExecutionException;

    public FutureTask(Callable<V> callable) {
        if (callable == null)
            throw new NullPointerException();
        this.callable = callable;
        this.state = NEW;
    }

    public FutureTask(Runnable runnable, V result) {
        this.callable = Executors.callable(runnable, result);
        this.state = NEW;
    }

    public boolean isCancelled();

    public boolean isDone();

    public boolean cancel(boolean mayInterruptIfRunning);

    public V get() throws InterruptedException, ExecutionException;

    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    protected void done();

    protected void set(V v);

    protected void setException(Throwable t);

    public void run();

    protected boolean runAndReset();

    private void handlePossibleCancellationInterrupt(int s);

    static final class WaitNode {

        volatile Thread thread;

        volatile WaitNode next;

        WaitNode() {
            thread = Thread.currentThread();
        }
    }

    private void finishCompletion();

    private int awaitDone(boolean timed, long nanos) throws InterruptedException;

    private void removeWaiter(WaitNode node);

    public String toString();

    private static final VarHandle STATE;

    private static final VarHandle RUNNER;

    private static final VarHandle WAITERS;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            STATE = l.findVarHandle(FutureTask.class, "state", int.class);
            RUNNER = l.findVarHandle(FutureTask.class, "runner", Thread.class);
            WAITERS = l.findVarHandle(FutureTask.class, "waiters", WaitNode.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
        Class<?> ensureLoaded = LockSupport.class;
    }
}
