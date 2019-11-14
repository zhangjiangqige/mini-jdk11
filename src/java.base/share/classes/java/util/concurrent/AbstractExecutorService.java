package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AbstractExecutorService implements ExecutorService {

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value);

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable);

    public Future<?> submit(Runnable task);

    public <T> Future<T> submit(Runnable task, T result);

    public <T> Future<T> submit(Callable<T> task);

    private <T> T doInvokeAny(Collection<? extends Callable<T>> tasks, boolean timed, long nanos) throws InterruptedException, ExecutionException, TimeoutException;

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;

    private static <T> void cancelAll(ArrayList<Future<T>> futures);

    private static <T> void cancelAll(ArrayList<Future<T>> futures, int j);
}
