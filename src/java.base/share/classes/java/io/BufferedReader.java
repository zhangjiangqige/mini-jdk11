package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "lock", "nullness", "index" })
public class BufferedReader extends Reader {

    private Reader in;

    private char[] cb;

    private int nChars, nextChar;

    private static final int INVALIDATED = -2;

    private static final int UNMARKED = -1;

    private int markedChar = UNMARKED;

    private int readAheadLimit = 0;

    private boolean skipLF = false;

    private boolean markedSkipLF = false;

    private static int defaultCharBufferSize = 8192;

    private static int defaultExpectedLineLength = 80;

    public BufferedReader(Reader in, @Positive int sz) {
        super(in);
        if (sz <= 0)
            throw new IllegalArgumentException("Buffer size <= 0");
        this.in = in;
        cb = new char[sz];
        nextChar = nChars = 0;
    }

    public BufferedReader(Reader in) {
        this(in, defaultCharBufferSize);
    }

    private void ensureOpen() throws IOException;

    private void fill() throws IOException;

    @GTENegativeOne
    public int read(@GuardSatisfied BufferedReader this) throws IOException;

    private int read1(char[] cbuf, int off, int len) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(@GuardSatisfied BufferedReader this, char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    String readLine(@GuardSatisfied BufferedReader this, boolean ignoreLF) throws IOException;

    @Nullable
    public String readLine(@GuardSatisfied BufferedReader this) throws IOException;

    @NonNegative
    public long skip(@GuardSatisfied BufferedReader this, @NonNegative long n) throws IOException;

    @EnsuresNonNullIf(expression = { "readLine()" }, result = true)
    @Pure
    public boolean ready(@GuardSatisfied BufferedReader this) throws IOException;

    public boolean markSupported();

    public void mark(@GuardSatisfied BufferedReader this, @NonNegative int readAheadLimit) throws IOException;

    public void reset(@GuardSatisfied BufferedReader this) throws IOException;

    public void close(@GuardSatisfied BufferedReader this) throws IOException;

    public Stream<String> lines(@GuardSatisfied BufferedReader this);
}
