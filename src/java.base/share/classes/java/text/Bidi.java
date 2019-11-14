package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.text.bidi.BidiBase;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Bidi {

    public static final int DIRECTION_LEFT_TO_RIGHT = 0;

    public static final int DIRECTION_RIGHT_TO_LEFT = 1;

    public static final int DIRECTION_DEFAULT_LEFT_TO_RIGHT = -2;

    public static final int DIRECTION_DEFAULT_RIGHT_TO_LEFT = -1;

    private BidiBase bidiBase;

    public Bidi(String paragraph, int flags) {
        if (paragraph == null) {
            throw new IllegalArgumentException("paragraph is null");
        }
        bidiBase = new BidiBase(paragraph.toCharArray(), 0, null, 0, paragraph.length(), flags);
    }

    public Bidi(AttributedCharacterIterator paragraph) {
        if (paragraph == null) {
            throw new IllegalArgumentException("paragraph is null");
        }
        bidiBase = new BidiBase(0, 0);
        bidiBase.setPara(paragraph);
    }

    public Bidi(char[] text, int textStart, byte[] embeddings, int embStart, int paragraphLength, int flags) {
        if (text == null) {
            throw new IllegalArgumentException("text is null");
        }
        if (paragraphLength < 0) {
            throw new IllegalArgumentException("bad length: " + paragraphLength);
        }
        if (textStart < 0 || paragraphLength > text.length - textStart) {
            throw new IllegalArgumentException("bad range: " + textStart + " length: " + paragraphLength + " for text of length: " + text.length);
        }
        if (embeddings != null && (embStart < 0 || paragraphLength > embeddings.length - embStart)) {
            throw new IllegalArgumentException("bad range: " + embStart + " length: " + paragraphLength + " for embeddings of length: " + text.length);
        }
        bidiBase = new BidiBase(text, textStart, embeddings, embStart, paragraphLength, flags);
    }

    public Bidi createLineBidi(int lineStart, int lineLimit);

    public boolean isMixed();

    public boolean isLeftToRight();

    public boolean isRightToLeft();

    public int getLength();

    public boolean baseIsLeftToRight();

    public int getBaseLevel();

    public int getLevelAt(int offset);

    public int getRunCount();

    public int getRunLevel(int run);

    public int getRunStart(int run);

    public int getRunLimit(int run);

    public static boolean requiresBidi(char[] text, int start, int limit);

    public static void reorderVisually(byte[] levels, int levelStart, Object[] objects, int objectStart, int count);

    public String toString();
}
