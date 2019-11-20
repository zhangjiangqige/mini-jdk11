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

    @ReturnsFormat
    public static String asFormat(String format, ConversionCategory... cc) throws IllegalFormatException;

    public static void tryFormatSatisfiability(String format) throws IllegalFormatException;

    public static ConversionCategory[] formatParameterCategories(String format) throws IllegalFormatException;

    public static class ExcessiveOrMissingFormatArgumentException extends MissingFormatArgumentException {

        public ExcessiveOrMissingFormatArgumentException(int expected, int found) {
        }

        public int getExpected();

        public int getFound();

        @Override
        public String getMessage();
    }

    public static class IllegalFormatConversionCategoryException extends IllegalFormatConversionException {

        public IllegalFormatConversionCategoryException(ConversionCategory expected, ConversionCategory found) {
        }

        public ConversionCategory getExpected();

        public ConversionCategory getFound();

        @Override
        public String getMessage();
    }
}
