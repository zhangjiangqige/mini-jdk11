package java.lang.management;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.openmbean.CompositeData;
import sun.management.MemoryNotifInfoCompositeData;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MemoryNotificationInfo {

    public static final String MEMORY_THRESHOLD_EXCEEDED;

    public static final String MEMORY_COLLECTION_THRESHOLD_EXCEEDED;

    public MemoryNotificationInfo(String poolName, MemoryUsage usage, long count) {
    }

    public String getPoolName();

    public MemoryUsage getUsage();

    public long getCount();

    public static MemoryNotificationInfo from(CompositeData cd);
}
