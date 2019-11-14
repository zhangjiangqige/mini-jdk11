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

    private RegexUtil() {
        throw new Error("do not instantiate");
    }

    public static class CheckedPatternSyntaxException extends Exception {

        private static final long serialVersionUID = 6266881831979001480L;

        private final PatternSyntaxException pse;

        public CheckedPatternSyntaxException(PatternSyntaxException pse) {
            this.pse = pse;
        }

        public CheckedPatternSyntaxException(String desc, String regex, @GTENegativeOne int index) {
            this(new PatternSyntaxException(desc, regex, index));
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

    @SideEffectFree
    private static String regexErrorMessage(String s, int expectedGroups, int actualGroups);

    @SuppressWarnings({ "purity", "lock" })
    @Pure
    private static int getGroupCount(Pattern p);
}
