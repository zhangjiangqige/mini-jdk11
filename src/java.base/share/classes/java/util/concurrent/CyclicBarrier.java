package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CyclicBarrier {

    public CyclicBarrier(int parties, Runnable barrierAction) {
    }

    public CyclicBarrier(int parties) {
    }

    public int getParties();

    public int await() throws InterruptedException, BrokenBarrierException;

    public int await(long timeout, TimeUnit unit) throws InterruptedException, BrokenBarrierException, TimeoutException;

    public boolean isBroken();

    public void reset();

    public int getNumberWaiting();
}
