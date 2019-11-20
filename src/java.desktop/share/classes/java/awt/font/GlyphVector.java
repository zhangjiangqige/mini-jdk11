package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.Shape;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphJustificationInfo;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class GlyphVector implements Cloneable {

    public abstract Font getFont();

    public abstract FontRenderContext getFontRenderContext();

    public abstract void performDefaultLayout();

    public abstract int getNumGlyphs();

    public abstract int getGlyphCode(int glyphIndex);

    public abstract int[] getGlyphCodes(int beginGlyphIndex, int numEntries, int[] codeReturn);

    public int getGlyphCharIndex(int glyphIndex);

    public int[] getGlyphCharIndices(int beginGlyphIndex, int numEntries, int[] codeReturn);

    public abstract Rectangle2D getLogicalBounds();

    public abstract Rectangle2D getVisualBounds();

    public Rectangle getPixelBounds(FontRenderContext renderFRC, float x, float y);

    public abstract Shape getOutline();

    public abstract Shape getOutline(float x, float y);

    public abstract Shape getGlyphOutline(int glyphIndex);

    public Shape getGlyphOutline(int glyphIndex, float x, float y);

    public abstract Point2D getGlyphPosition(int glyphIndex);

    public abstract void setGlyphPosition(int glyphIndex, Point2D newPos);

    public abstract AffineTransform getGlyphTransform(int glyphIndex);

    public abstract void setGlyphTransform(int glyphIndex, AffineTransform newTX);

    public int getLayoutFlags();

    public static final int FLAG_HAS_TRANSFORMS;

    public static final int FLAG_HAS_POSITION_ADJUSTMENTS;

    public static final int FLAG_RUN_RTL;

    public static final int FLAG_COMPLEX_GLYPHS;

    public static final int FLAG_MASK;

    public abstract float[] getGlyphPositions(int beginGlyphIndex, int numEntries, float[] positionReturn);

    public abstract Shape getGlyphLogicalBounds(int glyphIndex);

    public abstract Shape getGlyphVisualBounds(int glyphIndex);

    public Rectangle getGlyphPixelBounds(int index, FontRenderContext renderFRC, float x, float y);

    public abstract GlyphMetrics getGlyphMetrics(int glyphIndex);

    public abstract GlyphJustificationInfo getGlyphJustificationInfo(int glyphIndex);

    public abstract boolean equals(GlyphVector set);
}
