/*
 * Copyright (c) 1994, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
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

    @SideEffectFree
    public String() {
    }

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public String(String original) {
    }

    @SideEffectFree
    public String(char @GuardSatisfied [] value) {
    }

    @SideEffectFree
    public String(char @GuardSatisfied [] value, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
    }

    @SideEffectFree
    public String(int @GuardSatisfied [] codePoints, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
    }

    @SideEffectFree
    @Deprecated()
    public String(byte @GuardSatisfied [] ascii, int hibyte, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
    }

    @SideEffectFree
    @Deprecated()
    public String(byte @GuardSatisfied [] ascii, int hibyte) {
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length, String charsetName) throws UnsupportedEncodingException {
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length, Charset charset) {
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, String charsetName) throws UnsupportedEncodingException {
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, Charset charset) {
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length) {
    }

    @SideEffectFree
    public String(@PolySigned byte @GuardSatisfied [] bytes) {
    }

    @SideEffectFree
    public String(@GuardSatisfied StringBuffer buffer) {
    }

    @SideEffectFree
    public String(@GuardSatisfied StringBuilder builder) {
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

    @Deprecated()
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

    @Pure
    public boolean contentEquals(@GuardSatisfied CharSequence cs);

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @Pure
    public boolean equalsIgnoreCase(@Nullable String anotherString);

    @Pure
    public int compareTo(String anotherString);

    public static final Comparator<String> CASE_INSENSITIVE_ORDER;

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

    @Pure
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int lastIndexOf(String str);

    @Pure
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int lastIndexOf(String str, int fromIndex);

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
}
