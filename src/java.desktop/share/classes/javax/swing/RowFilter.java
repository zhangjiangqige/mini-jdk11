package javax.swing;

import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@AnnotatedFor({ "regex" })
public abstract class RowFilter<M, I> {

    public enum ComparisonType {

        BEFORE, AFTER, EQUAL, NOT_EQUAL
    }

    public static <M, I> RowFilter<M, I> regexFilter(@Regex String regex, int... indices);

    public static <M, I> RowFilter<M, I> dateFilter(ComparisonType type, Date date, int... indices);

    public static <M, I> RowFilter<M, I> numberFilter(ComparisonType type, Number number, int... indices);

    public static <M, I> RowFilter<M, I> orFilter(Iterable<? extends RowFilter<? super M, ? super I>> filters);

    public static <M, I> RowFilter<M, I> andFilter(Iterable<? extends RowFilter<? super M, ? super I>> filters);

    public static <M, I> RowFilter<M, I> notFilter(RowFilter<M, I> filter);

    public abstract boolean include(Entry<? extends M, ? extends I> entry);

    public abstract static class Entry<M, I> {

        public Entry() {
        }

        public abstract M getModel();

        public abstract int getValueCount();

        public abstract Object getValue(int index);

        public String getStringValue(int index);

        public abstract I getIdentifier();
    }
}
