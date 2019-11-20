package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.ActionEvent;
import java.awt.peer.MenuComponentPeer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.AccessControlContext;
import java.security.AccessController;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import sun.awt.AWTAccessor;
import sun.awt.AppContext;
import sun.awt.ComponentFactory;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class MenuComponent implements java.io.Serializable {

    public MenuComponent() throws HeadlessException {
    }

    public String getName();

    public void setName(String name);

    public MenuContainer getParent();

    public Font getFont();

    public void setFont(Font f);

    public void removeNotify();

    @Deprecated
    public boolean postEvent(Event evt);

    public final void dispatchEvent(AWTEvent e);

    protected void processEvent(AWTEvent e);

    protected String paramString();

    public String toString();

    protected final Object getTreeLock();

    public AccessibleContext getAccessibleContext();

    protected abstract class AccessibleAWTMenuComponent extends AccessibleContext implements java.io.Serializable, AccessibleComponent, AccessibleSelection {

        protected AccessibleAWTMenuComponent() {
        }

        public AccessibleSelection getAccessibleSelection();

        public String getAccessibleName();

        public String getAccessibleDescription();

        public AccessibleRole getAccessibleRole();

        public AccessibleStateSet getAccessibleStateSet();

        public Accessible getAccessibleParent();

        public int getAccessibleIndexInParent();

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public java.util.Locale getLocale();

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

        public void addFocusListener(java.awt.event.FocusListener l);

        public void removeFocusListener(java.awt.event.FocusListener l);

        public int getAccessibleSelectionCount();

        public Accessible getAccessibleSelection(int i);

        public boolean isAccessibleChildSelected(int i);

        public void addAccessibleSelection(int i);

        public void removeAccessibleSelection(int i);

        public void clearAccessibleSelection();

        public void selectAllAccessibleSelection();
    }
}
