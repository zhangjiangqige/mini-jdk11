package javax.swing.text;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.io.*;
import java.awt.font.TextAttribute;
import java.text.Bidi;
import javax.swing.UIManager;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.tree.TreeNode;
import sun.font.BidiUtils;
import sun.swing.SwingUtilities2;
import sun.swing.text.UndoableEditLockSupport;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public abstract class AbstractDocument implements Document, Serializable {

    protected AbstractDocument(Content data) {
    }

    protected AbstractDocument(Content data, AttributeContext context) {
    }

    public Dictionary<Object, Object> getDocumentProperties();

    public void setDocumentProperties(Dictionary<Object, Object> x);

    protected void fireInsertUpdate(DocumentEvent e);

    protected void fireChangedUpdate(DocumentEvent e);

    protected void fireRemoveUpdate(DocumentEvent e);

    protected void fireUndoableEditUpdate(UndoableEditEvent e);

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    public int getAsynchronousLoadPriority();

    public void setAsynchronousLoadPriority(int p);

    public void setDocumentFilter(DocumentFilter filter);

    public DocumentFilter getDocumentFilter();

    public void render(Runnable r);

    public int getLength();

    public void addDocumentListener(DocumentListener listener);

    public void removeDocumentListener(DocumentListener listener);

    public DocumentListener[] getDocumentListeners();

    public void addUndoableEditListener(UndoableEditListener listener);

    public void removeUndoableEditListener(UndoableEditListener listener);

    public UndoableEditListener[] getUndoableEditListeners();

    public final Object getProperty(Object key);

    public final void putProperty(Object key, Object value);

    public void remove(int offs, int len) throws BadLocationException;

    public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException;

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException;

    public String getText(int offset, int length) throws BadLocationException;

    public void getText(int offset, int length, Segment txt) throws BadLocationException;

    public synchronized Position createPosition(int offs) throws BadLocationException;

    public final Position getStartPosition();

    public final Position getEndPosition();

    public Element[] getRootElements();

    public abstract Element getDefaultRootElement();

    public Element getBidiRootElement();

    public abstract Element getParagraphElement(int pos);

    protected final AttributeContext getAttributeContext();

    protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr);

    protected void removeUpdate(DefaultDocumentEvent chng);

    protected void postRemoveUpdate(DefaultDocumentEvent chng);

    public void dump(PrintStream out);

    protected final Content getContent();

    protected Element createLeafElement(Element parent, AttributeSet a, int p0, int p1);

    protected Element createBranchElement(Element parent, AttributeSet a);

    protected final synchronized Thread getCurrentWriter();

    protected final synchronized void writeLock();

    protected final synchronized void writeUnlock();

    public final synchronized void readLock();

    public final synchronized void readUnlock();

    protected EventListenerList listenerList;

    protected static final String BAD_LOCATION;

    @Interned
    public static final String ParagraphElementName;

    @Interned
    public static final String ContentElementName;

    @Interned
    public static final String SectionElementName;

    @Interned
    public static final String BidiElementName;

    @Interned
    public static final String ElementNameAttribute;

    public interface Content {

        public Position createPosition(int offset) throws BadLocationException;

        public int length();

        public UndoableEdit insertString(int where, String str) throws BadLocationException;

        public UndoableEdit remove(int where, int nitems) throws BadLocationException;

        public String getString(int where, int len) throws BadLocationException;

        public void getChars(int where, int len, Segment txt) throws BadLocationException;
    }

    public interface AttributeContext {

        public AttributeSet addAttribute(AttributeSet old, Object name, Object value);

        public AttributeSet addAttributes(AttributeSet old, AttributeSet attr);

        public AttributeSet removeAttribute(AttributeSet old, Object name);

        public AttributeSet removeAttributes(AttributeSet old, Enumeration<?> names);

        public AttributeSet removeAttributes(AttributeSet old, AttributeSet attrs);

        public AttributeSet getEmptySet();

        public void reclaim(AttributeSet a);
    }

    @SuppressWarnings("serial")
    public abstract class AbstractElement implements Element, MutableAttributeSet, Serializable, TreeNode {

        public AbstractElement(Element parent, AttributeSet a) {
        }

        public void dump(PrintStream psOut, int indentAmount);

        public int getAttributeCount();

        public boolean isDefined(Object attrName);

        public boolean isEqual(AttributeSet attr);

        public AttributeSet copyAttributes();

        public Object getAttribute(Object attrName);

        public Enumeration<?> getAttributeNames();

        public boolean containsAttribute(Object name, Object value);

        public boolean containsAttributes(AttributeSet attrs);

        public AttributeSet getResolveParent();

        public void addAttribute(Object name, Object value);

        public void addAttributes(AttributeSet attr);

        public void removeAttribute(Object name);

        public void removeAttributes(Enumeration<?> names);

        public void removeAttributes(AttributeSet attrs);

        public void setResolveParent(AttributeSet parent);

        public Document getDocument();

        public Element getParentElement();

        public AttributeSet getAttributes();

        public String getName();

        public abstract int getStartOffset();

        public abstract int getEndOffset();

        public abstract Element getElement(int index);

        public abstract int getElementCount();

        public abstract int getElementIndex(int offset);

        public abstract boolean isLeaf();

        public TreeNode getChildAt(int childIndex);

        public int getChildCount();

        public TreeNode getParent();

        public int getIndex(TreeNode node);

        public abstract boolean getAllowsChildren();

        public abstract Enumeration<TreeNode> children();
    }

    @SuppressWarnings("serial")
    public class BranchElement extends AbstractElement {

        public BranchElement(Element parent, AttributeSet a) {
        }

        public Element positionToElement(int pos);

        public void replace(int offset, int length, Element[] elems);

        public String toString();

        public String getName();

        public int getStartOffset();

        public int getEndOffset();

        public Element getElement(int index);

        public int getElementCount();

        public int getElementIndex(int offset);

        public boolean isLeaf();

        public boolean getAllowsChildren();

        public Enumeration<TreeNode> children();
    }

    @SuppressWarnings("serial")
    public class LeafElement extends AbstractElement {

        public LeafElement(Element parent, AttributeSet a, int offs0, int offs1) {
        }

        public String toString();

        public int getStartOffset();

        public int getEndOffset();

        public String getName();

        public int getElementIndex(int pos);

        public Element getElement(int index);

        public int getElementCount();

        public boolean isLeaf();

        public boolean getAllowsChildren();

        @Override
        public Enumeration<TreeNode> children();
    }

    public class DefaultDocumentEvent extends CompoundEdit implements DocumentEvent {

        public DefaultDocumentEvent(int offs, int len, DocumentEvent.EventType type) {
        }

        public String toString();

        public boolean addEdit(UndoableEdit anEdit);

        public void redo() throws CannotRedoException;

        public void undo() throws CannotUndoException;

        public boolean isSignificant();

        public String getPresentationName();

        public String getUndoPresentationName();

        public String getRedoPresentationName();

        public DocumentEvent.EventType getType();

        public int getOffset();

        public int getLength();

        public Document getDocument();

        public DocumentEvent.ElementChange getChange(Element elem);
    }

    public static class ElementEdit extends AbstractUndoableEdit implements DocumentEvent.ElementChange {

        public ElementEdit(Element e, int index, Element[] removed, Element[] added) {
        }

        public Element getElement();

        public int getIndex();

        public Element[] getChildrenRemoved();

        public Element[] getChildrenAdded();

        public void redo() throws CannotRedoException;

        public void undo() throws CannotUndoException;
    }
}
