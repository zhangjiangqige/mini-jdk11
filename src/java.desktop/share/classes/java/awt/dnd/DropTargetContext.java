package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.peer.DropTargetContextPeer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import sun.awt.AWTAccessor;
import sun.awt.AWTAccessor.DropTargetContextAccessor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DropTargetContext implements Serializable {

    private static final long serialVersionUID = -634158968993743371L;

    static {
        AWTAccessor.setDropTargetContextAccessor(new DropTargetContextAccessor() {

            @Override
            public void reset(DropTargetContext dtc) {
                dtc.reset();
            }

            @Override
            public void setDropTargetContextPeer(DropTargetContext dtc, DropTargetContextPeer dtcp) {
                dtc.setDropTargetContextPeer(dtcp);
            }
        });
    }

    DropTargetContext(DropTarget dt) {
        super();
        dropTarget = dt;
    }

    public DropTarget getDropTarget();

    public Component getComponent();

    void reset();

    protected void setTargetActions(int actions);

    protected int getTargetActions();

    public void dropComplete(boolean success) throws InvalidDnDOperationException;

    protected void acceptDrag(int dragOperation);

    protected void rejectDrag();

    protected void acceptDrop(int dropOperation);

    protected void rejectDrop();

    protected DataFlavor[] getCurrentDataFlavors();

    protected List<DataFlavor> getCurrentDataFlavorsAsList();

    protected boolean isDataFlavorSupported(DataFlavor df);

    protected Transferable getTransferable() throws InvalidDnDOperationException;

    DropTargetContextPeer getDropTargetContextPeer();

    void setDropTargetContextPeer(final DropTargetContextPeer dtcp);

    protected Transferable createTransferableProxy(Transferable t, boolean local);

    protected class TransferableProxy implements Transferable {

        TransferableProxy(Transferable t, boolean local) {
            proxy = new sun.awt.datatransfer.TransferableProxy(t, local);
            transferable = t;
            isLocal = local;
        }

        public DataFlavor[] getTransferDataFlavors();

        public boolean isDataFlavorSupported(DataFlavor flavor);

        public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException;

        protected Transferable transferable;

        protected boolean isLocal;

        private sun.awt.datatransfer.TransferableProxy proxy;
    }

    private final DropTarget dropTarget;

    private transient DropTargetContextPeer dropTargetContextPeer;

    private transient Transferable transferable;
}
