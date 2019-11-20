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

    public EventQueue() {
    }

    public void postEvent(AWTEvent theEvent);

    public AWTEvent getNextEvent() throws InterruptedException;

    public AWTEvent peekEvent();

    public AWTEvent peekEvent(int id);

    protected void dispatchEvent(final AWTEvent event);

    public static long getMostRecentEventTime();

    public static AWTEvent getCurrentEvent();

    public void push(EventQueue newEventQueue);

    protected void pop() throws EmptyStackException;

    public SecondaryLoop createSecondaryLoop();

    public static boolean isDispatchThread();

    @SafeEffect
    public static void invokeLater(@UI Runnable runnable);

    public static void invokeAndWait(Runnable runnable) throws InterruptedException, InvocationTargetException;
}
