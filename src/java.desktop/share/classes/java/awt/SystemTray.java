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

    public static SystemTray getSystemTray();

    public static boolean isSupported();

    public void add(TrayIcon trayIcon) throws AWTException;

    public void remove(TrayIcon trayIcon);

    public TrayIcon[] getTrayIcons();

    public Dimension getTrayIconSize();

    public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

    public synchronized PropertyChangeListener[] getPropertyChangeListeners(String propertyName);
}
