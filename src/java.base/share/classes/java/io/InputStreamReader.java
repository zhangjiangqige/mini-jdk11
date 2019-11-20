package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import sun.nio.cs.StreamDecoder;

@AnnotatedFor({ "index" })
public class InputStreamReader extends Reader {

    public InputStreamReader(InputStream in) {
    }

    public InputStreamReader(InputStream in, String charsetName) throws UnsupportedEncodingException {
    }

    public InputStreamReader(InputStream in, Charset cs) {
    }

    public InputStreamReader(InputStream in, CharsetDecoder dec) {
    }

    @Nullable
    public String getEncoding();

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(char[] cbuf, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length) throws IOException;

    public boolean ready() throws IOException;

    public void close() throws IOException;
}
