package java.lang.management;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Map;

@AnnotatedFor({ "nullness" })
public interface ThreadMXBean extends PlatformManagedObject {

    public int getThreadCount();

    public int getPeakThreadCount();

    public long getTotalStartedThreadCount();

    public int getDaemonThreadCount();

    public long[] getAllThreadIds();

    @Nullable
    public ThreadInfo getThreadInfo(long id);

    @Nullable
    public ThreadInfo[] getThreadInfo(long[] ids);

    @Nullable
    public ThreadInfo getThreadInfo(long id, int maxDepth);

    @Nullable
    public ThreadInfo[] getThreadInfo(long[] ids, int maxDepth);

    public boolean isThreadContentionMonitoringSupported();

    public boolean isThreadContentionMonitoringEnabled();

    public void setThreadContentionMonitoringEnabled(boolean enable);

    public long getCurrentThreadCpuTime();

    public long getCurrentThreadUserTime();

    public long getThreadCpuTime(long id);

    public long getThreadUserTime(long id);

    public boolean isThreadCpuTimeSupported();

    public boolean isCurrentThreadCpuTimeSupported();

    public boolean isThreadCpuTimeEnabled();

    public void setThreadCpuTimeEnabled(boolean enable);

    public long @Nullable [] findMonitorDeadlockedThreads();

    public void resetPeakThreadCount();

    public long @Nullable [] findDeadlockedThreads();

    public boolean isObjectMonitorUsageSupported();

    public boolean isSynchronizerUsageSupported();

    @Nullable
    public ThreadInfo[] getThreadInfo(long[] ids, boolean lockedMonitors, boolean lockedSynchronizers);

    public default ThreadInfo[] getThreadInfo(long[] ids, boolean lockedMonitors, boolean lockedSynchronizers, int maxDepth);

    public ThreadInfo[] dumpAllThreads(boolean lockedMonitors, boolean lockedSynchronizers);

    public default ThreadInfo[] dumpAllThreads(boolean lockedMonitors, boolean lockedSynchronizers, int maxDepth);
}
