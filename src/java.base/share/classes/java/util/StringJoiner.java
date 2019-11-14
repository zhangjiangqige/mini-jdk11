package java.util;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public final class StringJoiner {

    private final String prefix;

    private final String delimiter;

    private final String suffix;

    private String[] elts;

    private int size;

    private int len;

    private String emptyValue;

    public StringJoiner(CharSequence delimiter) {
        this(delimiter, "", "");
    }

    public StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        Objects.requireNonNull(prefix, "The prefix must not be null");
        Objects.requireNonNull(delimiter, "The delimiter must not be null");
        Objects.requireNonNull(suffix, "The suffix must not be null");
        this.prefix = prefix.toString();
        this.delimiter = delimiter.toString();
        this.suffix = suffix.toString();
    }

    public StringJoiner setEmptyValue(CharSequence emptyValue);

    private static int getChars(String s, char[] chars, int start);

    @Override
    public String toString();

    public StringJoiner add(@Nullable CharSequence newElement);

    public StringJoiner merge(StringJoiner other);

    private void compactElts();

    public int length();
}
