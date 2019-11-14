package java.lang.management;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.openmbean.CompositeData;
import sun.management.MemoryUsageCompositeData;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MemoryUsage {

    private final long init;

    private final long used;

    private final long committed;

    private final long max;

    public MemoryUsage(long init, long used, long committed, long max) {
        if (init < -1) {
            throw new IllegalArgumentException("init parameter = " + init + " is negative but not -1.");
        }
        if (max < -1) {
            throw new IllegalArgumentException("max parameter = " + max + " is negative but not -1.");
        }
        if (used < 0) {
            throw new IllegalArgumentException("used parameter = " + used + " is negative.");
        }
        if (committed < 0) {
            throw new IllegalArgumentException("committed parameter = " + committed + " is negative.");
        }
        if (used > committed) {
            throw new IllegalArgumentException("used = " + used + " should be <= committed = " + committed);
        }
        if (max >= 0 && committed > max) {
            throw new IllegalArgumentException("committed = " + committed + " should be < max = " + max);
        }
        this.init = init;
        this.used = used;
        this.committed = committed;
        this.max = max;
    }

    private MemoryUsage(CompositeData cd) {
        MemoryUsageCompositeData.validateCompositeData(cd);
        this.init = MemoryUsageCompositeData.getInit(cd);
        this.used = MemoryUsageCompositeData.getUsed(cd);
        this.committed = MemoryUsageCompositeData.getCommitted(cd);
        this.max = MemoryUsageCompositeData.getMax(cd);
    }

    public long getInit();

    public long getUsed();

    public long getCommitted();

    public long getMax();

    public String toString();

    public static MemoryUsage from(CompositeData cd);
}
