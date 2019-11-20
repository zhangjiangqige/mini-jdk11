package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.*;

@AnnotatedFor({ "indexs", "interning", "lock", "nullness" })
@UsesObjectEquals
public class StringTokenizer implements Enumeration<Object> {

    public StringTokenizer(String str, @Nullable String delim, boolean returnDelims) {
    }

    public StringTokenizer(String str, @Nullable String delim) {
    }

    public StringTokenizer(String str) {
    }

    public boolean hasMoreTokens();

    public String nextToken();

    public String nextToken(String delim);

    public boolean hasMoreElements();

    public Object nextElement();

    @NonNegative
    public int countTokens();
}
