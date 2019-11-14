package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.FlavorMap;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.AccessController;
import java.util.EventListener;
import sun.awt.AWTAccessor;
import sun.awt.AWTAccessor.DragSourceContextAccessor;
import sun.awt.dnd.SunDragSourceContextPeer;
import sun.security.action.GetIntegerAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DragSource implements Serializable {

    private static final long serialVersionUID = 6236096958971414066L;

    private static Cursor load(String name);

    public static final Cursor DefaultCopyDrop = load("DnD.Cursor.CopyDrop");

    public static final Cursor DefaultMoveDrop = load("DnD.Cursor.MoveDrop");

    public static final Cursor DefaultLinkDrop = load("DnD.Cursor.LinkDrop");

    public static final Cursor DefaultCopyNoDrop = load("DnD.Cursor.CopyNoDrop");

    public static final Cursor DefaultMoveNoDrop = load("DnD.Cursor.MoveNoDrop");

    public static final Cursor DefaultLinkNoDrop = load("DnD.Cursor.LinkNoDrop");

    private static final DragSource dflt = (GraphicsEnvironment.isHeadless()) ? null : new DragSource();

    static final String dragSourceListenerK = "dragSourceL";

    static final String dragSourceMotionListenerK = "dragSourceMotionL";

    public static DragSource getDefaultDragSource();

    public static boolean isDragImageSupported();

    public DragSource() throws HeadlessException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException();
        }
    }

    public void startDrag(DragGestureEvent trigger, Cursor dragCursor, Image dragImage, Point imageOffset, Transferable transferable, DragSourceListener dsl, FlavorMap flavorMap) throws InvalidDnDOperationException;

    public void startDrag(DragGestureEvent trigger, Cursor dragCursor, Transferable transferable, DragSourceListener dsl, FlavorMap flavorMap) throws InvalidDnDOperationException;

    public void startDrag(DragGestureEvent trigger, Cursor dragCursor, Image dragImage, Point dragOffset, Transferable transferable, DragSourceListener dsl) throws InvalidDnDOperationException;

    public void startDrag(DragGestureEvent trigger, Cursor dragCursor, Transferable transferable, DragSourceListener dsl) throws InvalidDnDOperationException;

    protected DragSourceContext createDragSourceContext(DragGestureEvent dgl, Cursor dragCursor, Image dragImage, Point imageOffset, Transferable t, DragSourceListener dsl);

    public FlavorMap getFlavorMap();

    public <T extends DragGestureRecognizer> T createDragGestureRecognizer(Class<T> recognizerAbstractClass, Component c, int actions, DragGestureListener dgl);

    public DragGestureRecognizer createDefaultDragGestureRecognizer(Component c, int actions, DragGestureListener dgl);

    public void addDragSourceListener(DragSourceListener dsl);

    public void removeDragSourceListener(DragSourceListener dsl);

    public DragSourceListener[] getDragSourceListeners();

    public void addDragSourceMotionListener(DragSourceMotionListener dsml);

    public void removeDragSourceMotionListener(DragSourceMotionListener dsml);

    public DragSourceMotionListener[] getDragSourceMotionListeners();

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    void processDragEnter(DragSourceDragEvent dsde);

    void processDragOver(DragSourceDragEvent dsde);

    void processDropActionChanged(DragSourceDragEvent dsde);

    void processDragExit(DragSourceEvent dse);

    void processDragDropEnd(DragSourceDropEvent dsde);

    void processDragMouseMoved(DragSourceDragEvent dsde);

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    public static int getDragThreshold();

    private transient FlavorMap flavorMap = SystemFlavorMap.getDefaultFlavorMap();

    private transient DragSourceListener listener;

    private transient DragSourceMotionListener motionListener;
}
