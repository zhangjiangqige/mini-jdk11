package javax.swing.text;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Vector;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class PlainDocument extends AbstractDocument {

    @Interned
    public static final String tabSizeAttribute = "tabSize";

    @Interned
    public static final String lineLimitAttribute = "lineLimit";

    public PlainDocument() {
        this(new GapContent());
    }

    public PlainDocument(Content c) {
        super(c);
        putProperty(tabSizeAttribute, Integer.valueOf(8));
        defaultRoot = createDefaultRoot();
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException;

    public Element getDefaultRootElement();

    protected AbstractElement createDefaultRoot();

    public Element getParagraphElement(int pos);

    protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr);

    protected void removeUpdate(DefaultDocumentEvent chng);

    private void insertComposedTextUpdate(DefaultDocumentEvent chng, AttributeSet attr);

    private AbstractElement defaultRoot;

    private Vector<Element> added = new Vector<Element>();

    private Vector<Element> removed = new Vector<Element>();

    private transient Segment s;
}
