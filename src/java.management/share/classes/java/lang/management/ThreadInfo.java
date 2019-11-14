package java.lang.management;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.openmbean.ArrayType;
import javax.management.openmbean.CompositeData;
import sun.management.ManagementFactoryHelper;
import sun.management.ThreadInfoCompositeData;
import static java.lang.Thread.State.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ThreadInfo {

    private String threadName;

    private long threadId;

    private long blockedTime;

    private long blockedCount;

    private long waitedTime;

    private long waitedCount;

    private LockInfo lock;

    private String lockName;

    private long lockOwnerId;

    private String lockOwnerName;

    private boolean daemon;

    private boolean inNative;

    private boolean suspended;

    private Thread.State threadState;

    private int priority;

    private StackTraceElement[] stackTrace;

    private MonitorInfo[] lockedMonitors;

    private LockInfo[] lockedSynchronizers;

    private static MonitorInfo[] EMPTY_MONITORS = new MonitorInfo[0];

    private static LockInfo[] EMPTY_SYNCS = new LockInfo[0];

    private ThreadInfo(Thread t, int state, Object lockObj, Thread lockOwner, long blockedCount, long blockedTime, long waitedCount, long waitedTime, StackTraceElement[] stackTrace) {
        initialize(t, state, lockObj, lockOwner, blockedCount, blockedTime, waitedCount, waitedTime, stackTrace, EMPTY_MONITORS, EMPTY_SYNCS);
    }

    private ThreadInfo(Thread t, int state, Object lockObj, Thread lockOwner, long blockedCount, long blockedTime, long waitedCount, long waitedTime, StackTraceElement[] stackTrace, Object[] monitors, int[] stackDepths, Object[] synchronizers) {
        int numMonitors = (monitors == null ? 0 : monitors.length);
        MonitorInfo[] lockedMonitors;
        if (numMonitors == 0) {
            lockedMonitors = EMPTY_MONITORS;
        } else {
            lockedMonitors = new MonitorInfo[numMonitors];
            for (int i = 0; i < numMonitors; i++) {
                Object lock = monitors[i];
                String className = lock.getClass().getName();
                int identityHashCode = System.identityHashCode(lock);
                int depth = stackDepths[i];
                StackTraceElement ste = (depth >= 0 ? stackTrace[depth] : null);
                lockedMonitors[i] = new MonitorInfo(className, identityHashCode, depth, ste);
            }
        }
        int numSyncs = (synchronizers == null ? 0 : synchronizers.length);
        LockInfo[] lockedSynchronizers;
        if (numSyncs == 0) {
            lockedSynchronizers = EMPTY_SYNCS;
        } else {
            lockedSynchronizers = new LockInfo[numSyncs];
            for (int i = 0; i < numSyncs; i++) {
                Object lock = synchronizers[i];
                String className = lock.getClass().getName();
                int identityHashCode = System.identityHashCode(lock);
                lockedSynchronizers[i] = new LockInfo(className, identityHashCode);
            }
        }
        initialize(t, state, lockObj, lockOwner, blockedCount, blockedTime, waitedCount, waitedTime, stackTrace, lockedMonitors, lockedSynchronizers);
    }

    private void initialize(Thread t, int state, Object lockObj, Thread lockOwner, long blockedCount, long blockedTime, long waitedCount, long waitedTime, StackTraceElement[] stackTrace, MonitorInfo[] lockedMonitors, LockInfo[] lockedSynchronizers);

    private ThreadInfo(CompositeData cd) {
        ThreadInfoCompositeData ticd = ThreadInfoCompositeData.getInstance(cd);
        threadId = ticd.threadId();
        threadName = ticd.threadName();
        blockedTime = ticd.blockedTime();
        blockedCount = ticd.blockedCount();
        waitedTime = ticd.waitedTime();
        waitedCount = ticd.waitedCount();
        lockName = ticd.lockName();
        lockOwnerId = ticd.lockOwnerId();
        lockOwnerName = ticd.lockOwnerName();
        threadState = ticd.threadState();
        suspended = ticd.suspended();
        inNative = ticd.inNative();
        stackTrace = ticd.stackTrace();
        lock = ticd.lockInfo();
        lockedMonitors = ticd.lockedMonitors();
        lockedSynchronizers = ticd.lockedSynchronizers();
        daemon = ticd.isDaemon();
        priority = ticd.getPriority();
    }

    public long getThreadId();

    public String getThreadName();

    public Thread.State getThreadState();

    public long getBlockedTime();

    public long getBlockedCount();

    public long getWaitedTime();

    public long getWaitedCount();

    public LockInfo getLockInfo();

    public String getLockName();

    public long getLockOwnerId();

    public String getLockOwnerName();

    public StackTraceElement[] getStackTrace();

    public boolean isSuspended();

    public boolean isInNative();

    public boolean isDaemon();

    public int getPriority();

    public String toString();

    private static final int MAX_FRAMES = 8;

    public static ThreadInfo from(CompositeData cd);

    public MonitorInfo[] getLockedMonitors();

    public LockInfo[] getLockedSynchronizers();

    private static final StackTraceElement[] NO_STACK_TRACE = new StackTraceElement[0];
}
