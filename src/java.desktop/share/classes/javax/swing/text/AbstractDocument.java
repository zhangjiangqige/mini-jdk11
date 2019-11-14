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
        this(data, StyleContext.getDefaultStyleContext());
    }

    protected AbstractDocument(Content data, AttributeContext context) {
        this.data = data;
        this.context = context;
        bidiRoot = new BidiRootElement();
        if (defaultI18NProperty == null) {
            String o = java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<String>() {

                public String run() {
                    return System.getProperty(I18NProperty);
                }
            });
            if (o != null) {
                defaultI18NProperty = Boolean.valueOf(o);
            } else {
                defaultI18NProperty = Boolean.FALSE;
            }
        }
        putProperty(I18NProperty, defaultI18NProperty);
        writeLock();
        try {
            Element[] p = new Element[1];
            p[0] = new BidiElement(bidiRoot, 0, 1, 0);
            bidiRoot.replace(0, 0, p);
        } finally {
            writeUnlock();
        }
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

    void handleRemove(int offs, int len) throws BadLocationException;

    public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException;

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException;

    private void handleInsertString(int offs, String str, AttributeSet a) throws BadLocationException;

    public String getText(int offset, int length) throws BadLocationException;

    public void getText(int offset, int length, Segment txt) throws BadLocationException;

    public synchronized Position createPosition(int offs) throws BadLocationException;

    public final Position getStartPosition();

    public final Position getEndPosition();

    public Element[] getRootElements();

    public abstract Element getDefaultRootElement();

    private DocumentFilter.FilterBypass getFilterBypass();

    public Element getBidiRootElement();

    static boolean isLeftToRight(Document doc, int p0, int p1);

    public abstract Element getParagraphElement(int pos);

    protected final AttributeContext getAttributeContext();

    protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr);

    protected void removeUpdate(DefaultDocumentEvent chng);

    protected void postRemoveUpdate(DefaultDocumentEvent chng);

    void updateBidi(DefaultDocumentEvent chng);

    private byte[] calculateBidiLevels(int firstPStart, int lastPEnd);

    public void dump(PrintStream out);

    protected final Content getContent();

    protected Element createLeafElement(Element parent, AttributeSet a, int p0, int p1);

    protected Element createBranchElement(Element parent, AttributeSet a);

    protected final synchronized Thread getCurrentWriter();

    protected final synchronized void writeLock();

    protected final synchronized void writeUnlock();

    public final synchronized void readLock();

    public final synchronized void readUnlock();

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    private transient int numReaders;

    private transient Thread currWriter;

    private transient int numWriters;

    private transient boolean notifyingListeners;

    private static Boolean defaultI18NProperty;

    private Dictionary<Object, Object> documentProperties = null;

    protected EventListenerList listenerList = new EventListenerList();

    private Content data;

    private AttributeContext context;

    private transient BranchElement bidiRoot;

    private DocumentFilter documentFilter;

    private transient DocumentFilter.FilterBypass filterBypass;

    private static final String BAD_LOCK_STATE = "document lock failure";

    protected static final String BAD_LOCATION = "document location failure";

    @Interned
    public static final String ParagraphElementName = "paragraph";

    @Interned
    public static final String ContentElementName = "content";

    @Interned
    public static final String SectionElementName = "section";

    @Interned
    public static final String BidiElementName = "bidi level";

    @Interned
    public static final String ElementNameAttribute = "$ename";

    static final String I18NProperty = "i18n";

    static final Object MultiByteProperty = "multiByte";

    static final String AsyncLoadPriority = "load priority";

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
            this.parent = parent;
            attributes = getAttributeContext().getEmptySet();
            if (a != null) {
                addAttributes(a);
            }
        }

        private void indent(PrintWriter out, int n);

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

        private void checkForIllegalCast();

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

        private void writeObject(ObjectOutputStream s) throws IOException;

        private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

        private Element parent;

        private transient AttributeSet attributes;
    }

    @SuppressWarnings("serial")
    public class BranchElement extends AbstractElement {

        public BranchElement(Element parent, AttributeSet a) {
            super(parent, a);
            children = new AbstractElement[1];
            nchildren = 0;
            lastIndex = -1;
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

        private AbstractElement[] children;

        private int nchildren;

        private int lastIndex;
    }

    @SuppressWarnings("serial")
    public class LeafElement extends AbstractElement {

        public LeafElement(Element parent, AttributeSet a, int offs0, int offs1) {
            super(parent, a);
            try {
                p0 = createPosition(offs0);
                p1 = createPosition(offs1);
            } catch (BadLocationException e) {
                p0 = null;
                p1 = null;
                throw new StateInvariantError("Can't create Position references");
            }
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

        private void writeObject(ObjectOutputStream s) throws IOException;

        private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

        private transient Position p0;

        private transient Position p1;
    }

    class BidiRootElement extends BranchElement {

        BidiRootElement() {
            super(null, null);
        }

        public String getName();
    }

    class BidiElement extends LeafElement {

        BidiElement(Element parent, int start, int end, int level) {
            super(parent, new SimpleAttributeSet(), start, end);
            addAttribute(StyleConstants.BidiLevel, Integer.valueOf(level));
        }

        public String getName();

        int getLevel();

        boolean isLeftToRight();
    }

    public class DefaultDocumentEvent extends CompoundEdit implements DocumentEvent {

        public DefaultDocumentEvent(int offs, int len, DocumentEvent.EventType type) {
            super();
            offset = offs;
            length = len;
            this.type = type;
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

        private int offset;

        private int length;

        private Hashtable<Element, ElementChange> changeLookup;

        private DocumentEvent.EventType type;
    }

    static class DefaultDocumentEventUndoableWrapper implements UndoableEdit, UndoableEditLockSupport {

        final DefaultDocumentEvent dde;

        public DefaultDocumentEventUndoableWrapper(DefaultDocumentEvent dde) {
            this.dde = dde;
        }

        @Override
        public void undo() throws CannotUndoException;

        @Override
        public boolean canUndo();

        @Override
        public void redo() throws CannotRedoException;

        @Override
        public boolean canRedo();

        @Override
        public void die();

        @Override
        public boolean addEdit(UndoableEdit anEdit);

        @Override
        public boolean replaceEdit(UndoableEdit anEdit);

        @Override
        public boolean isSignificant();

        @Override
        public String getPresentationName();

        @Override
        public String getUndoPresentationName();

        @Override
        public String getRedoPresentationName();

        @Override
        public void lockEdit();

        @Override
        public void unlockEdit();
    }

    class UndoRedoDocumentEvent implements DocumentEvent {

        private DefaultDocumentEvent src = null;

        private EventType type = null;

        public UndoRedoDocumentEvent(DefaultDocumentEvent src, boolean isUndo) {
            this.src = src;
            if (isUndo) {
                if (src.getType().equals(EventType.INSERT)) {
                    type = EventType.REMOVE;
                } else if (src.getType().equals(EventType.REMOVE)) {
                    type = EventType.INSERT;
                } else {
                    type = src.getType();
                }
            } else {
                type = src.getType();
            }
        }

        public DefaultDocumentEvent getSource();

        public int getOffset();

        public int getLength();

        public Document getDocument();

        public DocumentEvent.EventType getType();

        public DocumentEvent.ElementChange getChange(Element elem);
    }

    public static class ElementEdit extends AbstractUndoableEdit implements DocumentEvent.ElementChange {

        public ElementEdit(Element e, int index, Element[] removed, Element[] added) {
            super();
            this.e = e;
            this.index = index;
            this.removed = removed;
            this.added = added;
        }

        public Element getElement();

        public int getIndex();

        public Element[] getChildrenRemoved();

        public Element[] getChildrenAdded();

        public void redo() throws CannotRedoException;

        public void undo() throws CannotUndoException;

        private Element e;

        private int index;

        private Element[] removed;

        private Element[] added;
    }

    private class DefaultFilterBypass extends DocumentFilter.FilterBypass {

        public Document getDocument();

        public void remove(int offset, int length) throws BadLocationException;

        public void insertString(int offset, String string, AttributeSet attr) throws BadLocationException;

        public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException;
    }
}
