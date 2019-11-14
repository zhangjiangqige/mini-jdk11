package javax.accessibility;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public class AccessibleRelation extends AccessibleBundle {

    private Object[] target = new Object[0];

    @Interned
    public static final String LABEL_FOR = new String("labelFor");

    @Interned
    public static final String LABELED_BY = new String("labeledBy");

    @Interned
    public static final String MEMBER_OF = new String("memberOf");

    @Interned
    public static final String CONTROLLER_FOR = new String("controllerFor");

    @Interned
    public static final String CONTROLLED_BY = new String("controlledBy");

    @Interned
    public static final String FLOWS_TO = "flowsTo";

    @Interned
    public static final String FLOWS_FROM = "flowsFrom";

    @Interned
    public static final String SUBWINDOW_OF = "subwindowOf";

    @Interned
    public static final String PARENT_WINDOW_OF = "parentWindowOf";

    @Interned
    public static final String EMBEDS = "embeds";

    @Interned
    public static final String EMBEDDED_BY = "embeddedBy";

    @Interned
    public static final String CHILD_NODE_OF = "childNodeOf";

    @Interned
    public static final String LABEL_FOR_PROPERTY = "labelForProperty";

    @Interned
    public static final String LABELED_BY_PROPERTY = "labeledByProperty";

    @Interned
    public static final String MEMBER_OF_PROPERTY = "memberOfProperty";

    @Interned
    public static final String CONTROLLER_FOR_PROPERTY = "controllerForProperty";

    @Interned
    public static final String CONTROLLED_BY_PROPERTY = "controlledByProperty";

    @Interned
    public static final String FLOWS_TO_PROPERTY = "flowsToProperty";

    @Interned
    public static final String FLOWS_FROM_PROPERTY = "flowsFromProperty";

    @Interned
    public static final String SUBWINDOW_OF_PROPERTY = "subwindowOfProperty";

    @Interned
    public static final String PARENT_WINDOW_OF_PROPERTY = "parentWindowOfProperty";

    @Interned
    public static final String EMBEDS_PROPERTY = "embedsProperty";

    @Interned
    public static final String EMBEDDED_BY_PROPERTY = "embeddedByProperty";

    @Interned
    public static final String CHILD_NODE_OF_PROPERTY = "childNodeOfProperty";

    public AccessibleRelation(String key) {
        this.key = key;
        this.target = null;
    }

    public AccessibleRelation(String key, Object target) {
        this.key = key;
        this.target = new Object[1];
        this.target[0] = target;
    }

    public AccessibleRelation(String key, Object[] target) {
        this.key = key;
        this.target = target;
    }

    public String getKey();

    public Object[] getTarget();

    public void setTarget(Object target);

    public void setTarget(Object[] target);
}
