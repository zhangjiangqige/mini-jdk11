package java.io;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.checker.i18n.qual.Localized;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.Formatter;
import java.util.Locale;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

@CFComment({ "lock: TODO: Should parameters be @GuardSatisfied, or is the default of @GuardedBy({}) appropriate? (@GuardedBy({}) is more conservative.)" })
@AnnotatedFor({ "formatter", "i18n", "index", "lock", "nullness", "signedness" })
public class PrintStream extends FilterOutputStream implements Appendable, Closeable {

    private final boolean autoFlush;

    private boolean trouble = false;

    private Formatter formatter;

    private BufferedWriter textOut;

    private OutputStreamWriter charOut;

    private static <T> T requireNonNull(T obj, String message);

    private static Charset toCharset(String csn) throws UnsupportedEncodingException;

    private PrintStream(boolean autoFlush, OutputStream out) {
        super(out);
        this.autoFlush = autoFlush;
        this.charOut = new OutputStreamWriter(this);
        this.textOut = new BufferedWriter(charOut);
    }

    private PrintStream(boolean autoFlush, Charset charset, OutputStream out) {
        this(out, autoFlush, charset);
    }

    public PrintStream(OutputStream out) {
        this(out, false);
    }

    public PrintStream(OutputStream out, boolean autoFlush) {
        this(autoFlush, requireNonNull(out, "Null output stream"));
    }

    public PrintStream(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
        this(requireNonNull(out, "Null output stream"), autoFlush, toCharset(encoding));
    }

    public PrintStream(OutputStream out, boolean autoFlush, Charset charset) {
        super(out);
        this.autoFlush = autoFlush;
        this.charOut = new OutputStreamWriter(this, charset);
        this.textOut = new BufferedWriter(charOut);
    }

    public PrintStream(String fileName) throws FileNotFoundException {
        this(false, new FileOutputStream(fileName));
    }

    public PrintStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(false, toCharset(csn), new FileOutputStream(fileName));
    }

    public PrintStream(String fileName, Charset charset) throws IOException {
        this(false, requireNonNull(charset, "charset"), new FileOutputStream(fileName));
    }

    public PrintStream(File file) throws FileNotFoundException {
        this(false, new FileOutputStream(file));
    }

    public PrintStream(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(false, toCharset(csn), new FileOutputStream(file));
    }

    public PrintStream(File file, Charset charset) throws IOException {
        this(false, requireNonNull(charset, "charset"), new FileOutputStream(file));
    }

    private void ensureOpen() throws IOException;

    public void flush(@GuardSatisfied PrintStream this);

    private boolean closing = false;

    public void close(@GuardSatisfied PrintStream this);

    public boolean checkError(@GuardSatisfied PrintStream this);

    protected void setError();

    protected void clearError();

    public void write(@GuardSatisfied PrintStream this, int b);

    public void write(@GuardSatisfied PrintStream this, @PolySigned byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    private void write(@PolySigned char[] buf);

    private void write(String s);

    private void newLine();

    public void print(@GuardSatisfied PrintStream this, boolean b);

    public void print(@GuardSatisfied PrintStream this, char c);

    public void print(@GuardSatisfied PrintStream this, int i);

    public void print(@GuardSatisfied PrintStream this, long l);

    public void print(@GuardSatisfied PrintStream this, float f);

    public void print(@GuardSatisfied PrintStream this, double d);

    public void print(@GuardSatisfied PrintStream this, @PolySigned char[] s);

    public void print(@GuardSatisfied PrintStream this, @Nullable String s);

    public void print(@GuardSatisfied PrintStream this, @Nullable Object obj);

    public void println(@GuardSatisfied PrintStream this);

    public void println(@GuardSatisfied PrintStream this, boolean x);

    public void println(@GuardSatisfied PrintStream this, char x);

    public void println(@GuardSatisfied PrintStream this, int x);

    public void println(@GuardSatisfied PrintStream this, long x);

    public void println(@GuardSatisfied PrintStream this, float x);

    public void println(@GuardSatisfied PrintStream this, double x);

    public void println(@GuardSatisfied PrintStream this, char[] x);

    public void println(@GuardSatisfied PrintStream this, @Nullable @Localized String x);

    public void println(@GuardSatisfied PrintStream this, @Nullable Object x);

    @CFComment({ "lock/nullness: The vararg arrays can actually be null, but let's not annotate them because passing null is bad style; see whether this annotation is useful." })
    @FormatMethod
    public PrintStream printf(@GuardSatisfied PrintStream this, String format, @Nullable Object... args);

    @FormatMethod
    public PrintStream printf(@GuardSatisfied PrintStream this, @Nullable Locale l, String format, @Nullable Object... args);

    @FormatMethod
    public PrintStream format(@GuardSatisfied PrintStream this, String format, @Nullable Object... args);

    @FormatMethod
    public PrintStream format(@GuardSatisfied PrintStream this, @Nullable Locale l, String format, @Nullable Object... args);

    public PrintStream append(@Nullable CharSequence csq);

    public PrintStream append(@Nullable CharSequence csq, @IndexOrHigh({ "#1" }) int start, @IndexOrHigh({ "#1" }) int end);

    public PrintStream append(char c);
}
