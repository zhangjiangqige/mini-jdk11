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

    private static void checkIndices(int[] columns);

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

    private abstract static class GeneralFilter<M, I> extends RowFilter<M, I> {

        private int[] columns;

        GeneralFilter(int[] columns) {
            checkIndices(columns);
            this.columns = columns;
        }

        @Override
        public boolean include(Entry<? extends M, ? extends I> value);

        protected abstract boolean include(Entry<? extends M, ? extends I> value, int index);
    }

    private static class RegexFilter<M, I> extends GeneralFilter<M, I> {

        private Matcher matcher;

        RegexFilter(Pattern regex, int[] columns) {
            super(columns);
            if (regex == null) {
                throw new IllegalArgumentException("Pattern must be non-null");
            }
            matcher = regex.matcher("");
        }

        @Override
        protected boolean include(Entry<? extends M, ? extends I> value, int index);
    }

    private static class DateFilter<M, I> extends GeneralFilter<M, I> {

        private long date;

        private ComparisonType type;

        DateFilter(ComparisonType type, long date, int[] columns) {
            super(columns);
            if (type == null) {
                throw new IllegalArgumentException("type must be non-null");
            }
            this.type = type;
            this.date = date;
        }

        @Override
        protected boolean include(Entry<? extends M, ? extends I> value, int index);
    }

    private static class NumberFilter<M, I> extends GeneralFilter<M, I> {

        private boolean isComparable;

        private Number number;

        private ComparisonType type;

        NumberFilter(ComparisonType type, Number number, int[] columns) {
            super(columns);
            if (type == null || number == null) {
                throw new IllegalArgumentException("type and number must be non-null");
            }
            this.type = type;
            this.number = number;
            isComparable = (number instanceof Comparable);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected boolean include(Entry<? extends M, ? extends I> value, int index);

        private int longCompare(Number o);
    }

    private static class OrFilter<M, I> extends RowFilter<M, I> {

        List<RowFilter<? super M, ? super I>> filters;

        OrFilter(Iterable<? extends RowFilter<? super M, ? super I>> filters) {
            this.filters = new ArrayList<RowFilter<? super M, ? super I>>();
            for (RowFilter<? super M, ? super I> filter : filters) {
                if (filter == null) {
                    throw new IllegalArgumentException("Filter must be non-null");
                }
                this.filters.add(filter);
            }
        }

        public boolean include(Entry<? extends M, ? extends I> value);
    }

    private static class AndFilter<M, I> extends OrFilter<M, I> {

        AndFilter(Iterable<? extends RowFilter<? super M, ? super I>> filters) {
            super(filters);
        }

        public boolean include(Entry<? extends M, ? extends I> value);
    }

    private static class NotFilter<M, I> extends RowFilter<M, I> {

        private RowFilter<M, I> filter;

        NotFilter(RowFilter<M, I> filter) {
            if (filter == null) {
                throw new IllegalArgumentException("filter must be non-null");
            }
            this.filter = filter;
        }

        public boolean include(Entry<? extends M, ? extends I> value);
    }
}
