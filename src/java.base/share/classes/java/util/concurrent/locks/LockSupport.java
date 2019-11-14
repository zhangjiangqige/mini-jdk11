package java.util.concurrent.locks;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class LockSupport {

    private LockSupport() {
    }

    private static void setBlocker(Thread t, Object arg);

    public static void unpark(Thread thread);

    public static void park(Object blocker);

    public static void parkNanos(Object blocker, long nanos);

    public static void parkUntil(Object blocker, long deadline);

    public static Object getBlocker(Thread t);

    public static void park();

    public static void parkNanos(long nanos);

    public static void parkUntil(long deadline);

    static final int nextSecondarySeed();

    static final long getThreadId(Thread thread);

    private static final Unsafe U = Unsafe.getUnsafe();

    private static final long PARKBLOCKER = U.objectFieldOffset(Thread.class, "parkBlocker");

    private static final long SECONDARY = U.objectFieldOffset(Thread.class, "threadLocalRandomSecondarySeed");

    private static final long TID = U.objectFieldOffset(Thread.class, "tid");
}
