package javax.swing;

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.beans.*;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;
import java.util.EventListener;
import java.util.Set;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectInputValidation;
import java.io.InvalidObjectException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import static javax.swing.ClientPropertyKey.*;
import javax.accessibility.*;
import sun.awt.AWTAccessor;
import sun.awt.SunToolkit;
import sun.swing.SwingAccessor;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "interning", "guieffect" })
@UIType
@JavaBean(defaultProperty = "UIClassID")
@SuppressWarnings("serial")
public abstract class JComponent extends Container implements Serializable, TransferHandler.HasGetTransferHandler {

    private static final String uiClassID = "ComponentUI";

    private static final Hashtable<ObjectInputStream, ReadObjectCallback> readObjectCallbacks = new Hashtable<ObjectInputStream, ReadObjectCallback>(1);

    private static Set<KeyStroke> managingFocusForwardTraversalKeys;

    private static Set<KeyStroke> managingFocusBackwardTraversalKeys;

    private static final int NOT_OBSCURED = 0;

    private static final int PARTIALLY_OBSCURED = 1;

    private static final int COMPLETELY_OBSCURED = 2;

    static boolean DEBUG_GRAPHICS_LOADED;

    private static final Object INPUT_VERIFIER_SOURCE_KEY = new StringBuilder("InputVerifierSourceKey");

    private boolean isAlignmentXSet;

    private float alignmentX;

    private boolean isAlignmentYSet;

    private float alignmentY;

    protected transient ComponentUI ui;

    protected EventListenerList listenerList = new EventListenerList();

    private transient ArrayTable clientProperties;

    private VetoableChangeSupport vetoableChangeSupport;

    private boolean autoscrolls;

    private Border border;

    private int flags;

    private InputVerifier inputVerifier = null;

    private boolean verifyInputWhenFocusTarget = true;

    transient Component paintingChild;

    public static final int WHEN_FOCUSED = 0;

    public static final int WHEN_ANCESTOR_OF_FOCUSED_COMPONENT = 1;

    public static final int WHEN_IN_FOCUSED_WINDOW = 2;

    public static final int UNDEFINED_CONDITION = -1;

    private static final String KEYBOARD_BINDINGS_KEY = "_KeyboardBindings";

    private static final String WHEN_IN_FOCUSED_WINDOW_BINDINGS = "_WhenInFocusedWindow";

    @Interned
    public static final String TOOL_TIP_TEXT_KEY = "ToolTipText";

    private static final String NEXT_FOCUS = "nextFocus";

    private JPopupMenu popupMenu;

    private static final int IS_DOUBLE_BUFFERED = 0;

    private static final int ANCESTOR_USING_BUFFER = 1;

    private static final int IS_PAINTING_TILE = 2;

    private static final int IS_OPAQUE = 3;

    private static final int KEY_EVENTS_ENABLED = 4;

    private static final int FOCUS_INPUTMAP_CREATED = 5;

    private static final int ANCESTOR_INPUTMAP_CREATED = 6;

    private static final int WIF_INPUTMAP_CREATED = 7;

    private static final int ACTIONMAP_CREATED = 8;

    private static final int CREATED_DOUBLE_BUFFER = 9;

    private static final int IS_PRINTING = 11;

    private static final int IS_PRINTING_ALL = 12;

    private static final int IS_REPAINTING = 13;

    private static final int WRITE_OBJ_COUNTER_FIRST = 14;

    private static final int RESERVED_1 = 15;

    private static final int RESERVED_2 = 16;

    private static final int RESERVED_3 = 17;

    private static final int RESERVED_4 = 18;

    private static final int RESERVED_5 = 19;

    private static final int RESERVED_6 = 20;

    private static final int WRITE_OBJ_COUNTER_LAST = 21;

    private static final int REQUEST_FOCUS_DISABLED = 22;

    private static final int INHERITS_POPUP_MENU = 23;

    private static final int OPAQUE_SET = 24;

    private static final int AUTOSCROLLS_SET = 25;

    private static final int FOCUS_TRAVERSAL_KEYS_FORWARD_SET = 26;

    private static final int FOCUS_TRAVERSAL_KEYS_BACKWARD_SET = 27;

    private transient AtomicBoolean revalidateRunnableScheduled = new AtomicBoolean(false);

    private static java.util.List<Rectangle> tempRectangles = new java.util.ArrayList<Rectangle>(11);

