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

    private static final long serialVersionUID = -6992448646407690164L;

    private final ReentrantReadWriteLock.ReadLock readerLock;

    private final ReentrantReadWriteLock.WriteLock writerLock;

    final Sync sync;

    public ReentrantReadWriteLock() {
        this(false);
    }

    public ReentrantReadWriteLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
        readerLock = new ReadLock(this);
        writerLock = new WriteLock(this);
    }

    public ReentrantReadWriteLock.WriteLock writeLock();

    public ReentrantReadWriteLock.ReadLock readLock();

    abstract static class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = 6317671515068378041L;

        static final int SHARED_SHIFT = 16;

        static final int SHARED_UNIT = (1 << SHARED_SHIFT);

        static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;

        static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

        static int sharedCount(int c);

        static int exclusiveCount(int c);

        static final class HoldCounter {

            int count;

            final long tid = LockSupport.getThreadId(Thread.currentThread());
        }

        static final class ThreadLocalHoldCounter extends ThreadLocal<HoldCounter> {

            public HoldCounter initialValue();
        }

        private transient ThreadLocalHoldCounter readHolds;

        private transient HoldCounter cachedHoldCounter;

        private transient Thread firstReader;

        private transient int firstReaderHoldCount;

        Sync() {
            readHolds = new ThreadLocalHoldCounter();
            setState(getState());
        }

        abstract boolean readerShouldBlock();

        abstract boolean writerShouldBlock();

        @ReservedStackAccess
        protected final boolean tryRelease(int releases);

        @ReservedStackAccess
        protected final boolean tryAcquire(int acquires);

        @ReservedStackAccess
        protected final boolean tryReleaseShared(int unused);

        private static IllegalMonitorStateException unmatchedUnlockException();

        @ReservedStackAccess
        protected final int tryAcquireShared(int unused);

        final int fullTryAcquireShared(Thread current);

        @ReservedStackAccess
        final boolean tryWriteLock();

        @ReservedStackAccess
        final boolean tryReadLock();

        protected final boolean isHeldExclusively();

        final ConditionObject newCondition();

        final Thread getOwner();

        final int getReadLockCount();

        final boolean isWriteLocked();

        final int getWriteHoldCount();

        final int getReadHoldCount();

        private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

        final int getCount();
    }

    static final class NonfairSync extends Sync {

        private static final long serialVersionUID = -8159625535654395037L;

        final boolean writerShouldBlock();

        final boolean readerShouldBlock();
    }

    static final class FairSync extends Sync {

        private static final long serialVersionUID = -2274990926593161451L;

        final boolean writerShouldBlock();

        final boolean readerShouldBlock();
    }

    public static class ReadLock implements Lock, java.io.Serializable {

        private static final long serialVersionUID = -5992448646407690164L;

        private final Sync sync;

        protected ReadLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
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

        private static final long serialVersionUID = -4992448646407690164L;

        private final Sync sync;

        protected WriteLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
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
