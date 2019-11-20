package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CountDownLatch {

    public CountDownLatch(int count) {
    }

    public void await() throws InterruptedException;

    public boolean await(long timeout, TimeUnit unit) throws InterruptedException;

    public void countDown();

    public long getCount();

    public String toString();
}
