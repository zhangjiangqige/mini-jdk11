package org.checkerframework.checker.formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.formatter.qual.ConversionCategory;
import org.checkerframework.checker.formatter.qual.ReturnsFormat;

public class FormatUtil {

    private static class Conversion {

        private final int index;

        private final ConversionCategory cath;

        public Conversion(char c, int index) {
            this.index = index;
            this.cath = ConversionCategory.fromConversionChar(c);
        }

        int index();

        ConversionCategory category();
    }

    @ReturnsFormat
    public static String asFormat(String format, ConversionCategory... cc) throws IllegalFormatException;

    public static void tryFormatSatisfiability(String format) throws IllegalFormatException;

    public static ConversionCategory[] formatParameterCategories(String format) throws IllegalFormatException;

    private static final String formatSpecifier = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";

    private static Pattern fsPattern = Pattern.compile(formatSpecifier);

    private static int indexFromFormat(Matcher m);

    private static char conversionCharFromFormat(Matcher m);

    private static Conversion[] parse(String format);

    public static class ExcessiveOrMissingFormatArgumentException extends MissingFormatArgumentException {

        private static final long serialVersionUID = 17000126L;

        private final int expected;

        private final int found;

        public ExcessiveOrMissingFormatArgumentException(int expected, int found) {
            super("-");
            this.expected = expected;
            this.found = found;
        }

        public int getExpected();

        public int getFound();

        @Override
        public String getMessage();
    }

    public static class IllegalFormatConversionCategoryException extends IllegalFormatConversionException {

        private static final long serialVersionUID = 17000126L;

        private final ConversionCategory expected;

        private final ConversionCategory found;

        public IllegalFormatConversionCategoryException(ConversionCategory expected, ConversionCategory found) {
            super(expected.chars.length() == 0 ? '-' : expected.chars.charAt(0), found.types == null ? Object.class : found.types[0]);
            this.expected = expected;
            this.found = found;
        }

        public ConversionCategory getExpected();

        public ConversionCategory getFound();

        @Override
        public String getMessage();
    }
}
