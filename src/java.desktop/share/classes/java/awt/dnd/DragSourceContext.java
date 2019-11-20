package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.AWTError;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TooManyListenersException;
import sun.awt.AWTAccessor;
import sun.awt.ComponentFactory;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DragSourceContext implements DragSourceListener, DragSourceMotionListener, Serializable {

    protected static final int DEFAULT;

    protected static final int ENTER;

    protected static final int OVER;

    protected static final int CHANGED;

    public DragSourceContext(DragGestureEvent trigger, Cursor dragCursor, Image dragImage, Point offset, Transferable t, DragSourceListener dsl) {
    }

    public DragSource getDragSource();

    public Component getComponent();

    public DragGestureEvent getTrigger();

    public int getSourceActions();

    public synchronized void setCursor(Cursor c);

    public Cursor getCursor();

    public synchronized void addDragSourceListener(DragSourceListener dsl) throws TooManyListenersException;

    public synchronized void removeDragSourceListener(DragSourceListener dsl);

    public void transferablesFlavorsChanged();

    public void dragEnter(DragSourceDragEvent dsde);

    public void dragOver(DragSourceDragEvent dsde);

    public void dragExit(DragSourceEvent dse);

    public void dropActionChanged(DragSourceDragEvent dsde);

    public void dragDropEnd(DragSourceDropEvent dsde);

    public void dragMouseMoved(DragSourceDragEvent dsde);

    public Transferable getTransferable();

    @SuppressWarnings("fallthrough")
    protected synchronized void updateCurrentCursor(int sourceAct, int targetAct, int status);
}
