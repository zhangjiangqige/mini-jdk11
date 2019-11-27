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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.SignedPositive;
import org.checkerframework.checker.signedness.qual.SignednessGlb;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.checker.signedness.qual.Unsigned;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Native;
import java.math.*;
import java.util.Objects;
import jdk.internal.HotSpotIntrinsicCandidate;
import static java.lang.String.COMPACT_STRINGS;
import static java.lang.String.LATIN1;
import static java.lang.String.UTF16;

@AnnotatedFor({ "nullness", "index", "signedness" })
public final class Long extends Number implements Comparable<Long> {

    @Native
    public static final long MIN_VALUE;

    @Native
    @SignedPositive
    public static final long MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static final Class<Long> TYPE;

    @SideEffectFree
    public static String toString(long i, @Positive int radix);

    public static String toUnsignedString(@Unsigned long i, @Positive int radix);

    @SideEffectFree
    public static String toHexString(@Unsigned long i);

    @SideEffectFree
    public static String toOctalString(@Unsigned long i);

    @SideEffectFree
    public static String toBinaryString(@Unsigned long i);

    public static String toString(long i);

    public static String toUnsignedString(@Unsigned long i);

    @Pure
    public static long parseLong(String s, @Positive int radix) throws NumberFormatException;

    public static long parseLong(CharSequence s, int beginIndex, int endIndex, int radix) throws NumberFormatException;

    public static long parseLong(String s) throws NumberFormatException;

    @Unsigned
    public static long parseUnsignedLong(String s, @Positive int radix) throws NumberFormatException;

    public static long parseUnsignedLong(CharSequence s, int beginIndex, int endIndex, int radix) throws NumberFormatException;

    @Unsigned
    public static long parseUnsignedLong(String s) throws NumberFormatException;

    @SideEffectFree
    public static Long valueOf(String s, @Positive int radix) throws NumberFormatException;

    public static Long valueOf(String s) throws NumberFormatException;

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public static Long valueOf(long l);

    @SideEffectFree
    public static Long decode(String nm) throws NumberFormatException;

    @SideEffectFree
    @Deprecated()
    @PolyIndex
    public Long(@PolyIndex long value) {
    }

    @SideEffectFree
    @Deprecated()
    public Long(String s) throws NumberFormatException {
    }

    @Pure
    @PolyIndex
    public byte byteValue(@PolyIndex Long this);

    @Pure
    @PolyIndex
    public short shortValue(@PolyIndex Long this);

    @Pure
    @PolyIndex
    public int intValue(@PolyIndex Long this);

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public long longValue(@PolyIndex Long this);

    @Pure
    public float floatValue();

    @Pure
    public double doubleValue();

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(long value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @SideEffectFree
    @Nullable
    public static Long getLong(@Nullable String nm);

    @SideEffectFree
    public static Long getLong(@Nullable String nm, long val);

    @SideEffectFree
    @PolyNull
    public static Long getLong(@Nullable String nm, @PolyNull Long val);

    @Pure
    public int compareTo(Long anotherLong);

    public static int compare(long x, long y);

    public static int compareUnsigned(@Unsigned long x, @Unsigned long y);

    @Unsigned
    public static long divideUnsigned(@Unsigned long dividend, @Unsigned long divisor);

    @Unsigned
    public static long remainderUnsigned(@Unsigned long dividend, @Unsigned long divisor);

    @Native
    @SignedPositive
    public static final int SIZE;

    @SignedPositive
    public static final int BYTES;

    @Pure
    public static long highestOneBit(@UnknownSignedness long i);

    @Pure
    public static long lowestOneBit(@UnknownSignedness long i);

    @Pure
    @HotSpotIntrinsicCandidate
    @NonNegative
    public static int numberOfLeadingZeros(@UnknownSignedness long i);

    @Pure
    @HotSpotIntrinsicCandidate
    @NonNegative
    public static int numberOfTrailingZeros(@UnknownSignedness long i);

    @Pure
    @HotSpotIntrinsicCandidate
    @NonNegative
    public static int bitCount(@UnknownSignedness long i);

    @Pure
    @PolySigned
    public static long rotateLeft(@PolySigned long i, int distance);

    @Pure
    @PolySigned
    public static long rotateRight(@PolySigned long i, int distance);

    @Pure
    @SignednessGlb
    public static long reverse(@PolySigned long i);

    @Pure
    @GTENegativeOne
    public static int signum(long i);

    @Pure
    @HotSpotIntrinsicCandidate
    @SignednessGlb
    public static long reverseBytes(@PolySigned long i);

    public static long sum(long a, long b);

    public static long max(long a, long b);

    public static long min(long a, long b);
}
