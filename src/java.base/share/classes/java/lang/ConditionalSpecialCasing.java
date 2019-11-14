package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.text.BreakIterator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import sun.text.Normalizer;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class ConditionalSpecialCasing {

    static final int FINAL_CASED = 1;

    static final int AFTER_SOFT_DOTTED = 2;

    static final int MORE_ABOVE = 3;

    static final int AFTER_I = 4;

    static final int NOT_BEFORE_DOT = 5;

    static final int COMBINING_CLASS_ABOVE = 230;

    static Entry[] entry = { new Entry(0x03A3, new char[] { 0x03C2 }, new char[] { 0x03A3 }, null, FINAL_CASED), new Entry(0x0130, new char[] { 0x0069, 0x0307 }, new char[] { 0x0130 }, null, 0), new Entry(0x0307, new char[] { 0x0307 }, new char[] {}, "lt", AFTER_SOFT_DOTTED), new Entry(0x0049, new char[] { 0x0069, 0x0307 }, new char[] { 0x0049 }, "lt", MORE_ABOVE), new Entry(0x004A, new char[] { 0x006A, 0x0307 }, new char[] { 0x004A }, "lt", MORE_ABOVE), new Entry(0x012E, new char[] { 0x012F, 0x0307 }, new char[] { 0x012E }, "lt", MORE_ABOVE), new Entry(0x00CC, new char[] { 0x0069, 0x0307, 0x0300 }, new char[] { 0x00CC }, "lt", 0), new Entry(0x00CD, new char[] { 0x0069, 0x0307, 0x0301 }, new char[] { 0x00CD }, "lt", 0), new Entry(0x0128, new char[] { 0x0069, 0x0307, 0x0303 }, new char[] { 0x0128 }, "lt", 0), new Entry(0x0130, new char[] { 0x0069 }, new char[] { 0x0130 }, "tr", 0), new Entry(0x0130, new char[] { 0x0069 }, new char[] { 0x0130 }, "az", 0), new Entry(0x0307, new char[] {}, new char[] { 0x0307 }, "tr", AFTER_I), new Entry(0x0307, new char[] {}, new char[] { 0x0307 }, "az", AFTER_I), new Entry(0x0049, new char[] { 0x0131 }, new char[] { 0x0049 }, "tr", NOT_BEFORE_DOT), new Entry(0x0049, new char[] { 0x0131 }, new char[] { 0x0049 }, "az", NOT_BEFORE_DOT), new Entry(0x0069, new char[] { 0x0069 }, new char[] { 0x0130 }, "tr", 0), new Entry(0x0069, new char[] { 0x0069 }, new char[] { 0x0130 }, "az", 0) };

    static Hashtable<Integer, HashSet<Entry>> entryTable = new Hashtable<>();

    static {
        for (Entry cur : entry) {
            Integer cp = cur.getCodePoint();
            HashSet<Entry> set = entryTable.get(cp);
            if (set == null) {
                set = new HashSet<>();
                entryTable.put(cp, set);
            }
            set.add(cur);
        }
    }

    static int toLowerCaseEx(String src, int index, Locale locale);

    static int toUpperCaseEx(String src, int index, Locale locale);

    static char[] toLowerCaseCharArray(String src, int index, Locale locale);

    static char[] toUpperCaseCharArray(String src, int index, Locale locale);

    private static char[] lookUpTable(String src, int index, Locale locale, boolean bLowerCasing);

    private static boolean isConditionMet(String src, int index, Locale locale, int condition);

    private static boolean isFinalCased(String src, int index, Locale locale);

    private static boolean isAfterI(String src, int index);

    private static boolean isAfterSoftDotted(String src, int index);

    private static boolean isMoreAbove(String src, int index);

    private static boolean isBeforeDot(String src, int index);

    private static boolean isCased(int ch);

    private static boolean isSoftDotted(int ch);

    static class Entry {

        int ch;

        char[] lower;

        char[] upper;

        String lang;

        int condition;

        Entry(int ch, char[] lower, char[] upper, String lang, int condition) {
            this.ch = ch;
            this.lower = lower;
            this.upper = upper;
            this.lang = lang;
            this.condition = condition;
        }

        int getCodePoint();

        char[] getLowerCase();

        char[] getUpperCase();

        String getLanguage();

        int getCondition();
    }
}
