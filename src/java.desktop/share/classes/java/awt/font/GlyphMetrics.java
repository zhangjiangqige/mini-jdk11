package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.Rectangle2D;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class GlyphMetrics {

    public static final byte STANDARD;

    public static final byte LIGATURE;

    public static final byte COMBINING;

    public static final byte COMPONENT;

    public static final byte WHITESPACE;

    public GlyphMetrics(float advance, Rectangle2D bounds, byte glyphType) {
    }

    public GlyphMetrics(boolean horizontal, float advanceX, float advanceY, Rectangle2D bounds, byte glyphType) {
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
