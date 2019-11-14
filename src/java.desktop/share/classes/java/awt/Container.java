package java.awt;

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.dnd.DropTarget;
import java.awt.event.*;
import java.awt.peer.ContainerPeer;
import java.awt.peer.ComponentPeer;
import java.awt.peer.LightweightPeer;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;
import javax.accessibility.*;
import sun.util.logging.PlatformLogger;
import sun.awt.AppContext;
import sun.awt.AWTAccessor;
import sun.awt.AWTAccessor.MouseEventAccessor;
import sun.awt.PeerEvent;
import sun.awt.SunToolkit;
import sun.awt.dnd.SunDropTargetEvent;
import sun.java2d.pipe.Region;
import sun.security.action.GetBooleanAction;

@AnnotatedFor({ "guieffect" })
@UIType
public class Container extends Component {

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.Container");

    private static final PlatformLogger eventLog = PlatformLogger.getLogger("java.awt.event.Container");

    private static final Component[] EMPTY_ARRAY = new Component[0];

    private java.util.List<Component> component = new ArrayList<>();

    LayoutManager layoutMgr;

    private LightweightDispatcher dispatcher;

    private transient FocusTraversalPolicy focusTraversalPolicy;

    private boolean focusCycleRoot = false;

    private boolean focusTraversalPolicyProvider;

    private transient Set<Thread> printingThreads;

    private transient boolean printing = false;

    transient ContainerListener containerListener;

    transient int listeningChildren;

    transient int listeningBoundsChildren;

    transient int descendantsCount;

    transient Color preserveBackgroundColor = null;

    private static final long serialVersionUID = 4613797578919906343L;

    static final boolean INCLUDE_SELF = true;

    static final boolean SEARCH_HEAVYWEIGHTS = true;

    private transient int numOfHWComponents = 0;

    private transient int numOfLWComponents = 0;

