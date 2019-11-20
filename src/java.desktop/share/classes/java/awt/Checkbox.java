package java.awt;

import org.checkerframework.checker.i18n.qual.Localized;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.peer.CheckboxPeer;
import java.awt.event.*;
import java.util.EventListener;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import javax.accessibility.*;

@AnnotatedFor({ "i18n" })
public class Checkbox extends Component implements ItemSelectable, Accessible {

    public Checkbox() throws HeadlessException {
    }

    public Checkbox(String label) throws HeadlessException {
    }

    public Checkbox(String label, boolean state) throws HeadlessException {
    }

    public Checkbox(String label, boolean state, CheckboxGroup group) throws HeadlessException {
    }

    public Checkbox(String label, CheckboxGroup group, boolean state) throws HeadlessException {
    }

    public void addNotify();

    @Localized
    public String getLabel();

    public void setLabel(@Localized String label);

    public boolean getState();

    public void setState(boolean state);

    public Object[] getSelectedObjects();

    public CheckboxGroup getCheckboxGroup();

    public void setCheckboxGroup(CheckboxGroup g);

    public synchronized void addItemListener(ItemListener l);

    public synchronized void removeItemListener(ItemListener l);

    public synchronized ItemListener[] getItemListeners();

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    protected void processEvent(AWTEvent e);

    protected void processItemEvent(ItemEvent e);

    protected String paramString();

    public AccessibleContext getAccessibleContext();

    protected class AccessibleAWTCheckbox extends AccessibleAWTComponent implements ItemListener, AccessibleAction, AccessibleValue {

        public AccessibleAWTCheckbox() {
        }

        public void itemStateChanged(ItemEvent e);

        public AccessibleAction getAccessibleAction();

        public AccessibleValue getAccessibleValue();

        public int getAccessibleActionCount();

        public String getAccessibleActionDescription(int i);

        public boolean doAccessibleAction(int i);

        public Number getCurrentAccessibleValue();

        public boolean setCurrentAccessibleValue(Number n);

        public Number getMinimumAccessibleValue();

        public Number getMaximumAccessibleValue();

        public AccessibleRole getAccessibleRole();

        public AccessibleStateSet getAccessibleStateSet();
    }
}
