package java.awt;

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.applet.Applet;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.PaintEvent;
import java.awt.event.TextEvent;
import java.awt.im.InputContext;
import java.awt.im.InputMethodRequests;
import java.awt.image.BufferStrategy;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ComponentPeer;
import java.awt.peer.ContainerPeer;
import java.awt.peer.LightweightPeer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.Transient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import sun.awt.AWTAccessor;
import sun.awt.AppContext;
import sun.awt.ComponentFactory;
import sun.awt.ConstrainableGraphics;
import sun.awt.EmbeddedFrame;
import sun.awt.RequestFocusController;
import sun.awt.SubRegionShowable;
import sun.awt.SunToolkit;
import sun.awt.dnd.SunDropTargetEvent;
import sun.awt.im.CompositionArea;
import sun.awt.image.VSyncedBSManager;
import sun.font.FontManager;
import sun.font.FontManagerFactory;
import sun.font.SunFontManager;
import sun.java2d.SunGraphics2D;
import sun.java2d.SunGraphicsEnvironment;
import sun.java2d.pipe.Region;
import sun.java2d.pipe.hw.ExtendedBufferCapabilities;
import sun.security.action.GetPropertyAction;
import sun.swing.SwingAccessor;
import sun.util.logging.PlatformLogger;
import static sun.java2d.pipe.hw.ExtendedBufferCapabilities.VSyncType.VSYNC_DEFAULT;
import static sun.java2d.pipe.hw.ExtendedBufferCapabilities.VSyncType.VSYNC_ON;

@AnnotatedFor({ "guieffect", "interning" })
@UsesObjectEquals
@UIType
public abstract class Component implements ImageObserver, MenuContainer, Serializable {

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.Component");

    private static final PlatformLogger eventLog = PlatformLogger.getLogger("java.awt.event.Component");

    private static final PlatformLogger focusLog = PlatformLogger.getLogger("java.awt.focus.Component");

    private static final PlatformLogger mixingLog = PlatformLogger.getLogger("java.awt.mixing.Component");

    transient volatile ComponentPeer peer;

    transient Container parent;

    transient AppContext appContext;

    int x;

    int y;

    int width;

    int height;

    Color foreground;

    Color background;

    volatile Font font;

    Font peerFont;

    Cursor cursor;

    Locale locale;

    private transient volatile GraphicsConfiguration graphicsConfig;

    transient BufferStrategy bufferStrategy = null;

    boolean ignoreRepaint = false;

    boolean visible = true;

    boolean enabled = true;

    private volatile boolean valid = false;

    DropTarget dropTarget;

    Vector<PopupMenu> popups;

    private String name;

    private boolean nameExplicitlySet = false;

    private boolean focusable = true;

    private static final int FOCUS_TRAVERSABLE_UNKNOWN = 0;

    private static final int FOCUS_TRAVERSABLE_DEFAULT = 1;

    private static final int FOCUS_TRAVERSABLE_SET = 2;

    private int isFocusTraversableOverridden = FOCUS_TRAVERSABLE_UNKNOWN;

    Set<AWTKeyStroke>[] focusTraversalKeys;

    private static final String[] focusTraversalKeyPropertyNames = { "forwardFocusTraversalKeys", "backwardFocusTraversalKeys", "upCycleFocusTraversalKeys", "downCycleFocusTraversalKeys" };

    private boolean focusTraversalKeysEnabled = true;

    static final Object LOCK = new AWTTreeLock();

    static class AWTTreeLock {
    }

    private transient volatile AccessControlContext acc = AccessController.getContext();

    Dimension minSize;

    boolean minSizeSet;

    Dimension prefSize;

    boolean prefSizeSet;

    Dimension maxSize;

    boolean maxSizeSet;

    transient ComponentOrientation componentOrientation = ComponentOrientation.UNKNOWN;

    boolean newEventsOnly = false;

    transient ComponentListener componentListener;

    transient FocusListener focusListener;

    transient HierarchyListener hierarchyListener;

    transient HierarchyBoundsListener hierarchyBoundsListener;

    transient KeyListener keyListener;

    transient MouseListener mouseListener;

    transient MouseMotionListener mouseMotionListener;

    transient MouseWheelListener mouseWheelListener;

