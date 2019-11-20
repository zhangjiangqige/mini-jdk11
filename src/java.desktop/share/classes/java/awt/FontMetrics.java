package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.text.CharacterIterator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FontMetrics implements java.io.Serializable {

    protected Font font;

    protected FontMetrics(Font font) {
    }

    public Font getFont();

    public FontRenderContext getFontRenderContext();

    public int getLeading();

    public int getAscent();

    public int getDescent();

    public int getHeight();

    public int getMaxAscent();

    public int getMaxDescent();

    @Deprecated
    public int getMaxDecent();

    public int getMaxAdvance();

    public int charWidth(int codePoint);

    public int charWidth(char ch);

    public int stringWidth(String str);

    public int charsWidth(char[] data, int off, int len);

    @SuppressWarnings("deprecation")
    public int bytesWidth(byte[] data, int off, int len);

    public int[] getWidths();

    public boolean hasUniformLineMetrics();

    public LineMetrics getLineMetrics(String str, Graphics context);

    public LineMetrics getLineMetrics(String str, int beginIndex, int limit, Graphics context);

    public LineMetrics getLineMetrics(char[] chars, int beginIndex, int limit, Graphics context);

    public LineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int limit, Graphics context);

    public Rectangle2D getStringBounds(String str, Graphics context);

    public Rectangle2D getStringBounds(String str, int beginIndex, int limit, Graphics context);

    public Rectangle2D getStringBounds(char[] chars, int beginIndex, int limit, Graphics context);

    public Rectangle2D getStringBounds(CharacterIterator ci, int beginIndex, int limit, Graphics context);

    public Rectangle2D getMaxCharBounds(Graphics context);

    public String toString();
}
