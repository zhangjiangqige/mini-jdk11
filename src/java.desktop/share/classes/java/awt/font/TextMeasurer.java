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

    private static float EST_LINES = (float) 2.1;

    private FontRenderContext fFrc;

    private int fStart;

    private char[] fChars;

    private Bidi fBidi;

    private byte[] fLevels;

    private TextLineComponent[] fComponents;

    private int fComponentStart;

    private int fComponentLimit;

    private boolean haveLayoutWindow;

    private BreakIterator fLineBreak = null;

    private CharArrayIterator charIter = null;

    int layoutCount = 0;

    int layoutCharCount = 0;

    private StyledParagraph fParagraph;

    private boolean fIsDirectionLTR;

    private byte fBaseline;

    private float[] fBaselineOffsets;

    private float fJustifyRatio = 1;

    public TextMeasurer(AttributedCharacterIterator text, FontRenderContext frc) {
        fFrc = frc;
        initAll(text);
    }

    protected Object clone();

    private void invalidateComponents();

    private void initAll(AttributedCharacterIterator text);

    private void generateComponents(int startingAt, int endingAt);

    private int calcLineBreak(final int pos, final float maxAdvance);

    private int trailingCdWhitespaceStart(int startPos, int limitPos);

    private TextLineComponent[] makeComponentsOnRange(int startPos, int limitPos);

    private TextLine makeTextLineOnRange(int startPos, int limitPos);

    private void ensureComponents(int start, int limit);

    private void makeLayoutWindow(int localStart);

    public int getLineBreakIndex(int start, float maxAdvance);

    public float getAdvanceBetween(int start, int limit);

    public TextLayout getLayout(int start, int limit);

    private int formattedChars = 0;

    private static boolean wantStats = false;

    private boolean collectStats = false;

    private void printStats();

    public void insertChar(AttributedCharacterIterator newParagraph, int insertPos);

    public void deleteChar(AttributedCharacterIterator newParagraph, int deletePos);

    char[] getChars();
}
