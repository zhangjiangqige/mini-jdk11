package java.util.regex;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
final class ASCII {

    static final int UPPER = 0x00000100;

    static final int LOWER = 0x00000200;

    static final int DIGIT = 0x00000400;

    static final int SPACE = 0x00000800;

    static final int PUNCT = 0x00001000;

    static final int CNTRL = 0x00002000;

    static final int BLANK = 0x00004000;

    static final int HEX = 0x00008000;

    static final int UNDER = 0x00010000;

    static final int ASCII = 0x0000FF00;

    static final int ALPHA = (UPPER | LOWER);

    static final int ALNUM = (UPPER | LOWER | DIGIT);

    static final int GRAPH = (PUNCT | UPPER | LOWER | DIGIT);

    static final int WORD = (UPPER | LOWER | UNDER | DIGIT);

    static final int XDIGIT = (HEX);

    private static final int[] ctype = new int[] { CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, SPACE + CNTRL + BLANK, SPACE + CNTRL, SPACE + CNTRL, SPACE + CNTRL, SPACE + CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, CNTRL, SPACE + BLANK, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, DIGIT + HEX + 0, DIGIT + HEX + 1, DIGIT + HEX + 2, DIGIT + HEX + 3, DIGIT + HEX + 4, DIGIT + HEX + 5, DIGIT + HEX + 6, DIGIT + HEX + 7, DIGIT + HEX + 8, DIGIT + HEX + 9, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT, UPPER + HEX + 10, UPPER + HEX + 11, UPPER + HEX + 12, UPPER + HEX + 13, UPPER + HEX + 14, UPPER + HEX + 15, UPPER + 16, UPPER + 17, UPPER + 18, UPPER + 19, UPPER + 20, UPPER + 21, UPPER + 22, UPPER + 23, UPPER + 24, UPPER + 25, UPPER + 26, UPPER + 27, UPPER + 28, UPPER + 29, UPPER + 30, UPPER + 31, UPPER + 32, UPPER + 33, UPPER + 34, UPPER + 35, PUNCT, PUNCT, PUNCT, PUNCT, PUNCT | UNDER, PUNCT, LOWER + HEX + 10, LOWER + HEX + 11, LOWER + HEX + 12, LOWER + HEX + 13, LOWER + HEX + 14, LOWER + HEX + 15, LOWER + 16, LOWER + 17, LOWER + 18, LOWER + 19, LOWER + 20, LOWER + 21, LOWER + 22, LOWER + 23, LOWER + 24, LOWER + 25, LOWER + 26, LOWER + 27, LOWER + 28, LOWER + 29, LOWER + 30, LOWER + 31, LOWER + 32, LOWER + 33, LOWER + 34, LOWER + 35, PUNCT, PUNCT, PUNCT, PUNCT, CNTRL };

    static int getType(int ch);

    static boolean isType(int ch, int type);

    static boolean isAscii(int ch);

    static boolean isAlpha(int ch);

    static boolean isDigit(int ch);

    static boolean isAlnum(int ch);

    static boolean isGraph(int ch);

    static boolean isPrint(int ch);

    static boolean isPunct(int ch);

    static boolean isSpace(int ch);

    static boolean isHexDigit(int ch);

    static boolean isOctDigit(int ch);

    static boolean isCntrl(int ch);

    static boolean isLower(int ch);

    static boolean isUpper(int ch);

    static boolean isWord(int ch);

    static int toDigit(int ch);

    static int toLower(int ch);

    static int toUpper(int ch);
}
