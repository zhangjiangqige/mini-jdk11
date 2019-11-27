// This class should be kept in sync with plume.RegexUtil.
package org.checkerframework.checker.regex;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.EnsuresQualifierIf;

@SuppressWarnings("purity")
public final class RegexUtil {

    public static class CheckedPatternSyntaxException extends Exception {

        public CheckedPatternSyntaxException(PatternSyntaxException pse) {
        }

        public CheckedPatternSyntaxException(String desc, String regex, @GTENegativeOne int index) {
        }

        public String getDescription();

        public int getIndex();

        @Override
        @Pure
        public String getMessage(@GuardSatisfied CheckedPatternSyntaxException this);

        public String getPattern();
    }

    @Pure
    @EnsuresQualifierIf(result = true, expression = "#1", qualifier = Regex.class)
    public static boolean isRegex(String s);

    @SuppressWarnings({ "regex", "deterministic" })
    @Pure
    @EnsuresQualifierIf(result = true, expression = "#1", qualifier = Regex.class)
    public static boolean isRegex(String s, int groups);

    @SuppressWarnings({ "regex", "purity.not.deterministic.call", "lock" })
    @Pure
    @EnsuresQualifierIf(result = true, expression = "#1", qualifier = Regex.class)
    public static boolean isRegex(final char c);

    @SideEffectFree
    @Nullable
    public static String regexError(String s);

    @SuppressWarnings({ "regex", "not.sef" })
    @SideEffectFree
    @Nullable
    public static String regexError(String s, int groups);

    @SideEffectFree
    @Nullable
    public static PatternSyntaxException regexException(String s);

    @SuppressWarnings("regex")
    @SideEffectFree
    @Nullable
    public static PatternSyntaxException regexException(String s, int groups);

    @SideEffectFree
    @Regex
    public static String asRegex(String s);

    @SuppressWarnings("regex")
    @SideEffectFree
    @Regex
    public static String asRegex(String s, int groups);
}
