package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import jdk.internal.HotSpotIntrinsicCandidate;
import sun.nio.cs.HistoricallyNamedCharset;
import sun.nio.cs.ArrayDecoder;
import sun.nio.cs.ArrayEncoder;
import static java.lang.String.LATIN1;
import static java.lang.String.UTF16;
import static java.lang.String.COMPACT_STRINGS;
import static java.lang.Character.isSurrogate;
import static java.lang.Character.highSurrogate;
import static java.lang.Character.lowSurrogate;
import static java.lang.Character.isSupplementaryCodePoint;
import static java.lang.StringUTF16.putChar;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
class StringCoding {

    private StringCoding() {
    }

    private static final ThreadLocal<SoftReference<StringDecoder>> decoder = new ThreadLocal<>();

    private static final ThreadLocal<SoftReference<StringEncoder>> encoder = new ThreadLocal<>();

    private static final Charset ISO_8859_1 = sun.nio.cs.ISO_8859_1.INSTANCE;

    private static final Charset US_ASCII = sun.nio.cs.US_ASCII.INSTANCE;

    private static final Charset UTF_8 = sun.nio.cs.UTF_8.INSTANCE;

    private static <T> T deref(ThreadLocal<SoftReference<T>> tl);

    private static <T> void set(ThreadLocal<SoftReference<T>> tl, T ob);

    private static byte[] safeTrim(byte[] ba, int len, boolean isTrusted);

    private static int scale(int len, float expansionFactor);

    private static Charset lookupCharset(String csn);

    static class Result {

        byte[] value;

        byte coder;

        Result with();

        Result with(char[] val, int off, int len);

        Result with(byte[] val, byte coder);
    }

    @HotSpotIntrinsicCandidate
    public static boolean hasNegatives(byte[] ba, int off, int len);

    static class StringDecoder {

        private final String requestedCharsetName;

        private final Charset cs;

        private final boolean isASCIICompatible;

        private final CharsetDecoder cd;

        protected final Result result;

        StringDecoder(Charset cs, String rcn) {
            this.requestedCharsetName = rcn;
            this.cs = cs;
            this.cd = cs.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
            this.result = new Result();
            this.isASCIICompatible = (cd instanceof ArrayDecoder) && ((ArrayDecoder) cd).isASCIICompatible();
        }

        String charsetName();

        final String requestedCharsetName();

        Result decode(byte[] ba, int off, int len);
    }

    static Result decode(String charsetName, byte[] ba, int off, int len) throws UnsupportedEncodingException;

    static Result decode(Charset cs, byte[] ba, int off, int len);

    static Result decode(byte[] ba, int off, int len);

    private static class StringEncoder {

        private Charset cs;

        private CharsetEncoder ce;

        private final boolean isASCIICompatible;

        private final String requestedCharsetName;

        private final boolean isTrusted;

        private StringEncoder(Charset cs, String rcn) {
            this.requestedCharsetName = rcn;
            this.cs = cs;
            this.ce = cs.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
            this.isTrusted = (cs.getClass().getClassLoader0() == null);
            this.isASCIICompatible = (ce instanceof ArrayEncoder) && ((ArrayEncoder) ce).isASCIICompatible();
        }

        String charsetName();

        final String requestedCharsetName();

        byte[] encode(byte coder, byte[] val);
    }

    static byte[] encode(String charsetName, byte coder, byte[] val) throws UnsupportedEncodingException;

    static byte[] encode(Charset cs, byte coder, byte[] val);

    static byte[] encode(byte coder, byte[] val);

    private static native void err(String msg);

    private static final ThreadLocal<StringCoding.Result> resultCached = new ThreadLocal<>() {

        protected StringCoding.Result initialValue() {
            return new StringCoding.Result();
        }
    };

    private static Result decodeASCII(byte[] ba, int off, int len);

    private static byte[] encodeASCII(byte coder, byte[] val);

    private static Result decodeLatin1(byte[] ba, int off, int len);

    @HotSpotIntrinsicCandidate
    private static int implEncodeISOArray(byte[] sa, int sp, byte[] da, int dp, int len);

    private static byte[] encode8859_1(byte coder, byte[] val);

    private static byte[] encode8859_1(byte coder, byte[] val, boolean doReplace);

    private static boolean isNotContinuation(int b);

    private static boolean isMalformed3(int b1, int b2, int b3);

    private static boolean isMalformed3_2(int b1, int b2);

    private static boolean isMalformed4(int b2, int b3, int b4);

    private static boolean isMalformed4_2(int b1, int b2);

    private static boolean isMalformed4_3(int b3);

    private static int malformedN(byte[] src, int sp, int nb);

    private static void throwMalformed(int off, int nb);

    private static void throwMalformed(byte[] val);

    private static void throwUnmappable(int off, int nb);

    private static void throwUnmappable(byte[] val);

    private static char repl = '\ufffd';

    private static Result decodeUTF8(byte[] src, int sp, int len, boolean doReplace);

    private static Result decodeUTF8_0(byte[] src, int sp, int len, boolean doReplace);

    private static byte[] encodeUTF8(byte coder, byte[] val, boolean doReplace);

    private static byte[] encodeUTF8_UTF16(byte[] val, boolean doReplace);

    static String newStringUTF8NoRepl(byte[] src, int off, int len);

    static byte[] getBytesUTF8NoRepl(String s);

    private static boolean isASCII(byte[] src);

    private static String newStringLatin1(byte[] src);

    static String newStringNoRepl(byte[] src, Charset cs) throws CharacterCodingException;

    static String newStringNoRepl1(byte[] src, Charset cs);

    static byte[] getBytesNoRepl(String s, Charset cs) throws CharacterCodingException;

    static byte[] getBytesNoRepl1(String s, Charset cs);
}
