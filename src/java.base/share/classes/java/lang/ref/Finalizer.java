package java.lang.ref;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.PrivilegedAction;
import java.security.AccessController;
import jdk.internal.misc.JavaLangAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.VM;

@AnnotatedFor({ "nullness" })
@SuppressWarnings({ "rawtypes" })
final class Finalizer extends FinalReference<Object> {

    private static ReferenceQueue<Object> queue = new ReferenceQueue<>();

    private static Finalizer unfinalized = null;

    private static final Object lock = new Object();

    private Finalizer next, prev;

    @SuppressWarnings({ "unchecked" })
    private Finalizer(Object finalizee) {
        super(finalizee, queue);
        synchronized (lock) {
            if (unfinalized != null) {
                this.next = unfinalized;
                unfinalized.prev = this;
            }
            unfinalized = this;
        }
    }

    static ReferenceQueue<Object> getQueue();

    static void register(Object finalizee);

    private void runFinalizer(JavaLangAccess jla);

    private static void forkSecondaryFinalizer(final Runnable proc);

    static void runFinalization();

    private static class FinalizerThread extends Thread {

        private volatile boolean running;

        FinalizerThread(ThreadGroup g) {
            super(g, null, "Finalizer", 0, false);
        }

        public void run();
    }

    static {
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        for (ThreadGroup tgn = tg; tgn != null; tg = tgn, tgn = tg.getParent()) ;
        Thread finalizer = new FinalizerThread(tg);
        finalizer.setPriority(Thread.MAX_PRIORITY - 2);
        finalizer.setDaemon(true);
        finalizer.start();
    }
}
