package java.util.concurrent.locks;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class LockSupport {

    public static void unpark(Thread thread);

    public static void park(Object blocker);

    public static void parkNanos(Object blocker, long nanos);

    public static void parkUntil(Object blocker, long deadline);

    public static Object getBlocker(Thread t);

    public static void park();

    public static void parkNanos(long nanos);

    public static void parkUntil(long deadline);
}
