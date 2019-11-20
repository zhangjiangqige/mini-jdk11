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
}
