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

    MutableAttributeSet getInputAttributes();

    public void read(Reader in, Document doc, int pos) throws IOException, BadLocationException;

    public void write(Writer out, Document doc, int pos, int len) throws IOException, BadLocationException;

    @Interned
    public static final String EndOfLineStringProperty = "__EndOfLine__";

    @Interned
    public static final String insertContentAction = "insert-content";

    @Interned
    public static final String insertBreakAction = "insert-break";

    @Interned
    public static final String insertTabAction = "insert-tab";

    @Interned
    public static final String deletePrevCharAction = "delete-previous";

    @Interned
    public static final String deleteNextCharAction = "delete-next";

    @Interned
    public static final String deleteNextWordAction = "delete-next-word";

    @Interned
    public static final String deletePrevWordAction = "delete-previous-word";

    @Interned
    public static final String readOnlyAction = "set-read-only";

    @Interned
    public static final String writableAction = "set-writable";

    @Interned
    public static final String cutAction = "cut-to-clipboard";

    @Interned
    public static final String copyAction = "copy-to-clipboard";

    @Interned
    public static final String pasteAction = "paste-from-clipboard";

    @Interned
    public static final String beepAction = "beep";

    @Interned
    public static final String pageUpAction = "page-up";

    @Interned
    public static final String pageDownAction = "page-down";

    static final String selectionPageUpAction = "selection-page-up";

    static final String selectionPageDownAction = "selection-page-down";

    static final String selectionPageLeftAction = "selection-page-left";

    static final String selectionPageRightAction = "selection-page-right";

    @Interned
    public static final String forwardAction = "caret-forward";

    @Interned
    public static final String backwardAction = "caret-backward";

    @Interned
    public static final String selectionForwardAction = "selection-forward";

    @Interned
    public static final String selectionBackwardAction = "selection-backward";

    @Interned
    public static final String upAction = "caret-up";

    @Interned
    public static final String downAction = "caret-down";

    @Interned
    public static final String selectionUpAction = "selection-up";

    @Interned
    public static final String selectionDownAction = "selection-down";

    @Interned
    public static final String beginWordAction = "caret-begin-word";

    @Interned
    public static final String endWordAction = "caret-end-word";

    @Interned
    public static final String selectionBeginWordAction = "selection-begin-word";

    @Interned
    public static final String selectionEndWordAction = "selection-end-word";

    @Interned
    public static final String previousWordAction = "caret-previous-word";

    @Interned
    public static final String nextWordAction = "caret-next-word";

    @Interned
    public static final String selectionPreviousWordAction = "selection-previous-word";

    @Interned
    public static final String selectionNextWordAction = "selection-next-word";

    @Interned
    public static final String beginLineAction = "caret-begin-line";

    @Interned
    public static final String endLineAction = "caret-end-line";

    @Interned
    public static final String selectionBeginLineAction = "selection-begin-line";

    @Interned
    public static final String selectionEndLineAction = "selection-end-line";

    @Interned
    public static final String beginParagraphAction = "caret-begin-paragraph";

    @Interned
    public static final String endParagraphAction = "caret-end-paragraph";

    @Interned
    public static final String selectionBeginParagraphAction = "selection-begin-paragraph";

    @Interned
    public static final String selectionEndParagraphAction = "selection-end-paragraph";

    @Interned
    public static final String beginAction = "caret-begin";

    @Interned
    public static final String endAction = "caret-end";

    @Interned
    public static final String selectionBeginAction = "selection-begin";

    @Interned
    public static final String selectionEndAction = "selection-end";

    @Interned
    public static final String selectWordAction = "select-word";

    @Interned
    public static final String selectLineAction = "select-line";

    @Interned
    public static final String selectParagraphAction = "select-paragraph";

    @Interned
    public static final String selectAllAction = "select-all";

    static final String unselectAction = "unselect";

    static final String toggleComponentOrientationAction = "toggle-componentOrientation";

    @Interned
    public static final String defaultKeyTypedAction = "default-typed";

    private static final Action[] defaultActions = { new InsertContentAction(), new DeletePrevCharAction(), new DeleteNextCharAction(), new ReadOnlyAction(), new DeleteWordAction(deletePrevWordAction), new DeleteWordAction(deleteNextWordAction), new WritableAction(), new CutAction(), new CopyAction(), new PasteAction(), new VerticalPageAction(pageUpAction, -1, false), new VerticalPageAction(pageDownAction, 1, false), new VerticalPageAction(selectionPageUpAction, -1, true), new VerticalPageAction(selectionPageDownAction, 1, true), new PageAction(selectionPageLeftAction, true, true), new PageAction(selectionPageRightAction, false, true), new InsertBreakAction(), new BeepAction(), new NextVisualPositionAction(forwardAction, false, SwingConstants.EAST), new NextVisualPositionAction(backwardAction, false, SwingConstants.WEST), new NextVisualPositionAction(selectionForwardAction, true, SwingConstants.EAST), new NextVisualPositionAction(selectionBackwardAction, true, SwingConstants.WEST), new NextVisualPositionAction(upAction, false, SwingConstants.NORTH), new NextVisualPositionAction(downAction, false, SwingConstants.SOUTH), new NextVisualPositionAction(selectionUpAction, true, SwingConstants.NORTH), new NextVisualPositionAction(selectionDownAction, true, SwingConstants.SOUTH), new BeginWordAction(beginWordAction, false), new EndWordAction(endWordAction, false), new BeginWordAction(selectionBeginWordAction, true), new EndWordAction(selectionEndWordAction, true), new PreviousWordAction(previousWordAction, false), new NextWordAction(nextWordAction, false), new PreviousWordAction(selectionPreviousWordAction, true), new NextWordAction(selectionNextWordAction, true), new BeginLineAction(beginLineAction, false), new EndLineAction(endLineAction, false), new BeginLineAction(selectionBeginLineAction, true), new EndLineAction(selectionEndLineAction, true), new BeginParagraphAction(beginParagraphAction, false), new EndParagraphAction(endParagraphAction, false), new BeginParagraphAction(selectionBeginParagraphAction, true), new EndParagraphAction(selectionEndParagraphAction, true), new BeginAction(beginAction, false), new EndAction(endAction, false), new BeginAction(selectionBeginAction, true), new EndAction(selectionEndAction, true), new DefaultKeyTypedAction(), new InsertTabAction(), new SelectWordAction(), new SelectLineAction(), new SelectParagraphAction(), new SelectAllAction(), new UnselectAction(), new ToggleComponentOrientationAction(), new DumpModelAction() };

    @SuppressWarnings("serial")
    public static class DefaultKeyTypedAction extends TextAction {

        public DefaultKeyTypedAction() {
            super(defaultKeyTypedAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class InsertContentAction extends TextAction {

        public InsertContentAction() {
            super(insertContentAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class InsertBreakAction extends TextAction {

        public InsertBreakAction() {
            super(insertBreakAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class InsertTabAction extends TextAction {

        public InsertTabAction() {
            super(insertTabAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class DeletePrevCharAction extends TextAction {

        DeletePrevCharAction() {
            super(deletePrevCharAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class DeleteNextCharAction extends TextAction {

        DeleteNextCharAction() {
            super(deleteNextCharAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class DeleteWordAction extends TextAction {

        DeleteWordAction(String name) {
            super(name);
            assert (name == deletePrevWordAction) || (name == deleteNextWordAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class ReadOnlyAction extends TextAction {

        ReadOnlyAction() {
            super(readOnlyAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class WritableAction extends TextAction {

        WritableAction() {
            super(writableAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class CutAction extends TextAction {

        public CutAction() {
            super(cutAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class CopyAction extends TextAction {

        public CopyAction() {
            super(copyAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class PasteAction extends TextAction {

        public PasteAction() {
            super(pasteAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    public static class BeepAction extends TextAction {

        public BeepAction() {
            super(beepAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class VerticalPageAction extends TextAction {

        public VerticalPageAction(String nm, int direction, boolean select) {
            super(nm);
            this.select = select;
            this.direction = direction;
        }

        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent e);

        private int constrainY(JTextComponent target, int y, int vis);

        private int constrainOffset(JTextComponent text, int offset);

        @SuppressWarnings("deprecation")
        private int getAdjustedY(JTextComponent text, Rectangle visible, int index);

        private boolean select;

        private int direction;
    }

    @SuppressWarnings("serial")
    static class PageAction extends TextAction {

        public PageAction(String nm, boolean left, boolean select) {
            super(nm);
            this.select = select;
            this.left = left;
        }

        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent e);

        private boolean select;

        private boolean left;
    }

    @SuppressWarnings("serial")
    static class DumpModelAction extends TextAction {

        DumpModelAction() {
            super("dump-model");
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class NextVisualPositionAction extends TextAction {

        NextVisualPositionAction(String nm, boolean select, int direction) {
            super(nm);
            this.select = select;
            this.direction = direction;
        }

        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent e);

        private boolean select;

        private int direction;
    }

    @SuppressWarnings("serial")
    static class BeginWordAction extends TextAction {

        BeginWordAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class EndWordAction extends TextAction {

        EndWordAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class PreviousWordAction extends TextAction {

        PreviousWordAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class NextWordAction extends TextAction {

        NextWordAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class BeginLineAction extends TextAction {

        BeginLineAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class EndLineAction extends TextAction {

        EndLineAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class BeginParagraphAction extends TextAction {

        BeginParagraphAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class EndParagraphAction extends TextAction {

        EndParagraphAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class BeginAction extends TextAction {

        BeginAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class EndAction extends TextAction {

        EndAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }

    @SuppressWarnings("serial")
    static class SelectWordAction extends TextAction {

        SelectWordAction() {
            super(selectWordAction);
            start = new BeginWordAction("pigdog", false);
            end = new EndWordAction("pigdog", true);
        }

        public void actionPerformed(ActionEvent e);

        private Action start;

        private Action end;
    }

    @SuppressWarnings("serial")
    static class SelectLineAction extends TextAction {

        SelectLineAction() {
            super(selectLineAction);
            start = new BeginLineAction("pigdog", false);
            end = new EndLineAction("pigdog", true);
        }

        public void actionPerformed(ActionEvent e);

        private Action start;

        private Action end;
    }

    @SuppressWarnings("serial")
    static class SelectParagraphAction extends TextAction {

        SelectParagraphAction() {
            super(selectParagraphAction);
            start = new BeginParagraphAction("pigdog", false);
            end = new EndParagraphAction("pigdog", true);
        }

        public void actionPerformed(ActionEvent e);

        private Action start;

        private Action end;
    }

    @SuppressWarnings("serial")
    static class SelectAllAction extends TextAction {

        SelectAllAction() {
            super(selectAllAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class UnselectAction extends TextAction {

        UnselectAction() {
            super(unselectAction);
        }

        public void actionPerformed(ActionEvent e);
    }

    @SuppressWarnings("serial")
    static class ToggleComponentOrientationAction extends TextAction {

        ToggleComponentOrientationAction() {
            super(toggleComponentOrientationAction);
        }

        public void actionPerformed(ActionEvent e);
    }
}
