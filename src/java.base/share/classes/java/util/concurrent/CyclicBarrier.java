package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CyclicBarrier {

    private static class Generation {

        Generation() {
        }

        boolean broken;
    }

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition trip = lock.newCondition();

    private final int parties;

    private final Runnable barrierCommand;

    private Generation generation = new Generation();

    private int count;

    private void nextGeneration();

    private void breakBarrier();

    private int dowait(boolean timed, long nanos) throws InterruptedException, BrokenBarrierException, TimeoutException;

    public CyclicBarrier(int parties, Runnable barrierAction) {
        if (parties <= 0)
            throw new IllegalArgumentException();
        this.parties = parties;
        this.count = parties;
        this.barrierCommand = barrierAction;
    }

    public CyclicBarrier(int parties) {
        this(parties, null);
    }

    public int getParties();

    public int await() throws InterruptedException, BrokenBarrierException;

    public int await(long timeout, TimeUnit unit) throws InterruptedException, BrokenBarrierException, TimeoutException;

    public boolean isBroken();

    public void reset();

    public int getNumberWaiting();
}
