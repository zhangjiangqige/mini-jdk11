package java.io;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.nio.charset.Charset;
import jdk.internal.misc.JavaIOAccess;
import jdk.internal.misc.SharedSecrets;
import sun.nio.cs.StreamDecoder;
import sun.nio.cs.StreamEncoder;

@AnnotatedFor({ "formatter", "index", "interning", "nullness" })
@UsesObjectEquals
public final class Console implements Flushable {

    public PrintWriter writer();

    public Reader reader();

    @FormatMethod
    public Console format(String fmt, @Nullable Object... args);

    @FormatMethod
    public Console printf(String format, @Nullable Object... args);

    @Nullable
    public String readLine(String fmt, @Nullable Object... args);

    @Nullable
    public String readLine();

    public char @Nullable [] readPassword(String fmt, @Nullable Object... args);

    private void installShutdownHook();

    public char @Nullable [] readPassword();

    public void flush();

    private Object readLock;

    private Object writeLock;

    private Reader reader;

    private Writer out;

    private PrintWriter pw;

    private Formatter formatter;

    private Charset cs;

    private char[] rcb;

    private boolean restoreEcho;

    private boolean shutdownHookInstalled;

    private static native String encoding();

    private static native boolean echo(boolean on) throws IOException;

    private char[] readline(boolean zeroOut) throws IOException;

    private char[] grow();

    class LineReader extends Reader {

        private Reader in;

        private char[] cb;

        private int nChars, nextChar;

        boolean leftoverLF;

        LineReader(Reader in) {
            this.in = in;
            cb = new char[1024];
            nextChar = nChars = 0;
            leftoverLF = false;
        }

        public void close();

        public boolean ready() throws IOException;

        @GTENegativeOne
        @LTEqLengthOf({ "#1" })
        public int read(char[] cbuf, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length) throws IOException;
    }

    static {
        SharedSecrets.setJavaIOAccess(new JavaIOAccess() {

            public Console console() {
                if (istty()) {
                    if (cons == null)
                        cons = new Console();
                    return cons;
                }
                return null;
            }

            public Charset charset() {
                return cons.cs;
            }
        });
    }

    private static Console cons;

    private static native boolean istty();

    private Console() {
        readLock = new Object();
        writeLock = new Object();
        String csname = encoding();
        if (csname != null) {
            try {
                cs = Charset.forName(csname);
            } catch (Exception x) {
            }
        }
        if (cs == null)
            cs = Charset.defaultCharset();
        out = StreamEncoder.forOutputStreamWriter(new FileOutputStream(FileDescriptor.out), writeLock, cs);
        pw = new PrintWriter(out, true) {

            public void close() {
            }
        };
        formatter = new Formatter(out);
        reader = new LineReader(StreamDecoder.forInputStreamReader(new FileInputStream(FileDescriptor.in), readLock, cs));
        rcb = new char[1024];
    }
}
