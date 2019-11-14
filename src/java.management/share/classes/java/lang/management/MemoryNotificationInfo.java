package java.lang.management;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.openmbean.CompositeData;
import sun.management.MemoryNotifInfoCompositeData;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MemoryNotificationInfo {

    private final String poolName;

    private final MemoryUsage usage;

    private final long count;

    public static final String MEMORY_THRESHOLD_EXCEEDED = "java.management.memory.threshold.exceeded";

    public static final String MEMORY_COLLECTION_THRESHOLD_EXCEEDED = "java.management.memory.collection.threshold.exceeded";

    public MemoryNotificationInfo(String poolName, MemoryUsage usage, long count) {
        if (poolName == null) {
            throw new NullPointerException("Null poolName");
        }
        if (usage == null) {
            throw new NullPointerException("Null usage");
        }
        this.poolName = poolName;
        this.usage = usage;
        this.count = count;
    }

    MemoryNotificationInfo(CompositeData cd) {
        MemoryNotifInfoCompositeData.validateCompositeData(cd);
        this.poolName = MemoryNotifInfoCompositeData.getPoolName(cd);
        this.usage = MemoryNotifInfoCompositeData.getUsage(cd);
        this.count = MemoryNotifInfoCompositeData.getCount(cd);
    }

    public String getPoolName();

    public MemoryUsage getUsage();

    public long getCount();

    public static MemoryNotificationInfo from(CompositeData cd);
}
