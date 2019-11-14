package javax.swing.text.html;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.awt.AppContext;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.TextUI;
import java.util.*;
import javax.accessibility.*;
import java.lang.ref.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.swing.text.html.parser.ParserDelegator;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class HTMLEditorKit extends StyledEditorKit implements Accessible {

    private JEditorPane theEditor;

    public HTMLEditorKit() {
    }

    public String getContentType();

    public ViewFactory getViewFactory();

    public Document createDefaultDocument();

    private Parser ensureParser(HTMLDocument doc) throws IOException;

    public void read(Reader in, Document doc, int pos) throws IOException, BadLocationException;

    public void insertHTML(HTMLDocument doc, int offset, String html, int popDepth, int pushDepth, HTML.Tag insertTag) throws BadLocationException, IOException;

    public void write(Writer out, Document doc, int pos, int len) throws IOException, BadLocationException;

    public void install(JEditorPane c);

    public void deinstall(JEditorPane c);

    @Interned
    public static final String DEFAULT_CSS = "default.css";

    public void setStyleSheet(StyleSheet s);

    public StyleSheet getStyleSheet();

    static InputStream getResourceAsStream(final String name);

    public Action[] getActions();

    protected void createInputAttributes(Element element, MutableAttributeSet set);

    public MutableAttributeSet getInputAttributes();

    public void setDefaultCursor(Cursor cursor);

    public Cursor getDefaultCursor();

    public void setLinkCursor(Cursor cursor);

    public Cursor getLinkCursor();

    public boolean isAutoFormSubmission();

    public void setAutoFormSubmission(boolean isAuto);

    public Object clone();

    protected Parser getParser();

    private AccessibleContext accessibleContext;

    public AccessibleContext getAccessibleContext();

    private static final Cursor MoveCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    private static final Cursor DefaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

    private static final ViewFactory defaultFactory = new HTMLFactory();

    MutableAttributeSet input;

    private static final Object DEFAULT_STYLES_KEY = new Object();

    private LinkController linkHandler = new LinkController();

    private static Parser defaultParser = null;

    private Cursor defaultCursor = DefaultCursor;

    private Cursor linkCursor = MoveCursor;

    private boolean isAutoFormSubmission = true;

    @SuppressWarnings("serial")
    public static class LinkController extends MouseAdapter implements MouseMotionListener, Serializable {

        private Element curElem = null;

        private boolean curElemImage = false;

        private String href = null;

        private transient Position.Bias[] bias = new Position.Bias[1];

        private int curOffset;

        @SuppressWarnings("deprecation")
        public void mouseClicked(MouseEvent e);

        public void mouseDragged(MouseEvent e);

        @SuppressWarnings("deprecation")
        public void mouseMoved(MouseEvent e);

        @SuppressWarnings("deprecation")
        private String getMapHREF(JEditorPane html, HTMLDocument hdoc, Element elem, AttributeSet attr, int offset, int x, int y);

        @SuppressWarnings("deprecation")
        private boolean doesElementContainLocation(JEditorPane editor, Element e, int offset, int x, int y);

        protected void activateLink(int pos, JEditorPane editor);

        void activateLink(int pos, JEditorPane html, MouseEvent mouseEvent);

        HyperlinkEvent createHyperlinkEvent(JEditorPane html, HTMLDocument hdoc, String href, AttributeSet anchor, Element element, MouseEvent mouseEvent);

        void fireEvents(JEditorPane editor, HTMLDocument doc, String href, Element lastElem, MouseEvent mouseEvent);
    }

    public abstract static class Parser {

        public abstract void parse(Reader r, ParserCallback cb, boolean ignoreCharSet) throws IOException;
    }

    public static class ParserCallback {

        public static final Object IMPLIED = "_implied_";

        public void flush() throws BadLocationException;

        public void handleText(char[] data, int pos);

        public void handleComment(char[] data, int pos);

        public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos);

        public void handleEndTag(HTML.Tag t, int pos);

        public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos);

        public void handleError(String errorMsg, int pos);

        public void handleEndOfLineString(String eol);
    }

    public static class HTMLFactory implements ViewFactory {

        public View create(Element elem);

        static class BodyBlockView extends BlockView implements ComponentListener {

            public BodyBlockView(Element elem) {
                super(elem, View.Y_AXIS);
            }

            protected SizeRequirements calculateMajorAxisRequirements(int axis, SizeRequirements r);

            protected void layoutMinorAxis(int targetSpan, int axis, int[] offsets, int[] spans);

            public void setParent(View parent);

            public void componentResized(ComponentEvent e);

            public void componentHidden(ComponentEvent e);

            public void componentMoved(ComponentEvent e);

            public void componentShown(ComponentEvent e);

            private Reference<JViewport> cachedViewPort = null;

            private boolean isListening = false;

            private int viewVisibleWidth = Integer.MAX_VALUE;

            private int componentVisibleWidth = Integer.MAX_VALUE;
        }
    }

    @Interned
    public static final String BOLD_ACTION = "html-bold-action";

    @Interned
    public static final String ITALIC_ACTION = "html-italic-action";

    @Interned
    public static final String PARA_INDENT_LEFT = "html-para-indent-left";

    @Interned
    public static final String PARA_INDENT_RIGHT = "html-para-indent-right";

    @Interned
    public static final String FONT_CHANGE_BIGGER = "html-font-bigger";

    @Interned
    public static final String FONT_CHANGE_SMALLER = "html-font-smaller";

    @Interned
    public static final String COLOR_ACTION = "html-color-action";

    @Interned
    public static final String LOGICAL_STYLE_ACTION = "html-logical-style-action";

    @Interned
    public static final String IMG_ALIGN_TOP = "html-image-align-top";

    @Interned
    public static final String IMG_ALIGN_MIDDLE = "html-image-align-middle";

    @Interned
    public static final String IMG_ALIGN_BOTTOM = "html-image-align-bottom";

    @Interned
    public static final String IMG_BORDER = "html-image-border";

    private static final String INSERT_TABLE_HTML = "<table border=1><tr><td></td></tr></table>";

    private static final String INSERT_UL_HTML = "<ul><li></li></ul>";

    private static final String INSERT_OL_HTML = "<ol><li></li></ol>";

    private static final String INSERT_HR_HTML = "<hr>";

    private static final String INSERT_PRE_HTML = "<pre></pre>";

    private static final NavigateLinkAction nextLinkAction = new NavigateLinkAction("next-link-action");

    private static final NavigateLinkAction previousLinkAction = new NavigateLinkAction("previous-link-action");

    private static final ActivateLinkAction activateLinkAction = new ActivateLinkAction("activate-link-action");

    private static final Action[] defaultActions = { new InsertHTMLTextAction("InsertTable", INSERT_TABLE_HTML, HTML.Tag.BODY, HTML.Tag.TABLE), new InsertHTMLTextAction("InsertTableRow", INSERT_TABLE_HTML, HTML.Tag.TABLE, HTML.Tag.TR, HTML.Tag.BODY, HTML.Tag.TABLE), new InsertHTMLTextAction("InsertTableDataCell", INSERT_TABLE_HTML, HTML.Tag.TR, HTML.Tag.TD, HTML.Tag.BODY, HTML.Tag.TABLE), new InsertHTMLTextAction("InsertUnorderedList", INSERT_UL_HTML, HTML.Tag.BODY, HTML.Tag.UL), new InsertHTMLTextAction("InsertUnorderedListItem", INSERT_UL_HTML, HTML.Tag.UL, HTML.Tag.LI, HTML.Tag.BODY, HTML.Tag.UL), new InsertHTMLTextAction("InsertOrderedList", INSERT_OL_HTML, HTML.Tag.BODY, HTML.Tag.OL), new InsertHTMLTextAction("InsertOrderedListItem", INSERT_OL_HTML, HTML.Tag.OL, HTML.Tag.LI, HTML.Tag.BODY, HTML.Tag.OL), new InsertHRAction(), new InsertHTMLTextAction("InsertPre", INSERT_PRE_HTML, HTML.Tag.BODY, HTML.Tag.PRE), nextLinkAction, previousLinkAction, activateLinkAction, new BeginAction(beginAction, false), new BeginAction(selectionBeginAction, true) };

    private boolean foundLink = false;

    private int prevHypertextOffset = -1;

    private Object linkNavigationTag;

    @SuppressWarnings("serial")
    public abstract static class HTMLTextAction extends StyledTextAction {

        public HTMLTextAction(String name) {
            super(name);
        }

        protected HTMLDocument getHTMLDocument(JEditorPane e);

        protected HTMLEditorKit getHTMLEditorKit(JEditorPane e);

        protected Element[] getElementsAt(HTMLDocument doc, int offset);

        private Element[] getElementsAt(Element parent, int offset, int depth);

        protected int elementCountToTag(HTMLDocument doc, int offset, HTML.Tag tag);

        protected Element findElementMatchingTag(HTMLDocument doc, int offset, HTML.Tag tag);
    }

    @SuppressWarnings("serial")
    public static class InsertHTMLTextAction extends HTMLTextAction {

        public InsertHTMLTextAction(String name, String html, HTML.Tag parentTag, HTML.Tag addTag) {
            this(name, html, parentTag, addTag, null, null);
        }

        public InsertHTMLTextAction(String name, String html, HTML.Tag parentTag, HTML.Tag addTag, HTML.Tag alternateParentTag, HTML.Tag alternateAddTag) {
            this(name, html, parentTag, addTag, alternateParentTag, alternateAddTag, true);
        }

        InsertHTMLTextAction(String name, String html, HTML.Tag parentTag, HTML.Tag addTag, HTML.Tag alternateParentTag, HTML.Tag alternateAddTag, boolean adjustSelection) {
            super(name);
            this.html = html;
            this.parentTag = parentTag;
            this.addTag = addTag;
            this.alternateParentTag = alternateParentTag;
            this.alternateAddTag = alternateAddTag;
            this.adjustSelection = adjustSelection;
        }

        protected void insertHTML(JEditorPane editor, HTMLDocument doc, int offset, String html, int popDepth, int pushDepth, HTML.Tag addTag);

        protected void insertAtBoundary(JEditorPane editor, HTMLDocument doc, int offset, Element insertElement, String html, HTML.Tag parentTag, HTML.Tag addTag);

        @Deprecated
        protected void insertAtBoundry(JEditorPane editor, HTMLDocument doc, int offset, Element insertElement, String html, HTML.Tag parentTag, HTML.Tag addTag);

        boolean insertIntoTag(JEditorPane editor, HTMLDocument doc, int offset, HTML.Tag tag, HTML.Tag addTag);

        void adjustSelection(JEditorPane pane, HTMLDocument doc, int startOffset, int oldLength);

        public void actionPerformed(ActionEvent ae);

        protected String html;

        protected HTML.Tag parentTag;

        protected HTML.Tag addTag;

        protected HTML.Tag alternateParentTag;

        protected HTML.Tag alternateAddTag;

        boolean adjustSelection;
    }

    @SuppressWarnings("serial")
    static class InsertHRAction extends InsertHTMLTextAction {

        InsertHRAction() {
            super("InsertHR", "<hr>", null, HTML.Tag.IMPLIED, null, null, false);
        }

        public void actionPerformed(ActionEvent ae);
    }

    private static Object getAttrValue(AttributeSet attr, HTML.Attribute key);

    @SuppressWarnings("serial")
    static class NavigateLinkAction extends TextAction implements CaretListener {

        private static final FocusHighlightPainter focusPainter = new FocusHighlightPainter(null);

        private final boolean focusBack;

        public NavigateLinkAction(String actionName) {
            super(actionName);
            focusBack = "previous-link-action".equals(actionName);
        }

        public void caretUpdate(CaretEvent e);

        public void actionPerformed(ActionEvent e);

        private void moveCaretPosition(JTextComponent comp, HTMLEditorKit kit, int mark, int dot);

        private HTMLEditorKit getHTMLEditorKit(JTextComponent comp);

        static class FocusHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

            FocusHighlightPainter(Color color) {
                super(color);
            }

            public Shape paintLayer(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c, View view);
        }
    }

    @SuppressWarnings("serial")
    static class ActivateLinkAction extends TextAction {

        public ActivateLinkAction(String actionName) {
            super(actionName);
        }

        private void activateLink(String href, HTMLDocument doc, JEditorPane editor, int offset);

        private void doObjectAction(JEditorPane editor, Element elem);

        private View getRootView(JEditorPane editor);

        private View getView(JEditorPane editor, Element elem);

        private View getView(View parent, Element elem, int start);

        private Object lock(JEditorPane editor);

        private void unlock(Object key);

        public void actionPerformed(ActionEvent e);
    }

    private static int getBodyElementStart(JTextComponent comp);

    @SuppressWarnings("serial")
    static class BeginAction extends TextAction {

        BeginAction(String nm, boolean select) {
            super(nm);
            this.select = select;
        }

        public void actionPerformed(ActionEvent e);

        private boolean select;
    }
}