    private InputMap focusInputMap;

    private InputMap ancestorInputMap;

    private ComponentInputMap windowInputMap;

    private ActionMap actionMap;

    private static final String defaultLocale = "JComponent.defaultLocale";

    private static Component componentObtainingGraphicsFrom;

    private static Object componentObtainingGraphicsFromLock = new StringBuilder("componentObtainingGraphicsFrom");

    private transient Object aaHint;

    private transient Object lcdRenderingHint;

    static {
        SwingAccessor.setJComponentAccessor(new SwingAccessor.JComponentAccessor() {

            @Override
            public boolean getFlag(JComponent comp, int aFlag) {
                return comp.getFlag(aFlag);
            }

            @Override
            public void compWriteObjectNotify(JComponent comp) {
                comp.compWriteObjectNotify();
            }
        });
    }

    static Graphics safelyGetGraphics(Component c);

    static Graphics safelyGetGraphics(Component c, Component root);

    static void getGraphicsInvoked(Component root);

    private static boolean isComponentObtainingGraphicsFrom(Component c);

    @SuppressWarnings("deprecation")
    static Set<KeyStroke> getManagingFocusForwardTraversalKeys();

    @SuppressWarnings("deprecation")
    static Set<KeyStroke> getManagingFocusBackwardTraversalKeys();

    private static Rectangle fetchRectangle();

    private static void recycleRectangle(Rectangle rect);

    @BeanProperty(description = "Whether or not the JPopupMenu is inherited")
    public void setInheritsPopupMenu(boolean value);

    public boolean getInheritsPopupMenu();

    @BeanProperty(preferred = true, description = "Popup to show")
    public void setComponentPopupMenu(JPopupMenu popup);

    @SuppressWarnings("deprecation")
    public JPopupMenu getComponentPopupMenu();

    public JComponent() {
        super();
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        if (isManagingFocus()) {
            LookAndFeel.installProperty(this, "focusTraversalKeysForward", getManagingFocusForwardTraversalKeys());
            LookAndFeel.installProperty(this, "focusTraversalKeysBackward", getManagingFocusBackwardTraversalKeys());
        }
        super.setLocale(JComponent.getDefaultLocale());
    }

    public void updateUI();

    @Transient
    public ComponentUI getUI();

    @BeanProperty(hidden = true, visualUpdate = true, description = "The component's look and feel delegate.")
    protected void setUI(ComponentUI newUI);

    private void uninstallUIAndProperties();

    @BeanProperty(bound = false, expert = true, description = "UIClassID")
    public String getUIClassID();

    protected Graphics getComponentGraphics(Graphics g);

    protected void paintComponent(Graphics g);

    protected void paintChildren(Graphics g);

    protected void paintBorder(Graphics g);

    public void update(Graphics g);

    public void paint(Graphics g);

    void paintForceDoubleBuffered(Graphics g);

    boolean isPainting();

    private void adjustPaintFlags();

    public void printAll(Graphics g);

    public void print(Graphics g);

    protected void printComponent(Graphics g);

    protected void printChildren(Graphics g);

    protected void printBorder(Graphics g);

    @BeanProperty(bound = false)
    public boolean isPaintingTile();

    @BeanProperty(bound = false)
    public final boolean isPaintingForPrint();

    @Deprecated
    @BeanProperty(bound = false)
    public boolean isManagingFocus();

    private void registerNextFocusableComponent();

    private void registerNextFocusableComponent(Component nextFocusableComponent);

    private void deregisterNextFocusableComponent();

    @Deprecated
    public void setNextFocusableComponent(Component aComponent);

    @Deprecated
    public Component getNextFocusableComponent();

    public void setRequestFocusEnabled(boolean requestFocusEnabled);

    public boolean isRequestFocusEnabled();

    public void requestFocus();

    public boolean requestFocus(boolean temporary);

    public boolean requestFocusInWindow();

    protected boolean requestFocusInWindow(boolean temporary);

    public void grabFocus();

    @BeanProperty(description = "Whether the Component verifies input before accepting focus.")
    public void setVerifyInputWhenFocusTarget(boolean verifyInputWhenFocusTarget);

    public boolean getVerifyInputWhenFocusTarget();

    public FontMetrics getFontMetrics(Font font);

    @BeanProperty(preferred = true, description = "The preferred size of the component.")
    public void setPreferredSize(Dimension preferredSize);

