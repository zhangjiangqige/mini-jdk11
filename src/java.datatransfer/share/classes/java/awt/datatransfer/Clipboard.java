package java.awt.datatransfer;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import sun.datatransfer.DataFlavorUtil;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Clipboard {

    protected ClipboardOwner owner;

    protected Transferable contents;

    public Clipboard(String name) {
    }

    public String getName();

    public synchronized void setContents(Transferable contents, ClipboardOwner owner);

    public synchronized Transferable getContents(Object requestor);

    public DataFlavor[] getAvailableDataFlavors();

    public boolean isDataFlavorAvailable(DataFlavor flavor);

    public Object getData(DataFlavor flavor) throws UnsupportedFlavorException, IOException;

    public synchronized void addFlavorListener(FlavorListener listener);

    public synchronized void removeFlavorListener(FlavorListener listener);

    public synchronized FlavorListener[] getFlavorListeners();
}
