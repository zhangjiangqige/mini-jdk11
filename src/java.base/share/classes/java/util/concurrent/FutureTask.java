package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.LockSupport;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class FutureTask<V> implements RunnableFuture<V> {

    public FutureTask(Callable<V> callable) {
    }

    public FutureTask(Runnable runnable, V result) {
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

    public String toString();
}
