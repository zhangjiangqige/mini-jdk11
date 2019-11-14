package javax.swing.undo;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;

@AnnotatedFor({ "interning" })
public interface StateEditable {

    @Interned
    public static final String RCSID = "$Id: StateEditable.java,v 1.2 1997/09/08 19:39:08 marklin Exp $";

    public void storeState(Hashtable<Object, Object> state);

    public void restoreState(Hashtable<?, ?> state);
}
