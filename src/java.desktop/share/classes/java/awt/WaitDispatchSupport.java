package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.security.PrivilegedAction;
import java.security.AccessController;
import sun.awt.PeerEvent;
import sun.util.logging.PlatformLogger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class WaitDispatchSupport implements SecondaryLoop {

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.event.WaitDispatchSupport");

    private EventDispatchThread dispatchThread;

    private EventFilter filter;

    private volatile Conditional extCondition;

    private volatile Conditional condition;

    private long interval;

    private static Timer timer;

    private TimerTask timerTask;

    private AtomicBoolean keepBlockingEDT = new AtomicBoolean(false);

    private AtomicBoolean keepBlockingCT = new AtomicBoolean(false);

    private AtomicBoolean afterExit = new AtomicBoolean(false);

    private static synchronized void initializeTimer();

    public WaitDispatchSupport(EventDispatchThread dispatchThread) {
        this(dispatchThread, null);
    }

    public WaitDispatchSupport(EventDispatchThread dispatchThread, Conditional extCond) {
        if (dispatchThread == null) {
            throw new IllegalArgumentException("The dispatchThread can not be null");
        }
        this.dispatchThread = dispatchThread;
        this.extCondition = extCond;
        this.condition = new Conditional() {

            @Override
            public boolean evaluate() {
                if (log.isLoggable(PlatformLogger.Level.FINEST)) {
                    log.finest("evaluate(): blockingEDT=" + keepBlockingEDT.get() + ", blockingCT=" + keepBlockingCT.get());
                }
                boolean extEvaluate = (extCondition != null) ? extCondition.evaluate() : true;
                if (!keepBlockingEDT.get() || !extEvaluate || afterExit.get()) {
                    if (timerTask != null) {
                        timerTask.cancel();
                        timerTask = null;
                    }
                    return false;
                }
                return true;
            }
        };
    }

    public WaitDispatchSupport(EventDispatchThread dispatchThread, Conditional extCondition, EventFilter filter, long interval) {
        this(dispatchThread, extCondition);
        this.filter = filter;
        if (interval < 0) {
            throw new IllegalArgumentException("The interval value must be >= 0");
        }
        this.interval = interval;
        if (interval != 0) {
            initializeTimer();
        }
    }

    @Override
    public boolean enter();

    public boolean exit();

    private static final Object getTreeLock();

    private final Runnable wakingRunnable = new Runnable() {

        public void run() {
            log.fine("Wake up EDT");
            synchronized (getTreeLock()) {
                keepBlockingCT.set(false);
                getTreeLock().notifyAll();
            }
            log.fine("Wake up EDT done");
        }
    };

    private void wakeupEDT();
}
