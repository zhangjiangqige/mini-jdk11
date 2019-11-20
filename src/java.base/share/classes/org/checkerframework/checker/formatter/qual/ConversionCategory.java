package org.checkerframework.checker.formatter.qual;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.checkerframework.dataflow.qual.Pure;

public enum ConversionCategory {

    GENERAL(null, "bBhHsS"),
    CHAR(new Class<?>[] { Character.class, Byte.class, Short.class, Integer.class }, "cC"),
    INT(new Class<?>[] { Byte.class, Short.class, Integer.class, Long.class, BigInteger.class }, "doxX"),
    FLOAT(new Class<?>[] { Float.class, Double.class, BigDecimal.class }, "eEfgGaA"),
    TIME(new Class<?>[] { Long.class, Calendar.class, Date.class }, "tT"),
    CHAR_AND_INT(new Class<?>[] { Byte.class, Short.class, Integer.class }, null),
    INT_AND_TIME(new Class<?>[] { Long.class }, null),
    NULL(new Class<?>[0], null),
    UNUSED(null, null);

    @SuppressWarnings("ImmutableEnumChecker")
    public final Class<? extends Object>[] types;

    public final String chars;

    public static ConversionCategory fromConversionChar(char c);

    public static boolean isSubsetOf(ConversionCategory a, ConversionCategory b);

    public static ConversionCategory intersect(ConversionCategory a, ConversionCategory b);

    public static ConversionCategory union(ConversionCategory a, ConversionCategory b);

    @Pure
    @Override
    public String toString();
}