    private static final PlatformLogger mixingLog = PlatformLogger.getLogger("java.awt.mixing.Container");

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("ncomponents", Integer.TYPE), new ObjectStreamField("component", Component[].class), new ObjectStreamField("layoutMgr", LayoutManager.class), new ObjectStreamField("dispatcher", LightweightDispatcher.class), new ObjectStreamField("maxSize", Dimension.class), new ObjectStreamField("focusCycleRoot", Boolean.TYPE), new ObjectStreamField("containerSerializedDataVersion", Integer.TYPE), new ObjectStreamField("focusTraversalPolicyProvider", Boolean.TYPE) };

    static {
        Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
        AWTAccessor.setContainerAccessor(new AWTAccessor.ContainerAccessor() {

            @Override
            public void validateUnconditionally(Container cont) {
                cont.validateUnconditionally();
            }

            @Override
            public Component findComponentAt(Container cont, int x, int y, boolean ignoreEnabled) {
                return cont.findComponentAt(x, y, ignoreEnabled);
            }

            @Override
            public void startLWModal(Container cont) {
                cont.startLWModal();
            }

            @Override
            public void stopLWModal(Container cont) {
                cont.stopLWModal();
            }
        });
    }

    private static native void initIDs();

    public Container() {
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    void initializeFocusTraversalKeys();

    public int getComponentCount();

    @Deprecated
    public int countComponents();

    public Component getComponent(int n);

    public Component[] getComponents();

    final Component[] getComponents_NoClientCode();

    Component[] getComponentsSync();

    public Insets getInsets();

    @Deprecated
    public Insets insets();

    public Component add(Component comp);

    public Component add(String name, Component comp);

    public Component add(Component comp, int index);

    private void checkAddToSelf(Component comp);

    private void checkNotAWindow(Component comp);

    private void checkAdding(Component comp, int index);

    private boolean removeDelicately(Component comp, Container newParent, int newIndex);

    boolean canContainFocusOwner(Component focusOwnerCandidate);

    final boolean hasHeavyweightDescendants();

    final boolean hasLightweightDescendants();

    Container getHeavyweightContainer();

    private static boolean isRemoveNotifyNeeded(Component comp, Container oldContainer, Container newContainer);

    public void setComponentZOrder(Component comp, int index);

    @SuppressWarnings("deprecation")
    private void reparentTraverse(ContainerPeer parentPeer, Container child);

    @SuppressWarnings("deprecation")
    private void reparentChild(Component comp);

    private void addDelicately(Component comp, Container curParent, int index);

    public int getComponentZOrder(Component comp);

    public void add(Component comp, Object constraints);

    public void add(Component comp, Object constraints, int index);

    protected void addImpl(Component comp, Object constraints, int index);

    @Override
    final boolean updateChildGraphicsData(GraphicsConfiguration gc);

    void checkGD(String stringID);

    public void remove(int index);

    public void remove(Component comp);

    public void removeAll();

    int numListening(long mask);

    void adjustListeningChildren(long mask, int num);

    void adjustDescendants(int num);

    void adjustDescendantsOnParent(int num);

    int countHierarchyMembers();

    private int getListenersCount(int id, boolean enabledOnToolkit);

    final int createHierarchyEvents(int id, Component changed, Container changedParent, long changeFlags, boolean enabledOnToolkit);

    final void createChildHierarchyEvents(int id, long changeFlags, boolean enabledOnToolkit);

    public LayoutManager getLayout();

    public void setLayout(LayoutManager mgr);

    public void doLayout();

    @Deprecated
    public void layout();

    public boolean isValidateRoot();

    private static final boolean isJavaAwtSmartInvalidate;

    static {
        isJavaAwtSmartInvalidate = AccessController.doPrivileged(new GetBooleanAction("java.awt.smartInvalidate"));
    }

    @Override
    void invalidateParent();

    @SafeEffect
    @Override
    public void invalidate();

    public void validate();

    private static boolean descendUnconditionallyWhenValidating = false;

    final void validateUnconditionally();

    protected void validateTree();

    void invalidateTree();

    public void setFont(Font f);

    public Dimension getPreferredSize();

    @Deprecated
    public Dimension preferredSize();

    public Dimension getMinimumSize();

    @Deprecated
    public Dimension minimumSize();

    public Dimension getMaximumSize();

    public float getAlignmentX();

    public float getAlignmentY();

    public void paint(Graphics g);

    public void update(Graphics g);

    public void print(Graphics g);

    public void paintComponents(Graphics g);

    void lightweightPaint(Graphics g);

    void paintHeavyweightComponents(Graphics g);

    public void printComponents(Graphics g);

    void lightweightPrint(Graphics g);

    void printHeavyweightComponents(Graphics g);

    public synchronized void addContainerListener(ContainerListener l);

    public synchronized void removeContainerListener(ContainerListener l);

    public synchronized ContainerListener[] getContainerListeners();

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    boolean eventEnabled(AWTEvent e);

    protected void processEvent(AWTEvent e);

    protected void processContainerEvent(ContainerEvent e);

    void dispatchEventImpl(AWTEvent e);

    void dispatchEventToSelf(AWTEvent e);

    Component getMouseEventTarget(int x, int y, boolean includeSelf);

    Component getDropTargetEventTarget(int x, int y, boolean includeSelf);

    private Component getMouseEventTarget(int x, int y, boolean includeSelf, EventTargetFilter filter, boolean searchHeavyweights);

    private Component getMouseEventTargetImpl(int x, int y, boolean includeSelf, EventTargetFilter filter, boolean searchHeavyweightChildren, boolean searchHeavyweightDescendants);

    static interface EventTargetFilter {

        boolean accept(final Component comp);
    }

    static class MouseEventTargetFilter implements EventTargetFilter {

        static final EventTargetFilter FILTER = new MouseEventTargetFilter();

        private MouseEventTargetFilter() {
        }

        public boolean accept(final Component comp);
    }

    static class DropTargetEventTargetFilter implements EventTargetFilter {

        static final EventTargetFilter FILTER = new DropTargetEventTargetFilter();

        private DropTargetEventTargetFilter() {
        }

        public boolean accept(final Component comp);
    }

    void proxyEnableEvents(long events);

    @Deprecated
    public void deliverEvent(Event e);

    public Component getComponentAt(int x, int y);

    @Deprecated
    public Component locate(int x, int y);

    public Component getComponentAt(Point p);

    public Point getMousePosition(boolean allowChildren) throws HeadlessException;

    boolean isSameOrAncestorOf(Component comp, boolean allowChildren);

    public Component findComponentAt(int x, int y);

    final Component findComponentAt(int x, int y, boolean ignoreEnabled);

    final Component findComponentAtImpl(int x, int y, boolean ignoreEnabled);

    private static Component getChildAt(Component comp, int x, int y, boolean ignoreEnabled);

    public Component findComponentAt(Point p);

    public void addNotify();

    public void removeNotify();

    public boolean isAncestorOf(Component c);

    transient Component modalComp;

    transient AppContext modalAppContext;

    private void startLWModal();

    private void stopLWModal();

    static final class WakingRunnable implements Runnable {

        public void run();
    }

    protected String paramString();

    public void list(PrintStream out, int indent);

    public void list(PrintWriter out, int indent);

    public void setFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes);

    public Set<AWTKeyStroke> getFocusTraversalKeys(int id);

    public boolean areFocusTraversalKeysSet(int id);

    public boolean isFocusCycleRoot(Container container);

    private Container findTraversalRoot();

    final boolean containsFocus();

    private boolean isParentOf(Component comp);

    void clearMostRecentFocusOwnerOnHide();

    void clearCurrentFocusCycleRootOnHide();

    final Container getTraversalRoot();

    public void setFocusTraversalPolicy(FocusTraversalPolicy policy);

    public FocusTraversalPolicy getFocusTraversalPolicy();

    public boolean isFocusTraversalPolicySet();

    public void setFocusCycleRoot(boolean focusCycleRoot);

    public boolean isFocusCycleRoot();

    public final void setFocusTraversalPolicyProvider(boolean provider);

    public final boolean isFocusTraversalPolicyProvider();

    public void transferFocusDownCycle();

    void preProcessKeyEvent(KeyEvent e);

    void postProcessKeyEvent(KeyEvent e);

    boolean postsOldMouseEvents();

    public void applyComponentOrientation(ComponentOrientation o);

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    private int containerSerializedDataVersion = 1;

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    protected class AccessibleAWTContainer extends AccessibleAWTComponent {

        private static final long serialVersionUID = 5081320404842566097L;

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public Accessible getAccessibleAt(Point p);

        private transient volatile int propertyListenersCount = 0;

        protected ContainerListener accessibleContainerHandler = null;

        protected class AccessibleContainerHandler implements ContainerListener, Serializable {

            private static final long serialVersionUID = -480855353991814677L;

            public void componentAdded(ContainerEvent e);

            public void componentRemoved(ContainerEvent e);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener);

        public void removePropertyChangeListener(PropertyChangeListener listener);
    }

    Accessible getAccessibleAt(Point p);

    int getAccessibleChildrenCount();

    Accessible getAccessibleChild(int i);

    final void increaseComponentCount(Component c);

    final void decreaseComponentCount(Component c);

    private int getTopmostComponentIndex();

    private int getBottommostComponentIndex();

    @Override
    final Region getOpaqueShape();

    final void recursiveSubtractAndApplyShape(Region shape);

    final void recursiveSubtractAndApplyShape(Region shape, int fromZorder);

    final void recursiveSubtractAndApplyShape(Region shape, int fromZorder, int toZorder);

    final void recursiveApplyCurrentShape();

    final void recursiveApplyCurrentShape(int fromZorder);

    final void recursiveApplyCurrentShape(int fromZorder, int toZorder);

    @SuppressWarnings("deprecation")
    private void recursiveShowHeavyweightChildren();

    @SuppressWarnings("deprecation")
    private void recursiveHideHeavyweightChildren();

    @SuppressWarnings("deprecation")
    private void recursiveRelocateHeavyweightChildren(Point origin);

    final boolean isRecursivelyVisibleUpToHeavyweightContainer();

    @Override
    void mixOnShowing();

    @Override
    void mixOnHiding(boolean isLightweight);

    @Override
    void mixOnReshaping();

    @Override
    void mixOnZOrderChanging(int oldZorder, int newZorder);

    @Override
    void mixOnValidating();
}

