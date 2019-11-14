package javax.swing.text.html;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.Hashtable;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

@AnnotatedFor({ "interning" })
public class HTML {

    public static class Tag {

        public Tag() {
        }

        protected Tag(String id) {
            this(id, false, false);
        }

        protected Tag(String id, boolean causesBreak, boolean isBlock) {
            name = id;
            this.breakTag = causesBreak;
            this.blockTag = isBlock;
        }

        public boolean isBlock();

        public boolean breaksFlow();

        public boolean isPreformatted();

        public String toString();

        boolean isParagraph();

        boolean blockTag;

        boolean breakTag;

        String name;

        boolean unknown;

        public static final Tag A = new Tag("a");

        public static final Tag ADDRESS = new Tag("address");

        public static final Tag APPLET = new Tag("applet");

        public static final Tag AREA = new Tag("area");

        public static final Tag B = new Tag("b");

        public static final Tag BASE = new Tag("base");

        public static final Tag BASEFONT = new Tag("basefont");

        public static final Tag BIG = new Tag("big");

        public static final Tag BLOCKQUOTE = new Tag("blockquote", true, true);

        public static final Tag BODY = new Tag("body", true, true);

        public static final Tag BR = new Tag("br", true, false);

        public static final Tag CAPTION = new Tag("caption");

        public static final Tag CENTER = new Tag("center", true, false);

        public static final Tag CITE = new Tag("cite");

        public static final Tag CODE = new Tag("code");

        public static final Tag DD = new Tag("dd", true, true);

        public static final Tag DFN = new Tag("dfn");

        public static final Tag DIR = new Tag("dir", true, true);

        public static final Tag DIV = new Tag("div", true, true);

        public static final Tag DL = new Tag("dl", true, true);

        public static final Tag DT = new Tag("dt", true, true);

        public static final Tag EM = new Tag("em");

        public static final Tag FONT = new Tag("font");

        public static final Tag FORM = new Tag("form", true, false);

        public static final Tag FRAME = new Tag("frame");

        public static final Tag FRAMESET = new Tag("frameset");

        public static final Tag H1 = new Tag("h1", true, true);

        public static final Tag H2 = new Tag("h2", true, true);

        public static final Tag H3 = new Tag("h3", true, true);

        public static final Tag H4 = new Tag("h4", true, true);

        public static final Tag H5 = new Tag("h5", true, true);

        public static final Tag H6 = new Tag("h6", true, true);

        public static final Tag HEAD = new Tag("head", true, true);

        public static final Tag HR = new Tag("hr", true, false);

        public static final Tag HTML = new Tag("html", true, false);

        public static final Tag I = new Tag("i");

        public static final Tag IMG = new Tag("img");

        public static final Tag INPUT = new Tag("input");

        public static final Tag ISINDEX = new Tag("isindex", true, false);

        public static final Tag KBD = new Tag("kbd");

        public static final Tag LI = new Tag("li", true, true);

        public static final Tag LINK = new Tag("link");

        public static final Tag MAP = new Tag("map");

        public static final Tag MENU = new Tag("menu", true, true);

        public static final Tag META = new Tag("meta");

        static final Tag NOBR = new Tag("nobr");

        public static final Tag NOFRAMES = new Tag("noframes", true, true);

        public static final Tag OBJECT = new Tag("object");

        public static final Tag OL = new Tag("ol", true, true);

        public static final Tag OPTION = new Tag("option");

        public static final Tag P = new Tag("p", true, true);

        public static final Tag PARAM = new Tag("param");

        public static final Tag PRE = new Tag("pre", true, true);

        public static final Tag SAMP = new Tag("samp");

        public static final Tag SCRIPT = new Tag("script");

        public static final Tag SELECT = new Tag("select");

        public static final Tag SMALL = new Tag("small");

        public static final Tag SPAN = new Tag("span");

        public static final Tag STRIKE = new Tag("strike");

        public static final Tag S = new Tag("s");

        public static final Tag STRONG = new Tag("strong");

        public static final Tag STYLE = new Tag("style");

        public static final Tag SUB = new Tag("sub");

        public static final Tag SUP = new Tag("sup");

        public static final Tag TABLE = new Tag("table", false, true);

        public static final Tag TD = new Tag("td", true, true);

        public static final Tag TEXTAREA = new Tag("textarea");

        public static final Tag TH = new Tag("th", true, true);

        public static final Tag TITLE = new Tag("title", true, true);

        public static final Tag TR = new Tag("tr", false, true);

        public static final Tag TT = new Tag("tt");

        public static final Tag U = new Tag("u");

        public static final Tag UL = new Tag("ul", true, true);

        public static final Tag VAR = new Tag("var");

        public static final Tag IMPLIED = new Tag("p-implied");

        public static final Tag CONTENT = new Tag("content");

        public static final Tag COMMENT = new Tag("comment");

