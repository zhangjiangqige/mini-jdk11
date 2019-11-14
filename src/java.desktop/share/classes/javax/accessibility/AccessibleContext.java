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

    private volatile AppContext targetAppContext;

    static {
        AWTAccessor.setAccessibleContextAccessor(new AWTAccessor.AccessibleContextAccessor() {

            @Override
            public void setAppContext(AccessibleContext accessibleContext, AppContext appContext) {
                accessibleContext.targetAppContext = appContext;
            }

            @Override
            public AppContext getAppContext(AccessibleContext accessibleContext) {
                return accessibleContext.targetAppContext;
            }

            @Override
            public Object getNativeAXResource(AccessibleContext accessibleContext) {
                return accessibleContext.nativeAXResource;
            }

            @Override
            public void setNativeAXResource(AccessibleContext accessibleContext, Object value) {
                accessibleContext.nativeAXResource = value;
            }
        });
    }

    @Interned
    public static final String ACCESSIBLE_NAME_PROPERTY = "AccessibleName";

    @Interned
    public static final String ACCESSIBLE_DESCRIPTION_PROPERTY = "AccessibleDescription";

    @Interned
    public static final String ACCESSIBLE_STATE_PROPERTY = "AccessibleState";

    @Interned
    public static final String ACCESSIBLE_VALUE_PROPERTY = "AccessibleValue";

    @Interned
    public static final String ACCESSIBLE_SELECTION_PROPERTY = "AccessibleSelection";

    @Interned
    public static final String ACCESSIBLE_CARET_PROPERTY = "AccessibleCaret";

    @Interned
    public static final String ACCESSIBLE_VISIBLE_DATA_PROPERTY = "AccessibleVisibleData";

    @Interned
    public static final String ACCESSIBLE_CHILD_PROPERTY = "AccessibleChild";

    @Interned
    public static final String ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY = "AccessibleActiveDescendant";

    public static final String ACCESSIBLE_TABLE_CAPTION_CHANGED = "accessibleTableCaptionChanged";

    public static final String ACCESSIBLE_TABLE_SUMMARY_CHANGED = "accessibleTableSummaryChanged";

    public static final String ACCESSIBLE_TABLE_MODEL_CHANGED = "accessibleTableModelChanged";

    public static final String ACCESSIBLE_TABLE_ROW_HEADER_CHANGED = "accessibleTableRowHeaderChanged";

    public static final String ACCESSIBLE_TABLE_ROW_DESCRIPTION_CHANGED = "accessibleTableRowDescriptionChanged";

    public static final String ACCESSIBLE_TABLE_COLUMN_HEADER_CHANGED = "accessibleTableColumnHeaderChanged";

    public static final String ACCESSIBLE_TABLE_COLUMN_DESCRIPTION_CHANGED = "accessibleTableColumnDescriptionChanged";

    public static final String ACCESSIBLE_ACTION_PROPERTY = "accessibleActionProperty";

    public static final String ACCESSIBLE_HYPERTEXT_OFFSET = "AccessibleHypertextOffset";

    public static final String ACCESSIBLE_TEXT_PROPERTY = "AccessibleText";

    public static final String ACCESSIBLE_INVALIDATE_CHILDREN = "accessibleInvalidateChildren";

    public static final String ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED = "accessibleTextAttributesChanged";

    public static final String ACCESSIBLE_COMPONENT_BOUNDS_CHANGED = "accessibleComponentBoundsChanged";

    protected Accessible accessibleParent = null;

    protected String accessibleName = null;

    protected String accessibleDescription = null;

    private PropertyChangeSupport accessibleChangeSupport = null;

    private AccessibleRelationSet relationSet = new AccessibleRelationSet();

    private Object nativeAXResource;

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
