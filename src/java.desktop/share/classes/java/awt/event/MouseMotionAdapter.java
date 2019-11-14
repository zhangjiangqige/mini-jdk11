package java.awt.event;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class MouseMotionAdapter implements MouseMotionListener {

    public void mouseDragged(MouseEvent e);

    public void mouseMoved(MouseEvent e);
}
