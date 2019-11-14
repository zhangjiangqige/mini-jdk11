package java.io;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public class StreamTokenizer {

    private Reader reader = null;

    private InputStream input = null;

    private char[] buf = new char[20];

    private int peekc = NEED_CHAR;

    private static final int NEED_CHAR = Integer.MAX_VALUE;

    private static final int SKIP_LF = Integer.MAX_VALUE - 1;

    private boolean pushedBack;

    private boolean forceLower;

    private int LINENO = 1;

    private boolean eolIsSignificantP = false;

    private boolean slashSlashCommentsP = false;

    private boolean slashStarCommentsP = false;

    private byte[] ctype = new byte[256];

    private static final byte CT_WHITESPACE = 1;

    private static final byte CT_DIGIT = 2;

    private static final byte CT_ALPHA = 4;

    private static final byte CT_QUOTE = 8;

    private static final byte CT_COMMENT = 16;

    public int ttype = TT_NOTHING;

    public static final int TT_EOF = -1;

    public static final int TT_EOL = '\n';

    public static final int TT_NUMBER = -2;

    public static final int TT_WORD = -3;

    private static final int TT_NOTHING = -4;

    @Nullable
    public String sval;

    public double nval;

    private StreamTokenizer() {
        wordChars('a', 'z');
        wordChars('A', 'Z');
        wordChars(128 + 32, 255);
        whitespaceChars(0, ' ');
        commentChar('/');
        quoteChar('"');
        quoteChar('\'');
        parseNumbers();
    }

    @Deprecated
    public StreamTokenizer(InputStream is) {
        this();
        if (is == null) {
            throw new NullPointerException();
        }
        input = is;
    }

    public StreamTokenizer(Reader r) {
        this();
        if (r == null) {
            throw new NullPointerException();
        }
        reader = r;
    }

    public void resetSyntax();

    public void wordChars(int low, int hi);

    public void whitespaceChars(int low, int hi);

    public void ordinaryChars(int low, int hi);

    public void ordinaryChar(int ch);

    public void commentChar(int ch);

    public void quoteChar(int ch);

    public void parseNumbers();

    public void eolIsSignificant(boolean flag);

    public void slashStarComments(boolean flag);

    public void slashSlashComments(boolean flag);

    public void lowerCaseMode(boolean fl);

    private int read() throws IOException;

    public int nextToken() throws IOException;

    public void pushBack();

    @NonNegative
    public int lineno();

    @SideEffectFree
    public String toString(@GuardSatisfied StreamTokenizer this);
}
