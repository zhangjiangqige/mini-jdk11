package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ExecutorCompletionService<V> implements CompletionService<V> {

    private final Executor executor;

    private final AbstractExecutorService aes;

    private final BlockingQueue<Future<V>> completionQueue;

    private static class QueueingFuture<V> extends FutureTask<Void> {

        QueueingFuture(RunnableFuture<V> task, BlockingQueue<Future<V>> completionQueue) {
            super(task, null);
            this.task = task;
            this.completionQueue = completionQueue;
        }

        private final Future<V> task;

        private final BlockingQueue<Future<V>> completionQueue;

        protected void done();
    }

    private RunnableFuture<V> newTaskFor(Callable<V> task);

    private RunnableFuture<V> newTaskFor(Runnable task, V result);

    public ExecutorCompletionService(Executor executor) {
        if (executor == null)
            throw new NullPointerException();
        this.executor = executor;
        this.aes = (executor instanceof AbstractExecutorService) ? (AbstractExecutorService) executor : null;
        this.completionQueue = new LinkedBlockingQueue<Future<V>>();
    }

    public ExecutorCompletionService(Executor executor, BlockingQueue<Future<V>> completionQueue) {
        if (executor == null || completionQueue == null)
            throw new NullPointerException();
        this.executor = executor;
        this.aes = (executor instanceof AbstractExecutorService) ? (AbstractExecutorService) executor : null;
        this.completionQueue = completionQueue;
    }

    public Future<V> submit(Callable<V> task);

    public Future<V> submit(Runnable task, V result);

    public Future<V> take() throws InterruptedException;

    public Future<V> poll();

    public Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
}
