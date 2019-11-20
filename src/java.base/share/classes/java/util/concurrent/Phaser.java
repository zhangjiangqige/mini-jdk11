package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Phaser {

    public Phaser() {
    }

    public Phaser(int parties) {
    }

    public Phaser(Phaser parent) {
    }

    public Phaser(Phaser parent, int parties) {
    }

    public int register();

    public int bulkRegister(int parties);

    public int arrive();

    public int arriveAndDeregister();

    public int arriveAndAwaitAdvance();

    public int awaitAdvance(int phase);

    public int awaitAdvanceInterruptibly(int phase) throws InterruptedException;

    public int awaitAdvanceInterruptibly(int phase, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException;

    public void forceTermination();

    public final int getPhase();

    public int getRegisteredParties();

    public int getArrivedParties();

    public int getUnarrivedParties();

    public Phaser getParent();

    public Phaser getRoot();

    public boolean isTerminated();

    protected boolean onAdvance(int phase, int registeredParties);

    public String toString();
}
