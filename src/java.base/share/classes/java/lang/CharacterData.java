package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
abstract class CharacterData {

    abstract int getProperties(int ch);

    abstract int getType(int ch);

    abstract boolean isWhitespace(int ch);

    abstract boolean isMirrored(int ch);

    abstract boolean isJavaIdentifierStart(int ch);

    abstract boolean isJavaIdentifierPart(int ch);

    abstract boolean isUnicodeIdentifierStart(int ch);

    abstract boolean isUnicodeIdentifierPart(int ch);

    abstract boolean isIdentifierIgnorable(int ch);

    abstract int toLowerCase(int ch);

    abstract int toUpperCase(int ch);

    abstract int toTitleCase(int ch);

    abstract int digit(int ch, int radix);

    abstract int getNumericValue(int ch);

    abstract byte getDirectionality(int ch);

    int toUpperCaseEx(int ch);

    char[] toUpperCaseCharArray(int ch);

    boolean isOtherLowercase(int ch);

    boolean isOtherUppercase(int ch);

    boolean isOtherAlphabetic(int ch);

    boolean isIdeographic(int ch);

    static final CharacterData of(int ch);
}
