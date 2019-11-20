package javax.swing.undo;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;

@AnnotatedFor({ "interning" })
public interface StateEditable {

    @Interned
    public static final String RCSID;

    public void storeState(Hashtable<Object, Object> state);

    public void restoreState(Hashtable<?, ?> state);
}
