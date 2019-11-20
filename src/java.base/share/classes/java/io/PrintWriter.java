package java.io;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;
import java.util.Formatter;
import java.util.Locale;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

@AnnotatedFor({ "formatter", "index", "nullness" })
public class PrintWriter extends Writer {

    protected Writer out;

    public PrintWriter(Writer out) {
    }

    public PrintWriter(Writer out, boolean autoFlush) {
    }

    public PrintWriter(OutputStream out) {
    }

    public PrintWriter(OutputStream out, boolean autoFlush) {
    }

    public PrintWriter(OutputStream out, boolean autoFlush, Charset charset) {
    }

    public PrintWriter(String fileName) throws FileNotFoundException {
    }

    public PrintWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public PrintWriter(String fileName, Charset charset) throws IOException {
    }

    public PrintWriter(File file) throws FileNotFoundException {
    }

    public PrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public PrintWriter(File file, Charset charset) throws IOException {
    }

    public void flush();

    public void close();

    public boolean checkError();

    protected void setError();

    protected void clearError();

    public void write(int c);

    public void write(char[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    public void write(char[] buf);

    public void write(String s, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    public void write(String s);

    public void print(boolean b);

    public void print(char c);

    public void print(int i);

    public void print(long l);

    public void print(float f);

    public void print(double d);

    public void print(char[] s);

    public void print(@Nullable String s);

    public void print(@Nullable Object obj);

    public void println();

    public void println(boolean x);

    public void println(char x);

    public void println(int x);

    public void println(long x);

    public void println(float x);

    public void println(double x);

    public void println(char[] x);

    public void println(@Nullable String x);

    public void println(@Nullable Object x);

    @FormatMethod
    public PrintWriter printf(String format, @Nullable Object... args);

    @FormatMethod
    public PrintWriter printf(@Nullable Locale l, String format, @Nullable Object... args);

    @FormatMethod
    public PrintWriter format(String format, @Nullable Object... args);

    @FormatMethod
    public PrintWriter format(@Nullable Locale l, String format, @Nullable Object... args);

    public PrintWriter append(@Nullable CharSequence csq);

    public PrintWriter append(@Nullable CharSequence csq, @IndexOrHigh({ "#1" }) int start, @IndexOrHigh({ "#1" }) int end);

    public PrintWriter append(char c);
}
