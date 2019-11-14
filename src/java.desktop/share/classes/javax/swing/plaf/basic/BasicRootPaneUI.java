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

    private static RootPaneUI rootPaneUI = new BasicRootPaneUI();

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

    InputMap getInputMap(int condition, JComponent c);

    ComponentInputMap createInputMap(int condition, JComponent c);

    static void loadActionMap(LazyActionMap map);

    void updateDefaultButtonBindings(JRootPane root);

    public void propertyChange(PropertyChangeEvent e);

    static class Actions extends UIAction {

        @Interned
        public static final String PRESS = "press";

        @Interned
        public static final String RELEASE = "release";

        @Interned
        public static final String POST_POPUP = "postPopup";

        Actions(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent evt);

        @Override
        public boolean accept(Object sender);
    }

    @SuppressWarnings("serial")
    private static class RootPaneInputMap extends ComponentInputMapUIResource {

        public RootPaneInputMap(JComponent c) {
            super(c);
        }
    }
}
