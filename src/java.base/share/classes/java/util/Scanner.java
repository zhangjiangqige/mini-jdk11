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

    public Scanner(Readable source) {
    }

    public Scanner(InputStream source) {
    }

    public Scanner(InputStream source, String charsetName) {
    }

    public Scanner(InputStream source, Charset charset) {
    }

    public Scanner(File source) throws FileNotFoundException {
    }

    public Scanner(File source, String charsetName) throws FileNotFoundException {
    }

    public Scanner(File source, Charset charset) throws IOException {
    }

    public Scanner(Path source) throws IOException {
    }

    public Scanner(Path source, String charsetName) throws IOException {
    }

    public Scanner(Path source, Charset charset) throws IOException {
    }

    public Scanner(String source) {
    }

    public Scanner(ReadableByteChannel source) {
    }

    public Scanner(ReadableByteChannel source, String charsetName) {
    }

    public Scanner(ReadableByteChannel source, Charset charset) {
    }

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

    public Stream<MatchResult> findAll(Pattern pattern);

    public Stream<MatchResult> findAll(String patString);
}