    @Transient
    public Dimension getPreferredSize();

    @BeanProperty(description = "The maximum size of the component.")
    public void setMaximumSize(Dimension maximumSize);

    @Transient
    public Dimension getMaximumSize();

    @BeanProperty(description = "The minimum size of the component.")
    public void setMinimumSize(Dimension minimumSize);

    @Transient
    public Dimension getMinimumSize();

    public boolean contains(int x, int y);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The component's border.")
    public void setBorder(Border border);

    public Border getBorder();

    @BeanProperty(expert = true)
    public Insets getInsets();

    public Insets getInsets(Insets insets);

    public float getAlignmentY();

    @BeanProperty(description = "The preferred vertical alignment of the component.")
    public void setAlignmentY(float alignmentY);

    public float getAlignmentX();

    @BeanProperty(description = "The preferred horizontal alignment of the component.")
    public void setAlignmentX(float alignmentX);

    private float validateAlignment(float alignment);

    @BeanProperty(description = "The component's input verifier.")
    public void setInputVerifier(InputVerifier inputVerifier);

    public InputVerifier getInputVerifier();

    @BeanProperty(bound = false)
    public Graphics getGraphics();

    @BeanProperty(bound = false, preferred = true, enumerationValues = { "DebugGraphics.NONE_OPTION", "DebugGraphics.LOG_OPTION", "DebugGraphics.FLASH_OPTION", "DebugGraphics.BUFFERED_OPTION" }, description = "Diagnostic options for graphics operations.")
    public void setDebugGraphicsOptions(int debugOptions);

    public int getDebugGraphicsOptions();

    int shouldDebugGraphics();

