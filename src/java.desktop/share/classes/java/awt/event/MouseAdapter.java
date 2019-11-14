package java.awt.event;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class MouseAdapter implements MouseListener, MouseWheelListener, MouseMotionListener {

    public void mouseClicked(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseReleased(MouseEvent e);

    public void mouseEntered(MouseEvent e);

    public void mouseExited(MouseEvent e);

    public void mouseWheelMoved(MouseWheelEvent e);

    public void mouseDragged(MouseEvent e);

    public void mouseMoved(MouseEvent e);
}
