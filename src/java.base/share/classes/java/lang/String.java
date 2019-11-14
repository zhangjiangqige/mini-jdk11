package java.lang;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.LengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.index.qual.SameLen;
import org.checkerframework.checker.index.qual.SubstringIndexFor;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.PolyRegex;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.checker.signature.qual.PolySignature;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.common.value.qual.MinLen;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.ObjectStreamField;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Native;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Spliterator;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.vm.annotation.Stable;

@AnnotatedFor({ "formatter", "index", "interning", "lock", "nullness", "regex", "signature", "signedness" })
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {

    @Stable
    private final byte[] value;

    private final byte coder;

    private int hash;

    private static final long serialVersionUID = -6849794470754667710L;

    static final boolean COMPACT_STRINGS;

    static {
        COMPACT_STRINGS = true;
    }

    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];

    @SideEffectFree
    public String() {
        this.value = "".value;
        this.coder = "".coder;
    }

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public String(String original) {
        this.value = original.value;
        this.coder = original.coder;
        this.hash = original.hash;
    }

    @SideEffectFree
    public String(char @GuardSatisfied [] value) {
        this(value, 0, value.length, null);
    }

    @SideEffectFree
    public String(char @GuardSatisfied [] value, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
        this(value, offset, count, rangeCheck(value, offset, count));
    }

    private static Void rangeCheck(char[] value, int offset, int count);

    @SideEffectFree
    public String(int @GuardSatisfied [] codePoints, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
        checkBoundsOffCount(offset, count, codePoints.length);
        if (count == 0) {
            this.value = "".value;
            this.coder = "".coder;
            return;
        }
        if (COMPACT_STRINGS) {
            byte[] val = StringLatin1.toBytes(codePoints, offset, count);
            if (val != null) {
                this.coder = LATIN1;
                this.value = val;
                return;
            }
        }
        this.coder = UTF16;
        this.value = StringUTF16.toBytes(codePoints, offset, count);
    }

    @SideEffectFree
    @Deprecated(since = "1.1")
    public String(byte @GuardSatisfied [] ascii, int hibyte, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
        checkBoundsOffCount(offset, count, ascii.length);
        if (count == 0) {
            this.value = "".value;
            this.coder = "".coder;
            return;
        }
        if (COMPACT_STRINGS && (byte) hibyte == 0) {
            this.value = Arrays.copyOfRange(ascii, offset, offset + count);
            this.coder = LATIN1;
        } else {
            hibyte <<= 8;
            byte[] val = StringUTF16.newBytesFor(count);
            for (int i = 0; i < count; i++) {
                StringUTF16.putChar(val, i, hibyte | (ascii[offset++] & 0xff));
            }
            this.value = val;
            this.coder = UTF16;
        }
    }

    @SideEffectFree
    @Deprecated(since = "1.1")
    public String(byte @GuardSatisfied [] ascii, int hibyte) {
        this(ascii, hibyte, 0, ascii.length);
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length, String charsetName) throws UnsupportedEncodingException {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        checkBoundsOffCount(offset, length, bytes.length);
        StringCoding.Result ret = StringCoding.decode(charsetName, bytes, offset, length);
        this.value = ret.value;
        this.coder = ret.coder;
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length, Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        checkBoundsOffCount(offset, length, bytes.length);
        StringCoding.Result ret = StringCoding.decode(charset, bytes, offset, length);
        this.value = ret.value;
        this.coder = ret.coder;
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, String charsetName) throws UnsupportedEncodingException {
        this(bytes, 0, bytes.length, charsetName);
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, Charset charset) {
        this(bytes, 0, bytes.length, charset);
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length) {
        checkBoundsOffCount(offset, length, bytes.length);
        StringCoding.Result ret = StringCoding.decode(bytes, offset, length);
        this.value = ret.value;
        this.coder = ret.coder;
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes) {
        this(bytes, 0, bytes.length);
    }

    @SideEffectFree
    public String(@GuardSatisfied StringBuffer buffer) {
        this(buffer.toString());
    }

    @SideEffectFree
    public String(@GuardSatisfied StringBuilder builder) {
        this(builder, null);
    }

    @Pure
    @LengthOf({ "this" })
    public int length();

    @SuppressWarnings("contracts.conditional.postcondition.not.satisfied")
    @CFComment("index: The postcondition is EnsuresMinLenIf.  It's true because: values.length != 0 => this is @MinLen(1), as values.length is @LengthOf(this).")
    @Pure
    public boolean isEmpty();

    @Pure
    public char charAt(@IndexFor({ "this" }) int index);

    @Pure
    public int codePointAt(@IndexFor({ "this" }) int index);

    @Pure
    public int codePointBefore(@LTEqLengthOf({ "this" }) @Positive int index);

    @Pure
    @NonNegative
    public int codePointCount(@IndexOrHigh({ "this" }) int beginIndex, @IndexOrHigh({ "this" }) int endIndex);

    @Pure
    @IndexOrHigh({ "this" })
    public int offsetByCodePoints(@IndexOrHigh({ "this" }) int index, int codePointOffset);

    public void getChars(@IndexOrHigh({ "this" }) int srcBegin, @IndexOrHigh({ "this" }) int srcEnd, char @GuardSatisfied [] dst, @IndexOrHigh({ "#3" }) int dstBegin);

    @Deprecated(since = "1.1")
    public void getBytes(@IndexOrHigh({ "this" }) int srcBegin, @IndexOrHigh({ "this" }) int srcEnd, byte @GuardSatisfied [] dst, @IndexOrHigh({ "#3" }) int dstBegin);

    @SideEffectFree
    @PolySigned
    public byte[] getBytes(String charsetName) throws UnsupportedEncodingException;

    @SideEffectFree
    @PolySigned
    public byte[] getBytes(Charset charset);

    @PolySigned
    public byte[] getBytes();

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @Pure
    public boolean equals(@GuardSatisfied @Nullable Object anObject);

    @Pure
    public boolean contentEquals(@GuardSatisfied StringBuffer sb);

    private boolean nonSyncContentEquals(AbstractStringBuilder sb);

    @Pure
    public boolean contentEquals(@GuardSatisfied CharSequence cs);

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @Pure
    public boolean equalsIgnoreCase(@Nullable String anotherString);

    @Pure
    public int compareTo(String anotherString);

    public static final Comparator<String> CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();

    private static class CaseInsensitiveComparator implements Comparator<String>, java.io.Serializable {

        private static final long serialVersionUID = 8575799808933029326L;

        public int compare(String s1, String s2);

        private Object readResolve();
    }

    @Pure
    public int compareToIgnoreCase(String str);

    @Pure
    public boolean regionMatches(int toffset, String other, int ooffset, int len);

    @Pure
    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len);

    @Pure
    public boolean startsWith(String prefix, int toffset);

    @Pure
    public boolean startsWith(String prefix);

    @Pure
    public boolean endsWith(String suffix);

    @Pure
    public int hashCode();

    @Pure
    @IndexOrLow({ "this" })
    public int indexOf(int ch);

    @Pure
    @IndexOrLow({ "this" })
    public int indexOf(int ch, int fromIndex);

    @Pure
    @IndexOrLow({ "this" })
    public int lastIndexOf(int ch);

    @Pure
    @IndexOrLow({ "this" })
    public int lastIndexOf(int ch, int fromIndex);

    @Pure
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int indexOf(String str);

    @Pure
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int indexOf(String str, int fromIndex);

    static int indexOf(byte[] src, byte srcCoder, int srcCount, String tgtStr, int fromIndex);

    @Pure
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int lastIndexOf(String str);

    @Pure
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int lastIndexOf(String str, int fromIndex);

    static int lastIndexOf(byte[] src, byte srcCoder, int srcCount, String tgtStr, int fromIndex);

    @SideEffectFree
    public String substring(@IndexOrHigh({ "this" }) int beginIndex);

    @SideEffectFree
    public String substring(@IndexOrHigh({ "this" }) int beginIndex, @IndexOrHigh({ "this" }) int endIndex);

    @SideEffectFree
    public CharSequence subSequence(@IndexOrHigh({ "this" }) int beginIndex, @IndexOrHigh({ "this" }) int endIndex);

    @SideEffectFree
    public String concat(String str);

    @SideEffectFree
    public String replace(char oldChar, char newChar);

    @SideEffectFree
    public boolean matches(@Regex String regex);

    @Pure
    public boolean contains(CharSequence s);

    @SideEffectFree
    public String replaceFirst(@Regex String regex, String replacement);

    @SideEffectFree
    public String replaceAll(@Regex String regex, String replacement);

    @SideEffectFree
    public String replace(@GuardSatisfied CharSequence target, @GuardSatisfied CharSequence replacement);

    @SideEffectFree
    public String[] split(@Regex String regex, int limit);

    @SideEffectFree
    public String[] split(@Regex String regex);

    public static String join(CharSequence delimiter, CharSequence... elements);

    public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements);

    @SideEffectFree
    public String toLowerCase(@GuardSatisfied Locale locale);

    public String toLowerCase();

    @SideEffectFree
    public String toUpperCase(@GuardSatisfied Locale locale);

    public String toUpperCase();

    @SideEffectFree
    public String trim();

    public String strip();

    public String stripLeading();

    public String stripTrailing();

    public boolean isBlank();

    private int indexOfNonWhitespace();

    public Stream<String> lines();

    @Pure
    @SameLen({ "this" })
    @PolyRegex
    public String toString(@PolyRegex String this);

    @Override
    public IntStream chars();

    @Override
    public IntStream codePoints();

    @SideEffectFree
    @PolySigned
    public char @SameLen({ "this" }) [] toCharArray();

    @SideEffectFree
    @FormatMethod
    public static String format(String format, @GuardSatisfied @Nullable Object@GuardSatisfied ... args);

    @SideEffectFree
    @FormatMethod
    public static String format(@GuardSatisfied @Nullable Locale l, String format, @GuardSatisfied @Nullable Object@GuardSatisfied ... args);

    @SideEffectFree
    public static String valueOf(@GuardSatisfied @Nullable Object obj);

    @SideEffectFree
    @SameLen({ "#1" })
    public static String valueOf(char @GuardSatisfied [] data);

    @SideEffectFree
    public static String valueOf(char @GuardSatisfied [] data, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count);

    @SideEffectFree
    public static String copyValueOf(char @GuardSatisfied [] data, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count);

    @SideEffectFree
    @SameLen({ "#1" })
    public static String copyValueOf(char @GuardSatisfied [] data);

    @SideEffectFree
    public static String valueOf(boolean b);

    @SideEffectFree
    public static String valueOf(char c);

    @SideEffectFree
    public static String valueOf(int i);

    @SideEffectFree
    public static String valueOf(long l);

    @SideEffectFree
    public static String valueOf(float f);

    @SideEffectFree
    public static String valueOf(double d);

    @Pure
    @Interned
    @SameLen({ "this" })
    @PolySignature
    @PolyRegex
    public native String intern(@PolySignature @PolyRegex String this);

    public String repeat(int count);

    void getBytes(byte[] dst, int dstBegin, byte coder);

    String(char[] value, int off, int len, Void sig) {
        if (len == 0) {
            this.value = "".value;
            this.coder = "".coder;
            return;
        }
        if (COMPACT_STRINGS) {
            byte[] val = StringUTF16.compress(value, off, len);
            if (val != null) {
                this.value = val;
                this.coder = LATIN1;
                return;
            }
        }
        this.coder = UTF16;
        this.value = StringUTF16.toBytes(value, off, len);
    }

    String(AbstractStringBuilder asb, Void sig) {
        byte[] val = asb.getValue();
        int length = asb.length();
        if (asb.isLatin1()) {
            this.coder = LATIN1;
            this.value = Arrays.copyOfRange(val, 0, length);
        } else {
            if (COMPACT_STRINGS) {
                byte[] buf = StringUTF16.compress(val, 0, length);
                if (buf != null) {
                    this.coder = LATIN1;
                    this.value = buf;
                    return;
                }
            }
            this.coder = UTF16;
            this.value = Arrays.copyOfRange(val, 0, length << 1);
        }
    }

    String(byte[] value, byte coder) {
        this.value = value;
        this.coder = coder;
    }

    byte coder();

    byte[] value();

    private boolean isLatin1();

    @Native
    static final byte LATIN1 = 0;

    @Native
    static final byte UTF16 = 1;

    static void checkIndex(int index, int length);

    static void checkOffset(int offset, int length);

    static void checkBoundsOffCount(int offset, int count, int length);

    static void checkBoundsBeginEnd(int begin, int end, int length);

    static String valueOfCodePoint(int codePoint);
}
