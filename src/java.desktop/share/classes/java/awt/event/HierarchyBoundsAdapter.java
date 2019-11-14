package java.awt.event;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class HierarchyBoundsAdapter implements HierarchyBoundsListener {

    public void ancestorMoved(HierarchyEvent e);

    public void ancestorResized(HierarchyEvent e);
}
