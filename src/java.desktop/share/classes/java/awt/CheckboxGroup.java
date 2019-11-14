package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CheckboxGroup implements java.io.Serializable {

    Checkbox selectedCheckbox = null;

    private static final long serialVersionUID = 3729780091441768983L;

    public CheckboxGroup() {
    }

    public Checkbox getSelectedCheckbox();

    @Deprecated
    public Checkbox getCurrent();

    public void setSelectedCheckbox(Checkbox box);

    @Deprecated
    public synchronized void setCurrent(Checkbox box);

    public String toString();
}
