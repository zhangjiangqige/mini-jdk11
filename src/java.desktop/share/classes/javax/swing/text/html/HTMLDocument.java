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
    }

    public HTMLDocument(StyleSheet styles) {
    }

    public HTMLDocument(Content c, StyleSheet styles) {
    }

    public HTMLEditorKit.ParserCallback getReader(int pos);

    public HTMLEditorKit.ParserCallback getReader(int pos, int popDepth, int pushDepth, HTML.Tag insertTag);

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

    protected void fireChangedUpdate(DocumentEvent e);

    protected void fireUndoableEditUpdate(UndoableEditEvent e);

    @Interned
    public static final String AdditionalComments;

    public abstract static class Iterator {

        public abstract AttributeSet getAttributes();

        public abstract int getStartOffset();

        public abstract int getEndOffset();

        public abstract void next();

        public abstract boolean isValid();

        public abstract HTML.Tag getTag();
    }

    public class HTMLReader extends HTMLEditorKit.ParserCallback {

        public HTMLReader(int offset) {
        }

        public HTMLReader(int offset, int popDepth, int pushDepth, HTML.Tag insertTag) {
        }

        public void flush() throws BadLocationException;

        public void handleText(char[] data, int pos);

        public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos);

        public void handleComment(char[] data, int pos);

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
        }

        public class PreAction extends BlockAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        public class CharacterAction extends TagAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
        }

        public class FormAction extends SpecialAction {

            public void start(HTML.Tag t, MutableAttributeSet attr);

            public void end(HTML.Tag t);
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

        protected Vector<ElementSpec> parseBuffer;

        protected MutableAttributeSet charAttr;
    }

    public class RunElement extends LeafElement {

        public RunElement(Element parent, AttributeSet a, int offs0, int offs1) {
        }

        public String getName();

        public AttributeSet getResolveParent();
    }

    public class BlockElement extends BranchElement {

        public BlockElement(Element parent, AttributeSet a) {
        }

        public String getName();

        public AttributeSet getResolveParent();
    }
}
