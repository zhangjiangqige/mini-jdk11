package javax.accessibility;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public interface AccessibleAction {

    public static final String TOGGLE_EXPAND = new String("toggleexpand");

    public static final String INCREMENT = new String("increment");

    public static final String DECREMENT = new String("decrement");

    @Interned
    public static final String CLICK = new String("click");

    @Interned
    public static final String TOGGLE_POPUP = new String("toggle popup");

    public int getAccessibleActionCount();

    public String getAccessibleActionDescription(int i);

    public boolean doAccessibleAction(int i);
}
