package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Font;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.AttributedString;
import java.text.Bidi;
import java.text.BreakIterator;
import java.text.CharacterIterator;
import java.awt.font.FontRenderContext;
import java.util.Hashtable;
import java.util.Map;
import sun.font.AttributeValues;
import sun.font.BidiUtils;
import sun.font.TextLineComponent;
import sun.font.TextLabelFactory;
import sun.font.FontResolver;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class TextMeasurer implements Cloneable {

    public TextMeasurer(AttributedCharacterIterator text, FontRenderContext frc) {
    }

    protected Object clone();

    public int getLineBreakIndex(int start, float maxAdvance);

    public float getAdvanceBetween(int start, int limit);

    public TextLayout getLayout(int start, int limit);

    public void insertChar(AttributedCharacterIterator newParagraph, int insertPos);

    public void deleteChar(AttributedCharacterIterator newParagraph, int deletePos);
}
