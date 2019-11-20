package javax.swing.plaf.basic;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.ActionEvent;
import java.awt.KeyboardFocusManager;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.plaf.*;
import sun.swing.DefaultLookup;
import sun.swing.UIAction;

@AnnotatedFor({ "interning" })
public class BasicRootPaneUI extends RootPaneUI implements PropertyChangeListener {

    public static ComponentUI createUI(JComponent c);

    public void installUI(JComponent c);

    public void uninstallUI(JComponent c);

    protected void installDefaults(JRootPane c);

    protected void installComponents(JRootPane root);

    protected void installListeners(JRootPane root);

    protected void installKeyboardActions(JRootPane root);

    protected void uninstallDefaults(JRootPane root);

    protected void uninstallComponents(JRootPane root);

    protected void uninstallListeners(JRootPane root);

    protected void uninstallKeyboardActions(JRootPane root);

    public void propertyChange(PropertyChangeEvent e);
}