    public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke, int aCondition);

    private void registerWithKeyboardManager(boolean onlyIfNew);

    private void unregisterWithKeyboardManager();

    void componentInputMapChanged(ComponentInputMap inputMap);

    private void registerWithKeyboardManager(KeyStroke aKeyStroke);

    private void unregisterWithKeyboardManager(KeyStroke aKeyStroke);

    public void registerKeyboardAction(ActionListener anAction, KeyStroke aKeyStroke, int aCondition);

    public void unregisterKeyboardAction(KeyStroke aKeyStroke);

    @BeanProperty(bound = false)
    public KeyStroke[] getRegisteredKeyStrokes();

    public int getConditionForKeyStroke(KeyStroke aKeyStroke);

    public ActionListener getActionForKeyStroke(KeyStroke aKeyStroke);

    public void resetKeyboardActions();

    public final void setInputMap(int condition, InputMap map);

    public final InputMap getInputMap(int condition);

    public final InputMap getInputMap();

    public final void setActionMap(ActionMap am);

    public final ActionMap getActionMap();

    final InputMap getInputMap(int condition, boolean create);

    final ActionMap getActionMap(boolean create);

    public int getBaseline(int width, int height);

    @BeanProperty(bound = false)
    public BaselineResizeBehavior getBaselineResizeBehavior();

    @Deprecated
    public boolean requestDefaultFocus();

    @BeanProperty(hidden = true, visualUpdate = true)
    public void setVisible(boolean aFlag);

    @BeanProperty(expert = true, preferred = true, visualUpdate = true, description = "The enabled state of the component.")
    public void setEnabled(boolean enabled);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The foreground color of the component.")
    public void setForeground(Color fg);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The background color of the component.")
    public void setBackground(Color bg);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The font for the component.")
    public void setFont(Font font);

    public static Locale getDefaultLocale();

    public static void setDefaultLocale(Locale l);

    protected void processComponentKeyEvent(KeyEvent e);

    protected void processKeyEvent(KeyEvent e);

    @SuppressWarnings("deprecation")
    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed);

    @SuppressWarnings("deprecation")
    boolean processKeyBindings(KeyEvent e, boolean pressed);

    static boolean processKeyBindingsForAllComponents(KeyEvent e, Container container, boolean pressed);

    @BeanProperty(bound = false, preferred = true, description = "The text to display in a tool tip.")
    public void setToolTipText(String text);

    public String getToolTipText();

    public String getToolTipText(MouseEvent event);

    public Point getToolTipLocation(MouseEvent event);

    public Point getPopupLocation(MouseEvent event);

    public JToolTip createToolTip();

    public void scrollRectToVisible(Rectangle aRect);

    @BeanProperty(bound = false, expert = true, description = "Determines if this component automatically scrolls its contents when dragged.")
    public void setAutoscrolls(boolean autoscrolls);

    public boolean getAutoscrolls();

    @BeanProperty(hidden = true, description = "Mechanism for transfer of data to and from the component")
    public void setTransferHandler(TransferHandler newHandler);

    public TransferHandler getTransferHandler();

    TransferHandler.DropLocation dropLocationForPoint(Point p);

    Object setDropLocation(TransferHandler.DropLocation location, Object state, boolean forDrop);

    void dndDone();

    protected void processMouseEvent(MouseEvent e);

    protected void processMouseMotionEvent(MouseEvent e);

    void superProcessMouseMotionEvent(MouseEvent e);

    void setCreatedDoubleBuffer(boolean newValue);

    boolean getCreatedDoubleBuffer();

    final class ActionStandin implements Action {

        private final ActionListener actionListener;

        private final String command;

        private final Action action;

        ActionStandin(ActionListener actionListener, String command) {
            this.actionListener = actionListener;
            if (actionListener instanceof Action) {
                this.action = (Action) actionListener;
            } else {
                this.action = null;
            }
            this.command = command;
        }

        public Object getValue(String key);

        public boolean isEnabled();

        public void actionPerformed(ActionEvent ae);

        public void putValue(String key, Object value);

        public void setEnabled(boolean b);

        public void addPropertyChangeListener(PropertyChangeListener listener);

        public void removePropertyChangeListener(PropertyChangeListener listener);
    }

    static final class IntVector {

        int[] array = null;

        int count = 0;

        int capacity = 0;

        int size();

        int elementAt(int index);

        void addElement(int value);

        void setElementAt(int value, int index);
    }

    @SuppressWarnings("serial")
    static class KeyboardState implements Serializable {

        private static final Object keyCodesKey = JComponent.KeyboardState.class;

        static IntVector getKeyCodeArray();

        static void registerKeyPressed(int keyCode);

        static void registerKeyReleased(int keyCode);

        static boolean keyIsPressed(int keyCode);

        static boolean shouldProcess(KeyEvent e);
    }

    static final sun.awt.RequestFocusController focusController = new sun.awt.RequestFocusController() {

        public boolean acceptRequestFocus(Component from, Component to, boolean temporary, boolean focusedWindowChangeAllowed, FocusEvent.Cause cause) {
            if ((to == null) || !(to instanceof JComponent)) {
                return true;
            }
            if ((from == null) || !(from instanceof JComponent)) {
                return true;
            }
            JComponent target = (JComponent) to;
            if (!target.getVerifyInputWhenFocusTarget()) {
                return true;
            }
            JComponent jFocusOwner = (JComponent) from;
            InputVerifier iv = jFocusOwner.getInputVerifier();
            if (iv == null) {
                return true;
            } else {
                Object currentSource = SwingUtilities.appContextGet(INPUT_VERIFIER_SOURCE_KEY);
                if (currentSource == jFocusOwner) {
                    return true;
                }
                SwingUtilities.appContextPut(INPUT_VERIFIER_SOURCE_KEY, jFocusOwner);
                try {
                    return iv.shouldYieldFocus(jFocusOwner, target);
                } finally {
                    if (currentSource != null) {
                        SwingUtilities.appContextPut(INPUT_VERIFIER_SOURCE_KEY, currentSource);
                    } else {
                        SwingUtilities.appContextRemove(INPUT_VERIFIER_SOURCE_KEY);
                    }
                }
            }
        }
    };

    @Deprecated
    public void enable();

    @Deprecated
    public void disable();

    @SuppressWarnings("serial")
    public abstract class AccessibleJComponent extends AccessibleAWTContainer implements AccessibleExtendedComponent {

        protected AccessibleJComponent() {
            super();
        }

        private transient volatile int propertyListenersCount = 0;

        @Deprecated
        protected FocusListener accessibleFocusHandler = null;

        protected class AccessibleContainerHandler implements ContainerListener {

            public void componentAdded(ContainerEvent e);

            public void componentRemoved(ContainerEvent e);
        }

        @Deprecated
        protected class AccessibleFocusHandler implements FocusListener {

            public void focusGained(FocusEvent event);

            public void focusLost(FocusEvent event);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener);

        public void removePropertyChangeListener(PropertyChangeListener listener);

        protected String getBorderTitle(Border b);

        public String getAccessibleName();

        public String getAccessibleDescription();

        public AccessibleRole getAccessibleRole();

        public AccessibleStateSet getAccessibleStateSet();

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        AccessibleExtendedComponent getAccessibleExtendedComponent();

        public String getToolTipText();

        public String getTitledBorderText();

        public AccessibleKeyBinding getAccessibleKeyBinding();
    }

    private ArrayTable getClientProperties();

    public final Object getClientProperty(Object key);

    public final void putClientProperty(Object key, Object value);

    void clientPropertyChanged(Object key, Object oldValue, Object newValue);

    void setUIProperty(String propertyName, Object value);

    public void setFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes);

    public static boolean isLightweightComponent(Component c);

    @Deprecated
    public void reshape(int x, int y, int w, int h);

    public Rectangle getBounds(Rectangle rv);

    public Dimension getSize(Dimension rv);

    public Point getLocation(Point rv);

    @BeanProperty(bound = false)
    public int getX();

    @BeanProperty(bound = false)
    public int getY();

    @BeanProperty(bound = false)
    public int getWidth();

    @BeanProperty(bound = false)
    public int getHeight();

    public boolean isOpaque();

    @BeanProperty(expert = true, description = "The component's opacity")
    public void setOpaque(boolean isOpaque);

    boolean rectangleIsObscured(int x, int y, int width, int height);

    @SuppressWarnings("deprecation")
    static final void computeVisibleRect(Component c, Rectangle visibleRect);

    public void computeVisibleRect(Rectangle visibleRect);

    @BeanProperty(bound = false)
    public Rectangle getVisibleRect();

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue);

    public void firePropertyChange(String propertyName, int oldValue, int newValue);

    public void firePropertyChange(String propertyName, char oldValue, char newValue);

    protected void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws java.beans.PropertyVetoException;

    public synchronized void addVetoableChangeListener(VetoableChangeListener listener);

    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener);

    @BeanProperty(bound = false)
    public synchronized VetoableChangeListener[] getVetoableChangeListeners();

    @BeanProperty(bound = false)
    @SuppressWarnings("deprecation")
    public Container getTopLevelAncestor();

    private AncestorNotifier getAncestorNotifier();

    public void addAncestorListener(AncestorListener listener);

    public void removeAncestorListener(AncestorListener listener);

    @BeanProperty(bound = false)
    public AncestorListener[] getAncestorListeners();

    @SuppressWarnings("unchecked")
    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    public void addNotify();

    public void removeNotify();

    public void repaint(long tm, int x, int y, int width, int height);

    public void repaint(Rectangle r);

    @SafeEffect
    public void revalidate();

    @Override
    public boolean isValidateRoot();

    @BeanProperty(bound = false)
    public boolean isOptimizedDrawingEnabled();

    protected boolean isPaintingOrigin();

    public void paintImmediately(int x, int y, int w, int h);

    public void paintImmediately(Rectangle r);

    boolean alwaysOnTop();

    void setPaintingChild(Component paintingChild);

    @SuppressWarnings("deprecation")
    void _paintImmediately(int x, int y, int w, int h);

    void paintToOffscreen(Graphics g, int x, int y, int w, int h, int maxX, int maxY);

    private int getObscuredState(int compIndex, int x, int y, int width, int height);

    boolean checkIfChildObscuredBySibling();

    private void setFlag(int aFlag, boolean aValue);

    private boolean getFlag(int aFlag);

    static void setWriteObjCounter(JComponent comp, byte count);

    static byte getWriteObjCounter(JComponent comp);

    public void setDoubleBuffered(boolean aFlag);

    public boolean isDoubleBuffered();

    @BeanProperty(bound = false)
    public JRootPane getRootPane();

    void compWriteObjectNotify();

    private class ReadObjectCallback implements ObjectInputValidation {

        private final Vector<JComponent> roots = new Vector<JComponent>(1);

        private final ObjectInputStream inputStream;

        ReadObjectCallback(ObjectInputStream s) throws Exception {
            inputStream = s;
            s.registerValidation(this, 0);
        }

        public void validateObject() throws InvalidObjectException;

        private void registerComponent(JComponent c);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    private void writeObject(ObjectOutputStream s) throws IOException;

    protected String paramString();

    @Override
    @Deprecated
    public void hide();
}
