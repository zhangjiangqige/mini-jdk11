package javax.accessibility;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.IllegalComponentStateException;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Locale;
import sun.awt.AWTAccessor;
import sun.awt.AppContext;

@AnnotatedFor({ "interning" })
@JavaBean(description = "Minimal information that all accessible objects return")
public abstract class AccessibleContext {

    @Interned
    public static final String ACCESSIBLE_NAME_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_DESCRIPTION_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_STATE_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_VALUE_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_SELECTION_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_CARET_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_VISIBLE_DATA_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_CHILD_PROPERTY;

    @Interned
    public static final String ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY;

    public static final String ACCESSIBLE_TABLE_CAPTION_CHANGED;

    public static final String ACCESSIBLE_TABLE_SUMMARY_CHANGED;

    public static final String ACCESSIBLE_TABLE_MODEL_CHANGED;

    public static final String ACCESSIBLE_TABLE_ROW_HEADER_CHANGED;

    public static final String ACCESSIBLE_TABLE_ROW_DESCRIPTION_CHANGED;

    public static final String ACCESSIBLE_TABLE_COLUMN_HEADER_CHANGED;

    public static final String ACCESSIBLE_TABLE_COLUMN_DESCRIPTION_CHANGED;

    public static final String ACCESSIBLE_ACTION_PROPERTY;

    public static final String ACCESSIBLE_HYPERTEXT_OFFSET;

    public static final String ACCESSIBLE_TEXT_PROPERTY;

    public static final String ACCESSIBLE_INVALIDATE_CHILDREN;

    public static final String ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED;

    public static final String ACCESSIBLE_COMPONENT_BOUNDS_CHANGED;

    protected Accessible accessibleParent;

    protected String accessibleName;

    protected String accessibleDescription;

    public String getAccessibleName();

    @BeanProperty(preferred = true, description = "Sets the accessible name for the component.")
    public void setAccessibleName(String s);

    public String getAccessibleDescription();

    @BeanProperty(preferred = true, description = "Sets the accessible description for the component.")
    public void setAccessibleDescription(String s);

    public abstract AccessibleRole getAccessibleRole();

    public abstract AccessibleStateSet getAccessibleStateSet();

    public Accessible getAccessibleParent();

    public void setAccessibleParent(Accessible a);

    public abstract int getAccessibleIndexInParent();

    public abstract int getAccessibleChildrenCount();

    public abstract Accessible getAccessibleChild(int i);

    public abstract Locale getLocale() throws IllegalComponentStateException;

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public AccessibleAction getAccessibleAction();

    public AccessibleComponent getAccessibleComponent();

    public AccessibleSelection getAccessibleSelection();

    public AccessibleText getAccessibleText();

    public AccessibleEditableText getAccessibleEditableText();

    public AccessibleValue getAccessibleValue();

    public AccessibleIcon[] getAccessibleIcon();

    public AccessibleRelationSet getAccessibleRelationSet();

    public AccessibleTable getAccessibleTable();

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue);
}
