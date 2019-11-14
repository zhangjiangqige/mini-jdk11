package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness", "index" })
public class LineNumberReader extends BufferedReader {

    private int lineNumber = 0;

    private int markedLineNumber;

    private boolean skipLF;

    private boolean markedSkipLF;

    public LineNumberReader(Reader in) {
        super(in);
    }

    public LineNumberReader(Reader in, @NonNegative int sz) {
        super(in, sz);
    }

    public void setLineNumber(@GuardSatisfied LineNumberReader this, @NonNegative int lineNumber);

    @NonNegative
    public int getLineNumber(@GuardSatisfied LineNumberReader this);

    @SuppressWarnings("fallthrough")
    public int read(@GuardSatisfied LineNumberReader this) throws IOException;

    @SuppressWarnings("fallthrough")
    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(@GuardSatisfied LineNumberReader this, char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Nullable
    public String readLine(@GuardSatisfied LineNumberReader this) throws IOException;

    private static final int maxSkipBufferSize = 8192;

    private char[] skipBuffer = null;

    @NonNegative
    public long skip(@GuardSatisfied LineNumberReader this, @NonNegative long n) throws IOException;

    public void mark(@GuardSatisfied LineNumberReader this, @NonNegative int readAheadLimit) throws IOException;

    public void reset(@GuardSatisfied LineNumberReader this) throws IOException;
}