    transient InputMethodListener inputMethodListener;

    static final String actionListenerK = "actionL";

    static final String adjustmentListenerK = "adjustmentL";

    static final String componentListenerK = "componentL";

    static final String containerListenerK = "containerL";

    static final String focusListenerK = "focusL";

    static final String itemListenerK = "itemL";

    static final String keyListenerK = "keyL";

    static final String mouseListenerK = "mouseL";

    static final String mouseMotionListenerK = "mouseMotionL";

    static final String mouseWheelListenerK = "mouseWheelL";

    static final String textListenerK = "textL";

    static final String ownedWindowK = "ownedL";

    static final String windowListenerK = "windowL";

    static final String inputMethodListenerK = "inputMethodL";

    static final String hierarchyListenerK = "hierarchyL";

    static final String hierarchyBoundsListenerK = "hierarchyBoundsL";

    static final String windowStateListenerK = "windowStateL";

    static final String windowFocusListenerK = "windowFocusL";

    long eventMask = AWTEvent.INPUT_METHODS_ENABLED_MASK;

    static boolean isInc;

    static int incRate;

    static {
        Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
        String s = java.security.AccessController.doPrivileged(new GetPropertyAction("awt.image.incrementaldraw"));
        isInc = (s == null || s.equals("true"));
        s = java.security.AccessController.doPrivileged(new GetPropertyAction("awt.image.redrawrate"));
        incRate = (s != null) ? Integer.parseInt(s) : 100;
    }

    public static final float TOP_ALIGNMENT = 0.0f;

    public static final float CENTER_ALIGNMENT = 0.5f;

    public static final float BOTTOM_ALIGNMENT = 1.0f;

    public static final float LEFT_ALIGNMENT = 0.0f;

    public static final float RIGHT_ALIGNMENT = 1.0f;

    private static final long serialVersionUID = -7644114512714619750L;

    private PropertyChangeSupport changeSupport;

    private transient Object objectLock = new Object();

    Object getObjectLock();

    final AccessControlContext getAccessControlContext();

    boolean isPacked = false;

    private int boundsOp = ComponentPeer.DEFAULT_OPERATION;

    public enum BaselineResizeBehavior {

        CONSTANT_ASCENT, CONSTANT_DESCENT, CENTER_OFFSET, OTHER
    }

    private transient Region compoundShape = null;

    private transient Region mixingCutoutRegion = null;

    private transient boolean isAddNotifyComplete = false;

    int getBoundsOp();

    void setBoundsOp(int op);

    transient boolean backgroundEraseDisabled;

