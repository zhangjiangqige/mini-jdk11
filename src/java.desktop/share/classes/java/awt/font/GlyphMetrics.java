package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.Rectangle2D;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class GlyphMetrics {

    private boolean horizontal;

    private float advanceX;

    private float advanceY;

    private Rectangle2D.Float bounds;

    private byte glyphType;

    public static final byte STANDARD = 0;

    public static final byte LIGATURE = 1;

    public static final byte COMBINING = 2;

    public static final byte COMPONENT = 3;

    public static final byte WHITESPACE = 4;

    public GlyphMetrics(float advance, Rectangle2D bounds, byte glyphType) {
        this.horizontal = true;
        this.advanceX = advance;
        this.advanceY = 0;
        this.bounds = new Rectangle2D.Float();
        this.bounds.setRect(bounds);
        this.glyphType = glyphType;
    }

    public GlyphMetrics(boolean horizontal, float advanceX, float advanceY, Rectangle2D bounds, byte glyphType) {
        this.horizontal = horizontal;
        this.advanceX = advanceX;
        this.advanceY = advanceY;
        this.bounds = new Rectangle2D.Float();
        this.bounds.setRect(bounds);
        this.glyphType = glyphType;
    }

    public float getAdvance();

    public float getAdvanceX();

    public float getAdvanceY();

    public Rectangle2D getBounds2D();

    public float getLSB();

    public float getRSB();

    public int getType();

    public boolean isStandard();

    public boolean isLigature();

    public boolean isCombining();

    public boolean isComponent();

    public boolean isWhitespace();
}
