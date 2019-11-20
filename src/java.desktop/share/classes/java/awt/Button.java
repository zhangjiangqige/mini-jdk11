package java.awt;

import org.checkerframework.checker.i18n.qual.Localized;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.peer.ButtonPeer;
import java.beans.BeanProperty;
import java.util.EventListener;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import javax.accessibility.*;

@AnnotatedFor({ "i18n" })
public class Button extends Component implements Accessible {

    public Button() throws HeadlessException {
    }

    public Button(String label) throws HeadlessException {
    }

    public void addNotify();

    @Localized
    public String getLabel();

    public void setLabel(@Localized String label);

    public void setActionCommand(String command);

    public String getActionCommand();

    public synchronized void addActionListener(ActionListener l);

    public synchronized void removeActionListener(ActionListener l);

    public synchronized ActionListener[] getActionListeners();

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    protected void processEvent(AWTEvent e);

    protected void processActionEvent(ActionEvent e);

    protected String paramString();

    @BeanProperty(expert = true, description = "The AccessibleContext associated with this Button.")
    public AccessibleContext getAccessibleContext();

    protected class AccessibleAWTButton extends AccessibleAWTComponent implements AccessibleAction, AccessibleValue {

        public String getAccessibleName();

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
    }
}