    static {
        AWTAccessor.setComponentAccessor(new AWTAccessor.ComponentAccessor() {

            public void setBackgroundEraseDisabled(Component comp, boolean disabled) {
                comp.backgroundEraseDisabled = disabled;
            }

            public boolean getBackgroundEraseDisabled(Component comp) {
                return comp.backgroundEraseDisabled;
            }

            public Rectangle getBounds(Component comp) {
                return new Rectangle(comp.x, comp.y, comp.width, comp.height);
            }

            public void setGraphicsConfiguration(Component comp, GraphicsConfiguration gc) {
                comp.setGraphicsConfiguration(gc);
            }

            public void requestFocus(Component comp, FocusEvent.Cause cause) {
                comp.requestFocus(cause);
            }

            public boolean canBeFocusOwner(Component comp) {
                return comp.canBeFocusOwner();
            }

            public boolean isVisible(Component comp) {
                return comp.isVisible_NoClientCode();
            }

            public void setRequestFocusController(RequestFocusController requestController) {
                Component.setRequestFocusController(requestController);
            }

            public AppContext getAppContext(Component comp) {
                return comp.appContext;
            }

            public void setAppContext(Component comp, AppContext appContext) {
                comp.appContext = appContext;
            }

            public Container getParent(Component comp) {
                return comp.getParent_NoClientCode();
            }

            public void setParent(Component comp, Container parent) {
                comp.parent = parent;
            }

            public void setSize(Component comp, int width, int height) {
                comp.width = width;
                comp.height = height;
            }

            public Point getLocation(Component comp) {
                return comp.location_NoClientCode();
            }

            public void setLocation(Component comp, int x, int y) {
                comp.x = x;
                comp.y = y;
            }

            public boolean isEnabled(Component comp) {
                return comp.isEnabledImpl();
            }

            public boolean isDisplayable(Component comp) {
                return comp.peer != null;
            }

            public Cursor getCursor(Component comp) {
                return comp.getCursor_NoClientCode();
            }

            @SuppressWarnings("unchecked")
            public <T extends ComponentPeer> T getPeer(Component comp) {
                return (T) comp.peer;
            }

            public void setPeer(Component comp, ComponentPeer peer) {
                comp.peer = peer;
            }

            public boolean isLightweight(Component comp) {
                return (comp.peer instanceof LightweightPeer);
            }

            public boolean getIgnoreRepaint(Component comp) {
                return comp.ignoreRepaint;
            }

            public int getWidth(Component comp) {
                return comp.width;
            }

            public int getHeight(Component comp) {
                return comp.height;
            }

            public int getX(Component comp) {
                return comp.x;
            }

            public int getY(Component comp) {
                return comp.y;
            }

            public Color getForeground(Component comp) {
                return comp.foreground;
            }

            public Color getBackground(Component comp) {
                return comp.background;
            }

            public void setBackground(Component comp, Color background) {
                comp.background = background;
            }

            public Font getFont(Component comp) {
                return comp.getFont_NoClientCode();
            }

            public void processEvent(Component comp, AWTEvent e) {
                comp.processEvent(e);
            }

            public AccessControlContext getAccessControlContext(Component comp) {
                return comp.getAccessControlContext();
            }

            public void revalidateSynchronously(Component comp) {
                comp.revalidateSynchronously();
            }

            @Override
            public void createBufferStrategy(Component comp, int numBuffers, BufferCapabilities caps) throws AWTException {
                comp.createBufferStrategy(numBuffers, caps);
            }

            @Override
            public BufferStrategy getBufferStrategy(Component comp) {
                return comp.getBufferStrategy();
            }
        });
    }

