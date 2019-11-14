package java.util.concurrent.locks;

import org.checkerframework.checker.lock.qual.EnsuresLockHeld;
import org.checkerframework.checker.lock.qual.EnsuresLockHeldIf;
import org.checkerframework.checker.lock.qual.MayReleaseLocks;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.concurrent.TimeUnit;

@AnnotatedFor({ "lock" })
public interface Lock {

    @EnsuresLockHeld({ "this" })
    @ReleasesNoLocks
    void lock();

    @EnsuresLockHeld({ "this" })
    @ReleasesNoLocks
    void lockInterruptibly() throws InterruptedException;

    @EnsuresLockHeldIf(expression = { "this" }, result = true)
    @ReleasesNoLocks
    boolean tryLock();

    @EnsuresLockHeldIf(expression = { "this" }, result = true)
    @ReleasesNoLocks
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    @MayReleaseLocks
    void unlock();

    Condition newCondition();
}
