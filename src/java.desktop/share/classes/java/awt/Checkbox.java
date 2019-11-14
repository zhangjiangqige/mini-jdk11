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

    static {
        Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
    }

    String label;

    boolean state;

    CheckboxGroup group;

    transient ItemListener itemListener;

    private static final String base = "checkbox";

    private static int nameCounter = 0;

    private static final long serialVersionUID = 7270714317450821763L;

    void setStateInternal(boolean state);

    public Checkbox() throws HeadlessException {
        this("", false, null);
    }

    public Checkbox(String label) throws HeadlessException {
        this(label, false, null);
    }

    public Checkbox(String label, boolean state) throws HeadlessException {
        this(label, state, null);
    }

    public Checkbox(String label, boolean state, CheckboxGroup group) throws HeadlessException {
        GraphicsEnvironment.checkHeadless();
        this.label = label;
        this.state = state;
        this.group = group;
        if (state && (group != null)) {
            group.setSelectedCheckbox(this);
        }
    }

    public Checkbox(String label, CheckboxGroup group, boolean state) throws HeadlessException {
        this(label, state, group);
    }

    String constructComponentName();

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

    boolean eventEnabled(AWTEvent e);

    protected void processEvent(AWTEvent e);

    protected void processItemEvent(ItemEvent e);

    protected String paramString();

    private int checkboxSerializedDataVersion = 1;

    private void writeObject(ObjectOutputStream s) throws java.io.IOException;

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException, HeadlessException;

    private static native void initIDs();

    public AccessibleContext getAccessibleContext();

    protected class AccessibleAWTCheckbox extends AccessibleAWTComponent implements ItemListener, AccessibleAction, AccessibleValue {

        private static final long serialVersionUID = 7881579233144754107L;

        public AccessibleAWTCheckbox() {
            super();
            Checkbox.this.addItemListener(this);
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
