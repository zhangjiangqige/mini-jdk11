package java.util.regex;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.regex.qual.PolyRegex;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "index", "interning", "lock", "nullness", "regex" })
@UsesObjectEquals
public final class Pattern implements java.io.Serializable {

    public static final int UNIX_LINES;

    public static final int CASE_INSENSITIVE;

    public static final int COMMENTS;

    public static final int MULTILINE;

    public static final int LITERAL;

    public static final int DOTALL;

    public static final int UNICODE_CASE;

    public static final int CANON_EQ;

    public static final int UNICODE_CHARACTER_CLASS;

    @CFComment({ "lock/nullness: pure wrt equals(@GuardSatisfied Pattern this) but not ==" })
    @Pure
    @Regex
    public static Pattern compile(@Regex String regex);

    @CFComment({ "lock/nullness: pure wrt equals(@GuardSatisfied Pattern this) but not ==" })
    @Pure
    public static Pattern compile(@Regex String regex, int flags);

    public String pattern();

    @SideEffectFree
    public String toString(@GuardSatisfied Pattern this);

    @PolyRegex
    public Matcher matcher(@PolyRegex Pattern this, CharSequence input);

    public int flags();

    public static boolean matches(@Regex String regex, CharSequence input);

    public String[] split(CharSequence input, int limit);

    public String[] split(CharSequence input);

    @CFComment({ "nullness: pure wrt equals() but not ==" })
    @Pure
    @Regex
    public static String quote(String s);

    static enum Qtype {

        GREEDY, LAZY, POSSESSIVE, INDEPENDENT
    }

    public Predicate<String> asPredicate();

    public Predicate<String> asMatchPredicate();

    public Stream<String> splitAsStream(final CharSequence input);
}
