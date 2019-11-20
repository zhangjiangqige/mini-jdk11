package javax.accessibility;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public class AccessibleRelation extends AccessibleBundle {

    @Interned
    public static final String LABEL_FOR;

    @Interned
    public static final String LABELED_BY;

    @Interned
    public static final String MEMBER_OF;

    @Interned
    public static final String CONTROLLER_FOR;

    @Interned
    public static final String CONTROLLED_BY;

    @Interned
    public static final String FLOWS_TO;

    @Interned
    public static final String FLOWS_FROM;

    @Interned
    public static final String SUBWINDOW_OF;

    @Interned
    public static final String PARENT_WINDOW_OF;

    @Interned
    public static final String EMBEDS;

    @Interned
    public static final String EMBEDDED_BY;

    @Interned
    public static final String CHILD_NODE_OF;

    @Interned
    public static final String LABEL_FOR_PROPERTY;

    @Interned
    public static final String LABELED_BY_PROPERTY;

    @Interned
    public static final String MEMBER_OF_PROPERTY;

    @Interned
    public static final String CONTROLLER_FOR_PROPERTY;

    @Interned
    public static final String CONTROLLED_BY_PROPERTY;

    @Interned
    public static final String FLOWS_TO_PROPERTY;

    @Interned
    public static final String FLOWS_FROM_PROPERTY;

    @Interned
    public static final String SUBWINDOW_OF_PROPERTY;

    @Interned
    public static final String PARENT_WINDOW_OF_PROPERTY;

    @Interned
    public static final String EMBEDS_PROPERTY;

    @Interned
    public static final String EMBEDDED_BY_PROPERTY;

    @Interned
    public static final String CHILD_NODE_OF_PROPERTY;

    public AccessibleRelation(String key) {
    }

    public AccessibleRelation(String key, Object target) {
    }

    public AccessibleRelation(String key, Object[] target) {
    }

    public String getKey();

    public Object[] getTarget();

    public void setTarget(Object target);

    public void setTarget(Object[] target);
}
