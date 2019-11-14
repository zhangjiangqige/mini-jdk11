package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import javax.swing.plaf.*;
import javax.accessibility.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@JavaBean(description = "A component which can be selected or deselected.")
@SwingContainer(false)
@SuppressWarnings("serial")
public class JCheckBox extends JToggleButton implements Accessible {

    @Interned
    public static final String BORDER_PAINTED_FLAT_CHANGED_PROPERTY = "borderPaintedFlat";

    private boolean flat = false;

    private static final String uiClassID = "CheckBoxUI";

    public JCheckBox() {
        this(null, null, false);
    }

    public JCheckBox(Icon icon) {
        this(null, icon, false);
    }

    public JCheckBox(Icon icon, boolean selected) {
        this(null, icon, selected);
    }

    public JCheckBox(String text) {
        this(text, null, false);
    }

    public JCheckBox(Action a) {
        this();
        setAction(a);
    }

    public JCheckBox(String text, boolean selected) {
        this(text, null, selected);
    }

    public JCheckBox(String text, Icon icon) {
        this(text, icon, false);
    }

    public JCheckBox(String text, Icon icon, boolean selected) {
        super(text, icon, selected);
        setUIProperty("borderPainted", Boolean.FALSE);
        setHorizontalAlignment(LEADING);
    }

    @BeanProperty(visualUpdate = true, description = "Whether the border is painted flat.")
    public void setBorderPaintedFlat(boolean b);

    public boolean isBorderPaintedFlat();

    public void updateUI();

    @BeanProperty(bound = false, expert = true, description = "A string that specifies the name of the L&F class")
    public String getUIClassID();

    void setIconFromAction(Action a);

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    protected String paramString();

    @BeanProperty(bound = false, expert = true, description = "The AccessibleContext associated with this CheckBox.")
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    protected class AccessibleJCheckBox extends AccessibleJToggleButton {

        public AccessibleRole getAccessibleRole();
    }
}
