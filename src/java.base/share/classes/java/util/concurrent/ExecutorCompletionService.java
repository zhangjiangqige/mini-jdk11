package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ExecutorCompletionService<V> implements CompletionService<V> {

    public ExecutorCompletionService(Executor executor) {
    }

    public ExecutorCompletionService(Executor executor, BlockingQueue<Future<V>> completionQueue) {
    }

    public Future<V> submit(Callable<V> task);

    public Future<V> submit(Runnable task, V result);

    public Future<V> take() throws InterruptedException;

    public Future<V> poll();

    public Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
}
