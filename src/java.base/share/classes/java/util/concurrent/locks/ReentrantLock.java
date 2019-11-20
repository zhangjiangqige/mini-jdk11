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

    public ReentrantLock() {
    }

    public ReentrantLock(boolean fair) {
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
