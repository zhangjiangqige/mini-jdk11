package java.util.regex;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public final class Matcher implements MatchResult {

    public Pattern pattern();

    public MatchResult toMatchResult();

    public Matcher usePattern(Pattern newPattern);

    public Matcher reset();

    public Matcher reset(CharSequence input);

    @Pure
    @NonNegative
    public int start();

    @Pure
    @GTENegativeOne
    public int start(@NonNegative int group);

    public int start(String name);

    @Pure
    @NonNegative
    public int end();

    @Pure
    @GTENegativeOne
    public int end(@NonNegative int group);

    public int end(String name);

    @SideEffectFree
    public String group();

    @SideEffectFree
    @Nullable
    public String group(@NonNegative int group);

    public String group(String name);

    @Pure
    @NonNegative
    public int groupCount();

    public boolean matches();

    public boolean find();

    public boolean find(@NonNegative int start);

    public boolean lookingAt();

    public static String quoteReplacement(String s);

    public Matcher appendReplacement(StringBuffer sb, String replacement);

    public Matcher appendReplacement(StringBuilder sb, String replacement);

    public StringBuffer appendTail(StringBuffer sb);

    public StringBuilder appendTail(StringBuilder sb);

    public String replaceAll(String replacement);

    public String replaceAll(Function<MatchResult, String> replacer);

    public Stream<MatchResult> results();

    public String replaceFirst(String replacement);

    public String replaceFirst(Function<MatchResult, String> replacer);

    public Matcher region(@NonNegative int start, @NonNegative int end);

    @Pure
    @NonNegative
    public int regionStart();

    @Pure
    @NonNegative
    public int regionEnd();

    @Pure
    public boolean hasTransparentBounds();

    public Matcher useTransparentBounds(boolean b);

    @Pure
    public boolean hasAnchoringBounds();

    public Matcher useAnchoringBounds(boolean b);

    @SideEffectFree
    public String toString(@GuardSatisfied Matcher this);

    @Pure
    public boolean hitEnd();

    @Pure
    public boolean requireEnd();
}
