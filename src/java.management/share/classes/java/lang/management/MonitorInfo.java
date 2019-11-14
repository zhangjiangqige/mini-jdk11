package java.lang.management;

import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import javax.management.openmbean.CompositeData;
import sun.management.MonitorInfoCompositeData;

@AnnotatedFor({ "signature" })
public class MonitorInfo extends LockInfo {

    private int stackDepth;

    private StackTraceElement stackFrame;

    public MonitorInfo(@BinaryName String className, int identityHashCode, int stackDepth, StackTraceElement stackFrame) {
        super(className, identityHashCode);
        if (stackDepth >= 0 && stackFrame == null) {
            throw new IllegalArgumentException("Parameter stackDepth is " + stackDepth + " but stackFrame is null");
        }
        if (stackDepth < 0 && stackFrame != null) {
            throw new IllegalArgumentException("Parameter stackDepth is " + stackDepth + " but stackFrame is not null");
        }
        this.stackDepth = stackDepth;
        this.stackFrame = stackFrame;
    }

    public int getLockedStackDepth();

    public StackTraceElement getLockedStackFrame();

    public static MonitorInfo from(CompositeData cd);
}
