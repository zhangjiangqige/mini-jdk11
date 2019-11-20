package org.checkerframework.checker.i18nformatter.qual;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public enum I18nConversionCategory {

    UNUSED(null, null), GENERAL(null, null), DATE(new Class<?>[] { Date.class, Number.class }, new String[] { "date", "time" }), NUMBER(new Class<?>[] { Number.class }, new String[] { "number", "choice" });

    @SuppressWarnings("ImmutableEnumChecker")
    public final Class<? extends Object>[] types;

    @SuppressWarnings("ImmutableEnumChecker")
    public final String[] strings;

    public static I18nConversionCategory stringToI18nConversionCategory(String string);

    public static boolean isSubsetOf(I18nConversionCategory a, I18nConversionCategory b);

    public static I18nConversionCategory intersect(I18nConversionCategory a, I18nConversionCategory b);

    public static I18nConversionCategory union(I18nConversionCategory a, I18nConversionCategory b);

    @Override
    public String toString();
}