    protected Component() {
        appContext = AppContext.getAppContext();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    void initializeFocusTraversalKeys();

    String constructComponentName();

    public String getName();

    public void setName(String name);

    public Container getParent();

    final Container getParent_NoClientCode();

    Container getContainer();

    public synchronized void setDropTarget(DropTarget dt);

    public synchronized DropTarget getDropTarget();

    public GraphicsConfiguration getGraphicsConfiguration();

    final GraphicsConfiguration getGraphicsConfiguration_NoClientCode();

    void setGraphicsConfiguration(GraphicsConfiguration gc);

    final boolean updateGraphicsData(GraphicsConfiguration gc);

    private boolean updateSelfGraphicsData(GraphicsConfiguration gc);

    boolean updateChildGraphicsData(GraphicsConfiguration gc);

    void checkGD(String stringID);

    public final Object getTreeLock();

    final void checkTreeLock();

    public Toolkit getToolkit();

    final Toolkit getToolkitImpl();

    final ComponentFactory getComponentFactory();

    public boolean isValid();

    public boolean isDisplayable();

    @Transient
    public boolean isVisible();

    final boolean isVisible_NoClientCode();

    boolean isRecursivelyVisible();

    private Rectangle getRecursivelyVisibleBounds();

    Point pointRelativeToComponent(Point absolute);

    Component findUnderMouseInWindow(PointerInfo pi);

    public Point getMousePosition() throws HeadlessException;

    boolean isSameOrAncestorOf(Component comp, boolean allowChildren);

    public boolean isShowing();

    public boolean isEnabled();

    final boolean isEnabledImpl();

    public void setEnabled(boolean b);

    @Deprecated
    public void enable();

    @Deprecated
    public void enable(boolean b);

    @Deprecated
    public void disable();

    public boolean isDoubleBuffered();

    public void enableInputMethods(boolean enable);

    public void setVisible(boolean b);

    @Deprecated
    public void show();

    @Deprecated
    public void show(boolean b);

    boolean containsFocus();

    void clearMostRecentFocusOwnerOnHide();

    void clearCurrentFocusCycleRootOnHide();

    @Deprecated
    public void hide();

    @Transient
    public Color getForeground();

    public void setForeground(Color c);

    public boolean isForegroundSet();

    @Transient
    public Color getBackground();

    public void setBackground(Color c);

    public boolean isBackgroundSet();

    @Transient
    public Font getFont();

    final Font getFont_NoClientCode();

    public void setFont(Font f);

    public boolean isFontSet();

    public Locale getLocale();

    public void setLocale(Locale l);

    public ColorModel getColorModel();

    public Point getLocation();

    public Point getLocationOnScreen();

    final Point getLocationOnScreen_NoTreeLock();

    @Deprecated
    public Point location();

    private Point location_NoClientCode();

    public void setLocation(int x, int y);

    @Deprecated
    public void move(int x, int y);

    public void setLocation(Point p);

    public Dimension getSize();

    @Deprecated
    public Dimension size();

    public void setSize(int width, int height);

    @Deprecated
    public void resize(int width, int height);

    public void setSize(Dimension d);

    @Deprecated
    public void resize(Dimension d);

    public Rectangle getBounds();

    @Deprecated
    public Rectangle bounds();

    public void setBounds(int x, int y, int width, int height);

    @Deprecated
    public void reshape(int x, int y, int width, int height);

    private void repaintParentIfNeeded(int oldX, int oldY, int oldWidth, int oldHeight);

    private void reshapeNativePeer(int x, int y, int width, int height, int op);

    @SuppressWarnings("deprecation")
    private void notifyNewBounds(boolean resized, boolean moved);

    public void setBounds(Rectangle r);

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public Rectangle getBounds(Rectangle rv);

    public Dimension getSize(Dimension rv);

    public Point getLocation(Point rv);

    public boolean isOpaque();

    public boolean isLightweight();

    public void setPreferredSize(Dimension preferredSize);

    public boolean isPreferredSizeSet();

    public Dimension getPreferredSize();

    @Deprecated
    public Dimension preferredSize();

    public void setMinimumSize(Dimension minimumSize);

    public boolean isMinimumSizeSet();

    public Dimension getMinimumSize();

    @Deprecated
    public Dimension minimumSize();

    public void setMaximumSize(Dimension maximumSize);

    public boolean isMaximumSizeSet();

    public Dimension getMaximumSize();

    public float getAlignmentX();

    public float getAlignmentY();

    public int getBaseline(int width, int height);

    public BaselineResizeBehavior getBaselineResizeBehavior();

    public void doLayout();

    @Deprecated
    public void layout();

    public void validate();

    public void invalidate();

    void invalidateParent();

    final void invalidateIfValid();

    public void revalidate();

    final void revalidateSynchronously();

    public Graphics getGraphics();

    final Graphics getGraphics_NoClientCode();

    public FontMetrics getFontMetrics(Font font);

    public void setCursor(Cursor cursor);

    final void updateCursorImmediately();

    public Cursor getCursor();

    final Cursor getCursor_NoClientCode();

    public boolean isCursorSet();

    public void paint(Graphics g);

    public void update(Graphics g);

    public void paintAll(Graphics g);

    void lightweightPaint(Graphics g);

    void paintHeavyweightComponents(Graphics g);

    @SafeEffect
    public void repaint();

    @SafeEffect
    public void repaint(long tm);

    @SafeEffect
    public void repaint(int x, int y, int width, int height);

    @SafeEffect
    public void repaint(long tm, int x, int y, int width, int height);

    public void print(Graphics g);

    public void printAll(Graphics g);

    void lightweightPrint(Graphics g);

    void printHeavyweightComponents(Graphics g);

    private Insets getInsets_NoClientCode();

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h);

    public Image createImage(ImageProducer producer);

    public Image createImage(int width, int height);

    public VolatileImage createVolatileImage(int width, int height);

    public VolatileImage createVolatileImage(int width, int height, ImageCapabilities caps) throws AWTException;

    public boolean prepareImage(Image image, ImageObserver observer);

    public boolean prepareImage(Image image, int width, int height, ImageObserver observer);

    public int checkImage(Image image, ImageObserver observer);

    public int checkImage(Image image, int width, int height, ImageObserver observer);

    void createBufferStrategy(int numBuffers);

    void createBufferStrategy(int numBuffers, BufferCapabilities caps) throws AWTException;

    private class ProxyCapabilities extends ExtendedBufferCapabilities {

