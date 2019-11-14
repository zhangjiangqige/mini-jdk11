package java.lang.ref;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.misc.JavaLangRefAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.ref.Cleaner;

@AnnotatedFor({ "lock", "nullness" })
@SuppressWarnings({ "rawtypes" })
public abstract class Reference<T> {

    private T referent;

    volatile ReferenceQueue<? super T> queue;

    @SuppressWarnings("rawtypes")
    volatile Reference next;

    private transient Reference<T> discovered;

    private static class ReferenceHandler extends Thread {

        private static void ensureClassInitialized(Class<?> clazz);

        static {
            ensureClassInitialized(Cleaner.class);
        }

        ReferenceHandler(ThreadGroup g, String name) {
            super(g, null, name, 0, false);
        }

        @SuppressWarnings({ "unchecked" })
        public void run();
    }

    private static native Reference<Object> getAndClearReferencePendingList();

    private static native boolean hasReferencePendingList();

    private static native void waitForReferencePendingList();

    private static final Object processPendingLock = new Object();

    private static boolean processPendingActive = false;

    private static void processPendingReferences();

    private static boolean waitForReferenceProcessing() throws InterruptedException;

    static {
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        for (ThreadGroup tgn = tg; tgn != null; tg = tgn, tgn = tg.getParent()) ;
        Thread handler = new ReferenceHandler(tg, "Reference Handler");
        handler.setPriority(Thread.MAX_PRIORITY);
        handler.setDaemon(true);
        handler.start();
        SharedSecrets.setJavaLangRefAccess(new JavaLangRefAccess() {

            @Override
            public boolean waitForReferenceProcessing() throws InterruptedException {
                return Reference.waitForReferenceProcessing();
            }

            @Override
            public void runFinalization() {
                Finalizer.runFinalization();
            }
        });
    }

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    @Nullable
    public T get(@GuardSatisfied Reference<T> this);

    public void clear();

    public boolean isEnqueued();

    public boolean enqueue();

    @Override
    protected Object clone() throws CloneNotSupportedException;

    Reference(T referent) {
        this(referent, null);
    }

    @SuppressWarnings({ "unchecked" })
    Reference(T referent, ReferenceQueue<? super T> queue) {
        this.referent = referent;
        this.queue = (queue == null) ? ReferenceQueue.NULL : queue;
    }

    @ForceInline
    public static void reachabilityFence(Object ref);
}
