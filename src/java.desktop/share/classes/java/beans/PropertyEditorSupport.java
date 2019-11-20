package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.beans.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PropertyEditorSupport implements PropertyEditor {

    public PropertyEditorSupport() {
    }

    public PropertyEditorSupport(Object source) {
    }

    public Object getSource();

    public void setSource(Object source);

    public void setValue(Object value);

    public Object getValue();

    public boolean isPaintable();

    public void paintValue(java.awt.Graphics gfx, java.awt.Rectangle box);

    public String getJavaInitializationString();

    public String getAsText();

    public void setAsText(String text) throws java.lang.IllegalArgumentException;

    public String[] getTags();

    public java.awt.Component getCustomEditor();

    public boolean supportsCustomEditor();

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener);

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener);

    public void firePropertyChange();
}
