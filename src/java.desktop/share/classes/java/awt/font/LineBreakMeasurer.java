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

    private BreakIterator breakIter;

    private int start;

    private int pos;

    private int limit;

    private TextMeasurer measurer;

    private CharArrayIterator charIter;

    public LineBreakMeasurer(AttributedCharacterIterator text, FontRenderContext frc) {
        this(text, BreakIterator.getLineInstance(), frc);
    }

    public LineBreakMeasurer(AttributedCharacterIterator text, BreakIterator breakIter, FontRenderContext frc) {
        if (text.getEndIndex() - text.getBeginIndex() < 1) {
            throw new IllegalArgumentException("Text must contain at least one character.");
        }
        this.breakIter = breakIter;
        this.measurer = new TextMeasurer(text, frc);
        this.limit = text.getEndIndex();
        this.pos = this.start = text.getBeginIndex();
        charIter = new CharArrayIterator(measurer.getChars(), this.start);
        this.breakIter.setText(charIter);
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
