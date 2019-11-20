package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.text.BreakIterator;
import java.text.CharacterIterator;
import java.text.AttributedCharacterIterator;
import java.awt.font.FontRenderContext;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class LineBreakMeasurer {

    public LineBreakMeasurer(AttributedCharacterIterator text, FontRenderContext frc) {
    }

    public LineBreakMeasurer(AttributedCharacterIterator text, BreakIterator breakIter, FontRenderContext frc) {
    }

    public int nextOffset(float wrappingWidth);

    public int nextOffset(float wrappingWidth, int offsetLimit, boolean requireNextWord);

    public TextLayout nextLayout(float wrappingWidth);

    public TextLayout nextLayout(float wrappingWidth, int offsetLimit, boolean requireNextWord);

    public int getPosition();

    public void setPosition(int newPosition);

    public void insertChar(AttributedCharacterIterator newParagraph, int insertPos);

    public void deleteChar(AttributedCharacterIterator newParagraph, int deletePos);
}
