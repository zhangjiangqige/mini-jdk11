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

    public DropTarget getDropTarget();

    public Component getComponent();

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

    protected Transferable createTransferableProxy(Transferable t, boolean local);

    protected class TransferableProxy implements Transferable {

        public DataFlavor[] getTransferDataFlavors();

        public boolean isDataFlavorSupported(DataFlavor flavor);

        public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException;

        protected Transferable transferable;

        protected boolean isLocal;
    }
}
