package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.common.value.qual.IntRange;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.math.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.text.*;
import java.text.spi.NumberFormatProvider;
import java.util.function.Consumer;
import java.util.regex.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.ResourceBundleBasedAdapter;

@AnnotatedFor({ "index", "interning", "lock", "signedness" })
@UsesObjectEquals
public final class Scanner implements Iterator<String>, Closeable {

    private CharBuffer buf;

    private static final int BUFFER_SIZE = 1024;

    private int position;

    private Matcher matcher;

    private Pattern delimPattern;

    private Pattern hasNextPattern;

    private int hasNextPosition;

    private String hasNextResult;

    private Readable source;

    private boolean sourceClosed = false;

    private boolean needInput = false;

    private boolean skipped = false;

    private int savedScannerPosition = -1;

    private Object typeCache = null;

    private boolean matchValid = false;

    private boolean closed = false;

    @Positive
    private int radix = 10;

    @IntRange(from = 2, to = 36)
    private int defaultRadix = 10;

    private Locale locale = null;

    private PatternLRUCache patternCache = new PatternLRUCache(7);

    private IOException lastException;

    int modCount;

    private static Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    private static Pattern FIND_ANY_PATTERN = Pattern.compile("(?s).*");

    private static Pattern NON_ASCII_DIGIT = Pattern.compile("[\\p{javaDigit}&&[^0-9]]");

    private String groupSeparator = "\\,";

    private String decimalSeparator = "\\.";

    private String nanString = "NaN";

    private String infinityString = "Infinity";

    private String positivePrefix = "";

    private String negativePrefix = "\\-";

    private String positiveSuffix = "";

    private String negativeSuffix = "";

    private static volatile Pattern boolPattern;

    private static final String BOOLEAN_PATTERN = "true|false";

    private static Pattern boolPattern();

    private Pattern integerPattern;

    private String digits = "0123456789abcdefghijklmnopqrstuvwxyz";

    private String non0Digit = "[\\p{javaDigit}&&[^0]]";

    private int SIMPLE_GROUP_INDEX = 5;

    private String buildIntegerPatternString();

    private Pattern integerPattern();

    private static volatile Pattern separatorPattern;

    private static volatile Pattern linePattern;

    private static final String LINE_SEPARATOR_PATTERN = "\r\n|[\n\r\u2028\u2029\u0085]";

    private static final String LINE_PATTERN = ".*(" + LINE_SEPARATOR_PATTERN + ")|.+$";

    private static Pattern separatorPattern();

    private static Pattern linePattern();

    private Pattern floatPattern;

    private Pattern decimalPattern;

    private void buildFloatAndDecimalPattern();

    private Pattern floatPattern();

    private Pattern decimalPattern();

    private Scanner(Readable source, Pattern pattern) {
        assert source != null : "source should not be null";
        assert pattern != null : "pattern should not be null";
        this.source = source;
        delimPattern = pattern;
        buf = CharBuffer.allocate(BUFFER_SIZE);
        buf.limit(0);
        matcher = delimPattern.matcher(buf);
        matcher.useTransparentBounds(true);
        matcher.useAnchoringBounds(false);
        useLocale(Locale.getDefault(Locale.Category.FORMAT));
    }

    public Scanner(Readable source) {
        this(Objects.requireNonNull(source, "source"), WHITESPACE_PATTERN);
    }

    public Scanner(InputStream source) {
        this(new InputStreamReader(source), WHITESPACE_PATTERN);
    }

    public Scanner(InputStream source, String charsetName) {
        this(source, toCharset(charsetName));
    }

    public Scanner(InputStream source, Charset charset) {
        this(makeReadable(Objects.requireNonNull(source, "source"), charset), WHITESPACE_PATTERN);
    }

    private static Charset toCharset(String csn);

    private static Readable makeReadable(Path source, Charset charset) throws IOException;

    private static Readable makeReadable(InputStream source, Charset charset);