        static final Tag[] allTags = { A, ADDRESS, APPLET, AREA, B, BASE, BASEFONT, BIG, BLOCKQUOTE, BODY, BR, CAPTION, CENTER, CITE, CODE, DD, DFN, DIR, DIV, DL, DT, EM, FONT, FORM, FRAME, FRAMESET, H1, H2, H3, H4, H5, H6, HEAD, HR, HTML, I, IMG, INPUT, ISINDEX, KBD, LI, LINK, MAP, MENU, META, NOBR, NOFRAMES, OBJECT, OL, OPTION, P, PARAM, PRE, SAMP, SCRIPT, SELECT, SMALL, SPAN, STRIKE, S, STRONG, STYLE, SUB, SUP, TABLE, TD, TEXTAREA, TH, TITLE, TR, TT, U, UL, VAR };

        static {
            getTag("html");
        }
    }

    @SuppressWarnings("serial")
    public static class UnknownTag extends Tag implements Serializable {

        public UnknownTag(String id) {
            super(id);
        }

        public int hashCode();

        public boolean equals(Object obj);

        private void writeObject(java.io.ObjectOutputStream s) throws IOException;

        private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;
    }

    public static final class Attribute {

        Attribute(String id) {
            name = id;
        }

        public String toString();

        private String name;

        public static final Attribute SIZE = new Attribute("size");

        public static final Attribute COLOR = new Attribute("color");

        public static final Attribute CLEAR = new Attribute("clear");

        public static final Attribute BACKGROUND = new Attribute("background");

        public static final Attribute BGCOLOR = new Attribute("bgcolor");

        public static final Attribute TEXT = new Attribute("text");

        public static final Attribute LINK = new Attribute("link");

        public static final Attribute VLINK = new Attribute("vlink");

        public static final Attribute ALINK = new Attribute("alink");

        public static final Attribute WIDTH = new Attribute("width");

        public static final Attribute HEIGHT = new Attribute("height");

        public static final Attribute ALIGN = new Attribute("align");

        public static final Attribute NAME = new Attribute("name");

        public static final Attribute HREF = new Attribute("href");

        public static final Attribute REL = new Attribute("rel");

        public static final Attribute REV = new Attribute("rev");

        public static final Attribute TITLE = new Attribute("title");

        public static final Attribute TARGET = new Attribute("target");

        public static final Attribute SHAPE = new Attribute("shape");

        public static final Attribute COORDS = new Attribute("coords");

        public static final Attribute ISMAP = new Attribute("ismap");

        public static final Attribute NOHREF = new Attribute("nohref");

        public static final Attribute ALT = new Attribute("alt");

        public static final Attribute ID = new Attribute("id");

        public static final Attribute SRC = new Attribute("src");

        public static final Attribute HSPACE = new Attribute("hspace");

        public static final Attribute VSPACE = new Attribute("vspace");

        public static final Attribute USEMAP = new Attribute("usemap");

        public static final Attribute LOWSRC = new Attribute("lowsrc");

        public static final Attribute CODEBASE = new Attribute("codebase");

        public static final Attribute CODE = new Attribute("code");

        public static final Attribute ARCHIVE = new Attribute("archive");

        public static final Attribute VALUE = new Attribute("value");

        public static final Attribute VALUETYPE = new Attribute("valuetype");

        public static final Attribute TYPE = new Attribute("type");

        public static final Attribute CLASS = new Attribute("class");

        public static final Attribute STYLE = new Attribute("style");

        public static final Attribute LANG = new Attribute("lang");

        public static final Attribute FACE = new Attribute("face");

        public static final Attribute DIR = new Attribute("dir");

        public static final Attribute DECLARE = new Attribute("declare");

        public static final Attribute CLASSID = new Attribute("classid");

        public static final Attribute DATA = new Attribute("data");

        public static final Attribute CODETYPE = new Attribute("codetype");

        public static final Attribute STANDBY = new Attribute("standby");

        public static final Attribute BORDER = new Attribute("border");

        public static final Attribute SHAPES = new Attribute("shapes");

        public static final Attribute NOSHADE = new Attribute("noshade");

        public static final Attribute COMPACT = new Attribute("compact");

        public static final Attribute START = new Attribute("start");

        public static final Attribute ACTION = new Attribute("action");

        public static final Attribute METHOD = new Attribute("method");

        public static final Attribute ENCTYPE = new Attribute("enctype");

        public static final Attribute CHECKED = new Attribute("checked");

        public static final Attribute MAXLENGTH = new Attribute("maxlength");

        public static final Attribute MULTIPLE = new Attribute("multiple");

        public static final Attribute SELECTED = new Attribute("selected");

        public static final Attribute ROWS = new Attribute("rows");

        public static final Attribute COLS = new Attribute("cols");

        public static final Attribute DUMMY = new Attribute("dummy");

        public static final Attribute CELLSPACING = new Attribute("cellspacing");

