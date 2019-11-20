package java.lang;

import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.EnsuresLockHeldIf;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.AccessControlContext;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.LockSupport;
import jdk.internal.misc.TerminatingThreadLocal;
import sun.nio.ch.Interruptible;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import sun.security.util.SecurityConstants;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public class Thread implements Runnable {

    public static final int MIN_PRIORITY;

    public static final int NORM_PRIORITY;

    public static final int MAX_PRIORITY;

    @HotSpotIntrinsicCandidate
    public static native Thread currentThread();

    public static native void yield();

    public static native void sleep(long millis) throws InterruptedException;

    public static void sleep(long millis, int nanos) throws InterruptedException;

    @HotSpotIntrinsicCandidate
    public static void onSpinWait();

    @Override
    protected Object clone() throws CloneNotSupportedException;

    public Thread() {
    }

    public Thread(@Nullable Runnable target) {
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable target) {
    }

    public Thread(String name) {
    }

    public Thread(@Nullable ThreadGroup group, String name) {
    }

    public Thread(@Nullable Runnable target, String name) {
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable target, String name) {
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable target, String name, long stackSize) {
    }

    public Thread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
    }

    public synchronized void start();

    @Override
    public void run();

    @Deprecated()
    public final void stop();

    public void interrupt();

    public static boolean interrupted();

    @Pure
    public boolean isInterrupted(@GuardSatisfied Thread this);

    @Pure
    public final native boolean isAlive(@GuardSatisfied Thread this);

    @Deprecated()
    public final void suspend();

    @Deprecated()
    public final void resume();

    public final void setPriority(@UnknownInitialization(java.lang.Thread.class) Thread this, int newPriority);

    public final int getPriority();

    public final synchronized void setName(String name);

    public final String getName();

    @Nullable
    public final ThreadGroup getThreadGroup();

    public static int activeCount();

    public static int enumerate(Thread[] tarray);

    @Deprecated()
    public native int countStackFrames();

    public final synchronized void join(long millis) throws InterruptedException;

    public final synchronized void join(long millis, int nanos) throws InterruptedException;

    public final void join() throws InterruptedException;

    public static void dumpStack();

    public final void setDaemon(@UnknownInitialization Thread this, boolean on);

    @Pure
    public final boolean isDaemon(@GuardSatisfied Thread this);

    public final void checkAccess();

    @Override
    @SideEffectFree
    public String toString(@GuardSatisfied Thread this);

    @CallerSensitive
    @Nullable
    public ClassLoader getContextClassLoader();

    public void setContextClassLoader(@Nullable ClassLoader cl);

    @EnsuresLockHeldIf(expression = { "#1" }, result = true)
    @ReleasesNoLocks
    public static native boolean holdsLock(Object obj);

    public StackTraceElement[] getStackTrace();

    public static Map<Thread, StackTraceElement[]> getAllStackTraces();

    public long getId();

    public enum State {

        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED
    }

    public State getState();

    @FunctionalInterface
    public interface UncaughtExceptionHandler {
    }

    public static void setDefaultUncaughtExceptionHandler(@Nullable UncaughtExceptionHandler eh);

    @Nullable
    public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler();

    @Nullable
    public UncaughtExceptionHandler getUncaughtExceptionHandler();

    public void setUncaughtExceptionHandler(@Nullable UncaughtExceptionHandler eh);
}
