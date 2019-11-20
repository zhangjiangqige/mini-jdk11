package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index" })
@Deprecated
public class LineNumberInputStream extends FilterInputStream {

    public LineNumberInputStream(InputStream in) {
    }

    @SuppressWarnings("fallthrough")
    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public long skip(long n) throws IOException;

    public void setLineNumber(@NonNegative int lineNumber);

    @NonNegative
    public int getLineNumber();

    @NonNegative
    public int available() throws IOException;

    public void mark(@NonNegative int readlimit);

    public void reset() throws IOException;
}
