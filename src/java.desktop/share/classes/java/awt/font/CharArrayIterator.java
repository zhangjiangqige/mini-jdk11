package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.text.CharacterIterator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class CharArrayIterator implements CharacterIterator {

    private char[] chars;

    private int pos;

    private int begin;

    CharArrayIterator(char[] chars) {
        reset(chars, 0);
    }

    CharArrayIterator(char[] chars, int begin) {
        reset(chars, begin);
    }

    public char first();

    public char last();

    public char current();

    public char next();

    public char previous();

    public char setIndex(int position);

    public int getBeginIndex();

    public int getEndIndex();

    public int getIndex();

    public Object clone();

    void reset(char[] chars);

    void reset(char[] chars, int begin);
}
