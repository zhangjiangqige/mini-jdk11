package java.awt;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.peer.FontPeer;
import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.CharacterIterator;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import sun.awt.ComponentFactory;
import sun.font.StandardGlyphVector;
import sun.font.AttributeMap;
import sun.font.AttributeValues;
import sun.font.CompositeFont;
import sun.font.CreatedFontTracker;
import sun.font.Font2D;
import sun.font.Font2DHandle;
import sun.font.FontAccess;
import sun.font.FontDesignMetrics;
import sun.font.FontManager;
import sun.font.FontManagerFactory;
import sun.font.FontUtilities;
import sun.font.GlyphLayout;
import sun.font.FontLineMetrics;
import sun.font.CoreMetrics;
import static sun.font.EAttribute.*;

@AnnotatedFor({ "interning" })
public class Font implements java.io.Serializable {

    private static class FontAccessImpl extends FontAccess {

        public Font2D getFont2D(Font font);

        public void setFont2D(Font font, Font2DHandle handle);

        public void setCreatedFont(Font font);

        public boolean isCreatedFont(Font font);

        @Override
        public FontPeer getFontPeer(final Font font);
    }

    static {
        Toolkit.loadLibraries();
        initIDs();
        FontAccess.setFontAccess(new FontAccessImpl());
    }

    private Hashtable<Object, Object> fRequestedAttributes;

    @Interned
    public static final String DIALOG = "Dialog";

    @Interned
    public static final String DIALOG_INPUT = "DialogInput";

    @Interned
    public static final String SANS_SERIF = "SansSerif";

    @Interned
    public static final String SERIF = "Serif";

    @Interned
    public static final String MONOSPACED = "Monospaced";

    public static final int PLAIN = 0;

    public static final int BOLD = 1;

    public static final int ITALIC = 2;

    public static final int ROMAN_BASELINE = 0;

    public static final int CENTER_BASELINE = 1;

    public static final int HANGING_BASELINE = 2;

    public static final int TRUETYPE_FONT = 0;

    public static final int TYPE1_FONT = 1;

    protected String name;

    protected int style;

    protected int size;

    protected float pointSize;

    private transient FontPeer peer;

    private transient long pData;

    private transient Font2DHandle font2DHandle;

    private transient AttributeValues values;

    private transient boolean hasLayoutAttributes;

    private transient boolean createdFont = false;

    private transient boolean nonIdentityTx;

    private static final AffineTransform identityTx = new AffineTransform();

    private static final long serialVersionUID = -4206021311591459213L;

    private FontPeer getFontPeer();

    private AttributeValues getAttributeValues();

    private Font2D getFont2D();

    public Font(String name, int style, int size) {
        this.name = (name != null) ? name : "Default";
        this.style = (style & ~0x03) == 0 ? style : 0;
        this.size = size;
        this.pointSize = size;
    }

    private Font(String name, int style, float sizePts) {
        this.name = (name != null) ? name : "Default";
        this.style = (style & ~0x03) == 0 ? style : 0;
        this.size = (int) (sizePts + 0.5);
        this.pointSize = sizePts;
    }

    private Font(String name, int style, float sizePts, boolean created, Font2DHandle handle) {
        this(name, style, sizePts);
        this.createdFont = created;
        if (created) {
            if (handle.font2D instanceof CompositeFont && handle.font2D.getStyle() != style) {
                FontManager fm = FontManagerFactory.getInstance();
                this.font2DHandle = fm.getNewComposite(null, style, handle);
            } else {
                this.font2DHandle = handle;
            }
        }
    }

    private Font(File fontFile, int fontFormat, boolean isCopy, CreatedFontTracker tracker) throws FontFormatException {
        this.createdFont = true;
        FontManager fm = FontManagerFactory.getInstance();
        Font2D[] fonts = fm.createFont2D(fontFile, fontFormat, false, isCopy, tracker);
        this.font2DHandle = fonts[0].handle;
        this.name = this.font2DHandle.font2D.getFontName(Locale.getDefault());
        this.style = Font.PLAIN;
        this.size = 1;
        this.pointSize = 1f;
    }

    private Font(AttributeValues values, String oldName, int oldStyle, boolean created, Font2DHandle handle) {
        this.createdFont = created;
        if (created) {
            this.font2DHandle = handle;
            String newName = null;
            if (oldName != null) {
                newName = values.getFamily();
                if (oldName.equals(newName))
                    newName = null;
            }
            int newStyle = 0;
            if (oldStyle == -1) {
                newStyle = -1;
            } else {
                if (values.getWeight() >= 2f)
                    newStyle = BOLD;
                if (values.getPosture() >= .2f)
                    newStyle |= ITALIC;
                if (oldStyle == newStyle)
                    newStyle = -1;
            }
            if (handle.font2D instanceof CompositeFont) {
                if (newStyle != -1 || newName != null) {
                    FontManager fm = FontManagerFactory.getInstance();
                    this.font2DHandle = fm.getNewComposite(newName, newStyle, handle);
                }
            } else if (newName != null) {
                this.createdFont = false;
                this.font2DHandle = null;
            }
        }
        initFromValues(values);
    }

    public Font(Map<? extends Attribute, ?> attributes) {
        initFromValues(AttributeValues.fromMap(attributes, RECOGNIZED_MASK));
    }

    protected Font(Font font) {
        if (font.values != null) {
            initFromValues(font.getAttributeValues().clone());
        } else {
            this.name = font.name;
            this.style = font.style;
            this.size = font.size;
            this.pointSize = font.pointSize;
        }
        this.font2DHandle = font.font2DHandle;
        this.createdFont = font.createdFont;
    }

    private static final int RECOGNIZED_MASK = AttributeValues.MASK_ALL & ~AttributeValues.getMask(EFONT);

