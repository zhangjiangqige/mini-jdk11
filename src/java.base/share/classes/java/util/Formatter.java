package java.util;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.spi.NumberFormatProvider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.UnsupportedTemporalTypeException;
import jdk.internal.math.DoubleConsts;
import jdk.internal.math.FormattedFloatingDecimal;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.ResourceBundleBasedAdapter;

@AnnotatedFor({ "formatter", "index", "lock", "nullness" })
public final class Formatter implements Closeable, Flushable {

    private Appendable a;

    private final Locale l;

    private IOException lastException;

    private final char zero;

    private static double scaleUp;

    private static final int MAX_FD_CHARS = 30;

    private static Charset toCharset(String csn) throws UnsupportedEncodingException;

    private static final Appendable nonNullAppendable(Appendable a);

    private Formatter(Locale l, Appendable a) {
        this.a = a;
        this.l = l;
        this.zero = getZero(l);
    }

    private Formatter(Charset charset, Locale l, File file) throws FileNotFoundException {
        this(l, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset)));
    }

    public Formatter() {
        this(Locale.getDefault(Locale.Category.FORMAT), new StringBuilder());
    }

    public Formatter(Appendable a) {
        this(Locale.getDefault(Locale.Category.FORMAT), nonNullAppendable(a));
    }

    public Formatter(Locale l) {
        this(l, new StringBuilder());
    }

    public Formatter(Appendable a, Locale l) {
        this(l, nonNullAppendable(a));
    }

    public Formatter(String fileName) throws FileNotFoundException {
        this(Locale.getDefault(Locale.Category.FORMAT), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName))));
    }

    public Formatter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(fileName, csn, Locale.getDefault(Locale.Category.FORMAT));
    }

    public Formatter(String fileName, String csn, Locale l) throws FileNotFoundException, UnsupportedEncodingException {
        this(toCharset(csn), l, new File(fileName));
    }

    public Formatter(String fileName, Charset charset, Locale l) throws IOException {
        this(Objects.requireNonNull(charset, "charset"), l, new File(fileName));
    }

    public Formatter(File file) throws FileNotFoundException {
        this(Locale.getDefault(Locale.Category.FORMAT), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))));
    }

    public Formatter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, csn, Locale.getDefault(Locale.Category.FORMAT));
    }

    public Formatter(File file, String csn, Locale l) throws FileNotFoundException, UnsupportedEncodingException {
        this(toCharset(csn), l, file);
    }

    public Formatter(File file, Charset charset, Locale l) throws IOException {
        this(Objects.requireNonNull(charset, "charset"), l, file);
    }

    public Formatter(PrintStream ps) {
        this(Locale.getDefault(Locale.Category.FORMAT), (Appendable) Objects.requireNonNull(ps));
    }

    public Formatter(OutputStream os) {
        this(Locale.getDefault(Locale.Category.FORMAT), new BufferedWriter(new OutputStreamWriter(os)));
    }

    public Formatter(OutputStream os, String csn) throws UnsupportedEncodingException {
        this(os, csn, Locale.getDefault(Locale.Category.FORMAT));
    }

    public Formatter(OutputStream os, String csn, Locale l) throws UnsupportedEncodingException {
        this(l, new BufferedWriter(new OutputStreamWriter(os, csn)));
    }

    public Formatter(OutputStream os, Charset charset, Locale l) {
        this(l, new BufferedWriter(new OutputStreamWriter(os, charset)));
    }

    private static char getZero(Locale l);

    public Locale locale();

    public Appendable out();

    public String toString();

    public void flush();

    public void close();

    private void ensureOpen();

    public IOException ioException();

    @FormatMethod
    public Formatter format(String format, Object... args);

    @FormatMethod
    public Formatter format(Locale l, String format, Object... args);

    private static final String formatSpecifier = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";

    private static Pattern fsPattern = Pattern.compile(formatSpecifier);

    private List<FormatString> parse(String s);

    private static void checkText(String s, int start, int end);

    private interface FormatString {

        int index();

        void print(Object arg, Locale l) throws IOException;

        String toString();
    }

    private class FixedString implements FormatString {

        private String s;

        private int start;

        private int end;

        FixedString(String s, int start, int end) {
            this.s = s;
            this.start = start;
            this.end = end;
        }

        public int index();

        public void print(Object arg, Locale l) throws IOException;

        public String toString();
    }

    public enum BigDecimalLayoutForm {

        SCIENTIFIC, DECIMAL_FLOAT
    }

    private class FormatSpecifier implements FormatString {

        private int index = -1;

        private Flags f = Flags.NONE;

        private int width;

        private int precision;

        private boolean dt = false;

        private char c;

        private int index(String s, int start, int end);

        public int index();

        private Flags flags(String s, int start, int end);

        private int width(String s, int start, int end);

        private int precision(String s, int start, int end);

        private char conversion(char conv);

        FormatSpecifier(String s, Matcher m) {
            index(s, m.start(1), m.end(1));
            flags(s, m.start(2), m.end(2));
            width(s, m.start(3), m.end(3));
            precision(s, m.start(4), m.end(4));
            int tTStart = m.start(5);
            if (tTStart >= 0) {
                dt = true;
                if (s.charAt(tTStart) == 'T') {
                    f.add(Flags.UPPERCASE);
                }
            }
            conversion(s.charAt(m.start(6)));
            if (dt)
                checkDateTime();
            else if (Conversion.isGeneral(c))
                checkGeneral();
            else if (Conversion.isCharacter(c))
                checkCharacter();
            else if (Conversion.isInteger(c))
                checkInteger();
            else if (Conversion.isFloat(c))
                checkFloat();
            else if (Conversion.isText(c))
                checkText();
            else
                throw new UnknownFormatConversionException(String.valueOf(c));
        }

        public void print(Object arg, Locale l) throws IOException;

        private void printInteger(Object arg, Locale l) throws IOException;

        private void printFloat(Object arg, Locale l) throws IOException;

        private void printDateTime(Object arg, Locale l) throws IOException;

        private void printCharacter(Object arg, Locale l) throws IOException;

        private void printString(Object arg, Locale l) throws IOException;

        private void printBoolean(Object arg, Locale l) throws IOException;

        private void printHashCode(Object arg, Locale l) throws IOException;

        private void print(String s, Locale l) throws IOException;

        private String toUpperCaseWithLocale(String s, Locale l);

        private Appendable appendJustified(Appendable a, CharSequence cs) throws IOException;

        public String toString();

        private void checkGeneral();

        private void checkDateTime();

        private void checkCharacter();

        private void checkInteger();

        private void checkBadFlags(Flags... badFlags);

        private void checkFloat();

        private void checkNumeric();

        private void checkText();

        private void print(byte value, Locale l) throws IOException;

        private void print(short value, Locale l) throws IOException;

        private void print(int value, Locale l) throws IOException;

        private void print(long value, Locale l) throws IOException;

        private StringBuilder leadingSign(StringBuilder sb, boolean neg);

        private StringBuilder trailingSign(StringBuilder sb, boolean neg);

        private void print(BigInteger value, Locale l) throws IOException;

        private void print(float value, Locale l) throws IOException;

        private void print(double value, Locale l) throws IOException;

        private void print(StringBuilder sb, double value, Locale l, Flags f, char c, int precision, boolean neg) throws IOException;

        private void addZeros(StringBuilder sb, int prec);

        private String hexDouble(double d, int prec);

        private void print(BigDecimal value, Locale l) throws IOException;

        private void print(StringBuilder sb, BigDecimal value, Locale l, Flags f, char c, int precision, boolean neg) throws IOException;

        private class BigDecimalLayout {

            private StringBuilder mant;

            private StringBuilder exp;

            private boolean dot = false;

            private int scale;

            public BigDecimalLayout(BigInteger intVal, int scale, BigDecimalLayoutForm form) {
                layout(intVal, scale, form);
            }

            public boolean hasDot();

            public int scale();

            public StringBuilder mantissa();

            public StringBuilder exponent();

            private void layout(BigInteger intVal, int scale, BigDecimalLayoutForm form);
        }

        private int adjustWidth(int width, Flags f, boolean neg);

        private void trailingZeros(StringBuilder sb, int nzeros);

        private void print(Calendar t, char c, Locale l) throws IOException;

        private Appendable print(StringBuilder sb, Calendar t, char c, Locale l) throws IOException;

        private void print(TemporalAccessor t, char c, Locale l) throws IOException;

        private Appendable print(StringBuilder sb, TemporalAccessor t, char c, Locale l) throws IOException;

        private void failMismatch(Flags f, char c);

        private void failConversion(char c, Object arg);

        private char getZero(Locale l);

        private StringBuilder localizedMagnitude(StringBuilder sb, long value, Flags f, int width, Locale l);

        private StringBuilder localizedMagnitude(StringBuilder sb, CharSequence value, final int offset, Flags f, int width, Locale l);

        private void localizedMagnitudeExp(StringBuilder sb, char[] value, final int offset, Locale l);
    }

    private static class Flags {

        private int flags;

        static final Flags NONE = new Flags(0);

        static final Flags LEFT_JUSTIFY = new Flags(1 << 0);

        static final Flags UPPERCASE = new Flags(1 << 1);

        static final Flags ALTERNATE = new Flags(1 << 2);

        static final Flags PLUS = new Flags(1 << 3);

        static final Flags LEADING_SPACE = new Flags(1 << 4);

        static final Flags ZERO_PAD = new Flags(1 << 5);

        static final Flags GROUP = new Flags(1 << 6);

        static final Flags PARENTHESES = new Flags(1 << 7);

        static final Flags PREVIOUS = new Flags(1 << 8);

        private Flags(int f) {
            flags = f;
        }

        public int valueOf();

        public boolean contains(Flags f);

        public Flags dup();

        private Flags add(Flags f);

        public Flags remove(Flags f);

        public static Flags parse(String s, int start, int end);

        private static Flags parse(char c);

        public static String toString(Flags f);

        public String toString();
    }

    private static class Conversion {

        static final char DECIMAL_INTEGER = 'd';

        static final char OCTAL_INTEGER = 'o';

        static final char HEXADECIMAL_INTEGER = 'x';

        static final char HEXADECIMAL_INTEGER_UPPER = 'X';

        static final char SCIENTIFIC = 'e';

        static final char SCIENTIFIC_UPPER = 'E';

        static final char GENERAL = 'g';

        static final char GENERAL_UPPER = 'G';

        static final char DECIMAL_FLOAT = 'f';

        static final char HEXADECIMAL_FLOAT = 'a';

        static final char HEXADECIMAL_FLOAT_UPPER = 'A';

        static final char CHARACTER = 'c';

        static final char CHARACTER_UPPER = 'C';

        static final char DATE_TIME = 't';

        static final char DATE_TIME_UPPER = 'T';

        static final char BOOLEAN = 'b';

        static final char BOOLEAN_UPPER = 'B';

        static final char STRING = 's';

        static final char STRING_UPPER = 'S';

        static final char HASHCODE = 'h';

        static final char HASHCODE_UPPER = 'H';

        static final char LINE_SEPARATOR = 'n';

        static final char PERCENT_SIGN = '%';

        static boolean isValid(char c);

        static boolean isGeneral(char c);

        static boolean isCharacter(char c);

        static boolean isInteger(char c);

        static boolean isFloat(char c);

        static boolean isText(char c);
    }

    private static class DateTime {

        static final char HOUR_OF_DAY_0 = 'H';

        static final char HOUR_0 = 'I';

        static final char HOUR_OF_DAY = 'k';

        static final char HOUR = 'l';

        static final char MINUTE = 'M';

        static final char NANOSECOND = 'N';

        static final char MILLISECOND = 'L';

        static final char MILLISECOND_SINCE_EPOCH = 'Q';

        static final char AM_PM = 'p';

        static final char SECONDS_SINCE_EPOCH = 's';

        static final char SECOND = 'S';

        static final char TIME = 'T';

        static final char ZONE_NUMERIC = 'z';

        static final char ZONE = 'Z';

        static final char NAME_OF_DAY_ABBREV = 'a';

        static final char NAME_OF_DAY = 'A';

        static final char NAME_OF_MONTH_ABBREV = 'b';

        static final char NAME_OF_MONTH = 'B';

        static final char CENTURY = 'C';

        static final char DAY_OF_MONTH_0 = 'd';

        static final char DAY_OF_MONTH = 'e';

        static final char NAME_OF_MONTH_ABBREV_X = 'h';

        static final char DAY_OF_YEAR = 'j';

        static final char MONTH = 'm';

        static final char YEAR_2 = 'y';

        static final char YEAR_4 = 'Y';

        static final char TIME_12_HOUR = 'r';

        static final char TIME_24_HOUR = 'R';

        static final char DATE_TIME = 'c';

        static final char DATE = 'D';

        static final char ISO_STANDARD_DATE = 'F';

        static boolean isValid(char c);
    }
}