    public Scanner(File source) throws FileNotFoundException {
        this((ReadableByteChannel) (new FileInputStream(source).getChannel()));
    }

    public Scanner(File source, String charsetName) throws FileNotFoundException {
        this(Objects.requireNonNull(source), toDecoder(charsetName));
    }

    public Scanner(File source, Charset charset) throws IOException {
        this(Objects.requireNonNull(source), charset.newDecoder());
    }

    private Scanner(File source, CharsetDecoder dec) throws FileNotFoundException {
        this(makeReadable((ReadableByteChannel) (new FileInputStream(source).getChannel()), dec));
    }

    private static CharsetDecoder toDecoder(String charsetName);

    private static Readable makeReadable(ReadableByteChannel source, CharsetDecoder dec);

    private static Readable makeReadable(ReadableByteChannel source, Charset charset);

    public Scanner(Path source) throws IOException {
        this(Files.newInputStream(source));
    }

    public Scanner(Path source, String charsetName) throws IOException {
        this(Objects.requireNonNull(source), toCharset(charsetName));
    }

    public Scanner(Path source, Charset charset) throws IOException {
        this(makeReadable(source, charset));
    }

    public Scanner(String source) {
        this(new StringReader(source), WHITESPACE_PATTERN);
    }

    public Scanner(ReadableByteChannel source) {
        this(makeReadable(Objects.requireNonNull(source, "source")), WHITESPACE_PATTERN);
    }

    private static Readable makeReadable(ReadableByteChannel source);

    public Scanner(ReadableByteChannel source, String charsetName) {
        this(makeReadable(Objects.requireNonNull(source, "source"), toDecoder(charsetName)), WHITESPACE_PATTERN);
    }

    public Scanner(ReadableByteChannel source, Charset charset) {
        this(makeReadable(Objects.requireNonNull(source, "source"), charset), WHITESPACE_PATTERN);
    }

    private void saveState();

    private void revertState();

    private boolean revertState(boolean b);

    private void cacheResult();

    private void cacheResult(String result);

    private void clearCaches();

    private String getCachedResult();

    private void useTypeCache();

    private void readInput();

    private boolean makeSpace();

    private void translateSavedIndexes(int offset);

    private void throwFor();

    private boolean hasTokenInBuffer();

    private String getCompleteTokenInBuffer(Pattern pattern);

    private boolean findPatternInBuffer(Pattern pattern, int horizon);

    private boolean matchPatternInBuffer(Pattern pattern);

    private void ensureOpen();

    public void close();

    @Nullable
    public IOException ioException();

    public Pattern delimiter();

    public Scanner useDelimiter(Pattern pattern);

    public Scanner useDelimiter(String pattern);

    public Locale locale();

    public Scanner useLocale(Locale locale);

    @Positive
    public int radix();

    public Scanner useRadix(@IntRange(from = 2, to = 36) int radix);

    private void setRadix(@Positive int radix);

    public MatchResult match();

    @SideEffectFree
    public String toString(@GuardSatisfied Scanner this);

    public boolean hasNext(@GuardSatisfied Scanner this);

    public String next(@GuardSatisfied Scanner this);

    public void remove(@GuardSatisfied Scanner this);

    public boolean hasNext(@GuardSatisfied Scanner this, String pattern);

    public String next(@GuardSatisfied Scanner this, String pattern);

    public boolean hasNext(@GuardSatisfied Scanner this, Pattern pattern);

    public String next(@GuardSatisfied Scanner this, Pattern pattern);

    public boolean hasNextLine();

    public String nextLine(@GuardSatisfied Scanner this);

    public String findInLine(String pattern);

    public String findInLine(Pattern pattern);

    public String findWithinHorizon(String pattern, @NonNegative int horizon);

    public String findWithinHorizon(Pattern pattern, @NonNegative int horizon);

    public Scanner skip(@GuardSatisfied Scanner this, Pattern pattern);