        private BufferCapabilities orig;

        private ProxyCapabilities(BufferCapabilities orig) {
            super(orig.getFrontBufferCapabilities(), orig.getBackBufferCapabilities(), orig.getFlipContents() == BufferCapabilities.FlipContents.BACKGROUND ? BufferCapabilities.FlipContents.BACKGROUND : BufferCapabilities.FlipContents.COPIED);
            this.orig = orig;
        }
    }

    BufferStrategy getBufferStrategy();

    Image getBackBuffer();

    protected class FlipBufferStrategy extends BufferStrategy {

        protected int numBuffers;

        protected BufferCapabilities caps;

        protected Image drawBuffer;

        protected VolatileImage drawVBuffer;

        protected boolean validatedContents;

        int width;

        int height;

        @SuppressWarnings("deprecation")
        protected FlipBufferStrategy(int numBuffers, BufferCapabilities caps) throws AWTException {
            if (!(Component.this instanceof Window) && !(Component.this instanceof Canvas) && !(Component.this instanceof Applet)) {
                throw new ClassCastException("Component must be a Canvas or Window or Applet");
            }
            this.numBuffers = numBuffers;
            this.caps = caps;
            createBuffers(numBuffers, caps);
        }

        protected void createBuffers(int numBuffers, BufferCapabilities caps) throws AWTException;

        private void updateInternalBuffers();

        protected Image getBackBuffer();

        protected void flip(BufferCapabilities.FlipContents flipAction);

        void flipSubRegion(int x1, int y1, int x2, int y2, BufferCapabilities.FlipContents flipAction);

        protected void destroyBuffers();

        public BufferCapabilities getCapabilities();

        public Graphics getDrawGraphics();

        protected void revalidate();

        void revalidate(boolean checkSize);

        public boolean contentsLost();

        public boolean contentsRestored();

        public void show();

        void showSubRegion(int x1, int y1, int x2, int y2);

        public void dispose();
    }

    protected class BltBufferStrategy extends BufferStrategy {

        protected BufferCapabilities caps;

        protected VolatileImage[] backBuffers;

        protected boolean validatedContents;

        protected int width;

        protected int height;

        private Insets insets;

        protected BltBufferStrategy(int numBuffers, BufferCapabilities caps) {
            this.caps = caps;
            createBackBuffers(numBuffers - 1);
        }

        public void dispose();

        protected void createBackBuffers(int numBuffers);

        public BufferCapabilities getCapabilities();

        public Graphics getDrawGraphics();

        Image getBackBuffer();

        public void show();

        void showSubRegion(int x1, int y1, int x2, int y2);

        protected void revalidate();

        void revalidate(boolean checkSize);

        public boolean contentsLost();

        public boolean contentsRestored();
    }

    private class FlipSubRegionBufferStrategy extends FlipBufferStrategy implements SubRegionShowable {

        protected FlipSubRegionBufferStrategy(int numBuffers, BufferCapabilities caps) throws AWTException {
            super(numBuffers, caps);
        }

        public void show(int x1, int y1, int x2, int y2);

        public boolean showIfNotLost(int x1, int y1, int x2, int y2);
    }

    private class BltSubRegionBufferStrategy extends BltBufferStrategy implements SubRegionShowable {

        protected BltSubRegionBufferStrategy(int numBuffers, BufferCapabilities caps) {
            super(numBuffers, caps);
        }

        public void show(int x1, int y1, int x2, int y2);

        public boolean showIfNotLost(int x1, int y1, int x2, int y2);
    }

    private class SingleBufferStrategy extends BufferStrategy {

        private BufferCapabilities caps;

        public SingleBufferStrategy(BufferCapabilities caps) {
            this.caps = caps;
        }

        public BufferCapabilities getCapabilities();

        public Graphics getDrawGraphics();

        public boolean contentsLost();

        public boolean contentsRestored();

        public void show();
    }

    public void setIgnoreRepaint(boolean ignoreRepaint);

    public boolean getIgnoreRepaint();

    public boolean contains(int x, int y);

    @Deprecated
    public boolean inside(int x, int y);

    public boolean contains(Point p);

    public Component getComponentAt(int x, int y);

    @Deprecated
    public Component locate(int x, int y);

