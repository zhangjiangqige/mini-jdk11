package javax.swing.text.html;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.font.TextAttribute;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import sun.swing.SwingUtilities2;
import static sun.swing.SwingUtilities2.IMPLIED_CR;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class HTMLDocument extends DefaultStyledDocument {

    public HTMLDocument() {
        this(new GapContent(BUFFER_SIZE_DEFAULT), new StyleSheet());
    }

    public HTMLDocument(StyleSheet styles) {
        this(new GapContent(BUFFER_SIZE_DEFAULT), styles);
    }

    public HTMLDocument(Content c, StyleSheet styles) {
        super(c, styles);
    }

    public HTMLEditorKit.ParserCallback getReader(int pos);

    public HTMLEditorKit.ParserCallback getReader(int pos, int popDepth, int pushDepth, HTML.Tag insertTag);

    HTMLEditorKit.ParserCallback getReader(int pos, int popDepth, int pushDepth, HTML.Tag insertTag, boolean insertInsertTag);

    public URL getBase();

    public void setBase(URL u);

    protected void insert(int offset, ElementSpec[] data) throws BadLocationException;

    protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr);

    protected void create(ElementSpec[] data);

    public void setParagraphAttributes(int offset, int length, AttributeSet s, boolean replace);

    public StyleSheet getStyleSheet();

    public Iterator getIterator(HTML.Tag t);

    protected Element createLeafElement(Element parent, AttributeSet a, int p0, int p1);

    protected Element createBranchElement(Element parent, AttributeSet a);

    protected AbstractElement createDefaultRoot();

    public void setTokenThreshold(int n);

    public int getTokenThreshold();

    public void setPreservesUnknownTags(boolean preservesTags);

    public boolean getPreservesUnknownTags();

    public void processHTMLFrameHyperlinkEvent(HTMLFrameHyperlinkEvent e);

    private Element findFrame(String frameName);

    static boolean matchNameAttribute(AttributeSet attr, HTML.Tag tag);

    private void updateFrameSet(Element element, String url);

    private void updateFrame(Element element, String url);

    boolean isFrameDocument();

    void setFrameDocumentState(boolean frameDoc);

    void addMap(Map map);

    void removeMap(Map map);

    Map getMap(String name);

    Enumeration<Object> getMaps();

    void setDefaultStyleSheetType(String contentType);

    String getDefaultStyleSheetType();

    public void setParser(HTMLEditorKit.Parser parser);

    public HTMLEditorKit.Parser getParser();

    public void setInnerHTML(Element elem, String htmlText) throws BadLocationException, IOException;

    public void setOuterHTML(Element elem, String htmlText) throws BadLocationException, IOException;

    public void insertAfterStart(Element elem, String htmlText) throws BadLocationException, IOException;

    public void insertBeforeEnd(Element elem, String htmlText) throws BadLocationException, IOException;

    public void insertBeforeStart(Element elem, String htmlText) throws BadLocationException, IOException;

    public void insertAfterEnd(Element elem, String htmlText) throws BadLocationException, IOException;

    public Element getElement(String id);

    public Element getElement(Element e, Object attribute, Object value);

    private Element getElement(Element e, Object attribute, Object value, boolean searchLeafAttributes);

    private void verifyParser();

    private void installParserIfNecessary();

    private void insertHTML(Element parent, int offset, String html, boolean wantsTrailingNewline) throws BadLocationException, IOException;

    private void removeElements(Element e, int index, int count) throws BadLocationException;

    private void removeElementsAtEnd(Element e, int index, int count, int start, int end) throws BadLocationException;

    private void replace(DefaultDocumentEvent dde, Element e, int index, int count, int start, int end, boolean remove, boolean create) throws BadLocationException;

    private void removeElements(Element e, int index, int count, int start, int end) throws BadLocationException;

    void obtainLock();

    void releaseLock();

    protected void fireChangedUpdate(DocumentEvent e);

    protected void fireUndoableEditUpdate(UndoableEditEvent e);

    boolean hasBaseTag();

    String getBaseTarget();

    private boolean frameDocument = false;

    private boolean preservesUnknownTags = true;

    private HashMap<String, ButtonGroup> radioButtonGroupsMap;

    static final String TokenThreshold = "token threshold";

    private static final int MaxThreshold = 10000;

    private static final int StepThreshold = 5;

    @Interned
    public static final String AdditionalComments = "AdditionalComments";

    static final String StyleType = "StyleType";

    URL base;

    boolean hasBaseTag = false;

    private String baseTarget = null;

    private HTMLEditorKit.Parser parser;

    private static AttributeSet contentAttributeSet;

    static String MAP_PROPERTY = "__MAP__";

    private static char[] NEWLINE;

    private boolean insertInBody = false;

    private static final String I18NProperty = "i18n";

    static {
        contentAttributeSet = new SimpleAttributeSet();
        ((MutableAttributeSet) contentAttributeSet).addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
        NEWLINE = new char[1];
        NEWLINE[0] = '\n';
    }

    public abstract static class Iterator {

        public abstract AttributeSet getAttributes();

        public abstract int getStartOffset();

        public abstract int getEndOffset();

        public abstract void next();

        public abstract boolean isValid();

        public abstract HTML.Tag getTag();
    }

    static class LeafIterator extends Iterator {

        LeafIterator(HTML.Tag t, Document doc) {
            tag = t;
            pos = new ElementIterator(doc);
            endOffset = 0;
            next();
        }

        public AttributeSet getAttributes();

        public int getStartOffset();

        public int getEndOffset();

        public void next();

        public HTML.Tag getTag();

        public boolean isValid();

        void nextLeaf(ElementIterator iter);

        void setEndOffset();

        private int endOffset;

        private HTML.Tag tag;

        private ElementIterator pos;
    }

    public class HTMLReader extends HTMLEditorKit.ParserCallback {

        public HTMLReader(int offset) {
            this(offset, 0, 0, null);
        }

        public HTMLReader(int offset, int popDepth, int pushDepth, HTML.Tag insertTag) {
            this(offset, popDepth, pushDepth, insertTag, true, false, true);
        }

        HTMLReader(int offset, int popDepth, int pushDepth, HTML.Tag insertTag, boolean insertInsertTag, boolean insertAfterImplied, boolean wantsTrailingNewline) {
            emptyDocument = (getLength() == 0);
            isStyleCSS = "text/css".equals(getDefaultStyleSheetType());
            this.offset = offset;
            threshold = HTMLDocument.this.getTokenThreshold();
            tagMap = new Hashtable<HTML.Tag, TagAction>(57);
            TagAction na = new TagAction();
            TagAction ba = new BlockAction();
            TagAction pa = new ParagraphAction();
            TagAction ca = new CharacterAction();
            TagAction sa = new SpecialAction();
            TagAction fa = new FormAction();
            TagAction ha = new HiddenAction();
            TagAction conv = new ConvertAction();
            tagMap.put(HTML.Tag.A, new AnchorAction());
            tagMap.put(HTML.Tag.ADDRESS, ca);
            tagMap.put(HTML.Tag.APPLET, ha);
            tagMap.put(HTML.Tag.AREA, new AreaAction());
            tagMap.put(HTML.Tag.B, conv);
            tagMap.put(HTML.Tag.BASE, new BaseAction());
            tagMap.put(HTML.Tag.BASEFONT, ca);
            tagMap.put(HTML.Tag.BIG, ca);
            tagMap.put(HTML.Tag.BLOCKQUOTE, ba);
            tagMap.put(HTML.Tag.BODY, ba);
            tagMap.put(HTML.Tag.BR, sa);
            tagMap.put(HTML.Tag.CAPTION, ba);
            tagMap.put(HTML.Tag.CENTER, ba);
            tagMap.put(HTML.Tag.CITE, ca);
            tagMap.put(HTML.Tag.CODE, ca);
            tagMap.put(HTML.Tag.DD, ba);
            tagMap.put(HTML.Tag.DFN, ca);
            tagMap.put(HTML.Tag.DIR, ba);
            tagMap.put(HTML.Tag.DIV, ba);
            tagMap.put(HTML.Tag.DL, ba);
            tagMap.put(HTML.Tag.DT, pa);
            tagMap.put(HTML.Tag.EM, ca);
            tagMap.put(HTML.Tag.FONT, conv);
            tagMap.put(HTML.Tag.FORM, new FormTagAction());
            tagMap.put(HTML.Tag.FRAME, sa);
            tagMap.put(HTML.Tag.FRAMESET, ba);
            tagMap.put(HTML.Tag.H1, pa);
            tagMap.put(HTML.Tag.H2, pa);
            tagMap.put(HTML.Tag.H3, pa);
            tagMap.put(HTML.Tag.H4, pa);
            tagMap.put(HTML.Tag.H5, pa);
            tagMap.put(HTML.Tag.H6, pa);
            tagMap.put(HTML.Tag.HEAD, new HeadAction());
            tagMap.put(HTML.Tag.HR, sa);
            tagMap.put(HTML.Tag.HTML, ba);
            tagMap.put(HTML.Tag.I, conv);
            tagMap.put(HTML.Tag.IMG, sa);
            tagMap.put(HTML.Tag.INPUT, fa);
            tagMap.put(HTML.Tag.ISINDEX, new IsindexAction());
            tagMap.put(HTML.Tag.KBD, ca);
            tagMap.put(HTML.Tag.LI, ba);
            tagMap.put(HTML.Tag.LINK, new LinkAction());
            tagMap.put(HTML.Tag.MAP, new MapAction());
            tagMap.put(HTML.Tag.MENU, ba);
            tagMap.put(HTML.Tag.META, new MetaAction());
            tagMap.put(HTML.Tag.NOBR, ca);
            tagMap.put(HTML.Tag.NOFRAMES, ba);
            tagMap.put(HTML.Tag.OBJECT, sa);
            tagMap.put(HTML.Tag.OL, ba);
            tagMap.put(HTML.Tag.OPTION, fa);
            tagMap.put(HTML.Tag.P, pa);
            tagMap.put(HTML.Tag.PARAM, new ObjectAction());
            tagMap.put(HTML.Tag.PRE, new PreAction());
            tagMap.put(HTML.Tag.SAMP, ca);
            tagMap.put(HTML.Tag.SCRIPT, ha);
            tagMap.put(HTML.Tag.SELECT, fa);
            tagMap.put(HTML.Tag.SMALL, ca);
            tagMap.put(HTML.Tag.SPAN, ca);
            tagMap.put(HTML.Tag.STRIKE, conv);
            tagMap.put(HTML.Tag.S, ca);
            tagMap.put(HTML.Tag.STRONG, ca);
            tagMap.put(HTML.Tag.STYLE, new StyleAction());
            tagMap.put(HTML.Tag.SUB, conv);
            tagMap.put(HTML.Tag.SUP, conv);
            tagMap.put(HTML.Tag.TABLE, ba);
            tagMap.put(HTML.Tag.TD, ba);
            tagMap.put(HTML.Tag.TEXTAREA, fa);
            tagMap.put(HTML.Tag.TH, ba);
            tagMap.put(HTML.Tag.TITLE, new TitleAction());
            tagMap.put(HTML.Tag.TR, ba);
            tagMap.put(HTML.Tag.TT, ca);
            tagMap.put(HTML.Tag.U, conv);
            tagMap.put(HTML.Tag.UL, ba);
            tagMap.put(HTML.Tag.VAR, ca);
            if (insertTag != null) {
                this.insertTag = insertTag;
                this.popDepth = popDepth;
                this.pushDepth = pushDepth;
                this.insertInsertTag = insertInsertTag;
                foundInsertTag = false;
            } else {
                foundInsertTag = true;
            }
            if (insertAfterImplied) {
                this.popDepth = popDepth;
                this.pushDepth = pushDepth;
                this.insertAfterImplied = true;
                foundInsertTag = false;
                midInsert = false;
                this.insertInsertTag = true;
                this.wantsTrailingNewline = wantsTrailingNewline;
            } else {
                midInsert = (!emptyDocument && insertTag == null);
                if (midInsert) {
                    generateEndsSpecsForMidInsert();
                }
            }
            if (!emptyDocument && !midInsert) {
                int targetOffset = Math.max(this.offset - 1, 0);
                Element elem = HTMLDocument.this.getCharacterElement(targetOffset);
                for (int i = 0; i <= this.popDepth; i++) {
                    elem = elem.getParentElement();
                }
                for (int i = 0; i < this.pushDepth; i++) {
                    int index = elem.getElementIndex(this.offset);
                    elem = elem.getElement(index);
                }
                AttributeSet attrs = elem.getAttributes();
                if (attrs != null) {
                    HTML.Tag tagToInsertInto = (HTML.Tag) attrs.getAttribute(StyleConstants.NameAttribute);
                    if (tagToInsertInto != null) {
                        this.inParagraph = tagToInsertInto.isParagraph();
                    }
                }
            }
        }

        private void generateEndsSpecsForMidInsert();

        private int depthTo(int offset);

        private int heightToElementWithName(Object name, int offset);

        private void adjustEndElement();

        private Element[] getPathTo(int offset);

        public void flush() throws BadLocationException;

        public void handleText(char[] data, int pos);

        public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos);

        public void handleComment(char[] data, int pos);

        private void addExternalComment(String comment);

        public void handleEndTag(HTML.Tag t, int pos);

        public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos);

        public void handleEndOfLineString(String eol);

        protected void registerTag(HTML.Tag t, TagAction a);

        public class TagAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);
        }

        public class BlockAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        private class FormTagAction extends BlockAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        public class ParagraphAction extends BlockAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);
        }

        public class SpecialAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet a);
        }

        public class IsindexAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet a);
        }

        public class HiddenAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);

            boolean isEmpty(HTML.Tag t);
        }

        class MetaAction extends HiddenAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            boolean isEmpty(HTML.Tag t);
        }

        class HeadAction extends BlockAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);

            boolean isEmpty(HTML.Tag t);

            private void handleLink(AttributeSet attr);
        }

        class LinkAction extends HiddenAction {

            public void start(HTML.Tag t, MutableAttributeSet a);
        }

        class MapAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);
        }

        class AreaAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);
        }

        class StyleAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);

            boolean isEmpty(HTML.Tag t);
        }

        public class PreAction extends BlockAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        public class CharacterAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        class ConvertAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        class AnchorAction extends CharacterAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        class TitleAction extends HiddenAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);

            boolean isEmpty(HTML.Tag t);
        }

        class BaseAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);
        }

        class ObjectAction extends SpecialAction {

            public void start(HTML.Tag t, MutableAttributeSet a);

            public void end(HTML.Tag t);

            void addParameter(AttributeSet a);
        }

        public class FormAction extends SpecialAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);

            void setModel(String type, MutableAttributeSet attr);

            Object selectModel;

            int optionCount;
        }

        protected void pushCharacterStyle();

        protected void popCharacterStyle();

        protected void textAreaContent(char[] data);

        protected void preContent(char[] data);

        protected void blockOpen(HTML.Tag t, MutableAttributeSet attr);

        protected void blockClose(HTML.Tag t);

        protected void addContent(char[] data, int offs, int length);

        protected void addContent(char[] data, int offs, int length, boolean generateImpliedPIfNecessary);

        protected void addSpecialElement(HTML.Tag t, MutableAttributeSet a);

        void flushBuffer(boolean endOfStream) throws BadLocationException;

        private void adjustEndSpecsForPartialInsert();

        void addCSSRules(String rules);

        void linkCSSStyleSheet(String href);

        private boolean canInsertTag(HTML.Tag t, AttributeSet attr, boolean isBlockTag);

        private boolean isInsertTag(HTML.Tag tag);

        private void foundInsertTag(boolean isBlockTag);

        private boolean receivedEndHTML;

        private int flushCount;

        private boolean insertAfterImplied;

        private boolean wantsTrailingNewline;

        int threshold;

        int offset;

        boolean inParagraph = false;

        boolean impliedP = false;

        boolean inPre = false;

        boolean inTextArea = false;

        TextAreaDocument textAreaDocument = null;

        boolean inTitle = false;

        boolean lastWasNewline = true;

        boolean emptyAnchor;

        boolean midInsert;

        boolean inBody;

        HTML.Tag insertTag;

        boolean insertInsertTag;

        boolean foundInsertTag;

        int insertTagDepthDelta;

        int popDepth;

        int pushDepth;

        Map lastMap;

        boolean inStyle = false;

        String defaultStyle;

        Vector<Object> styles;

        boolean inHead = false;

        boolean isStyleCSS;

        boolean emptyDocument;

        AttributeSet styleAttributes;

        Option option;

        protected Vector<ElementSpec> parseBuffer = new Vector<ElementSpec>();

        protected MutableAttributeSet charAttr = new TaggedAttributeSet();

        Stack<AttributeSet> charAttrStack = new Stack<AttributeSet>();

        Hashtable<HTML.Tag, TagAction> tagMap;

        int inBlock = 0;

        private HTML.Tag nextTagAfterPImplied = null;
    }

    static class TaggedAttributeSet extends SimpleAttributeSet {

        TaggedAttributeSet() {
            super();
        }
    }

    public class RunElement extends LeafElement {

        public RunElement(Element parent, AttributeSet a, int offs0, int offs1) {
            super(parent, a, offs0, offs1);
        }

        public String getName();

        public AttributeSet getResolveParent();
    }

    public class BlockElement extends BranchElement {

        public BlockElement(Element parent, AttributeSet a) {
            super(parent, a);
        }

        public String getName();

        public AttributeSet getResolveParent();
    }

    private static class FixedLengthDocument extends PlainDocument {

        private int maxLength;

        public FixedLengthDocument(int maxLength) {
            this.maxLength = maxLength;
        }

        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException;
    }
}
