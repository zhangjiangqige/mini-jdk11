package java.awt;

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.guieffect.qual.UI;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.*;
import java.awt.peer.ComponentPeer;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.EmptyStackException;
import sun.awt.*;
import sun.awt.dnd.SunDropTargetEvent;
import sun.util.logging.PlatformLogger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.AtomicInteger;
import java.security.AccessControlContext;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.JavaSecurityAccess;

@UIType
@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class EventQueue {

    private static final AtomicInteger threadInitNumber = new AtomicInteger(0);

    private static final int LOW_PRIORITY = 0;

    private static final int NORM_PRIORITY = 1;

    private static final int HIGH_PRIORITY = 2;

    private static final int ULTIMATE_PRIORITY = 3;

    private static final int NUM_PRIORITIES = ULTIMATE_PRIORITY + 1;

    private Queue[] queues = new Queue[NUM_PRIORITIES];

    private EventQueue nextQueue;

    private EventQueue previousQueue;

    private final Lock pushPopLock;

    private final Condition pushPopCond;

    private static final Runnable dummyRunnable = new Runnable() {

        public void run() {
        }
    };

    private EventDispatchThread dispatchThread;

    private final ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private long mostRecentEventTime = System.currentTimeMillis();

    private long mostRecentKeyEventTime = System.currentTimeMillis();

    private WeakReference<AWTEvent> currentEvent;

    private volatile int waitForID;

    private final AppContext appContext;

    private final String name = "AWT-EventQueue-" + threadInitNumber.getAndIncrement();

    private FwDispatcher fwDispatcher;

    private static volatile PlatformLogger eventLog;

    private static final PlatformLogger getEventLog();

    private static boolean fxAppThreadIsDispatchThread;

    static {
        AWTAccessor.setEventQueueAccessor(new AWTAccessor.EventQueueAccessor() {

            public Thread getDispatchThread(EventQueue eventQueue) {
                return eventQueue.getDispatchThread();
            }

            public boolean isDispatchThreadImpl(EventQueue eventQueue) {
                return eventQueue.isDispatchThreadImpl();
            }

            public void removeSourceEvents(EventQueue eventQueue, Object source, boolean removeAllEvents) {
                eventQueue.removeSourceEvents(source, removeAllEvents);
            }

            public boolean noEvents(EventQueue eventQueue) {
                return eventQueue.noEvents();
            }

            public void wakeup(EventQueue eventQueue, boolean isShutdown) {
                eventQueue.wakeup(isShutdown);
            }

            public void invokeAndWait(Object source, Runnable r) throws InterruptedException, InvocationTargetException {
                EventQueue.invokeAndWait(source, r);
            }

            public void setFwDispatcher(EventQueue eventQueue, FwDispatcher dispatcher) {
                eventQueue.setFwDispatcher(dispatcher);
            }

            @Override
            public long getMostRecentEventTime(EventQueue eventQueue) {
                return eventQueue.getMostRecentEventTimeImpl();
            }
        });
        AccessController.doPrivileged(new PrivilegedAction<Object>() {

            public Object run() {
                fxAppThreadIsDispatchThread = "true".equals(System.getProperty("javafx.embed.singleThread"));
                return null;
            }
        });
    }

    public EventQueue() {
        for (int i = 0; i < NUM_PRIORITIES; i++) {
            queues[i] = new Queue();
        }
        appContext = AppContext.getAppContext();
        pushPopLock = (Lock) appContext.get(AppContext.EVENT_QUEUE_LOCK_KEY);
        pushPopCond = (Condition) appContext.get(AppContext.EVENT_QUEUE_COND_KEY);
    }

    public void postEvent(AWTEvent theEvent);

    private void postEventPrivate(AWTEvent theEvent);

    private static int getPriority(AWTEvent theEvent);

    private void postEvent(AWTEvent theEvent, int priority);

    private boolean coalescePaintEvent(PaintEvent e);

    private PaintEvent mergePaintEvents(PaintEvent a, PaintEvent b);

    private boolean coalesceMouseEvent(MouseEvent e);

    private boolean coalescePeerEvent(PeerEvent e);

    private boolean coalesceOtherEvent(AWTEvent e, int priority);

    private boolean coalesceEvent(AWTEvent e, int priority);

    private void cacheEQItem(EventQueueItem entry);

    private void uncacheEQItem(EventQueueItem entry);

    private static final int PAINT = 0;

    private static final int UPDATE = 1;

    private static final int MOVE = 2;

    private static final int DRAG = 3;

    private static final int PEER = 4;

    private static final int CACHE_LENGTH = 5;

    private static int eventToCacheIndex(AWTEvent e);

    private boolean noEvents();

    public AWTEvent getNextEvent() throws InterruptedException;

    AWTEvent getNextEventPrivate() throws InterruptedException;

    AWTEvent getNextEvent(int id) throws InterruptedException;

    public AWTEvent peekEvent();

    public AWTEvent peekEvent(int id);

    private static final JavaSecurityAccess javaSecurityAccess = SharedSecrets.getJavaSecurityAccess();

    protected void dispatchEvent(final AWTEvent event);

    private static AccessControlContext getAccessControlContextFrom(Object src);

    private void dispatchEventImpl(final AWTEvent event, final Object src);

    public static long getMostRecentEventTime();

    private long getMostRecentEventTimeImpl();

    long getMostRecentEventTimeEx();

    public static AWTEvent getCurrentEvent();

    private AWTEvent getCurrentEventImpl();

    public void push(EventQueue newEventQueue);

    protected void pop() throws EmptyStackException;

    public SecondaryLoop createSecondaryLoop();

    private class FwSecondaryLoopWrapper implements SecondaryLoop {

        final private SecondaryLoop loop;

        final private EventFilter filter;

        public FwSecondaryLoopWrapper(SecondaryLoop loop, EventFilter filter) {
            this.loop = loop;
            this.filter = filter;
        }

        @Override
        public boolean enter();

        @Override
        public boolean exit();
    }

    SecondaryLoop createSecondaryLoop(Conditional cond, EventFilter filter, long interval);

    public static boolean isDispatchThread();

    final boolean isDispatchThreadImpl();

    final void initDispatchThread();

    final void detachDispatchThread(EventDispatchThread edt);

    final EventDispatchThread getDispatchThread();

    final void removeSourceEvents(Object source, boolean removeAllEvents);

    synchronized long getMostRecentKeyEventTime();

    static void setCurrentEventAndMostRecentTime(AWTEvent e);

    private void setCurrentEventAndMostRecentTimeImpl(AWTEvent e);

    @SafeEffect
    public static void invokeLater(@UI Runnable runnable);

    public static void invokeAndWait(Runnable runnable) throws InterruptedException, InvocationTargetException;

    static void invokeAndWait(Object source, Runnable runnable) throws InterruptedException, InvocationTargetException;

    private void wakeup(boolean isShutdown);

    private void setFwDispatcher(FwDispatcher dispatcher);
}

class Queue {

    EventQueueItem head;

    EventQueueItem tail;
}
