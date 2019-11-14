package java.util.concurrent.locks;

import org.checkerframework.checker.lock.qual.EnsuresLockHeld;
import org.checkerframework.checker.lock.qual.EnsuresLockHeldIf;
import org.checkerframework.checker.lock.qual.MayReleaseLocks;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import jdk.internal.vm.annotation.ReservedStackAccess;

@AnnotatedFor("lock")
public class ReentrantLock implements Lock, java.io.Serializable {

    private static final long serialVersionUID = 7373984872572414699L;

    private final Sync sync;

    abstract static class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = -5179523762034025860L;

        @ReservedStackAccess
        final boolean nonfairTryAcquire(int acquires);

        @ReservedStackAccess
        protected final boolean tryRelease(int releases);

        protected final boolean isHeldExclusively();

        final ConditionObject newCondition();

        final Thread getOwner();

        final int getHoldCount();

        final boolean isLocked();

        private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;
    }

    static final class NonfairSync extends Sync {

        private static final long serialVersionUID = 7316153563782823691L;

        protected final boolean tryAcquire(int acquires);
    }

    static final class FairSync extends Sync {

        private static final long serialVersionUID = -3000897897090466540L;

        @ReservedStackAccess
        protected final boolean tryAcquire(int acquires);
    }

    public ReentrantLock() {
        sync = new NonfairSync();
    }

    public ReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
    }

    @EnsuresLockHeld({ "this" })
    @ReleasesNoLocks
    public void lock();

    @EnsuresLockHeld({ "this" })
    @ReleasesNoLocks
    public void lockInterruptibly() throws InterruptedException;

    @EnsuresLockHeldIf(expression = { "this" }, result = true)
    @ReleasesNoLocks
    public boolean tryLock();

    @EnsuresLockHeldIf(expression = { "this" }, result = true)
    @ReleasesNoLocks
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException;

    @MayReleaseLocks
    public void unlock();

    public Condition newCondition();

    public int getHoldCount();

    @EnsuresLockHeldIf(expression = { "this" }, result = true)
    @ReleasesNoLocks
    public boolean isHeldByCurrentThread();

    public boolean isLocked();

    public final boolean isFair();

    protected Thread getOwner();

    public final boolean hasQueuedThreads();

    public final boolean hasQueuedThread(Thread thread);

    public final int getQueueLength();

    protected Collection<Thread> getQueuedThreads();

    public boolean hasWaiters(Condition condition);

    public int getWaitQueueLength(Condition condition);

    protected Collection<Thread> getWaitingThreads(Condition condition);

    public String toString();
}
