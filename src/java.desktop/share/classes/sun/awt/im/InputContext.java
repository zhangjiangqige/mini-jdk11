package sun.awt.im;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.AWTEvent;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.im.InputMethodRequests;
import java.awt.im.spi.InputMethod;
import java.lang.Character.Subset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import sun.util.logging.PlatformLogger;
import sun.awt.SunToolkit;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class InputContext extends java.awt.im.InputContext implements ComponentListener, WindowListener {

    private static final PlatformLogger log = PlatformLogger.getLogger("sun.awt.im.InputContext");

    private InputMethodLocator inputMethodLocator;

    private InputMethod inputMethod;

    private boolean inputMethodCreationFailed;

    private HashMap<InputMethodLocator, InputMethod> usedInputMethods;

    private Component currentClientComponent;

    private Component awtFocussedComponent;

    private boolean isInputMethodActive;

    private Subset[] characterSubsets = null;

    private boolean compositionAreaHidden = false;

    private static InputContext inputMethodWindowContext;

    private static InputMethod previousInputMethod = null;

    private boolean clientWindowNotificationEnabled = false;

    private Window clientWindowListened;

    private Rectangle clientWindowLocation = null;

    private HashMap<InputMethod, Boolean> perInputMethodState;

    private static AWTKeyStroke inputMethodSelectionKey;

    private static boolean inputMethodSelectionKeyInitialized = false;

    private static final String inputMethodSelectionKeyPath = "/java/awt/im/selectionKey";

    private static final String inputMethodSelectionKeyCodeName = "keyCode";

    private static final String inputMethodSelectionKeyModifiersName = "modifiers";

    protected InputContext() {
        InputMethodManager imm = InputMethodManager.getInstance();
        synchronized (InputContext.class) {
            if (!inputMethodSelectionKeyInitialized) {
                inputMethodSelectionKeyInitialized = true;
                if (imm.hasMultipleInputMethods()) {
                    initializeInputMethodSelectionKey();
                }
            }
        }
        selectInputMethod(imm.getDefaultKeyboardLocale());
    }

    public synchronized boolean selectInputMethod(Locale locale);

    public Locale getLocale();

    public void setCharacterSubsets(Subset[] subsets);

    public synchronized void reconvert();

    @SuppressWarnings("fallthrough")
    public void dispatchEvent(AWTEvent event);

    private void focusGained(Component source);

    private void activateInputMethod(boolean updateCompositionArea);

    static Window getComponentWindow(Component component);

    private void focusLost(Component source, boolean isTemporary);

    private boolean checkInputMethodSelectionKey(KeyEvent event);

    private void deactivateInputMethod(boolean isTemporary);

    synchronized void changeInputMethod(InputMethodLocator newLocator);

    Component getClientComponent();

    public synchronized void removeNotify(Component component);

    public synchronized void dispose();

    public synchronized Object getInputMethodControlObject();

    public void setCompositionEnabled(boolean enable);

    public boolean isCompositionEnabled();

    public String getInputMethodInfo();

    public void disableNativeIM();

    private synchronized InputMethod getInputMethod();

    private InputMethod getInputMethodInstance();

    private void logCreationFailed(Throwable throwable);

    InputMethodLocator getInputMethodLocator();

    public synchronized void endComposition();

    synchronized void enableClientWindowNotification(InputMethod requester, boolean enable);

    private synchronized void notifyClientWindowChange(Window window);

    private synchronized void addClientWindowListeners();

    private synchronized void removeClientWindowListeners();

    private boolean addedClientWindowListeners();

    public void componentResized(ComponentEvent e);

    public void componentMoved(ComponentEvent e);

    public void componentShown(ComponentEvent e);

    public void componentHidden(ComponentEvent e);

    public void windowOpened(WindowEvent e);

    public void windowClosing(WindowEvent e);

    public void windowClosed(WindowEvent e);

    public void windowIconified(WindowEvent e);

    public void windowDeiconified(WindowEvent e);

    public void windowActivated(WindowEvent e);

    public void windowDeactivated(WindowEvent e);

    private void initializeInputMethodSelectionKey();

    private AWTKeyStroke getInputMethodSelectionKeyStroke(Preferences root);
}
