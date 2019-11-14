package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.im.InputMethodHighlight;
import java.text.Annotation;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import sun.font.CodePointIterator;
import sun.font.Decoration;
import sun.font.FontResolver;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class StyledParagraph {

    private int length;

    private Decoration decoration;

    private Object font;

    private Vector<Decoration> decorations;

    int[] decorationStarts;

    private Vector<Object> fonts;

    int[] fontStarts;

    private static int INITIAL_SIZE = 8;

    public StyledParagraph(AttributedCharacterIterator aci, char[] chars) {
        int start = aci.getBeginIndex();
        int end = aci.getEndIndex();
        length = end - start;
        int index = start;
        aci.first();
        do {
            final int nextRunStart = aci.getRunLimit();
            final int localIndex = index - start;
            Map<? extends Attribute, ?> attributes = aci.getAttributes();
            attributes = addInputMethodAttrs(attributes);
            Decoration d = Decoration.getDecoration(attributes);
            addDecoration(d, localIndex);
            Object f = getGraphicOrFont(attributes);
            if (f == null) {
                addFonts(chars, attributes, localIndex, nextRunStart - start);
            } else {
                addFont(f, localIndex);
            }
            aci.setIndex(nextRunStart);
            index = nextRunStart;
        } while (index < end);
        if (decorations != null) {
            decorationStarts = addToVector(this, length, decorations, decorationStarts);
        }
        if (fonts != null) {
            fontStarts = addToVector(this, length, fonts, fontStarts);
        }
    }

    private static void insertInto(int pos, int[] starts, int numStarts);

    public static StyledParagraph insertChar(AttributedCharacterIterator aci, char[] chars, int insertPos, StyledParagraph oldParagraph);

    private static void deleteFrom(int deleteAt, int[] starts, int numStarts);

    public static StyledParagraph deleteChar(AttributedCharacterIterator aci, char[] chars, int deletePos, StyledParagraph oldParagraph);

    public int getRunLimit(int index);

    public Decoration getDecorationAt(int index);

    public Object getFontOrGraphicAt(int index);

    private static int findRunContaining(int index, int[] starts);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static int[] addToVector(Object obj, int index, Vector v, int[] starts);

    private void addDecoration(Decoration d, int index);

    private void addFont(Object f, int index);

    private void addFonts(char[] chars, Map<? extends Attribute, ?> attributes, int start, int limit);

    static Map<? extends Attribute, ?> addInputMethodAttrs(Map<? extends Attribute, ?> oldStyles);

    private static Object getGraphicOrFont(Map<? extends Attribute, ?> attributes);
}