    public Scanner skip(@GuardSatisfied Scanner this, String pattern);

    public boolean hasNextBoolean(@GuardSatisfied Scanner this);

    public boolean nextBoolean(@GuardSatisfied Scanner this);

    public boolean hasNextByte(@GuardSatisfied Scanner this);

    public boolean hasNextByte(@GuardSatisfied Scanner this, @Positive int radix);

    @PolySigned
    public byte nextByte(@GuardSatisfied Scanner this);

    @PolySigned
    public byte nextByte(@GuardSatisfied Scanner this, @Positive int radix);

    public boolean hasNextShort(@GuardSatisfied Scanner this);

    public boolean hasNextShort(@GuardSatisfied Scanner this, @Positive int radix);

    @PolySigned
    public short nextShort(@GuardSatisfied Scanner this);

    @PolySigned
    public short nextShort(@GuardSatisfied Scanner this, @Positive int radix);

    public boolean hasNextInt(@GuardSatisfied Scanner this);

    public boolean hasNextInt(@GuardSatisfied Scanner this, @Positive int radix);

    private String processIntegerToken(String token);

    @PolySigned
    public int nextInt(@GuardSatisfied Scanner this);

    @PolySigned
    public int nextInt(@GuardSatisfied Scanner this, @Positive int radix);

    public boolean hasNextLong(@GuardSatisfied Scanner this);

    public boolean hasNextLong(@GuardSatisfied Scanner this, @Positive int radix);

    @PolySigned
    public long nextLong(@GuardSatisfied Scanner this);

    @PolySigned
    public long nextLong(@GuardSatisfied Scanner this, @Positive int radix);

    private String processFloatToken(String token);

    public boolean hasNextFloat(@GuardSatisfied Scanner this);

    public float nextFloat(@GuardSatisfied Scanner this);

    public boolean hasNextDouble(@GuardSatisfied Scanner this);

    public double nextDouble(@GuardSatisfied Scanner this);

    public boolean hasNextBigInteger(@GuardSatisfied Scanner this);

    public boolean hasNextBigInteger(@GuardSatisfied Scanner this, @IntRange(from = 2, to = 36) int radix);

    public BigInteger nextBigInteger(@GuardSatisfied Scanner this);

    public BigInteger nextBigInteger(@GuardSatisfied Scanner this, @IntRange(from = 2, to = 36) int radix);

    public boolean hasNextBigDecimal(@GuardSatisfied Scanner this);

    public BigDecimal nextBigDecimal(@GuardSatisfied Scanner this);

    public Scanner reset(@GuardSatisfied Scanner this);

    public Stream<String> tokens();

    class TokenSpliterator extends Spliterators.AbstractSpliterator<String> {

        int expectedCount = -1;

        TokenSpliterator() {
            super(Long.MAX_VALUE, Spliterator.IMMUTABLE | Spliterator.NONNULL | Spliterator.ORDERED);
        }

        @Override
        public boolean tryAdvance(Consumer<? super String> cons);
    }

    public Stream<MatchResult> findAll(Pattern pattern);

    public Stream<MatchResult> findAll(String patString);

    class FindSpliterator extends Spliterators.AbstractSpliterator<MatchResult> {

        final Pattern pattern;

        int expectedCount = -1;

        private boolean advance = false;

        FindSpliterator(Pattern pattern) {
            super(Long.MAX_VALUE, Spliterator.IMMUTABLE | Spliterator.NONNULL | Spliterator.ORDERED);
            this.pattern = pattern;
        }

        @Override
        public boolean tryAdvance(Consumer<? super MatchResult> cons);

        private boolean nextInBuffer();
    }

    private static class PatternLRUCache {

        private Pattern[] oa = null;

        private final int size;

        PatternLRUCache(int size) {
            this.size = size;
        }

        boolean hasName(Pattern p, String s);

        void moveToFront(Object[] oa, int i);

        Pattern forName(String name);
    }
}
