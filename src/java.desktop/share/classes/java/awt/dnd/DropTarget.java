package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.TooManyListenersException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.FlavorMap;
import java.awt.datatransfer.SystemFlavorMap;
import javax.swing.Timer;
import java.awt.peer.ComponentPeer;
import java.awt.peer.LightweightPeer;
import java.awt.dnd.peer.DropTargetPeer;
import sun.awt.AWTAccessor;
import sun.awt.AWTAccessor.ComponentAccessor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DropTarget implements DropTargetListener, Serializable {

    private static final long serialVersionUID = -6283860791671019047L;

    public DropTarget(Component c, int ops, DropTargetListener dtl, boolean act, FlavorMap fm) throws HeadlessException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException();
        }
        component = c;
        setDefaultActions(ops);
        if (dtl != null)
            try {
                addDropTargetListener(dtl);
            } catch (TooManyListenersException tmle) {
            }
        if (c != null) {
            c.setDropTarget(this);
            setActive(act);
        }
        if (fm != null) {
            flavorMap = fm;
        } else {
            flavorMap = SystemFlavorMap.getDefaultFlavorMap();
        }
    }

    public DropTarget(Component c, int ops, DropTargetListener dtl, boolean act) throws HeadlessException {
        this(c, ops, dtl, act, null);
    }

    public DropTarget() throws HeadlessException {
        this(null, DnDConstants.ACTION_COPY_OR_MOVE, null, true, null);
    }

    public DropTarget(Component c, DropTargetListener dtl) throws HeadlessException {
        this(c, DnDConstants.ACTION_COPY_OR_MOVE, dtl, true, null);
    }

    public DropTarget(Component c, int ops, DropTargetListener dtl) throws HeadlessException {
        this(c, ops, dtl, true);
    }

    public synchronized void setComponent(Component c);

    public synchronized Component getComponent();

    public void setDefaultActions(int ops);

    void doSetDefaultActions(int ops);

    public int getDefaultActions();

    public synchronized void setActive(boolean isActive);

    public boolean isActive();

    public synchronized void addDropTargetListener(DropTargetListener dtl) throws TooManyListenersException;

    public synchronized void removeDropTargetListener(DropTargetListener dtl);

    public synchronized void dragEnter(DropTargetDragEvent dtde);

    public synchronized void dragOver(DropTargetDragEvent dtde);

    public synchronized void dropActionChanged(DropTargetDragEvent dtde);

    public synchronized void dragExit(DropTargetEvent dte);

    public synchronized void drop(DropTargetDropEvent dtde);

    public FlavorMap getFlavorMap();

    public void setFlavorMap(FlavorMap fm);

    public void addNotify();

    public void removeNotify();

    public DropTargetContext getDropTargetContext();

    protected DropTargetContext createDropTargetContext();

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    protected static class DropTargetAutoScroller implements ActionListener {

        protected DropTargetAutoScroller(Component c, Point p) {
            super();
            component = c;
            autoScroll = (Autoscroll) component;
            Toolkit t = Toolkit.getDefaultToolkit();
            Integer initial = Integer.valueOf(100);
            Integer interval = Integer.valueOf(100);
            try {
                initial = (Integer) t.getDesktopProperty("DnD.Autoscroll.initialDelay");
            } catch (Exception e) {
            }
            try {
                interval = (Integer) t.getDesktopProperty("DnD.Autoscroll.interval");
            } catch (Exception e) {
            }
            timer = new Timer(interval.intValue(), this);
            timer.setCoalesce(true);
            timer.setInitialDelay(initial.intValue());
            locn = p;
            prev = p;
            try {
                hysteresis = ((Integer) t.getDesktopProperty("DnD.Autoscroll.cursorHysteresis")).intValue();
            } catch (Exception e) {
            }
            timer.start();
        }

        @SuppressWarnings("deprecation")
        private void updateRegion();

        protected synchronized void updateLocation(Point newLocn);

        protected void stop();

        public synchronized void actionPerformed(ActionEvent e);

        private Component component;

        private Autoscroll autoScroll;

        private Timer timer;

        private Point locn;

        private Point prev;

        private Rectangle outer = new Rectangle();

        private Rectangle inner = new Rectangle();

        private int hysteresis = 10;
    }

    protected DropTargetAutoScroller createDropTargetAutoScroller(Component c, Point p);

    protected void initializeAutoscrolling(Point p);

    protected void updateAutoscroll(Point dragCursorLocn);

    protected void clearAutoscroll();

    private DropTargetContext dropTargetContext = createDropTargetContext();

    private Component component;

    private transient ComponentPeer componentPeer;

    private transient DropTargetPeer nativePeer;

    int actions = DnDConstants.ACTION_COPY_OR_MOVE;

    boolean active = true;

    private transient DropTargetAutoScroller autoScroller;

    private transient DropTargetListener dtListener;

    private transient FlavorMap flavorMap;

    private transient boolean isDraggingInside;
}
