package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CountDownLatch {

    private static final class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            setState(count);
        }

        int getCount();

        protected int tryAcquireShared(int acquires);

        protected boolean tryReleaseShared(int releases);
    }

    private final Sync sync;

    public CountDownLatch(int count) {
        if (count < 0)
            throw new IllegalArgumentException("count < 0");
        this.sync = new Sync(count);
    }

    public void await() throws InterruptedException;

    public boolean await(long timeout, TimeUnit unit) throws InterruptedException;

    public void countDown();

    public long getCount();

    public String toString();
}
