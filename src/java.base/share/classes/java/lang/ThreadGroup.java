package java.lang;

import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.PrintStream;
import java.util.Arrays;
import jdk.internal.misc.VM;

@AnnotatedFor({ "index", "interning", "lock", "nullable" })
@UsesObjectEquals
public class ThreadGroup implements Thread.UncaughtExceptionHandler {

    private final ThreadGroup parent;

    String name;

    int maxPriority;

    boolean destroyed;

    boolean daemon;

    int nUnstartedThreads = 0;

    @LTEqLengthOf({ "threads" })
    @NonNegative
    int nthreads;

    Thread[] threads;

    @LTEqLengthOf({ "groups" })
    @NonNegative
    int ngroups;

    ThreadGroup[] groups;

    private ThreadGroup() {
        this.name = "system";
        this.maxPriority = Thread.MAX_PRIORITY;
        this.parent = null;
    }

    public ThreadGroup(@Nullable String name) {
        this(Thread.currentThread().getThreadGroup(), name);
    }

    public ThreadGroup(ThreadGroup parent, @Nullable String name) {
        this(checkParentAccess(parent), parent, name);
    }

    private ThreadGroup(Void unused, ThreadGroup parent, String name) {
        this.name = name;
        this.maxPriority = parent.maxPriority;
        this.daemon = parent.daemon;
        this.parent = parent;
        parent.add(this);
    }

    private static Void checkParentAccess(ThreadGroup parent);

    @Nullable
    public final String getName();

    @Nullable
    public final ThreadGroup getParent();

    public final int getMaxPriority();

    @Pure
    public final boolean isDaemon(@GuardSatisfied ThreadGroup this);

    @Pure
    public synchronized boolean isDestroyed(@GuardSatisfied ThreadGroup this);

    public final void setDaemon(boolean daemon);

    @CFComment({ "index: groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings("index:array.access.unsafe.high")
    public final void setMaxPriority(int pri);

    public final boolean parentOf(ThreadGroup g);

    public final void checkAccess();

    @CFComment({ "index: groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings("index:array.access.unsafe.high")
    @NonNegative
    public int activeCount();

    @NonNegative
    public int enumerate(Thread[] list);

    @NonNegative
    public int enumerate(Thread[] list, boolean recurse);

    @CFComment({ "index:", "#1: To enter this loop, nt has to be positive, i.c, list.length - n is @Positive, and n is @NonNegative, hence if this loop is entered, n is @LTLengthOf(\"list\")", "#2: groupSnapshot.length = ngroupsSnapshot by #0.1, for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings({ "index:array.access.unsafe.high", "index:array.access.unsafe.high.range" })
    @NonNegative
    private int enumerate(Thread[] list, @NonNegative int n, boolean recurse);

    @CFComment({ "index: groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings("index:array.access.unsafe.high")
    @NonNegative
    public int activeGroupCount();

    @NonNegative
    public int enumerate(ThreadGroup[] list);

    @NonNegative
    public int enumerate(ThreadGroup[] list, boolean recurse);

    @CFComment({ "index: groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings("index:array.access.unsafe.high")
    @NonNegative
    private int enumerate(ThreadGroup[] list, @NonNegative int n, boolean recurse);

    @Deprecated(since = "1.2")
    public final void stop();

    @CFComment({ " groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings("index:array.access.unsafe.high")
    public final void interrupt();

    @Deprecated(since = "1.2")
    @SuppressWarnings("deprecation")
    public final void suspend();

    @CFComment({ "index: groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings({ "deprecation", "index:array.access.unsafe.high" })
    private boolean stopOrSuspend(boolean suspend);

    @CFComment({ "index:  // groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @Deprecated(since = "1.2")
    @SuppressWarnings({ "deprecation", "index:array.access.unsafe.high" })
    public final void resume();

    @CFComment({ "index: groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings("index:array.access.unsafe.high")
    public final void destroy();

    @CFComment({ "index: #1: If ngroups = groups.length, length of group is doubled" })
    @SuppressWarnings({ "index:array.access.unsafe.high", "index:compound.assignment.type.incompatible" })
    private final void add(ThreadGroup g);

    @CFComment({ "index: #1: ngroups - i <= groups.length - i" })
    @SuppressWarnings("index:argument.type.incompatible")
    private void remove(ThreadGroup g);

    void addUnstarted();

    @CFComment({ "index: #1: If nthreads = threads.length, length of threads is doubled" })
    @SuppressWarnings({ "index:array.access.unsafe.high", "index:compound.assignment.type.incompatible" })
    void add(Thread t);

    void threadStartFailed(Thread t);

    void threadTerminated(Thread t);

    @CFComment({ "index: #1: --nthreads - i < threads.length - i, also, --nthreads - i is @NonNegative as --nthreads >= i" })
    @SuppressWarnings("index:argument.type.incompatible")
    private void remove(Thread t);

    @CFComment({ "index: groupSnapshot.length = ngroupsSnapshot by #0.1", "for the else case, ngroupsSnapshot will be null and it will never enter the group as nGroups will be 0" })
    @SuppressWarnings("index:array.access.unsafe.high")
    public void list();

    void list(PrintStream out, int indent);

    public void uncaughtException(Thread t, Throwable e);

    @Deprecated(since = "1.2")
    public boolean allowThreadSuspension(boolean b);

    @SideEffectFree
    public String toString(@GuardSatisfied ThreadGroup this);
}
