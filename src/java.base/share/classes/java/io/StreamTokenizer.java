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

    public int ttype;

    public static final int TT_EOF;

    public static final int TT_EOL;

    public static final int TT_NUMBER;

    public static final int TT_WORD;

    @Nullable
    public String sval;

    public double nval;

    @Deprecated
    public StreamTokenizer(InputStream is) {
    }

    public StreamTokenizer(Reader r) {
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

    public int nextToken() throws IOException;

    public void pushBack();

    @NonNegative
    public int lineno();

    @SideEffectFree
    public String toString(@GuardSatisfied StreamTokenizer this);
}
