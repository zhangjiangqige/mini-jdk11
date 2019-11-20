package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import sun.nio.cs.StreamEncoder;

@AnnotatedFor({ "nullness", "index" })
public class OutputStreamWriter extends Writer {

    public OutputStreamWriter(OutputStream out, String charsetName) throws UnsupportedEncodingException {
    }

    public OutputStreamWriter(OutputStream out) {
    }

    public OutputStreamWriter(OutputStream out, Charset cs) {
    }

    public OutputStreamWriter(OutputStream out, CharsetEncoder enc) {
    }

    @Nullable
    public String getEncoding();

    public void write(int c) throws IOException;

    public void write(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void write(String str, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException;

    @Override
    public Writer append(CharSequence csq) throws IOException;

    public void flush() throws IOException;

    public void close() throws IOException;
}
