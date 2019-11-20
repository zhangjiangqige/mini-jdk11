package java.util;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public final class StringJoiner {

    public StringJoiner(CharSequence delimiter) {
    }

    public StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
    }

    public StringJoiner setEmptyValue(CharSequence emptyValue);

    @Override
    public String toString();

    public StringJoiner add(@Nullable CharSequence newElement);

    public StringJoiner merge(StringJoiner other);

    public int length();
}
