package java.awt.event;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FocusAdapter implements FocusListener {

    public void focusGained(FocusEvent e);

    public void focusLost(FocusEvent e);
}