    public Component getComponentAt(Point p);

    @Deprecated
    public void deliverEvent(Event e);

    public final void dispatchEvent(AWTEvent e);

    @SuppressWarnings("deprecation")
    void dispatchEventImpl(AWTEvent e);

    void autoProcessMouseWheel(MouseWheelEvent e);

    @SuppressWarnings("deprecation")
    boolean dispatchMouseWheelToAncestor(MouseWheelEvent e);

    boolean areInputMethodsEnabled();

    boolean eventEnabled(AWTEvent e);

    boolean eventTypeEnabled(int type);

    @Deprecated
    public boolean postEvent(Event e);

    public synchronized void addComponentListener(ComponentListener l);

    public synchronized void removeComponentListener(ComponentListener l);

    public synchronized ComponentListener[] getComponentListeners();

    public synchronized void addFocusListener(FocusListener l);

    public synchronized void removeFocusListener(FocusListener l);

    public synchronized FocusListener[] getFocusListeners();

    public void addHierarchyListener(HierarchyListener l);

    public void removeHierarchyListener(HierarchyListener l);

    public synchronized HierarchyListener[] getHierarchyListeners();

    public void addHierarchyBoundsListener(HierarchyBoundsListener l);

    public void removeHierarchyBoundsListener(HierarchyBoundsListener l);

    int numListening(long mask);

    int countHierarchyMembers();

    int createHierarchyEvents(int id, Component changed, Container changedParent, long changeFlags, boolean enabledOnToolkit);

    public synchronized HierarchyBoundsListener[] getHierarchyBoundsListeners();

    void adjustListeningChildrenOnParent(long mask, int num);

    public synchronized void addKeyListener(KeyListener l);

    public synchronized void removeKeyListener(KeyListener l);

    public synchronized KeyListener[] getKeyListeners();

    public synchronized void addMouseListener(MouseListener l);

    public synchronized void removeMouseListener(MouseListener l);

    public synchronized MouseListener[] getMouseListeners();

    public synchronized void addMouseMotionListener(MouseMotionListener l);

    public synchronized void removeMouseMotionListener(MouseMotionListener l);

    public synchronized MouseMotionListener[] getMouseMotionListeners();

    public synchronized void addMouseWheelListener(MouseWheelListener l);

    public synchronized void removeMouseWheelListener(MouseWheelListener l);

    public synchronized MouseWheelListener[] getMouseWheelListeners();

    public synchronized void addInputMethodListener(InputMethodListener l);

    public synchronized void removeInputMethodListener(InputMethodListener l);

    public synchronized InputMethodListener[] getInputMethodListeners();

    @SuppressWarnings("unchecked")
    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    public InputMethodRequests getInputMethodRequests();

    public InputContext getInputContext();

    protected final void enableEvents(long eventsToEnable);

    protected final void disableEvents(long eventsToDisable);

    transient sun.awt.EventQueueItem[] eventCache;

    private transient boolean coalescingEnabled = checkCoalescing();

    private static final Map<Class<?>, Boolean> coalesceMap = new java.util.WeakHashMap<Class<?>, Boolean>();

    private boolean checkCoalescing();

    private static final Class<?>[] coalesceEventsParams = { AWTEvent.class, AWTEvent.class };

    private static boolean isCoalesceEventsOverriden(Class<?> clazz);

    final boolean isCoalescingEnabled();

    protected AWTEvent coalesceEvents(AWTEvent existingEvent, AWTEvent newEvent);

    protected void processEvent(AWTEvent e);

    protected void processComponentEvent(ComponentEvent e);

    protected void processFocusEvent(FocusEvent e);

    protected void processKeyEvent(KeyEvent e);

    protected void processMouseEvent(MouseEvent e);

    protected void processMouseMotionEvent(MouseEvent e);

    protected void processMouseWheelEvent(MouseWheelEvent e);

    boolean postsOldMouseEvents();

    protected void processInputMethodEvent(InputMethodEvent e);

    protected void processHierarchyEvent(HierarchyEvent e);

    protected void processHierarchyBoundsEvent(HierarchyEvent e);

    @Deprecated
    public boolean handleEvent(Event evt);

    @Deprecated
    public boolean mouseDown(Event evt, int x, int y);

