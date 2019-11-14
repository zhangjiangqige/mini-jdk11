package java.awt.event;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public abstract class ComponentAdapter implements ComponentListener {

    public void componentResized(ComponentEvent e);

    public void componentMoved(ComponentEvent e);

    public void componentShown(ComponentEvent e);

    public void componentHidden(ComponentEvent e);
}
