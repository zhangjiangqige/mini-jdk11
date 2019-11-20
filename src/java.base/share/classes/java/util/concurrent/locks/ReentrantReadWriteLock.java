package java.util.concurrent.locks;

import org.checkerframework.checker.lock.qual.EnsuresLockHeld;
import org.checkerframework.checker.lock.qual.EnsuresLockHeldIf;
import org.checkerframework.checker.lock.qual.MayReleaseLocks;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import jdk.internal.vm.annotation.ReservedStackAccess;

@AnnotatedFor({ "lock" })
public class ReentrantReadWriteLock implements ReadWriteLock, java.io.Serializable {

    public ReentrantReadWriteLock() {
    }

    public ReentrantReadWriteLock(boolean fair) {
    }

    public ReentrantReadWriteLock.WriteLock writeLock();

    public ReentrantReadWriteLock.ReadLock readLock();

    public static class ReadLock implements Lock, java.io.Serializable {

        protected ReadLock(ReentrantReadWriteLock lock) {
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

        public String toString();
    }

    public static class WriteLock implements Lock, java.io.Serializable {

        protected WriteLock(ReentrantReadWriteLock lock) {
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

        public String toString();

        @EnsuresLockHeldIf(expression = { "this" }, result = true)
        @ReleasesNoLocks
        public boolean isHeldByCurrentThread();

        public int getHoldCount();
    }

    public final boolean isFair();

    protected Thread getOwner();

    public int getReadLockCount();

    public boolean isWriteLocked();

    public boolean isWriteLockedByCurrentThread();

    public int getWriteHoldCount();

    public int getReadHoldCount();

    protected Collection<Thread> getQueuedWriterThreads();

    protected Collection<Thread> getQueuedReaderThreads();

    public final boolean hasQueuedThreads();

    public final boolean hasQueuedThread(Thread thread);

    public final int getQueueLength();

    protected Collection<Thread> getQueuedThreads();

    public boolean hasWaiters(Condition condition);

    public int getWaitQueueLength(Condition condition);

    protected Collection<Thread> getWaitingThreads(Condition condition);

    public String toString();
}
