package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.InputEvent;
import java.awt.Component;
import java.awt.Point;
import java.io.InvalidObjectException;
import java.util.Collections;
import java.util.TooManyListenersException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class DragGestureRecognizer implements Serializable {

    private static final long serialVersionUID = 8996673345831063337L;

    protected DragGestureRecognizer(DragSource ds, Component c, int sa, DragGestureListener dgl) {
        super();
        if (ds == null)
            throw new IllegalArgumentException("null DragSource");
        dragSource = ds;
        component = c;
        sourceActions = sa & (DnDConstants.ACTION_COPY_OR_MOVE | DnDConstants.ACTION_LINK);
        try {
            if (dgl != null)
                addDragGestureListener(dgl);
        } catch (TooManyListenersException tmle) {
        }
    }

    protected DragGestureRecognizer(DragSource ds, Component c, int sa) {
        this(ds, c, sa, null);
    }

    protected DragGestureRecognizer(DragSource ds, Component c) {
        this(ds, c, DnDConstants.ACTION_NONE);
    }

    protected DragGestureRecognizer(DragSource ds) {
        this(ds, null);
    }

    protected abstract void registerListeners();

    protected abstract void unregisterListeners();

    public DragSource getDragSource();

    public synchronized Component getComponent();

    public synchronized void setComponent(Component c);

    public synchronized int getSourceActions();

    public synchronized void setSourceActions(int actions);

    public InputEvent getTriggerEvent();

    public void resetRecognizer();

    public synchronized void addDragGestureListener(DragGestureListener dgl) throws TooManyListenersException;

    public synchronized void removeDragGestureListener(DragGestureListener dgl);

    protected synchronized void fireDragGestureRecognized(int dragAction, Point p);

    protected synchronized void appendEvent(InputEvent awtie);

    private void writeObject(ObjectOutputStream s) throws IOException;

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    protected DragSource dragSource;

    protected Component component;

    protected transient DragGestureListener dragGestureListener;

    protected int sourceActions;

    protected ArrayList<InputEvent> events = new ArrayList<InputEvent>(1);
}
