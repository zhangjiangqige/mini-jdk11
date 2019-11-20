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

    public boolean getDragEnabled();

    public final void setDropMode(DropMode dropMode);

    public final DropMode getDropMode();

    @BeanProperty(bound = false)
    public final DropLocation getDropLocation();

    public Keymap getKeymap();

    public static Keymap addKeymap(String nm, Keymap parent);

    public static Keymap removeKeymap(String nm);

    public static Keymap getKeymap(String nm);

    @SuppressWarnings("serial")
    public static class KeyBinding {

        public KeyStroke key;

        public String actionName;

        public KeyBinding(KeyStroke key, String actionName) {
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

    @Deprecated()
    public Rectangle modelToView(int pos) throws BadLocationException;

    public Rectangle2D modelToView2D(int pos) throws BadLocationException;

    @Deprecated()
    public int viewToModel(Point pt);

    public int viewToModel2D(Point2D pt);

    public void cut();

    public void copy();

    public void paste();

    public void moveCaretPosition(int pos);

    @Interned
    public static final String FOCUS_ACCELERATOR_KEY;

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

        public AccessibleJTextComponent() {
        }

        public void caretUpdate(CaretEvent e);

        public void insertUpdate(DocumentEvent e);

        public void removeUpdate(DocumentEvent e);

        public void changedUpdate(DocumentEvent e);

        public AccessibleStateSet getAccessibleStateSet();

        public AccessibleRole getAccessibleRole();

        public AccessibleText getAccessibleText();

        public int getIndexAtPoint(Point p);

        public Rectangle getCharacterBounds(int i);

        public int getCharCount();

        public int getCaretPosition();

        public AttributeSet getCharacterAttribute(int i);

        public int getSelectionStart();

        public int getSelectionEnd();

        public String getSelectedText();

        public String getAtIndex(int part, int index);

        public String getAfterIndex(int part, int index);

        public String getBeforeIndex(int part, int index);

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

        public AccessibleTextSequence getTextSequenceAt(int part, int index);

        public AccessibleTextSequence getTextSequenceAfter(int part, int index);

        public AccessibleTextSequence getTextSequenceBefore(int part, int index);

        public Rectangle getTextBounds(int startIndex, int endIndex);

        public AccessibleAction getAccessibleAction();

        public int getAccessibleActionCount();

        public String getAccessibleActionDescription(int i);

        public boolean doAccessibleAction(int i);
    }

    public static final class DropLocation extends TransferHandler.DropLocation {

        public int getIndex();

        public Position.Bias getBias();

        public String toString();
    }

    protected String paramString();

    @Interned
    public static final String DEFAULT_KEYMAP;

    @SuppressWarnings("fallthrough")
    protected void processInputMethodEvent(InputMethodEvent e);

    @BeanProperty(bound = false)
    public InputMethodRequests getInputMethodRequests();

    public void addInputMethodListener(InputMethodListener l);

    protected boolean saveComposedText(int pos);

    protected void restoreComposedText();
}
