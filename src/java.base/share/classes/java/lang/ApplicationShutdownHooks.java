package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class ApplicationShutdownHooks {

    private static IdentityHashMap<Thread, Thread> hooks;

    static {
        try {
            Shutdown.add(1, false, new Runnable() {

                public void run() {
                    runHooks();
                }
            });
            hooks = new IdentityHashMap<>();
        } catch (IllegalStateException e) {
            hooks = null;
        }
    }

    private ApplicationShutdownHooks() {
    }

    static synchronized void add(Thread hook);

    static synchronized boolean remove(Thread hook);

    static void runHooks();
}
