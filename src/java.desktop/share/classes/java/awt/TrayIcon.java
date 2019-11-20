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

    public TrayIcon(Image image) {
    }

    public TrayIcon(Image image, String tooltip) {
    }

    public TrayIcon(Image image, String tooltip, PopupMenu popup) {
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
}