        public static final Attribute CELLPADDING = new Attribute("cellpadding");

        public static final Attribute VALIGN = new Attribute("valign");

        public static final Attribute HALIGN = new Attribute("halign");

        public static final Attribute NOWRAP = new Attribute("nowrap");

        public static final Attribute ROWSPAN = new Attribute("rowspan");

        public static final Attribute COLSPAN = new Attribute("colspan");

        public static final Attribute PROMPT = new Attribute("prompt");

        public static final Attribute HTTPEQUIV = new Attribute("http-equiv");

        public static final Attribute CONTENT = new Attribute("content");

        public static final Attribute LANGUAGE = new Attribute("language");

        public static final Attribute VERSION = new Attribute("version");

        public static final Attribute N = new Attribute("n");

        public static final Attribute FRAMEBORDER = new Attribute("frameborder");

        public static final Attribute MARGINWIDTH = new Attribute("marginwidth");

        public static final Attribute MARGINHEIGHT = new Attribute("marginheight");

        public static final Attribute SCROLLING = new Attribute("scrolling");

        public static final Attribute NORESIZE = new Attribute("noresize");

        public static final Attribute ENDTAG = new Attribute("endtag");

        public static final Attribute COMMENT = new Attribute("comment");

        static final Attribute MEDIA = new Attribute("media");

        static final Attribute[] allAttributes = { FACE, COMMENT, SIZE, COLOR, CLEAR, BACKGROUND, BGCOLOR, TEXT, LINK, VLINK, ALINK, WIDTH, HEIGHT, ALIGN, NAME, HREF, REL, REV, TITLE, TARGET, SHAPE, COORDS, ISMAP, NOHREF, ALT, ID, SRC, HSPACE, VSPACE, USEMAP, LOWSRC, CODEBASE, CODE, ARCHIVE, VALUE, VALUETYPE, TYPE, CLASS, STYLE, LANG, DIR, DECLARE, CLASSID, DATA, CODETYPE, STANDBY, BORDER, SHAPES, NOSHADE, COMPACT, START, ACTION, METHOD, ENCTYPE, CHECKED, MAXLENGTH, MULTIPLE, SELECTED, ROWS, COLS, DUMMY, CELLSPACING, CELLPADDING, VALIGN, HALIGN, NOWRAP, ROWSPAN, COLSPAN, PROMPT, HTTPEQUIV, CONTENT, LANGUAGE, VERSION, N, FRAMEBORDER, MARGINWIDTH, MARGINHEIGHT, SCROLLING, NORESIZE, MEDIA, ENDTAG };
    }

    private static final Hashtable<String, Tag> tagHashtable = new Hashtable<String, Tag>(73);

    private static final Hashtable<Object, Tag> scMapping = new Hashtable<Object, Tag>(8);

    static {
        for (int i = 0; i < Tag.allTags.length; i++) {
            tagHashtable.put(Tag.allTags[i].toString(), Tag.allTags[i]);
            StyleContext.registerStaticAttributeKey(Tag.allTags[i]);
        }
        StyleContext.registerStaticAttributeKey(Tag.IMPLIED);
        StyleContext.registerStaticAttributeKey(Tag.CONTENT);
        StyleContext.registerStaticAttributeKey(Tag.COMMENT);
        for (int i = 0; i < Attribute.allAttributes.length; i++) {
            StyleContext.registerStaticAttributeKey(Attribute.allAttributes[i]);
        }
        StyleContext.registerStaticAttributeKey(HTML.NULL_ATTRIBUTE_VALUE);
        scMapping.put(StyleConstants.Bold, Tag.B);
        scMapping.put(StyleConstants.Italic, Tag.I);
        scMapping.put(StyleConstants.Underline, Tag.U);
        scMapping.put(StyleConstants.StrikeThrough, Tag.STRIKE);
        scMapping.put(StyleConstants.Superscript, Tag.SUP);
        scMapping.put(StyleConstants.Subscript, Tag.SUB);
        scMapping.put(StyleConstants.FontFamily, Tag.FONT);
        scMapping.put(StyleConstants.FontSize, Tag.FONT);
    }

    public static Tag[] getAllTags();

    public static Tag getTag(String tagName);

    static Tag getTagForStyleConstantsKey(StyleConstants sc);

    public static int getIntegerAttributeValue(AttributeSet attr, Attribute key, int def);

    @Interned
    public static final String NULL_ATTRIBUTE_VALUE = "#DEFAULT";

    private static final Hashtable<String, Attribute> attHashtable = new Hashtable<String, Attribute>(77);

    static {
        for (int i = 0; i < Attribute.allAttributes.length; i++) {
            attHashtable.put(Attribute.allAttributes[i].toString(), Attribute.allAttributes[i]);
        }
    }

    public static Attribute[] getAllAttributeKeys();

    public static Attribute getAttributeKey(String attName);
}