    @Deprecated
    public boolean mouseDrag(Event evt, int x, int y);

    @Deprecated
    public boolean mouseUp(Event evt, int x, int y);

    @Deprecated
    public boolean mouseMove(Event evt, int x, int y);

    @Deprecated
    public boolean mouseEnter(Event evt, int x, int y);

    @Deprecated
    public boolean mouseExit(Event evt, int x, int y);

    @Deprecated
    public boolean keyDown(Event evt, int key);

    @Deprecated
    public boolean keyUp(Event evt, int key);

    @Deprecated
    public boolean action(Event evt, Object what);

    public void addNotify();

    public void removeNotify();

    @Deprecated
    public boolean gotFocus(Event evt, Object what);

    @Deprecated
    public boolean lostFocus(Event evt, Object what);

    @Deprecated
    public boolean isFocusTraversable();

    public boolean isFocusable();

    public void setFocusable(boolean focusable);

    final boolean isFocusTraversableOverridden();

    public void setFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes);

    public Set<AWTKeyStroke> getFocusTraversalKeys(int id);

    final void setFocusTraversalKeys_NoIDCheck(int id, Set<? extends AWTKeyStroke> keystrokes);

    final Set<AWTKeyStroke> getFocusTraversalKeys_NoIDCheck(int id);

    public boolean areFocusTraversalKeysSet(int id);

    public void setFocusTraversalKeysEnabled(boolean focusTraversalKeysEnabled);

    public boolean getFocusTraversalKeysEnabled();

    public void requestFocus();

    public void requestFocus(FocusEvent.Cause cause);

    protected boolean requestFocus(boolean temporary);

    protected boolean requestFocus(boolean temporary, FocusEvent.Cause cause);

    public boolean requestFocusInWindow();

    public boolean requestFocusInWindow(FocusEvent.Cause cause);

    protected boolean requestFocusInWindow(boolean temporary);

    boolean requestFocusInWindow(boolean temporary, FocusEvent.Cause cause);

    final boolean requestFocusHelper(boolean temporary, boolean focusedWindowChangeAllowed);

    final boolean requestFocusHelper(boolean temporary, boolean focusedWindowChangeAllowed, FocusEvent.Cause cause);

    private boolean isRequestFocusAccepted(boolean temporary, boolean focusedWindowChangeAllowed, FocusEvent.Cause cause);

    private static RequestFocusController requestFocusController = new DummyRequestFocusController();

    private static class DummyRequestFocusController implements RequestFocusController {

        public boolean acceptRequestFocus(Component from, Component to, boolean temporary, boolean focusedWindowChangeAllowed, FocusEvent.Cause cause);
    }

    static synchronized void setRequestFocusController(RequestFocusController requestController);

    public Container getFocusCycleRootAncestor();

    public boolean isFocusCycleRoot(Container container);

    Container getTraversalRoot();

    public void transferFocus();

    @Deprecated
    public void nextFocus();

    boolean transferFocus(boolean clearOnFailure);

    @SuppressWarnings("deprecation")
    final Component getNextFocusCandidate();

    public void transferFocusBackward();

    boolean transferFocusBackward(boolean clearOnFailure);

    public void transferFocusUpCycle();

    public boolean hasFocus();

    public boolean isFocusOwner();

    private boolean autoFocusTransferOnDisposal = true;

    void setAutoFocusTransferOnDisposal(boolean value);

    boolean isAutoFocusTransferOnDisposal();

    public void add(PopupMenu popup);

    @SuppressWarnings("unchecked")
    public void remove(MenuComponent popup);

    protected String paramString();

    public String toString();

    public void list();

    public void list(PrintStream out);

    public void list(PrintStream out, int indent);

    public void list(PrintWriter out);

    public void list(PrintWriter out, int indent);

    final Container getNativeContainer();

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public PropertyChangeListener[] getPropertyChangeListeners();

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName);

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue);

    protected void firePropertyChange(String propertyName, boolean oldValue, boolean newValue);

    protected void firePropertyChange(String propertyName, int oldValue, int newValue);

    public void firePropertyChange(String propertyName, byte oldValue, byte newValue);

    public void firePropertyChange(String propertyName, char oldValue, char newValue);

    public void firePropertyChange(String propertyName, short oldValue, short newValue);

    public void firePropertyChange(String propertyName, long oldValue, long newValue);

    public void firePropertyChange(String propertyName, float oldValue, float newValue);

    public void firePropertyChange(String propertyName, double oldValue, double newValue);

    private int componentSerializedDataVersion = 4;

    private void doSwingSerialization();

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    public void setComponentOrientation(ComponentOrientation o);

    public ComponentOrientation getComponentOrientation();

    public void applyComponentOrientation(ComponentOrientation orientation);

    final boolean canBeFocusOwner();

    final boolean canBeFocusOwnerRecursively();

    final void relocateComponent();

    Window getContainingWindow();

    private static native void initIDs();

    protected AccessibleContext accessibleContext = null;

    public AccessibleContext getAccessibleContext();

    protected abstract class AccessibleAWTComponent extends AccessibleContext implements Serializable, AccessibleComponent {

        private static final long serialVersionUID = 642321655757800191L;

        protected AccessibleAWTComponent() {
        }

        private transient volatile int propertyListenersCount = 0;

        protected ComponentListener accessibleAWTComponentHandler = null;

        protected FocusListener accessibleAWTFocusHandler = null;

        protected class AccessibleAWTComponentHandler implements ComponentListener, Serializable {

            private static final long serialVersionUID = -1009684107426231869L;

            public void componentHidden(ComponentEvent e);

            public void componentShown(ComponentEvent e);

            public void componentMoved(ComponentEvent e);

            public void componentResized(ComponentEvent e);
        }

        protected class AccessibleAWTFocusHandler implements FocusListener, Serializable {

            private static final long serialVersionUID = 3150908257351582233L;

            public void focusGained(FocusEvent event);

            public void focusLost(FocusEvent event);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener);

        public void removePropertyChangeListener(PropertyChangeListener listener);

        public String getAccessibleName();

        public String getAccessibleDescription();

        public AccessibleRole getAccessibleRole();

        public AccessibleStateSet getAccessibleStateSet();

        public Accessible getAccessibleParent();

        public int getAccessibleIndexInParent();

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public Locale getLocale();

        public AccessibleComponent getAccessibleComponent();

        public Color getBackground();

        public void setBackground(Color c);

        public Color getForeground();

        public void setForeground(Color c);

        public Cursor getCursor();

        public void setCursor(Cursor cursor);

        public Font getFont();

        public void setFont(Font f);

        public FontMetrics getFontMetrics(Font f);

        public boolean isEnabled();

        public void setEnabled(boolean b);

        public boolean isVisible();

        public void setVisible(boolean b);

        public boolean isShowing();

        public boolean contains(Point p);

        public Point getLocationOnScreen();

        public Point getLocation();

        public void setLocation(Point p);

        public Rectangle getBounds();

        public void setBounds(Rectangle r);

        public Dimension getSize();

        public void setSize(Dimension d);

        public Accessible getAccessibleAt(Point p);

        public boolean isFocusTraversable();

        public void requestFocus();

        public void addFocusListener(FocusListener l);

        public void removeFocusListener(FocusListener l);
    }

    int getAccessibleIndexInParent();

    AccessibleStateSet getAccessibleStateSet();

    static boolean isInstanceOf(Object obj, String className);

    final boolean areBoundsValid();

    void applyCompoundShape(Region shape);

    private Region getAppliedShape();

    Point getLocationOnWindow();

    final Region getNormalShape();

    Region getOpaqueShape();

    final int getSiblingIndexAbove();

    final ComponentPeer getHWPeerAboveMe();

    final int getSiblingIndexBelow();

    final boolean isNonOpaqueForMixing();

    private Region calculateCurrentShape();

    void applyCurrentShape();

    final void subtractAndApplyShape(Region s);

    private void applyCurrentShapeBelowMe();

    final void subtractAndApplyShapeBelowMe();

    void mixOnShowing();

    void mixOnHiding(boolean isLightweight);

    void mixOnReshaping();

    void mixOnZOrderChanging(int oldZorder, int newZorder);

    void mixOnValidating();

    final boolean isMixingNeeded();

    public void setMixingCutoutShape(Shape shape);

    void updateZOrder();
}
