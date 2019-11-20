package java.lang.management;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.openmbean.CompositeData;
import sun.management.MemoryUsageCompositeData;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MemoryUsage {

    public MemoryUsage(long init, long used, long committed, long max) {
    }

    public long getInit();

    public long getUsed();

    public long getCommitted();

    public long getMax();

    public String toString();

    public static MemoryUsage from(CompositeData cd);
}
