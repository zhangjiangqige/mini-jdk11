package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.*;

@AnnotatedFor({ "indexs", "interning", "lock", "nullness" })
@UsesObjectEquals
public class StringTokenizer implements Enumeration<Object> {

    private int currentPosition;

    private int newPosition;

    private int maxPosition;

    private String str;

    private String delimiters;

    private boolean retDelims;

    private boolean delimsChanged;

    private int maxDelimCodePoint;

    private boolean hasSurrogates = false;

    private int[] delimiterCodePoints;

    private void setMaxDelimCodePoint();

    public StringTokenizer(String str, @Nullable String delim, boolean returnDelims) {
        currentPosition = 0;
        newPosition = -1;
        delimsChanged = false;
        this.str = str;
        maxPosition = str.length();
        delimiters = delim;
        retDelims = returnDelims;
        setMaxDelimCodePoint();
    }

    public StringTokenizer(String str, @Nullable String delim) {
        this(str, delim, false);
    }

    public StringTokenizer(String str) {
        this(str, " \t\n\r\f", false);
    }

    private int skipDelimiters(int startPos);

    private int scanToken(int startPos);

    private boolean isDelimiter(int codePoint);

    public boolean hasMoreTokens();

    public String nextToken();

    public String nextToken(String delim);

    public boolean hasMoreElements();

    public Object nextElement();

    @NonNegative
    public int countTokens();
}
