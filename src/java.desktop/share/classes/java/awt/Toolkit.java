package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.event.AWTEventListener;
import java.awt.event.AWTEventListenerProxy;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.FocusEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InvocationEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.PaintEvent;
import java.awt.event.TextEvent;
import java.awt.event.WindowEvent;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import javax.accessibility.AccessibilityProvider;
import sun.awt.AWTAccessor;
import sun.awt.AWTPermissions;
import sun.awt.AppContext;
import sun.awt.HeadlessToolkit;
import sun.awt.PeerEvent;
import sun.awt.SunToolkit;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Toolkit {

    protected void loadSystemColors(int[] systemColors) throws HeadlessException;

    public void setDynamicLayout(final boolean dynamic) throws HeadlessException;

    protected boolean isDynamicLayoutSet() throws HeadlessException;

    public boolean isDynamicLayoutActive() throws HeadlessException;

    public abstract Dimension getScreenSize() throws HeadlessException;

    public abstract int getScreenResolution() throws HeadlessException;

    public Insets getScreenInsets(GraphicsConfiguration gc) throws HeadlessException;

    public abstract ColorModel getColorModel() throws HeadlessException;

    @Deprecated
    public abstract String[] getFontList();

    @Deprecated
    public abstract FontMetrics getFontMetrics(Font font);

    public abstract void sync();

    public static synchronized Toolkit getDefaultToolkit();

    public abstract Image getImage(String filename);

    public abstract Image getImage(URL url);

    public abstract Image createImage(String filename);

    public abstract Image createImage(URL url);

    public abstract boolean prepareImage(Image image, int width, int height, ImageObserver observer);

    public abstract int checkImage(Image image, int width, int height, ImageObserver observer);

    public abstract Image createImage(ImageProducer producer);

    public Image createImage(byte[] imagedata);

    public abstract Image createImage(byte[] imagedata, int imageoffset, int imagelength);

    public abstract PrintJob getPrintJob(Frame frame, String jobtitle, Properties props);

    public PrintJob getPrintJob(Frame frame, String jobtitle, JobAttributes jobAttributes, PageAttributes pageAttributes);

    public abstract void beep();

    public abstract Clipboard getSystemClipboard() throws HeadlessException;

    public Clipboard getSystemSelection() throws HeadlessException;

    @Deprecated()
    public int getMenuShortcutKeyMask() throws HeadlessException;

    public int getMenuShortcutKeyMaskEx() throws HeadlessException;

    public boolean getLockingKeyState(int keyCode) throws UnsupportedOperationException;

    public void setLockingKeyState(int keyCode, boolean on) throws UnsupportedOperationException;

    protected static Container getNativeContainer(Component c);

    public Cursor createCustomCursor(Image cursor, Point hotSpot, String name) throws IndexOutOfBoundsException, HeadlessException;

    public Dimension getBestCursorSize(int preferredWidth, int preferredHeight) throws HeadlessException;

    public int getMaximumCursorColors() throws HeadlessException;

    public boolean isFrameStateSupported(int state) throws HeadlessException;

    public static String getProperty(String key, String defaultValue);

    public final EventQueue getSystemEventQueue();

    protected abstract EventQueue getSystemEventQueueImpl();

    public <T extends DragGestureRecognizer> T createDragGestureRecognizer(Class<T> abstractRecognizerClass, DragSource ds, Component c, int srcActions, DragGestureListener dgl);

    public final synchronized Object getDesktopProperty(String propertyName);

    protected final void setDesktopProperty(String name, Object newValue);

    protected Object lazilyLoadDesktopProperty(String name);

    protected void initializeDesktopProperties();

    public void addPropertyChangeListener(String name, PropertyChangeListener pcl);

    public void removePropertyChangeListener(String name, PropertyChangeListener pcl);

    public PropertyChangeListener[] getPropertyChangeListeners();

    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName);

    protected final Map<String, Object> desktopProperties;

    protected final PropertyChangeSupport desktopPropsSupport;

    public boolean isAlwaysOnTopSupported();

    public abstract boolean isModalityTypeSupported(Dialog.ModalityType modalityType);

    public abstract boolean isModalExclusionTypeSupported(Dialog.ModalExclusionType modalExclusionType);

    public void addAWTEventListener(AWTEventListener listener, long eventMask);

    public void removeAWTEventListener(AWTEventListener listener);

    public AWTEventListener[] getAWTEventListeners();

    public AWTEventListener[] getAWTEventListeners(long eventMask);

    public abstract Map<java.awt.font.TextAttribute, ?> mapInputMethodHighlight(InputMethodHighlight highlight) throws HeadlessException;

    public boolean areExtraMouseButtonsEnabled() throws HeadlessException;
}
