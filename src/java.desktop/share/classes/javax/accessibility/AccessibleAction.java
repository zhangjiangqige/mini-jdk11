package javax.accessibility;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public interface AccessibleAction {

    public static final String TOGGLE_EXPAND;

    public static final String INCREMENT;

    public static final String DECREMENT;

    @Interned
    public static final String CLICK;

    @Interned
    public static final String TOGGLE_POPUP;

    public int getAccessibleActionCount();

    public String getAccessibleActionDescription(int i);

    public boolean doAccessibleAction(int i);
}
