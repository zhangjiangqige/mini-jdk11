package java.lang.management;

import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import javax.management.openmbean.CompositeData;
import sun.management.MonitorInfoCompositeData;

@AnnotatedFor({ "signature" })
public class MonitorInfo extends LockInfo {

    public MonitorInfo(@BinaryName String className, int identityHashCode, int stackDepth, StackTraceElement stackFrame) {
    }

    public int getLockedStackDepth();

    public StackTraceElement getLockedStackFrame();

    public static MonitorInfo from(CompositeData cd);
}