    private static final int PRIMARY_MASK = AttributeValues.getMask(EFAMILY, EWEIGHT, EWIDTH, EPOSTURE, ESIZE, ETRANSFORM, ESUPERSCRIPT, ETRACKING);

    private static final int SECONDARY_MASK = RECOGNIZED_MASK & ~PRIMARY_MASK;

    private static final int LAYOUT_MASK = AttributeValues.getMask(ECHAR_REPLACEMENT, EFOREGROUND, EBACKGROUND, EUNDERLINE, ESTRIKETHROUGH, ERUN_DIRECTION, EBIDI_EMBEDDING, EJUSTIFICATION, EINPUT_METHOD_HIGHLIGHT, EINPUT_METHOD_UNDERLINE, ESWAP_COLORS, ENUMERIC_SHAPING, EKERNING, ELIGATURES, ETRACKING, ESUPERSCRIPT);

    private static final int EXTRA_MASK = AttributeValues.getMask(ETRANSFORM, ESUPERSCRIPT, EWIDTH);

    private void initFromValues(AttributeValues values);

    public static boolean textRequiresLayout(char[] chars, int start, int end);

    public static Font getFont(Map<? extends Attribute, ?> attributes);

    private static boolean hasTempPermission();

    public static Font[] createFonts(InputStream fontStream) throws FontFormatException, IOException;

    private Font(Font2D font2D) {
        this.createdFont = true;
        this.font2DHandle = font2D.handle;
        this.name = font2D.getFontName(Locale.getDefault());
        this.style = Font.PLAIN;
        this.size = 1;
        this.pointSize = 1f;
    }

    public static Font[] createFonts(File fontFile) throws FontFormatException, IOException;

    public static Font createFont(int fontFormat, InputStream fontStream) throws java.awt.FontFormatException, java.io.IOException;

    private static Font[] createFont0(int fontFormat, InputStream fontStream, boolean allFonts, CreatedFontTracker tracker) throws java.awt.FontFormatException, java.io.IOException;

    public static Font createFont(int fontFormat, File fontFile) throws java.awt.FontFormatException, java.io.IOException;

    private static File checkFontFile(int fontFormat, File fontFile) throws FontFormatException, IOException;

    public AffineTransform getTransform();

    private static final float[] ssinfo = { 0.0f, 0.375f, 0.625f, 0.7916667f, 0.9027778f, 0.9768519f, 1.0262346f, 1.0591564f };

    public String getFamily();

    final String getFamily_NoClientCode();

    public String getFamily(Locale l);

    public String getPSName();

    public String getName();

    public String getFontName();

    public String getFontName(Locale l);

    public int getStyle();

    public int getSize();

    public float getSize2D();

    public boolean isPlain();

    public boolean isBold();

    public boolean isItalic();

    public boolean isTransformed();

    public boolean hasLayoutAttributes();

    public static Font getFont(String nm);

    public static Font decode(String str);

    public static Font getFont(String nm, Font font);

    transient int hash;

    public int hashCode();

    public boolean equals(Object obj);

    public String toString();

    private int fontSerializedDataVersion = 1;

    private void writeObject(java.io.ObjectOutputStream s) throws java.lang.ClassNotFoundException, java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.lang.ClassNotFoundException, java.io.IOException;

    public int getNumGlyphs();

    public int getMissingGlyphCode();

    public byte getBaselineFor(char c);

    public Map<TextAttribute, ?> getAttributes();

    public Attribute[] getAvailableAttributes();

    public Font deriveFont(int style, float size);

    public Font deriveFont(int style, AffineTransform trans);

    public Font deriveFont(float size);

    public Font deriveFont(AffineTransform trans);

    public Font deriveFont(int style);

    public Font deriveFont(Map<? extends Attribute, ?> attributes);

    public boolean canDisplay(char c);

    public boolean canDisplay(int codePoint);

    public int canDisplayUpTo(String str);

    public int canDisplayUpTo(char[] text, int start, int limit);

    public int canDisplayUpTo(CharacterIterator iter, int start, int limit);

    public float getItalicAngle();

    private float getItalicAngle(FontRenderContext frc);

    public boolean hasUniformLineMetrics();

    private transient SoftReference<FontLineMetrics> flmref;

    private FontLineMetrics defaultLineMetrics(FontRenderContext frc);

    public LineMetrics getLineMetrics(String str, FontRenderContext frc);

    public LineMetrics getLineMetrics(String str, int beginIndex, int limit, FontRenderContext frc);

    public LineMetrics getLineMetrics(char[] chars, int beginIndex, int limit, FontRenderContext frc);

    public LineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getStringBounds(String str, FontRenderContext frc);

    public Rectangle2D getStringBounds(String str, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getStringBounds(char[] chars, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getStringBounds(CharacterIterator ci, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getMaxCharBounds(FontRenderContext frc);

    public GlyphVector createGlyphVector(FontRenderContext frc, String str);

    public GlyphVector createGlyphVector(FontRenderContext frc, char[] chars);

    public GlyphVector createGlyphVector(FontRenderContext frc, CharacterIterator ci);

    public GlyphVector createGlyphVector(FontRenderContext frc, int[] glyphCodes);

    public GlyphVector layoutGlyphVector(FontRenderContext frc, char[] text, int start, int limit, int flags);

    public static final int LAYOUT_LEFT_TO_RIGHT = 0;

    public static final int LAYOUT_RIGHT_TO_LEFT = 1;

    public static final int LAYOUT_NO_START_CONTEXT = 2;

    public static final int LAYOUT_NO_LIMIT_CONTEXT = 4;

    private static void applyTransform(AffineTransform trans, AttributeValues values);

    private static void applyStyle(int style, AttributeValues values);

    private static native void initIDs();
}
