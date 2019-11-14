package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.im.InputMethodHighlight;
import java.awt.image.BufferedImage;
import java.text.Annotation;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.Bidi;
import java.text.CharacterIterator;
import java.util.Hashtable;
import java.util.Map;
import sun.font.AttributeValues;
import sun.font.BidiUtils;
import sun.font.CodePointIterator;
import sun.font.CoreMetrics;
import sun.font.Decoration;
import sun.font.FontLineMetrics;
import sun.font.FontResolver;
import sun.font.GraphicComponent;
import sun.font.LayoutPathImpl;
import sun.font.LayoutPathImpl.EmptyPath;
import sun.font.LayoutPathImpl.SegmentPathBuilder;
import sun.font.TextLabelFactory;
import sun.font.TextLineComponent;
import java.awt.geom.Line2D;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class TextLine {

    static final class TextLineMetrics {

        public final float ascent;

        public final float descent;

        public final float leading;

        public final float advance;

        public TextLineMetrics(float ascent, float descent, float leading, float advance) {
            this.ascent = ascent;
            this.descent = descent;
            this.leading = leading;
            this.advance = advance;
        }
    }

    private TextLineComponent[] fComponents;

    private float[] fBaselineOffsets;

    private int[] fComponentVisualOrder;

    private float[] locs;

    private char[] fChars;

    private int fCharsStart;

    private int fCharsLimit;

    private int[] fCharVisualOrder;

    private int[] fCharLogicalOrder;

    private byte[] fCharLevels;

    private boolean fIsDirectionLTR;

    private LayoutPathImpl lp;

    private boolean isSimple;

    private Rectangle pixelBounds;

    private FontRenderContext frc;

    private TextLineMetrics fMetrics = null;

    public TextLine(FontRenderContext frc, TextLineComponent[] components, float[] baselineOffsets, char[] chars, int charsStart, int charsLimit, int[] charLogicalOrder, byte[] charLevels, boolean isDirectionLTR) {
        int[] componentVisualOrder = computeComponentOrder(components, charLogicalOrder);
        this.frc = frc;
        fComponents = components;
        fBaselineOffsets = baselineOffsets;
        fComponentVisualOrder = componentVisualOrder;
        fChars = chars;
        fCharsStart = charsStart;
        fCharsLimit = charsLimit;
        fCharLogicalOrder = charLogicalOrder;
        fCharLevels = charLevels;
        fIsDirectionLTR = isDirectionLTR;
        checkCtorArgs();
        init();
    }

    private void checkCtorArgs();

    private void init();

    public Rectangle getPixelBounds(FontRenderContext frc, float x, float y);

    static Rectangle computePixelBounds(BufferedImage im);

    private abstract static class Function {

        abstract float computeFunction(TextLine line, int componentIndex, int indexInArray);
    }

    private static Function fgPosAdvF = new Function() {

        float computeFunction(TextLine line, int componentIndex, int indexInArray) {
            TextLineComponent tlc = line.fComponents[componentIndex];
            int vi = line.getComponentVisualIndex(componentIndex);
            return line.locs[vi * 2] + tlc.getCharX(indexInArray) + tlc.getCharAdvance(indexInArray);
        }
    };

    private static Function fgAdvanceF = new Function() {

        float computeFunction(TextLine line, int componentIndex, int indexInArray) {
            TextLineComponent tlc = line.fComponents[componentIndex];
            return tlc.getCharAdvance(indexInArray);
        }
    };

    private static Function fgXPositionF = new Function() {

        float computeFunction(TextLine line, int componentIndex, int indexInArray) {
            int vi = line.getComponentVisualIndex(componentIndex);
            TextLineComponent tlc = line.fComponents[componentIndex];
            return line.locs[vi * 2] + tlc.getCharX(indexInArray);
        }
    };

    private static Function fgYPositionF = new Function() {

        float computeFunction(TextLine line, int componentIndex, int indexInArray) {
            TextLineComponent tlc = line.fComponents[componentIndex];
            float charPos = tlc.getCharY(indexInArray);
            return charPos + line.getComponentShift(componentIndex);
        }
    };

    public int characterCount();

    public boolean isDirectionLTR();

    public TextLineMetrics getMetrics();

    public int visualToLogical(int visualIndex);

    public int logicalToVisual(int logicalIndex);

    public byte getCharLevel(int logicalIndex);

    public boolean isCharLTR(int logicalIndex);

    public int getCharType(int logicalIndex);

    public boolean isCharSpace(int logicalIndex);

    public boolean isCharWhitespace(int logicalIndex);

    public float getCharAngle(int logicalIndex);

    public CoreMetrics getCoreMetricsAt(int logicalIndex);

    public float getCharAscent(int logicalIndex);

    public float getCharDescent(int logicalIndex);

    public float getCharShift(int logicalIndex);

    private float applyFunctionAtIndex(int logicalIndex, Function f);

    public float getCharAdvance(int logicalIndex);

    public float getCharXPosition(int logicalIndex);

    public float getCharYPosition(int logicalIndex);

    public float getCharLinePosition(int logicalIndex);

    public float getCharLinePosition(int logicalIndex, boolean leading);

    public boolean caretAtOffsetIsValid(int offset);

    private int getComponentLogicalIndex(int vi);

    private int getComponentVisualIndex(int li);

    public Rectangle2D getCharBounds(int logicalIndex);

    private float getComponentShift(int index);

    public void draw(Graphics2D g2, float x, float y);

    public Rectangle2D getVisualBounds();

    public Rectangle2D getItalicBounds();

    public Shape getOutline(AffineTransform tx);

    public String toString();

    public static TextLine fastCreateTextLine(FontRenderContext frc, char[] chars, Font font, CoreMetrics lm, Map<? extends Attribute, ?> attributes);

    private static TextLineComponent[] expandArray(TextLineComponent[] orig);

    public static TextLineComponent[] createComponentsOnRun(int runStart, int runLimit, char[] chars, int[] charsLtoV, byte[] levels, TextLabelFactory factory, Font font, CoreMetrics cm, FontRenderContext frc, Decoration decorator, TextLineComponent[] components, int numComponents);

    public static TextLineComponent[] getComponents(StyledParagraph styledParagraph, char[] chars, int textStart, int textLimit, int[] charsLtoV, byte[] levels, TextLabelFactory factory);

    public static TextLine createLineFromText(char[] chars, StyledParagraph styledParagraph, TextLabelFactory factory, boolean isDirectionLTR, float[] baselineOffsets);

    private static int[] computeComponentOrder(TextLineComponent[] components, int[] charsLtoV);

    public static TextLine standardCreateTextLine(FontRenderContext frc, AttributedCharacterIterator text, char[] chars, float[] baselineOffsets);

    static boolean advanceToFirstFont(AttributedCharacterIterator aci);

    static float[] getNormalizedOffsets(float[] baselineOffsets, byte baseline);

    static Font getFontAtCurrentPos(AttributedCharacterIterator aci);

    private static int firstVisualChunk(int[] order, byte[] direction, int start, int limit);

    public TextLine getJustifiedLine(float justificationWidth, float justifyRatio, int justStart, int justLimit);

    public static float getAdvanceBetween(TextLineComponent[] components, int start, int limit);

    LayoutPathImpl getLayoutPath();
}
