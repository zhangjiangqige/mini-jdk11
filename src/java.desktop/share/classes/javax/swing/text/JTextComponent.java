package javax.swing.text;

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.beans.util.Cache;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.beans.Transient;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import java.util.concurrent.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.datatransfer.*;
import java.awt.im.InputContext;
import java.awt.im.InputMethodRequests;
import java.awt.font.TextHitInfo;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.print.PrintService;
import javax.print.attribute.PrintRequestAttributeSet;
import java.text.*;
import java.text.AttributedCharacterIterator.Attribute;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.accessibility.*;
import javax.print.attribute.*;
import sun.awt.AppContext;
import sun.swing.PrintingStatus;
import sun.swing.SwingUtilities2;
import sun.swing.text.TextComponentPrintable;
import sun.swing.SwingAccessor;

@AnnotatedFor({ "guieffect", "interning" })
@JavaBean(defaultProperty = "UI")
@SwingContainer(false)
@SuppressWarnings("serial")
public abstract class JTextComponent extends JComponent implements Scrollable, Accessible {

    public JTextComponent() {
        super();
        enableEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.INPUT_METHOD_EVENT_MASK);
        caretEvent = new MutableCaretEvent(this);
        addMouseListener(caretEvent);
        addFocusListener(caretEvent);
        setEditable(true);
        setDragEnabled(false);
        setLayout(null);
        updateUI();
    }

    public TextUI getUI();

    public void setUI(TextUI ui);

    public void updateUI();

    public void addCaretListener(CaretListener listener);

    public void removeCaretListener(CaretListener listener);

    @BeanProperty(bound = false)
    public CaretListener[] getCaretListeners();

    protected void fireCaretUpdate(CaretEvent e);

    @BeanProperty(expert = true, description = "the text document model")
    public void setDocument(Document doc);

    public Document getDocument();

    public void setComponentOrientation(ComponentOrientation o);

    @BeanProperty(bound = false)
    public Action[] getActions();

    @BeanProperty(description = "desired space between the border and text area")
    public void setMargin(Insets m);

    public Insets getMargin();

    public void setNavigationFilter(NavigationFilter filter);

    public NavigationFilter getNavigationFilter();

    @Transient
    public Caret getCaret();

    @BeanProperty(expert = true, description = "the caret used to select/navigate")
    public void setCaret(Caret c);

    public Highlighter getHighlighter();

    @BeanProperty(expert = true, description = "object responsible for background highlights")
    public void setHighlighter(Highlighter h);

    @BeanProperty(description = "set of key event to action bindings to use")
    public void setKeymap(Keymap map);

    @BeanProperty(bound = false, description = "determines whether automatic drag handling is enabled")
    public void setDragEnabled(boolean b);

    private static void checkDragEnabled(boolean b);

    public boolean getDragEnabled();

    public final void setDropMode(DropMode dropMode);

    private static void checkDropMode(DropMode dropMode);

    public final DropMode getDropMode();

    static {
        SwingAccessor.setJTextComponentAccessor(new SwingAccessor.JTextComponentAccessor() {

            public TransferHandler.DropLocation dropLocationForPoint(JTextComponent textComp, Point p) {
                return textComp.dropLocationForPoint(p);
            }

            public Object setDropLocation(JTextComponent textComp, TransferHandler.DropLocation location, Object state, boolean forDrop) {
                return textComp.setDropLocation(location, state, forDrop);
            }
        });
    }

    @SuppressWarnings("deprecation")
    DropLocation dropLocationForPoint(Point p);

    Object setDropLocation(TransferHandler.DropLocation location, Object state, boolean forDrop);

    @BeanProperty(bound = false)
    public final DropLocation getDropLocation();

    void updateInputMap(Keymap oldKm, Keymap newKm);

    public Keymap getKeymap();

    public static Keymap addKeymap(String nm, Keymap parent);

    public static Keymap removeKeymap(String nm);

    public static Keymap getKeymap(String nm);

    private static HashMap<String, Keymap> getKeymapTable();

    @SuppressWarnings("serial")
    public static class KeyBinding {

        public KeyStroke key;

        public String actionName;

        public KeyBinding(KeyStroke key, String actionName) {
            this.key = key;
            this.actionName = actionName;
        }
    }

    public static void loadKeymap(Keymap map, KeyBinding[] bindings, Action[] actions);

    public Color getCaretColor();

    @BeanProperty(preferred = true, description = "the color used to render the caret")
    public void setCaretColor(Color c);

    public Color getSelectionColor();

    @BeanProperty(preferred = true, description = "color used to render selection background")
    public void setSelectionColor(Color c);

    public Color getSelectedTextColor();

    @BeanProperty(preferred = true, description = "color used to render selected text")
    public void setSelectedTextColor(Color c);

    public Color getDisabledTextColor();

    @BeanProperty(preferred = true, description = "color used to render disabled text")
    public void setDisabledTextColor(Color c);

    public void replaceSelection(String content);

    public String getText(int offs, int len) throws BadLocationException;

    @Deprecated(since = "9")
    public Rectangle modelToView(int pos) throws BadLocationException;

    public Rectangle2D modelToView2D(int pos) throws BadLocationException;

    @Deprecated(since = "9")
    public int viewToModel(Point pt);

    public int viewToModel2D(Point2D pt);

    public void cut();

    public void copy();

    public void paste();

    private void invokeAction(String name, Action altAction);

    private void installDefaultTransferHandlerIfNecessary();

    public void moveCaretPosition(int pos);

    @Interned
    public static final String FOCUS_ACCELERATOR_KEY = "focusAcceleratorKey";

    @BeanProperty(description = "accelerator character used to grab focus")
    public void setFocusAccelerator(char aKey);

    public char getFocusAccelerator();

    public void read(Reader in, Object desc) throws IOException;

    public void write(Writer out) throws IOException;

    public void removeNotify();

    @BeanProperty(bound = false, description = "the caret position")
    public void setCaretPosition(int position);

    @Transient
    public int getCaretPosition();

    @SafeEffect
    @BeanProperty(bound = false, description = "the text of this component")
    public void setText(String t);

    public String getText();

    @BeanProperty(bound = false)
    public String getSelectedText();

    public boolean isEditable();

    @BeanProperty(description = "specifies if the text can be edited")
    public void setEditable(boolean b);

    @Transient
    public int getSelectionStart();

    @BeanProperty(bound = false, description = "starting location of the selection.")
    public void setSelectionStart(int selectionStart);

    @Transient
    public int getSelectionEnd();

    @BeanProperty(bound = false, description = "ending location of the selection.")
    public void setSelectionEnd(int selectionEnd);

    public void select(int selectionStart, int selectionEnd);

    public void selectAll();

    @SuppressWarnings("deprecation")
    public String getToolTipText(MouseEvent event);

    @BeanProperty(bound = false)
    public Dimension getPreferredScrollableViewportSize();

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction);

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction);

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportWidth();

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportHeight();

    public boolean print() throws PrinterException;

    public boolean print(final MessageFormat headerFormat, final MessageFormat footerFormat) throws PrinterException;

    public boolean print(final MessageFormat headerFormat, final MessageFormat footerFormat, final boolean showPrintDialog, final PrintService service, final PrintRequestAttributeSet attributes, final boolean interactive) throws PrinterException;

    public Printable getPrintable(final MessageFormat headerFormat, final MessageFormat footerFormat);

    @BeanProperty(bound = false)
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    public class AccessibleJTextComponent extends AccessibleJComponent implements AccessibleText, CaretListener, DocumentListener, AccessibleAction, AccessibleEditableText, AccessibleExtendedText {

        int caretPos;

        Point oldLocationOnScreen;

        public AccessibleJTextComponent() {
            Document doc = JTextComponent.this.getDocument();
            if (doc != null) {
                doc.addDocumentListener(this);
            }
            JTextComponent.this.addCaretListener(this);
            caretPos = getCaretPosition();
            try {
                oldLocationOnScreen = getLocationOnScreen();
            } catch (IllegalComponentStateException iae) {
            }
            JTextComponent.this.addComponentListener(new ComponentAdapter() {

                public void componentMoved(ComponentEvent e) {
                    try {
                        Point newLocationOnScreen = getLocationOnScreen();
                        firePropertyChange(ACCESSIBLE_VISIBLE_DATA_PROPERTY, oldLocationOnScreen, newLocationOnScreen);
                        oldLocationOnScreen = newLocationOnScreen;
                    } catch (IllegalComponentStateException iae) {
                    }
                }
            });
        }

        public void caretUpdate(CaretEvent e);

        public void insertUpdate(DocumentEvent e);

        public void removeUpdate(DocumentEvent e);

        public void changedUpdate(DocumentEvent e);

        public AccessibleStateSet getAccessibleStateSet();

        public AccessibleRole getAccessibleRole();

        public AccessibleText getAccessibleText();

        public int getIndexAtPoint(Point p);

        Rectangle getRootEditorRect();

        public Rectangle getCharacterBounds(int i);

        public int getCharCount();

        public int getCaretPosition();

        public AttributeSet getCharacterAttribute(int i);

        public int getSelectionStart();

        public int getSelectionEnd();

        public String getSelectedText();

        private class IndexedSegment extends Segment {

            public int modelOffset;
        }

        public String getAtIndex(int part, int index);

        public String getAfterIndex(int part, int index);

        public String getBeforeIndex(int part, int index);

        private String getAtIndex(int part, int index, int direction);

        private Element getParagraphElement(int index);

        private IndexedSegment getParagraphElementText(int index) throws BadLocationException;

        private IndexedSegment getSegmentAt(int part, int index) throws BadLocationException;

        public AccessibleEditableText getAccessibleEditableText();

        public void setTextContents(String s);

        public void insertTextAtIndex(int index, String s);

        public String getTextRange(int startIndex, int endIndex);

        public void delete(int startIndex, int endIndex);

        public void cut(int startIndex, int endIndex);

        public void paste(int startIndex);

        public void replaceText(int startIndex, int endIndex, String s);

        public void selectText(int startIndex, int endIndex);

        public void setAttributes(int startIndex, int endIndex, AttributeSet as);

        private AccessibleTextSequence getSequenceAtIndex(int part, int index, int direction);

        private int getRunEdge(int index, int direction) throws BadLocationException;

        public AccessibleTextSequence getTextSequenceAt(int part, int index);

        public AccessibleTextSequence getTextSequenceAfter(int part, int index);

        public AccessibleTextSequence getTextSequenceBefore(int part, int index);

        public Rectangle getTextBounds(int startIndex, int endIndex);

        public AccessibleAction getAccessibleAction();

        public int getAccessibleActionCount();

        public String getAccessibleActionDescription(int i);

        public boolean doAccessibleAction(int i);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    private Document model;

    private transient Caret caret;

    private NavigationFilter navigationFilter;

    private transient Highlighter highlighter;

    private transient Keymap keymap;

    private transient MutableCaretEvent caretEvent;

    private Color caretColor;

    private Color selectionColor;

    private Color selectedTextColor;

    private Color disabledTextColor;

    private boolean editable;

    private Insets margin;

    private char focusAccelerator;

    private boolean dragEnabled;

    private DropMode dropMode = DropMode.USE_SELECTION;

    private transient DropLocation dropLocation;

    public static final class DropLocation extends TransferHandler.DropLocation {

        private final int index;

        private final Position.Bias bias;

        private DropLocation(Point p, int index, Position.Bias bias) {
            super(p);
            this.index = index;
            this.bias = bias;
        }

        public int getIndex();

        public Position.Bias getBias();

        public String toString();
    }

    private static DefaultTransferHandler defaultTransferHandler;

    private static Cache<Class<?>, Boolean> METHOD_OVERRIDDEN = new Cache<Class<?>, Boolean>(Cache.Kind.WEAK, Cache.Kind.STRONG) {

        @Override
        public Boolean create(final Class<?> type) {
            if (JTextComponent.class == type) {
                return Boolean.FALSE;
            }
            if (get(type.getSuperclass())) {
                return Boolean.TRUE;
            }
            return AccessController.doPrivileged(new PrivilegedAction<Boolean>() {

                public Boolean run() {
                    try {
                        type.getDeclaredMethod("processInputMethodEvent", InputMethodEvent.class);
                        return Boolean.TRUE;
                    } catch (NoSuchMethodException exception) {
                        return Boolean.FALSE;
                    }
                }
            });
        }
    };

    protected String paramString();

    static class DefaultTransferHandler extends TransferHandler implements UIResource {

        public void exportToClipboard(JComponent comp, Clipboard clipboard, int action) throws IllegalStateException;

        public boolean importData(JComponent comp, Transferable t);

        public boolean canImport(JComponent comp, DataFlavor[] transferFlavors);

        public int getSourceActions(JComponent c);

        private DataFlavor getFlavor(DataFlavor[] flavors);
    }

    static final JTextComponent getFocusedComponent();

    @SuppressWarnings("deprecation")
    private int getCurrentEventModifiers();

    private static final Object KEYMAP_TABLE = new StringBuilder("JTextComponent_KeymapTable");

    private transient InputMethodRequests inputMethodRequestsHandler;

    private SimpleAttributeSet composedTextAttribute;

    private String composedTextContent;

    private Position composedTextStart;

    private Position composedTextEnd;

    private Position latestCommittedTextStart;

    private Position latestCommittedTextEnd;

    private ComposedTextCaret composedTextCaret;

    private transient Caret originalCaret;

    private boolean checkedInputOverride;

    private boolean needToSendKeyTypedEvent;

    static class DefaultKeymap implements Keymap {

        DefaultKeymap(String nm, Keymap parent) {
            this.nm = nm;
            this.parent = parent;
            bindings = new Hashtable<KeyStroke, Action>();
        }

        public Action getDefaultAction();

        public void setDefaultAction(Action a);

        public String getName();

        public Action getAction(KeyStroke key);

        public KeyStroke[] getBoundKeyStrokes();

        public Action[] getBoundActions();

        public KeyStroke[] getKeyStrokesForAction(Action a);

        public boolean isLocallyDefined(KeyStroke key);

        public void addActionForKeyStroke(KeyStroke key, Action a);

        public void removeKeyStrokeBinding(KeyStroke key);

        public void removeBindings();

        public Keymap getResolveParent();

        public void setResolveParent(Keymap parent);

        public String toString();

        String nm;

        Keymap parent;

        Hashtable<KeyStroke, Action> bindings;

        Action defaultAction;
    }

    static class KeymapWrapper extends InputMap {

        static final Object DefaultActionKey = new Object();

        private Keymap keymap;

        KeymapWrapper(Keymap keymap) {
            this.keymap = keymap;
        }

        public KeyStroke[] keys();

        public int size();

        public Object get(KeyStroke keyStroke);
    }

    static class KeymapActionMap extends ActionMap {

        private Keymap keymap;

        KeymapActionMap(Keymap keymap) {
            this.keymap = keymap;
        }

        public Object[] keys();

        public int size();

        public Action get(Object key);
    }

    private static final Object FOCUSED_COMPONENT = new StringBuilder("JTextComponent_FocusedComponent");

    @Interned
    public static final String DEFAULT_KEYMAP = "default";

    static class MutableCaretEvent extends CaretEvent implements ChangeListener, FocusListener, MouseListener {

        MutableCaretEvent(JTextComponent c) {
            super(c);
        }

        final void fire();

        public final String toString();

        public final int getDot();

        public final int getMark();

        public final void stateChanged(ChangeEvent e);

        public void focusGained(FocusEvent fe);

        public void focusLost(FocusEvent fe);

        public final void mousePressed(MouseEvent e);

        public final void mouseReleased(MouseEvent e);

        public final void mouseClicked(MouseEvent e);

        public final void mouseEntered(MouseEvent e);

        public final void mouseExited(MouseEvent e);

        private boolean dragActive;

        private int dot;

        private int mark;
    }

    @SuppressWarnings("fallthrough")
    protected void processInputMethodEvent(InputMethodEvent e);

    @BeanProperty(bound = false)
    public InputMethodRequests getInputMethodRequests();

    public void addInputMethodListener(InputMethodListener l);

    class InputMethodRequestsHandler implements InputMethodRequests, DocumentListener {

        public AttributedCharacterIterator cancelLatestCommittedText(Attribute[] attributes);

        public AttributedCharacterIterator getCommittedText(int beginIndex, int endIndex, Attribute[] attributes);

        public int getCommittedTextLength();

        public int getInsertPositionOffset();

        public TextHitInfo getLocationOffset(int x, int y);

        public Rectangle getTextLocation(TextHitInfo offset);

        public AttributedCharacterIterator getSelectedText(Attribute[] attributes);

        public void changedUpdate(DocumentEvent e);

        public void insertUpdate(DocumentEvent e);

        public void removeUpdate(DocumentEvent e);
    }

    private void replaceInputMethodText(InputMethodEvent e);

    private void createComposedTextAttribute(int composedIndex, AttributedCharacterIterator text);

    protected boolean saveComposedText(int pos);

    protected void restoreComposedText();

    private void mapCommittedTextToAction(String committedText);

    private void setInputMethodCaretPosition(InputMethodEvent e);

    private void exchangeCaret(Caret oldCaret, Caret newCaret);

    private boolean shouldSynthensizeKeyEvents();

    boolean composedTextExists();

    class ComposedTextCaret extends DefaultCaret implements Serializable {

        Color bg;

        public void install(JTextComponent c);

        public void paint(Graphics g);

        protected void positionCaret(MouseEvent me);
    }

    private class DoSetCaretPosition implements Runnable {

        JTextComponent host;

        Position newPos;

        DoSetCaretPosition(JTextComponent host, Position newPos) {
            this.host = host;
            this.newPos = newPos;
        }

        public void run();
    }
}
