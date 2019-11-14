package org.checkerframework.checker.i18nformatter;

import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.checkerframework.checker.i18nformatter.qual.I18nChecksFormat;
import org.checkerframework.checker.i18nformatter.qual.I18nConversionCategory;
import org.checkerframework.checker.i18nformatter.qual.I18nValidFormat;

public class I18nFormatUtil {

    public static void tryFormatSatisfiability(String format) throws IllegalFormatException;

    public static I18nConversionCategory[] formatParameterCategories(String format) throws IllegalFormatException;

    @I18nChecksFormat
    public static boolean hasFormat(String format, I18nConversionCategory... cc);

    @I18nValidFormat
    public static boolean isFormat(String format);

    private static class I18nConversion {

        public int index;

        public I18nConversionCategory category;

        public I18nConversion(int index, I18nConversionCategory category) {
            this.index = index;
            this.category = category;
        }

        @Override
        public String toString();
    }

    private static class MessageFormatParser {

        public static int maxOffset;

        private static Locale locale;

        private static List<I18nConversionCategory> categories;

        private static List<Integer> argumentIndices;

        private static int numFormat;

        private static final int SEG_RAW = 0;

        private static final int SEG_INDEX = 1;

        private static final int SEG_TYPE = 2;

        private static final int SEG_MODIFIER = 3;

        private static final int TYPE_NULL = 0;

        private static final int TYPE_NUMBER = 1;

        private static final int TYPE_DATE = 2;

        private static final int TYPE_TIME = 3;

        private static final int TYPE_CHOICE = 4;

        private static final String[] TYPE_KEYWORDS = { "", "number", "date", "time", "choice" };

        private static final int MODIFIER_DEFAULT = 0;

        private static final int MODIFIER_CURRENCY = 1;

        private static final int MODIFIER_PERCENT = 2;

        private static final int MODIFIER_INTEGER = 3;

        private static final String[] NUMBER_MODIFIER_KEYWORDS = { "", "currency", "percent", "integer" };

        private static final String[] DATE_TIME_MODIFIER_KEYWORDS = { "", "short", "medium", "long", "full" };

        public static I18nConversion[] parse(String pattern);

        private static void applyPattern(String pattern);

        private static void makeFormat(int offsetNumber, StringBuilder[] textSegments);

        private static final int findKeyword(String s, String[] list);
    }
}
