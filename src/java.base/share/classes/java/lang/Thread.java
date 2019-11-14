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

    private static native void registerNatives();

    static {
        registerNatives();
    }

    private volatile String name;

    private int priority;

    private boolean daemon = false;

    private boolean stillborn = false;

    private long eetop;

    private Runnable target;

    private ThreadGroup group;

    private ClassLoader contextClassLoader;

    private AccessControlContext inheritedAccessControlContext;

    private static int threadInitNumber;

    private static synchronized int nextThreadNum();

    ThreadLocal.ThreadLocalMap threadLocals = null;

    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;

    private final long stackSize;

    private long nativeParkEventPointer;

    private final long tid;

    private static long threadSeqNumber;

    private static synchronized long nextThreadID();

    private volatile int threadStatus;

    volatile Object parkBlocker;

    private volatile Interruptible blocker;

    private final Object blockerLock = new Object();

    static void blockedOn(Interruptible b);

    public static final int MIN_PRIORITY = 1;

    public static final int NORM_PRIORITY = 5;

    public static final int MAX_PRIORITY = 10;

    @HotSpotIntrinsicCandidate
    public static native Thread currentThread();

    public static native void yield();

    public static native void sleep(long millis) throws InterruptedException;

    public static void sleep(long millis, int nanos) throws InterruptedException;

    @HotSpotIntrinsicCandidate
    public static void onSpinWait();

    private Thread(ThreadGroup g, Runnable target, String name, long stackSize, AccessControlContext acc, boolean inheritThreadLocals) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = name;
        Thread parent = currentThread();
        SecurityManager security = System.getSecurityManager();
        if (g == null) {
            if (security != null) {
                g = security.getThreadGroup();
            }
            if (g == null) {
                g = parent.getThreadGroup();
            }
        }
        g.checkAccess();
        if (security != null) {
            if (isCCLOverridden(getClass())) {
                security.checkPermission(SecurityConstants.SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }
        g.addUnstarted();
        this.group = g;
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        if (security == null || isCCLOverridden(parent.getClass()))
            this.contextClassLoader = parent.getContextClassLoader();
        else
            this.contextClassLoader = parent.contextClassLoader;
        this.inheritedAccessControlContext = acc != null ? acc : AccessController.getContext();
        this.target = target;
        setPriority(priority);
        if (inheritThreadLocals && parent.inheritableThreadLocals != null)
            this.inheritableThreadLocals = ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        this.stackSize = stackSize;
        this.tid = nextThreadID();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException;

    public Thread() {
        this(null, null, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(@Nullable Runnable target) {
        this(null, target, "Thread-" + nextThreadNum(), 0);
    }

    Thread(Runnable target, AccessControlContext acc) {
        this(null, target, "Thread-" + nextThreadNum(), 0, acc, false);
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable target) {
        this(group, target, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(String name) {
        this(null, null, name, 0);
    }

    public Thread(@Nullable ThreadGroup group, String name) {
        this(group, null, name, 0);
    }

    public Thread(@Nullable Runnable target, String name) {
        this(null, target, name, 0);
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable target, String name) {
        this(group, target, name, 0);
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable target, String name, long stackSize) {
        this(group, target, name, stackSize, null, true);
    }

    public Thread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
        this(group, target, name, stackSize, null, inheritThreadLocals);
    }

    public synchronized void start();

    private native void start0();

    @Override
    public void run();

    private void exit();

    @Deprecated(since = "1.2")
    public final void stop();

    public void interrupt();

    public static boolean interrupted();

    @Pure
    public boolean isInterrupted(@GuardSatisfied Thread this);

    @HotSpotIntrinsicCandidate
    private native boolean isInterrupted(boolean ClearInterrupted);

    @Pure
    public final native boolean isAlive(@GuardSatisfied Thread this);

    @Deprecated(since = "1.2")
    public final void suspend();

    @Deprecated(since = "1.2")
    public final void resume();

    public final void setPriority(@UnknownInitialization(java.lang.Thread.class) Thread this, int newPriority);

    public final int getPriority();

    public final synchronized void setName(String name);

    public final String getName();

    @Nullable
    public final ThreadGroup getThreadGroup();

    public static int activeCount();

    public static int enumerate(Thread[] tarray);

    @Deprecated(since = "1.2", forRemoval = true)
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

    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];

    public StackTraceElement[] getStackTrace();

    public static Map<Thread, StackTraceElement[]> getAllStackTraces();

    private static class Caches {

        static final ConcurrentMap<WeakClassKey, Boolean> subclassAudits = new ConcurrentHashMap<>();

        static final ReferenceQueue<Class<?>> subclassAuditsQueue = new ReferenceQueue<>();
    }

    private static boolean isCCLOverridden(Class<?> cl);

    private static boolean auditSubclass(final Class<?> subcl);

    private static native StackTraceElement[][] dumpThreads(Thread[] threads);

    private static native Thread[] getThreads();

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

        void uncaughtException(Thread t, Throwable e);
    }

    private volatile UncaughtExceptionHandler uncaughtExceptionHandler;

    private static volatile UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public static void setDefaultUncaughtExceptionHandler(@Nullable UncaughtExceptionHandler eh);

    @Nullable
    public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler();

    @Nullable
    public UncaughtExceptionHandler getUncaughtExceptionHandler();

    public void setUncaughtExceptionHandler(@Nullable UncaughtExceptionHandler eh);

    private void dispatchUncaughtException(Throwable e);

    static void processQueue(ReferenceQueue<Class<?>> queue, ConcurrentMap<? extends WeakReference<Class<?>>, ?> map);

    static class WeakClassKey extends WeakReference<Class<?>> {

        private final int hash;

        WeakClassKey(Class<?> cl, ReferenceQueue<Class<?>> refQueue) {
            super(cl, refQueue);
            hash = System.identityHashCode(cl);
        }

        @Override
        public int hashCode();

        @Override
        public boolean equals(Object obj);
    }

    @jdk.internal.vm.annotation.Contended("tlr")
    long threadLocalRandomSeed;

    @jdk.internal.vm.annotation.Contended("tlr")
    int threadLocalRandomProbe;

    @jdk.internal.vm.annotation.Contended("tlr")
    int threadLocalRandomSecondarySeed;

    private native void setPriority0(int newPriority);

    private native void stop0(Object o);

    private native void suspend0();

    private native void resume0();

    private native void interrupt0();

    private native void setNativeName(String name);
}
