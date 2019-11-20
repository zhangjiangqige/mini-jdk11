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
    public static final String BORDER_PAINTED_FLAT_CHANGED_PROPERTY;

    public JCheckBox() {
    }

    public JCheckBox(Icon icon) {
    }

    public JCheckBox(Icon icon, boolean selected) {
    }

    public JCheckBox(String text) {
    }

    public JCheckBox(Action a) {
    }

    public JCheckBox(String text, boolean selected) {
    }

    public JCheckBox(String text, Icon icon) {
    }

    public JCheckBox(String text, Icon icon, boolean selected) {
    }

    @BeanProperty(visualUpdate = true, description = "Whether the border is painted flat.")
    public void setBorderPaintedFlat(boolean b);

    public boolean isBorderPaintedFlat();

    public void updateUI();

    @BeanProperty(bound = false, expert = true, description = "A string that specifies the name of the L&F class")
    public String getUIClassID();

    protected String paramString();

    @BeanProperty(bound = false, expert = true, description = "The AccessibleContext associated with this CheckBox.")
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    protected class AccessibleJCheckBox extends AccessibleJToggleButton {

        public AccessibleRole getAccessibleRole();
    }
}
