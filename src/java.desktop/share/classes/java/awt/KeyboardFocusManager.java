package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.peer.KeyboardFocusManagerPeer;
import java.awt.peer.LightweightPeer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import sun.util.logging.PlatformLogger;
import sun.awt.AppContext;
import sun.awt.SunToolkit;
import sun.awt.KeyboardFocusManagerPeerProvider;
import sun.awt.AWTAccessor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class KeyboardFocusManager implements KeyEventDispatcher, KeyEventPostProcessor {

    private static final PlatformLogger focusLog = PlatformLogger.getLogger("java.awt.focus.KeyboardFocusManager");

    static {
        Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
        AWTAccessor.setKeyboardFocusManagerAccessor(new AWTAccessor.KeyboardFocusManagerAccessor() {

            public int shouldNativelyFocusHeavyweight(Component heavyweight, Component descendant, boolean temporary, boolean focusedWindowChangeAllowed, long time, FocusEvent.Cause cause) {
                return KeyboardFocusManager.shouldNativelyFocusHeavyweight(heavyweight, descendant, temporary, focusedWindowChangeAllowed, time, cause);
            }

            public boolean processSynchronousLightweightTransfer(Component heavyweight, Component descendant, boolean temporary, boolean focusedWindowChangeAllowed, long time) {
                return KeyboardFocusManager.processSynchronousLightweightTransfer(heavyweight, descendant, temporary, focusedWindowChangeAllowed, time);
            }

            public void removeLastFocusRequest(Component heavyweight) {
                KeyboardFocusManager.removeLastFocusRequest(heavyweight);
            }

            @Override
            public Component getMostRecentFocusOwner(Window window) {
                return KeyboardFocusManager.getMostRecentFocusOwner(window);
            }

            public void setMostRecentFocusOwner(Window window, Component component) {
                KeyboardFocusManager.setMostRecentFocusOwner(window, component);
            }

            public KeyboardFocusManager getCurrentKeyboardFocusManager(AppContext ctx) {
                return KeyboardFocusManager.getCurrentKeyboardFocusManager(ctx);
            }

            public Container getCurrentFocusCycleRoot() {
                return KeyboardFocusManager.currentFocusCycleRoot;
            }
        });
    }

    transient KeyboardFocusManagerPeer peer;

    private static native void initIDs();

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.KeyboardFocusManager");

    public static final int FORWARD_TRAVERSAL_KEYS = 0;

    public static final int BACKWARD_TRAVERSAL_KEYS = 1;

    public static final int UP_CYCLE_TRAVERSAL_KEYS = 2;

    public static final int DOWN_CYCLE_TRAVERSAL_KEYS = 3;

    static final int TRAVERSAL_KEY_LENGTH = DOWN_CYCLE_TRAVERSAL_KEYS + 1;

    public static KeyboardFocusManager getCurrentKeyboardFocusManager();

    static synchronized KeyboardFocusManager getCurrentKeyboardFocusManager(AppContext appcontext);

    public static void setCurrentKeyboardFocusManager(KeyboardFocusManager newManager) throws SecurityException;

    private static Component focusOwner;

    private static Component permanentFocusOwner;

    private static Window focusedWindow;

    private static Window activeWindow;

    private FocusTraversalPolicy defaultPolicy = new DefaultFocusTraversalPolicy();

    private static final String[] defaultFocusTraversalKeyPropertyNames = { "forwardDefaultFocusTraversalKeys", "backwardDefaultFocusTraversalKeys", "upCycleDefaultFocusTraversalKeys", "downCycleDefaultFocusTraversalKeys" };

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Set<AWTKeyStroke>[] defaultFocusTraversalKeys = new Set[4];

    private static Container currentFocusCycleRoot;

    private VetoableChangeSupport vetoableSupport;

    private PropertyChangeSupport changeSupport;

    private java.util.LinkedList<KeyEventDispatcher> keyEventDispatchers;

    private java.util.LinkedList<KeyEventPostProcessor> keyEventPostProcessors;

    private static java.util.Map<Window, WeakReference<Component>> mostRecentFocusOwners = new WeakHashMap<>();

    private static AWTPermission replaceKeyboardFocusManagerPermission;

    transient SequencedEvent currentSequencedEvent = null;

    final void setCurrentSequencedEvent(SequencedEvent current);

    final SequencedEvent getCurrentSequencedEvent();

    static Set<AWTKeyStroke> initFocusTraversalKeysSet(String value, Set<AWTKeyStroke> targetSet);

    public KeyboardFocusManager() {
        @SuppressWarnings("deprecation")
        AWTKeyStroke[][] defaultFocusTraversalKeyStrokes = { { AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, 0, false), AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_DOWN_MASK | InputEvent.CTRL_MASK, false) }, { AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK, false), AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK | InputEvent.CTRL_DOWN_MASK | InputEvent.CTRL_MASK, false) }, {}, {} };
        for (int i = 0; i < TRAVERSAL_KEY_LENGTH; i++) {
            Set<AWTKeyStroke> work_set = new HashSet<>();
            for (int j = 0; j < defaultFocusTraversalKeyStrokes[i].length; j++) {
                work_set.add(defaultFocusTraversalKeyStrokes[i][j]);
            }
            defaultFocusTraversalKeys[i] = (work_set.isEmpty()) ? Collections.emptySet() : Collections.unmodifiableSet(work_set);
        }
        initPeer();
    }

    private void initPeer();

    public Component getFocusOwner();

    protected Component getGlobalFocusOwner() throws SecurityException;

    protected void setGlobalFocusOwner(Component focusOwner) throws SecurityException;

    public void clearFocusOwner();

    public void clearGlobalFocusOwner() throws SecurityException;

    private void _clearGlobalFocusOwner();

    void clearGlobalFocusOwnerPriv();

    Component getNativeFocusOwner();

    void setNativeFocusOwner(Component comp);

    Window getNativeFocusedWindow();

    public Component getPermanentFocusOwner();

    protected Component getGlobalPermanentFocusOwner() throws SecurityException;

    protected void setGlobalPermanentFocusOwner(Component permanentFocusOwner) throws SecurityException;

    public Window getFocusedWindow();

    protected Window getGlobalFocusedWindow() throws SecurityException;

    protected void setGlobalFocusedWindow(Window focusedWindow) throws SecurityException;

    public Window getActiveWindow();

    protected Window getGlobalActiveWindow() throws SecurityException;

    protected void setGlobalActiveWindow(Window activeWindow) throws SecurityException;

    public synchronized FocusTraversalPolicy getDefaultFocusTraversalPolicy();

    public void setDefaultFocusTraversalPolicy(FocusTraversalPolicy defaultPolicy);

    public void setDefaultFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes);

    public Set<AWTKeyStroke> getDefaultFocusTraversalKeys(int id);

    public Container getCurrentFocusCycleRoot();

    protected Container getGlobalCurrentFocusCycleRoot() throws SecurityException;

    public void setGlobalCurrentFocusCycleRoot(Container newFocusCycleRoot) throws SecurityException;

    void setGlobalCurrentFocusCycleRootPriv(final Container newFocusCycleRoot);

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public synchronized PropertyChangeListener[] getPropertyChangeListeners();

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public synchronized PropertyChangeListener[] getPropertyChangeListeners(String propertyName);

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue);

    public void addVetoableChangeListener(VetoableChangeListener listener);

    public void removeVetoableChangeListener(VetoableChangeListener listener);

    public synchronized VetoableChangeListener[] getVetoableChangeListeners();

    public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener);

    public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener);

    public synchronized VetoableChangeListener[] getVetoableChangeListeners(String propertyName);

    protected void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws PropertyVetoException;

    public void addKeyEventDispatcher(KeyEventDispatcher dispatcher);

    public void removeKeyEventDispatcher(KeyEventDispatcher dispatcher);

    @SuppressWarnings("unchecked")
    protected synchronized java.util.List<KeyEventDispatcher> getKeyEventDispatchers();

    public void addKeyEventPostProcessor(KeyEventPostProcessor processor);

    public void removeKeyEventPostProcessor(KeyEventPostProcessor processor);

    @SuppressWarnings("unchecked")
    protected java.util.List<KeyEventPostProcessor> getKeyEventPostProcessors();

    static void setMostRecentFocusOwner(Component component);

    static synchronized void setMostRecentFocusOwner(Window window, Component component);

    static void clearMostRecentFocusOwner(Component comp);

    static synchronized Component getMostRecentFocusOwner(Window window);

    public abstract boolean dispatchEvent(AWTEvent e);

    public final void redispatchEvent(Component target, AWTEvent e);

    public abstract boolean dispatchKeyEvent(KeyEvent e);

    public abstract boolean postProcessKeyEvent(KeyEvent e);

    public abstract void processKeyEvent(Component focusedComponent, KeyEvent e);

    protected abstract void enqueueKeyEvents(long after, Component untilFocused);

    protected abstract void dequeueKeyEvents(long after, Component untilFocused);

    protected abstract void discardKeyEvents(Component comp);

    public abstract void focusNextComponent(Component aComponent);

    public abstract void focusPreviousComponent(Component aComponent);

    public abstract void upFocusCycle(Component aComponent);

    public abstract void downFocusCycle(Container aContainer);

    public final void focusNextComponent();

    public final void focusPreviousComponent();

    public final void upFocusCycle();

    public final void downFocusCycle();

    void dumpRequests();

    private static final class LightweightFocusRequest {

        final Component component;

        final boolean temporary;

        final FocusEvent.Cause cause;

        LightweightFocusRequest(Component component, boolean temporary, FocusEvent.Cause cause) {
            this.component = component;
            this.temporary = temporary;
            this.cause = cause;
        }

        public String toString();
    }

    private static final class HeavyweightFocusRequest {

        final Component heavyweight;

        final LinkedList<LightweightFocusRequest> lightweightRequests;

        static final HeavyweightFocusRequest CLEAR_GLOBAL_FOCUS_OWNER = new HeavyweightFocusRequest();

        private HeavyweightFocusRequest() {
            heavyweight = null;
            lightweightRequests = null;
        }

        HeavyweightFocusRequest(Component heavyweight, Component descendant, boolean temporary, FocusEvent.Cause cause) {
            if (log.isLoggable(PlatformLogger.Level.FINE)) {
                if (heavyweight == null) {
                    log.fine("Assertion (heavyweight != null) failed");
                }
            }
            this.heavyweight = heavyweight;
            this.lightweightRequests = new LinkedList<LightweightFocusRequest>();
            addLightweightRequest(descendant, temporary, cause);
        }

        boolean addLightweightRequest(Component descendant, boolean temporary, FocusEvent.Cause cause);

        LightweightFocusRequest getFirstLightweightRequest();

        public String toString();
    }

    private static LinkedList<HeavyweightFocusRequest> heavyweightRequests = new LinkedList<HeavyweightFocusRequest>();

    private static LinkedList<LightweightFocusRequest> currentLightweightRequests;

    private static boolean clearingCurrentLightweightRequests;

    private static boolean allowSyncFocusRequests = true;

    private static Component newFocusOwner = null;

    private static volatile boolean disableRestoreFocus;

    static final int SNFH_FAILURE = 0;

    static final int SNFH_SUCCESS_HANDLED = 1;

    static final int SNFH_SUCCESS_PROCEED = 2;

    static boolean processSynchronousLightweightTransfer(Component heavyweight, Component descendant, boolean temporary, boolean focusedWindowChangeAllowed, long time);

    static int shouldNativelyFocusHeavyweight(Component heavyweight, Component descendant, boolean temporary, boolean focusedWindowChangeAllowed, long time, FocusEvent.Cause cause);

    static Window markClearGlobalFocusOwner();

    Component getCurrentWaitingRequest(Component parent);

    static boolean isAutoFocusTransferEnabled();

    static boolean isAutoFocusTransferEnabledFor(Component comp);

    private static Throwable dispatchAndCatchException(Throwable ex, Component comp, FocusEvent event);

    private static void handleException(Throwable ex);

    static void processCurrentLightweightRequests();

    static FocusEvent retargetUnexpectedFocusEvent(FocusEvent fe);

    static FocusEvent retargetFocusGained(FocusEvent fe);

    static FocusEvent retargetFocusLost(FocusEvent fe);

    static AWTEvent retargetFocusEvent(AWTEvent event);

    void clearMarkers();

    static boolean removeFirstRequest();

    static void removeLastFocusRequest(Component heavyweight);

    private static boolean focusedWindowChanged(Component to, Component from);

    private static boolean isTemporary(Component to, Component from);

    static Component getHeavyweight(Component comp);

    private static boolean isProxyActiveImpl(KeyEvent e);

    static boolean isProxyActive(KeyEvent e);

    private static HeavyweightFocusRequest getLastHWRequest();

    private static HeavyweightFocusRequest getFirstHWRequest();

    private static void checkReplaceKFMPermission() throws SecurityException;

    private void checkKFMSecurity() throws SecurityException;
}
