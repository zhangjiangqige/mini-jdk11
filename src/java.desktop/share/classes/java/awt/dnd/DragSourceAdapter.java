package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class DragSourceAdapter implements DragSourceListener, DragSourceMotionListener {

    public void dragEnter(DragSourceDragEvent dsde);

    public void dragOver(DragSourceDragEvent dsde);

    public void dragMouseMoved(DragSourceDragEvent dsde);

    public void dropActionChanged(DragSourceDragEvent dsde);

    public void dragExit(DragSourceEvent dse);

    public void dragDropEnd(DragSourceDropEvent dsde);
}
