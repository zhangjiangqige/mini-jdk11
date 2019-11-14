package javax.swing.text;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.event.*;

@AnnotatedFor({ "interning" })
public interface Document {

    public int getLength();

    public void addDocumentListener(DocumentListener listener);

    public void removeDocumentListener(DocumentListener listener);

    public void addUndoableEditListener(UndoableEditListener listener);

    public void removeUndoableEditListener(UndoableEditListener listener);

    public Object getProperty(Object key);

    public void putProperty(Object key, Object value);

    public void remove(int offs, int len) throws BadLocationException;

    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException;

    public String getText(int offset, int length) throws BadLocationException;

    public void getText(int offset, int length, Segment txt) throws BadLocationException;

    public Position getStartPosition();

    public Position getEndPosition();

    public Position createPosition(int offs) throws BadLocationException;

    public Element[] getRootElements();

    public Element getDefaultRootElement();

    public void render(Runnable r);

    @Interned
    public static final String StreamDescriptionProperty = "stream";

    @Interned
    public static final String TitleProperty = "title";
}
