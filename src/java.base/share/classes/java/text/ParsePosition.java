package java.text;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index" })
public class ParsePosition {

    @NonNegative
    int index = 0;

    @GTENegativeOne
    int errorIndex = -1;

    @NonNegative
    public int getIndex();

    public void setIndex(@NonNegative int index);

    public ParsePosition(@NonNegative int index) {
        this.index = index;
    }

    public void setErrorIndex(@GTENegativeOne int ei);

    @GTENegativeOne
    public int getErrorIndex();

    public boolean equals(Object obj);

    public int hashCode();

    public String toString();
}
