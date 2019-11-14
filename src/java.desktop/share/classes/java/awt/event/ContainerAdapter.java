package java.awt.event;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ContainerAdapter implements ContainerListener {

    public void componentAdded(ContainerEvent e);

    public void componentRemoved(ContainerEvent e);
}
