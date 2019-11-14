package java.util.concurrent;

import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface Future<V> {

    boolean cancel(boolean mayInterruptIfRunning);

    @Pure
    boolean isCancelled();

    @Pure
    boolean isDone();

    V get() throws InterruptedException, ExecutionException;

    V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
}