class LightweightDispatcher implements java.io.Serializable, AWTEventListener {

    private static final long serialVersionUID = 5184291520170872969L;

    private static final int LWD_MOUSE_DRAGGED_OVER = 1500;

    private static final PlatformLogger eventLog = PlatformLogger.getLogger("java.awt.event.LightweightDispatcher");

    private static final int BUTTONS_DOWN_MASK;

    static {
        int[] buttonsDownMask = AWTAccessor.getInputEventAccessor().getButtonDownMasks();
        int mask = 0;
        for (int buttonDownMask : buttonsDownMask) {
            mask |= buttonDownMask;
        }
        BUTTONS_DOWN_MASK = mask;
    }

    LightweightDispatcher(Container nativeContainer) {
        this.nativeContainer = nativeContainer;
        mouseEventTarget = new WeakReference<>(null);
        targetLastEntered = new WeakReference<>(null);
        targetLastEnteredDT = new WeakReference<>(null);
        eventMask = 0;
    }

    void dispose();

    void enableEvents(long events);

    boolean dispatchEvent(AWTEvent e);

    private boolean isMouseGrab(MouseEvent e);

    private boolean processMouseEvent(MouseEvent e);

    private boolean processDropTargetEvent(SunDropTargetEvent e);

    private void trackDropTargetEnterExit(Component targetOver, MouseEvent e);

    private void trackMouseEnterExit(Component targetOver, MouseEvent e);

    private Component retargetMouseEnterExit(Component targetOver, MouseEvent e, Component lastEntered, boolean inNativeContainer);

    private void startListeningForOtherDrags();

    private void stopListeningForOtherDrags();

    @SuppressWarnings("deprecation")
    public void eventDispatched(AWTEvent e);

    @SuppressWarnings("deprecation")
    void retargetMouseEvent(Component target, int id, MouseEvent e);

    private Container nativeContainer;

    private Component focus;

    private transient WeakReference<Component> mouseEventTarget;

    private transient WeakReference<Component> targetLastEntered;

    private transient WeakReference<Component> targetLastEnteredDT;

    private transient boolean isMouseInNativeContainer = false;

    private transient boolean isMouseDTInNativeContainer = false;

    private Cursor nativeCursor;

    private long eventMask;

    private static final long PROXY_EVENT_MASK = AWTEvent.FOCUS_EVENT_MASK | AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK;

    private static final long MOUSE_MASK = AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK;
}
