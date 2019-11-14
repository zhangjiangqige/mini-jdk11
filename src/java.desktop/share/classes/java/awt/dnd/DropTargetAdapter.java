package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class DropTargetAdapter implements DropTargetListener {

    public void dragEnter(DropTargetDragEvent dtde);

    public void dragOver(DropTargetDragEvent dtde);

    public void dropActionChanged(DropTargetDragEvent dtde);

    public void dragExit(DropTargetEvent dte);
}
