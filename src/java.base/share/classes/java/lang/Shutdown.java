package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.VM;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
class Shutdown {

    private static final int MAX_SYSTEM_HOOKS = 10;

    @Nullable
    private static final Runnable[] hooks = new Runnable[MAX_SYSTEM_HOOKS];

    private static int currentRunningHook = -1;

    private static class Lock {
    }

    private static Object lock = new Lock();

    private static Object haltLock = new Lock();

    static void add(int slot, boolean registerShutdownInProgress, Runnable hook);

    private static void runHooks();

    static native void beforeHalt();

    static void halt(int status);

    static native void halt0(int status);

    static void exit(int status);

    static void shutdown();
}
