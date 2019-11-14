package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.ActionListener;
import java.awt.peer.SystemTrayPeer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Vector;
import sun.awt.AWTAccessor;
import sun.awt.AWTPermissions;
import sun.awt.AppContext;
import sun.awt.HeadlessToolkit;
import sun.awt.SunToolkit;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class SystemTray {

    private static SystemTray systemTray;

    private int currentIconID = 0;

    private transient SystemTrayPeer peer;

    private static final TrayIcon[] EMPTY_TRAY_ARRAY = new TrayIcon[0];

    static {
        AWTAccessor.setSystemTrayAccessor(new AWTAccessor.SystemTrayAccessor() {

            public void firePropertyChange(SystemTray tray, String propertyName, Object oldValue, Object newValue) {
                tray.firePropertyChange(propertyName, oldValue, newValue);
            }
        });
    }

    private SystemTray() {
        addNotify();
    }

    public static SystemTray getSystemTray();

    public static boolean isSupported();

    public void add(TrayIcon trayIcon) throws AWTException;

    public void remove(TrayIcon trayIcon);

    public TrayIcon[] getTrayIcons();

    public Dimension getTrayIconSize();

    public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public synchronized PropertyChangeListener[] getPropertyChangeListeners(String propertyName);

    private void firePropertyChange(String propertyName, Object oldValue, Object newValue);

    private synchronized PropertyChangeSupport getCurrentChangeSupport();

    synchronized void addNotify();

    static void checkSystemTrayAllowed();

    private static void initializeSystemTrayIfNeeded();
}
