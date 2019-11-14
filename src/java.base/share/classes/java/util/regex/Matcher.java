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

    Pattern parentPattern;

    int[] groups;

    int from, to;

    int lookbehindTo;

    CharSequence text;

    static final int ENDANCHOR = 1;

    static final int NOANCHOR = 0;

    int acceptMode = NOANCHOR;

    int first = -1, last = 0;

    int oldLast = -1;

    int lastAppendPosition = 0;

    int[] locals;

    IntHashSet[] localsPos;

    boolean hitEnd;

    boolean requireEnd;

    boolean transparentBounds = false;

    boolean anchoringBounds = true;

    int modCount;

    Matcher() {
    }

    Matcher(Pattern parent, CharSequence text) {
        this.parentPattern = parent;
        this.text = text;
        int parentGroupCount = Math.max(parent.capturingGroupCount, 10);
        groups = new int[parentGroupCount * 2];
        locals = new int[parent.localCount];
        localsPos = new IntHashSet[parent.localTCNCount];
        reset();
    }

    public Pattern pattern();

    public MatchResult toMatchResult();

    private MatchResult toMatchResult(String text);

    private static class ImmutableMatchResult implements MatchResult {

        private final int first;

        private final int last;

        private final int[] groups;

        private final int groupCount;

        private final String text;

        ImmutableMatchResult(int first, int last, int groupCount, int[] groups, String text) {
            this.first = first;
            this.last = last;
            this.groupCount = groupCount;
            this.groups = groups;
            this.text = text;
        }

        @Override
        public int start();

        @Override
        public int start(int group);

        @Override
        public int end();

        @Override
        public int end(int group);

        @Override
        public int groupCount();

        @Override
        public String group();

        @Override
        public String group(int group);

        private void checkMatch();
    }

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

    private StringBuilder appendExpandedReplacement(String replacement, StringBuilder result);

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

    boolean search(int from);

    boolean match(int from, int anchor);

    int getTextLength();

    CharSequence getSubSequence(int beginIndex, int endIndex);

    char charAt(int i);

    int getMatchedGroupIndex(String name);
}
