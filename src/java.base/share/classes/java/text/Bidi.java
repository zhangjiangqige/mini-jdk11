package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.text.bidi.BidiBase;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Bidi {

    public static final int DIRECTION_LEFT_TO_RIGHT;

    public static final int DIRECTION_RIGHT_TO_LEFT;

    public static final int DIRECTION_DEFAULT_LEFT_TO_RIGHT;

    public static final int DIRECTION_DEFAULT_RIGHT_TO_LEFT;

    public Bidi(String paragraph, int flags) {
    }

    public Bidi(AttributedCharacterIterator paragraph) {
    }

    public Bidi(char[] text, int textStart, byte[] embeddings, int embStart, int paragraphLength, int flags) {
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
