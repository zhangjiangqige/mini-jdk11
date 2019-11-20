package javax.swing.text;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.awt.SunToolkit;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.*;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class DefaultEditorKit extends EditorKit {

    public DefaultEditorKit() {
    }

    public String getContentType();

    public ViewFactory getViewFactory();

    public Action[] getActions();

    public Caret createCaret();

    public Document createDefaultDocument();

    public void read(InputStream in, Document doc, int pos) throws IOException, BadLocationException;

    public void write(OutputStream out, Document doc, int pos, int len) throws IOException, BadLocationException;

    public void read(Reader in, Document doc, int pos) throws IOException, BadLocationException;

    public void write(Writer out, Document doc, int pos, int len) throws IOException, BadLocationException;

    @Interned
    public static final String EndOfLineStringProperty;

    @Interned
    public static final String insertContentAction;

    @Interned
    public static final String insertBreakAction;

    @Interned
    public static final String insertTabAction;

    @Interned
    public static final String deletePrevCharAction;

    @Interned
    public static final String deleteNextCharAction;

    @Interned
    public static final String deleteNextWordAction;

    @Interned
    public static final String deletePrevWordAction;

    @Interned
    public static final String readOnlyAction;

    @Interned
    public static final String writableAction;

    @Interned
    public static final String cutAction;

    @Interned
    public static final String copyAction;

    @Interned
    public static final String pasteAction;

    @Interned
    public static final String beepAction;

    @Interned
    public static final String pageUpAction;

    @Interned
    public static final String pageDownAction;

    @Interned
    public static final String forwardAction;

    @Interned
    public static final String backwardAction;

    @Interned
    public static final String selectionForwardAction;

    @Interned
    public static final String selectionBackwardAction;

    @Interned
    public static final String upAction;

    @Interned
    public static final String downAction;

    @Interned
    public static final String selectionUpAction;

    @Interned
    public static final String selectionDownAction;

    @Interned
    public static final String beginWordAction;

    @Interned
    public static final String endWordAction;

    @Interned
    public static final String selectionBeginWordAction;

    @Interned
    public static final String selectionEndWordAction;

    @Interned
    public static final String previousWordAction;

    @Interned
    public static final String nextWordAction;

    @Interned
    public static final String selectionPreviousWordAction;

    @Interned
    public static final String selectionNextWordAction;

    @Interned
    public static final String beginLineAction;

    @Interned
    public static final String endLineAction;

    @Interned
    public static final String selectionBeginLineAction;

    @Interned
    public static final String selectionEndLineAction;

    @Interned
    public static final String beginParagraphAction;

    @Interned
    public static final String endParagraphAction;

    @Interned
    public static final String selectionBeginParagraphAction;

    @Interned
    public static final String selectionEndParagraphAction;

    @Interned
    public static final String beginAction;

    @Interned
    public static final String endAction;

    @Interned
    public static final String selectionBeginAction;

    @Interned
    public static final String selectionEndAction;

    @Interned
    public static final String selectWordAction;

    @Interned
    public static final String selectLineAction;

    @Interned
    public static final String selectParagraphAction;

    @Interned
    public static final String selectAllAction;

    @Interned
    public static final String defaultKeyTypedAction;

    @SuppressWarnings("serial")
    public static class DefaultKeyTypedAction extends TextAction {

        public DefaultKeyTypedAction() {
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class InsertContentAction extends TextAction {

        public InsertContentAction() {
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class InsertBreakAction extends TextAction {

        public InsertBreakAction() {
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class InsertTabAction extends TextAction {

        public InsertTabAction() {
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class CutAction extends TextAction {

        public CutAction() {
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class CopyAction extends TextAction {

        public CopyAction() {
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class PasteAction extends TextAction {

        public PasteAction() {
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class BeepAction extends TextAction {

        public BeepAction() {
        }

        public void actionPerformed(ActionEvent e);
    }
}
