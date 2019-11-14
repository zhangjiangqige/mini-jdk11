package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.*;
import java.awt.peer.TrayIconPeer;
import sun.awt.AppContext;
import sun.awt.SunToolkit;
import sun.awt.AWTAccessor;
import sun.awt.HeadlessToolkit;
import java.util.EventObject;
import java.security.AccessControlContext;
import java.security.AccessController;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class TrayIcon {

    private Image image;

    private String tooltip;

    private PopupMenu popup;

    private boolean autosize;

    private int id;

    private String actionCommand;

    private transient TrayIconPeer peer;

    transient MouseListener mouseListener;

    transient MouseMotionListener mouseMotionListener;

    transient ActionListener actionListener;

    private final AccessControlContext acc = AccessController.getContext();

    final AccessControlContext getAccessControlContext();

    static {
        Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
        AWTAccessor.setTrayIconAccessor(new AWTAccessor.TrayIconAccessor() {

            public void addNotify(TrayIcon trayIcon) throws AWTException {
                trayIcon.addNotify();
            }

            public void removeNotify(TrayIcon trayIcon) {
                trayIcon.removeNotify();
            }
        });
    }

    private TrayIcon() throws UnsupportedOperationException, HeadlessException, SecurityException {
        SystemTray.checkSystemTrayAllowed();
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException();
        }
        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException();
        }
        SunToolkit.insertTargetMapping(this, AppContext.getAppContext());
    }

    public TrayIcon(Image image) {
        this();
        if (image == null) {
            throw new IllegalArgumentException("creating TrayIcon with null Image");
        }
        setImage(image);
    }

    public TrayIcon(Image image, String tooltip) {
        this(image);
        setToolTip(tooltip);
    }

    public TrayIcon(Image image, String tooltip, PopupMenu popup) {
        this(image, tooltip);
        setPopupMenu(popup);
    }

    public void setImage(Image image);

    public Image getImage();

    public void setPopupMenu(PopupMenu popup);

    public PopupMenu getPopupMenu();

    public void setToolTip(String tooltip);

    public String getToolTip();

    public void setImageAutoSize(boolean autosize);

    public boolean isImageAutoSize();

    public synchronized void addMouseListener(MouseListener listener);

    public synchronized void removeMouseListener(MouseListener listener);

    public synchronized MouseListener[] getMouseListeners();

    public synchronized void addMouseMotionListener(MouseMotionListener listener);

    public synchronized void removeMouseMotionListener(MouseMotionListener listener);

    public synchronized MouseMotionListener[] getMouseMotionListeners();

    public String getActionCommand();

    public void setActionCommand(String command);

    public synchronized void addActionListener(ActionListener listener);

    public synchronized void removeActionListener(ActionListener listener);

    public synchronized ActionListener[] getActionListeners();

    public enum MessageType {

        ERROR, WARNING, INFO, NONE
    }

    public void displayMessage(String caption, String text, MessageType messageType);

    public Dimension getSize();

    void addNotify() throws AWTException;

    void removeNotify();

    void setID(int id);

    int getID();

    void dispatchEvent(AWTEvent e);

    void processEvent(AWTEvent e);

    void processMouseEvent(MouseEvent e);

    void processMouseMotionEvent(MouseEvent e);

    void processActionEvent(ActionEvent e);

    private static native void initIDs();
}
