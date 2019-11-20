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

    public static ThreadInfo from(CompositeData cd);

    public MonitorInfo[] getLockedMonitors();

    public LockInfo[] getLockedSynchronizers();
}
