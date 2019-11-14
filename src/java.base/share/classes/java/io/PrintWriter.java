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

    private final boolean autoFlush;

    private boolean trouble = false;

    private Formatter formatter;

    private PrintStream psOut = null;

    private static Charset toCharset(String csn) throws UnsupportedEncodingException;

    public PrintWriter(Writer out) {
        this(out, false);
    }

    public PrintWriter(Writer out, boolean autoFlush) {
        super(out);
        this.out = out;
        this.autoFlush = autoFlush;
    }

    public PrintWriter(OutputStream out) {
        this(out, false);
    }

    public PrintWriter(OutputStream out, boolean autoFlush) {
        this(out, autoFlush, Charset.defaultCharset());
    }

    public PrintWriter(OutputStream out, boolean autoFlush, Charset charset) {
        this(new BufferedWriter(new OutputStreamWriter(out, charset)), autoFlush);
        if (out instanceof java.io.PrintStream) {
            psOut = (PrintStream) out;
        }
    }

    public PrintWriter(String fileName) throws FileNotFoundException {
        this(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName))), false);
    }

    private PrintWriter(Charset charset, File file) throws FileNotFoundException {
        this(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset)), false);
    }

    public PrintWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(toCharset(csn), new File(fileName));
    }

    public PrintWriter(String fileName, Charset charset) throws IOException {
        this(Objects.requireNonNull(charset, "charset"), new File(fileName));
    }

    public PrintWriter(File file) throws FileNotFoundException {
        this(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))), false);
    }

    public PrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(toCharset(csn), file);
    }

    public PrintWriter(File file, Charset charset) throws IOException {
        this(Objects.requireNonNull(charset, "charset"), file);
    }

    private void ensureOpen() throws IOException;

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

    private void newLine();

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
