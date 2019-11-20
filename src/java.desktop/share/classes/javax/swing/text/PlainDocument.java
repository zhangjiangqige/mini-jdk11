package javax.swing.text;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Vector;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class PlainDocument extends AbstractDocument {

    @Interned
    public static final String tabSizeAttribute;

    @Interned
    public static final String lineLimitAttribute;

    public PlainDocument() {
    }

    public PlainDocument(Content c) {
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException;

    public Element getDefaultRootElement();

    protected AbstractElement createDefaultRoot();

    public Element getParagraphElement(int pos);

    protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr);

    protected void removeUpdate(DefaultDocumentEvent chng);
}
