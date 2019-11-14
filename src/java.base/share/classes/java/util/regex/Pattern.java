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

    public static final int UNIX_LINES = 0x01;

    public static final int CASE_INSENSITIVE = 0x02;

    public static final int COMMENTS = 0x04;

    public static final int MULTILINE = 0x08;

    public static final int LITERAL = 0x10;

    public static final int DOTALL = 0x20;

    public static final int UNICODE_CASE = 0x40;

    public static final int CANON_EQ = 0x80;

    public static final int UNICODE_CHARACTER_CLASS = 0x100;

    private static final int ALL_FLAGS = CASE_INSENSITIVE | MULTILINE | DOTALL | UNICODE_CASE | CANON_EQ | UNIX_LINES | LITERAL | UNICODE_CHARACTER_CLASS | COMMENTS;

    private static final long serialVersionUID = 5073258162644648461L;

    private String pattern;

    private int flags;

    private transient int flags0;

    private transient volatile boolean compiled;

    private transient String normalizedPattern;

    transient Node root;

    transient Node matchRoot;

    transient int[] buffer;

    transient CharPredicate predicate;

    transient volatile Map<String, Integer> namedGroups;

    transient GroupHead[] groupNodes;

    transient List<Node> topClosureNodes;

    transient int localTCNCount;

    transient boolean hasGroupRef;

    private transient int[] temp;

    transient int capturingGroupCount;

    transient int localCount;

    private transient int cursor;

    private transient int patternLength;

    private transient boolean hasSupplementary;

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

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    private Pattern(String p, int f) {
        if ((f & ~ALL_FLAGS) != 0) {
            throw new IllegalArgumentException("Unknown flag 0x" + Integer.toHexString(f));
        }
        pattern = p;
        flags = f;
        if ((flags & UNICODE_CHARACTER_CLASS) != 0)
            flags |= UNICODE_CASE;
        flags0 = flags;
        capturingGroupCount = 1;
        localCount = 0;
        localTCNCount = 0;
        if (pattern.length() > 0) {
            compile();
        } else {
            root = new Start(lastAccept);
            matchRoot = lastAccept;
        }
    }

    private static String normalize(String pattern);

    private static void normalizeSlice(String src, int off, int limit, StringBuilder dst);

    private static void normalizeClazz(String src, int off, int limit, StringBuilder dst);

    private static void produceEquivalentAlternation(String src, Set<String> dst);

    private static String[] producePermutations(String input);

    private static int getClass(int c);

    private static String composeOneStep(String input);

    private void RemoveQEQuoting();

    private void compile();

    Map<String, Integer> namedGroups();

    static final class TreeInfo {

        int minLength;

        int maxLength;

        boolean maxValid;

        boolean deterministic;

        TreeInfo() {
            reset();
        }

        void reset();
    }

    private boolean has(int f);

    private void accept(int ch, String s);

    private void mark(int c);

    private int peek();

    private int read();

    private int readEscaped();

    private int next();

    private int nextEscaped();

    private int peekPastWhitespace(int ch);

    private int parsePastWhitespace(int ch);

    private int parsePastLine();

    private int peekPastLine();

    private boolean isLineSeparator(int ch);

    private int skip();

    private void unread();

    private PatternSyntaxException error(String s);

    private boolean findSupplementary(int start, int end);

    private static final boolean isSupplementary(int ch);

    private Node expr(Node end);

    @SuppressWarnings("fallthrough")
    private Node sequence(Node end);

    @SuppressWarnings("fallthrough")
    private Node atom();

    private void append(int ch, int len);

    private Node ref(int refNum);

    private int escape(boolean inclass, boolean create, boolean isrange);

    private CharPredicate clazz(boolean consume);

    private CharPredicate bitsOrSingle(BitClass bits, int ch);

    private CharPredicate single(final int ch);

    private CharPredicate range(BitClass bits);

    private CharPredicate family(boolean singleLetter, boolean isComplement);

    private CharProperty newCharProperty(CharPredicate p);

    private String groupname(int ch);

    private Node group0();

    private Node createGroup(boolean anonymous);

    @SuppressWarnings("fallthrough")
    private void addFlag();

    @SuppressWarnings("fallthrough")
    private void subFlag();

    static final int MAX_REPS = 0x7FFFFFFF;

    static enum Qtype {

        GREEDY, LAZY, POSSESSIVE, INDEPENDENT
    }

    private Node curly(Node prev, int cmin);

    private Node closure(Node prev);

    private int c();

    private int o();

    private int x();

    private int cursor();

    private void setcursor(int pos);

    private int uxxxx();

    private int u();

    private int N();

    private static final int countChars(CharSequence seq, int index, int lengthInCodePoints);

    private static final int countCodePoints(CharSequence seq);

    static final class BitClass extends BmpCharProperty {

        final boolean[] bits;

        BitClass() {
            this(new boolean[256]);
        }

        private BitClass(boolean[] bits) {
            super(ch -> ch < 256 && bits[ch]);
            this.bits = bits;
        }

        BitClass add(int c, int flags);
    }

    private Node newSlice(int[] buf, int count, boolean hasSupplementary);

    static class Node extends Object {

        Node next;

        Node() {
            next = Pattern.accept;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class LastNode extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static class Start extends Node {

        int minLength;

        Start(Node node) {
            this.next = node;
            TreeInfo info = new TreeInfo();
            next.study(info);
            minLength = info.minLength;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class StartS extends Start {

        StartS(Node node) {
            super(node);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class Begin extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class End extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class Caret extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class UnixCaret extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class LastMatch extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class Dollar extends Node {

        boolean multiline;

        Dollar(boolean mul) {
            multiline = mul;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class UnixDollar extends Node {

        boolean multiline;

        UnixDollar(boolean mul) {
            multiline = mul;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class LineEnding extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class CharProperty extends Node {

        CharPredicate predicate;

        CharProperty(CharPredicate predicate) {
            this.predicate = predicate;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    private static class BmpCharProperty extends CharProperty {

        BmpCharProperty(BmpCharPredicate predicate) {
            super(predicate);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    private static class NFCCharProperty extends Node {

        CharPredicate predicate;

        NFCCharProperty(CharPredicate predicate) {
            this.predicate = predicate;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class XGrapheme extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class GraphemeBound extends Node {

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static class SliceNode extends Node {

        int[] buffer;

        SliceNode(int[] buf) {
            buffer = buf;
        }

        boolean study(TreeInfo info);
    }

    static class Slice extends SliceNode {

        Slice(int[] buf) {
            super(buf);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static class SliceI extends SliceNode {

        SliceI(int[] buf) {
            super(buf);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class SliceU extends SliceNode {

        SliceU(int[] buf) {
            super(buf);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class SliceS extends Slice {

        SliceS(int[] buf) {
            super(buf);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static class SliceIS extends SliceNode {

        SliceIS(int[] buf) {
            super(buf);
        }

        int toLower(int c);

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class SliceUS extends SliceIS {

        SliceUS(int[] buf) {
            super(buf);
        }

        int toLower(int c);
    }

    static final class Ques extends Node {

        Node atom;

        Qtype type;

        Ques(Node node, Qtype type) {
            this.atom = node;
            this.type = type;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class CharPropertyGreedy extends Node {

        final CharPredicate predicate;

        final int cmin;

        CharPropertyGreedy(CharProperty cp, int cmin) {
            this.predicate = cp.predicate;
            this.cmin = cmin;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class BmpCharPropertyGreedy extends CharPropertyGreedy {

        BmpCharPropertyGreedy(BmpCharProperty bcp, int cmin) {
            super(bcp, cmin);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class Curly extends Node {

        Node atom;

        Qtype type;

        int cmin;

        int cmax;

        Curly(Node node, int cmin, int cmax, Qtype type) {
            this.atom = node;
            this.type = type;
            this.cmin = cmin;
            this.cmax = cmax;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean match0(Matcher matcher, int i, int j, CharSequence seq);

        boolean match1(Matcher matcher, int i, int j, CharSequence seq);

        boolean match2(Matcher matcher, int i, int j, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class GroupCurly extends Node {

        Node atom;

        Qtype type;

        int cmin;

        int cmax;

        int localIndex;

        int groupIndex;

        boolean capture;

        GroupCurly(Node node, int cmin, int cmax, Qtype type, int local, int group, boolean capture) {
            this.atom = node;
            this.type = type;
            this.cmin = cmin;
            this.cmax = cmax;
            this.localIndex = local;
            this.groupIndex = group;
            this.capture = capture;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean match0(Matcher matcher, int i, int j, CharSequence seq);

        boolean match1(Matcher matcher, int i, int j, CharSequence seq);

        boolean match2(Matcher matcher, int i, int j, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class BranchConn extends Node {

        BranchConn() {
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class Branch extends Node {

        Node[] atoms = new Node[2];

        int size = 2;

        Node conn;

        Branch(Node first, Node second, Node branchConn) {
            conn = branchConn;
            atoms[0] = first;
            atoms[1] = second;
        }

        void add(Node node);

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class GroupHead extends Node {

        int localIndex;

        GroupTail tail;

        GroupHead(int localCount) {
            localIndex = localCount;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean matchRef(Matcher matcher, int i, CharSequence seq);
    }

    static final class GroupRef extends Node {

        GroupHead head;

        GroupRef(GroupHead head) {
            this.head = head;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class GroupTail extends Node {

        int localIndex;

        int groupIndex;

        GroupTail(int localCount, int groupCount) {
            localIndex = localCount;
            groupIndex = groupCount + groupCount;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class Prolog extends Node {

        Loop loop;

        Prolog(Loop loop) {
            this.loop = loop;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class Loop extends Node {

        Node body;

        int countIndex;

        int beginIndex;

        int cmin, cmax;

        int posIndex;

        Loop(int countIndex, int beginIndex) {
            this.countIndex = countIndex;
            this.beginIndex = beginIndex;
            this.posIndex = -1;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean matchInit(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class LazyLoop extends Loop {

        LazyLoop(int countIndex, int beginIndex) {
            super(countIndex, beginIndex);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean matchInit(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class BackRef extends Node {

        int groupIndex;

        BackRef(int groupCount) {
            super();
            groupIndex = groupCount + groupCount;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static class CIBackRef extends Node {

        int groupIndex;

        boolean doUnicodeCase;

        CIBackRef(int groupCount, boolean doUnicodeCase) {
            super();
            groupIndex = groupCount + groupCount;
            this.doUnicodeCase = doUnicodeCase;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class First extends Node {

        Node atom;

        First(Node node) {
            this.atom = BnM.optimize(node);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class Conditional extends Node {

        Node cond, yes, not;

        Conditional(Node cond, Node yes, Node not) {
            this.cond = cond;
            this.yes = yes;
            this.not = not;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class Pos extends Node {

        Node cond;

        Pos(Node cond) {
            this.cond = cond;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class Neg extends Node {

        Node cond;

        Neg(Node cond) {
            this.cond = cond;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static Node lookbehindEnd = new Node() {

        boolean match(Matcher matcher, int i, CharSequence seq) {
            return i == matcher.lookbehindTo;
        }
    };

    static class Behind extends Node {

        Node cond;

        int rmax, rmin;

        Behind(Node cond, int rmax, int rmin) {
            this.cond = cond;
            this.rmax = rmax;
            this.rmin = rmin;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class BehindS extends Behind {

        BehindS(Node cond, int rmax, int rmin) {
            super(cond, rmax, rmin);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static class NotBehind extends Node {

        Node cond;

        int rmax, rmin;

        NotBehind(Node cond, int rmax, int rmin) {
            this.cond = cond;
            this.rmax = rmax;
            this.rmin = rmin;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class NotBehindS extends NotBehind {

        NotBehindS(Node cond, int rmax, int rmin) {
            super(cond, rmax, rmin);
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    static final class Bound extends Node {

        static int LEFT = 0x1;

        static int RIGHT = 0x2;

        static int BOTH = 0x3;

        static int NONE = 0x4;

        int type;

        boolean useUWORD;

        Bound(int n, boolean useUWORD) {
            type = n;
            this.useUWORD = useUWORD;
        }

        boolean isWord(int ch);

        int check(Matcher matcher, int i, CharSequence seq);

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    private static boolean hasBaseCharacter(Matcher matcher, int i, CharSequence seq);

    static class BnM extends Node {

        int[] buffer;

        int[] lastOcc;

        int[] optoSft;

        static Node optimize(Node node);

        BnM(int[] src, int[] lastOcc, int[] optoSft, Node next) {
            this.buffer = src;
            this.lastOcc = lastOcc;
            this.optoSft = optoSft;
            this.next = next;
        }

        boolean match(Matcher matcher, int i, CharSequence seq);

        boolean study(TreeInfo info);
    }

    static final class BnMS extends BnM {

        int lengthInChars;

        BnMS(int[] src, int[] lastOcc, int[] optoSft, Node next) {
            super(src, lastOcc, optoSft, next);
            for (int cp : buffer) {
                lengthInChars += Character.charCount(cp);
            }
        }

        boolean match(Matcher matcher, int i, CharSequence seq);
    }

    @FunctionalInterface
    static interface CharPredicate {

        boolean is(int ch);

        default CharPredicate and(CharPredicate p);

        default CharPredicate union(CharPredicate p);

        default CharPredicate union(CharPredicate p1, CharPredicate p2);

        default CharPredicate negate();
    }

    static interface BmpCharPredicate extends CharPredicate {

        default CharPredicate and(CharPredicate p);

        default CharPredicate union(CharPredicate p);

        static CharPredicate union(CharPredicate... predicates);
    }

    static BmpCharPredicate VertWS();

    static BmpCharPredicate HorizWS();

    static CharPredicate ALL();

    static CharPredicate DOT();

    static CharPredicate UNIXDOT();

    static CharPredicate SingleS(int c);

    static BmpCharPredicate Single(int c);

    static BmpCharPredicate SingleI(int lower, int upper);

    static CharPredicate SingleU(int lower);

    private static boolean inRange(int lower, int ch, int upper);

    static CharPredicate Range(int lower, int upper);

    static CharPredicate CIRange(int lower, int upper);

    static CharPredicate CIRangeU(int lower, int upper);

    static final Node accept = new Node();

    static final Node lastAccept = new LastNode();

    public Predicate<String> asPredicate();

    public Predicate<String> asMatchPredicate();

    public Stream<String> splitAsStream(final CharSequence input);
}
