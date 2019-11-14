package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index" })
public class StringReader extends Reader {

    private String str;

    private int length;

    private int next = 0;

    private int mark = 0;

    public StringReader(String s) {
        this.str = s;
        this.length = s.length();
    }

    private void ensureOpen() throws IOException;

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public long skip(long ns) throws IOException;

    public boolean ready() throws IOException;

    public boolean markSupported();

    public void mark(@NonNegative int readAheadLimit) throws IOException;

    public void reset() throws IOException;

    public void close();
}
