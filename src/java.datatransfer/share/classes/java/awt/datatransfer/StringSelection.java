package java.awt.datatransfer;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.StringReader;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class StringSelection implements Transferable, ClipboardOwner {

    private static final int STRING = 0;

    private static final int PLAIN_TEXT = 1;

    @SuppressWarnings("deprecation")
    private static final DataFlavor[] flavors = { DataFlavor.stringFlavor, DataFlavor.plainTextFlavor };

    private String data;

    public StringSelection(String data) {
        this.data = data;
    }

    public DataFlavor[] getTransferDataFlavors();

    public boolean isDataFlavorSupported(DataFlavor flavor);

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException;

    public void lostOwnership(Clipboard clipboard, Transferable contents);
}
