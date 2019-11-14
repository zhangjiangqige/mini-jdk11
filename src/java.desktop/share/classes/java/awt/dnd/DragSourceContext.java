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

    private static final long serialVersionUID = -115407898692194719L;

    protected static final int DEFAULT = 0;

    protected static final int ENTER = 1;

    protected static final int OVER = 2;

    protected static final int CHANGED = 3;

    static {
        AWTAccessor.setDragSourceContextAccessor(dsc -> dsc.peer);
    }

    public DragSourceContext(DragGestureEvent trigger, Cursor dragCursor, Image dragImage, Point offset, Transferable t, DragSourceListener dsl) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if (!(toolkit instanceof ComponentFactory)) {
            throw new AWTError("Unsupported toolkit: " + toolkit);
        }
        DragSourceContextPeer dscp = ((ComponentFactory) toolkit).createDragSourceContextPeer(trigger);
        if (dscp == null) {
            throw new NullPointerException("DragSourceContextPeer");
        }
        if (trigger == null) {
            throw new NullPointerException("Trigger");
        }
        if (trigger.getDragSource() == null) {
            throw new IllegalArgumentException("DragSource");
        }
        if (trigger.getComponent() == null) {
            throw new IllegalArgumentException("Component");
        }
        if (trigger.getSourceAsDragGestureRecognizer().getSourceActions() == DnDConstants.ACTION_NONE) {
            throw new IllegalArgumentException("source actions");
        }
        if (trigger.getDragAction() == DnDConstants.ACTION_NONE) {
            throw new IllegalArgumentException("no drag action");
        }
        if (t == null) {
            throw new NullPointerException("Transferable");
        }
        if (dragImage != null && offset == null) {
            throw new NullPointerException("offset");
        }
        peer = dscp;
        this.trigger = trigger;
        cursor = dragCursor;
        transferable = t;
        listener = dsl;
        sourceActions = trigger.getSourceAsDragGestureRecognizer().getSourceActions();
        useCustomCursor = (dragCursor != null);
        updateCurrentCursor(trigger.getDragAction(), getSourceActions(), DEFAULT);
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

    private void setCursorImpl(Cursor c);

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    private static Transferable emptyTransferable;

    private final transient DragSourceContextPeer peer;

    private DragGestureEvent trigger;

    private Cursor cursor;

    private transient Transferable transferable;

    private transient DragSourceListener listener;

    private boolean useCustomCursor;

    private int sourceActions;
}
