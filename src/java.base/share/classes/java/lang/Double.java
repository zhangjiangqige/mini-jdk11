/*
 * Copyright (c) 1994, 2017, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.math.FloatingDecimal;
import jdk.internal.math.DoubleConsts;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "nullness", "index" })
public final class Double extends Number implements Comparable<Double> {

    public static final double POSITIVE_INFINITY;

    public static final double NEGATIVE_INFINITY;

    public static final double NaN;

    public static final double MAX_VALUE;

    public static final double MIN_NORMAL;

    public static final double MIN_VALUE;

    public static final int MAX_EXPONENT;

    public static final int MIN_EXPONENT;

    public static final int SIZE;

    public static final int BYTES;

    @SuppressWarnings("unchecked")
    public static final Class<Double> TYPE;

    @SideEffectFree
    public static String toString(double d);

    @SideEffectFree
    public static String toHexString(double d);

    @SideEffectFree
    public static Double valueOf(String s) throws NumberFormatException;

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public static Double valueOf(double d);

    @Pure
    public static double parseDouble(String s) throws NumberFormatException;

    @Pure
    public static boolean isNaN(double v);

    @Pure
    public static boolean isInfinite(double v);

    public static boolean isFinite(double d);

    @Deprecated()
    public Double(double value) {
    }

    @Deprecated()
    public Double(String s) throws NumberFormatException {
    }

    @Pure
    public boolean isNaN();

    @Pure
    public boolean isInfinite();

    @SideEffectFree
    public String toString();

    @Pure
    public byte byteValue();

    @Pure
    public short shortValue();

    @Pure
    public int intValue();

    @Pure
    public long longValue();

    @Pure
    public float floatValue();

    @Pure
    @HotSpotIntrinsicCandidate
    public double doubleValue();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(double value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    @HotSpotIntrinsicCandidate
    public static long doubleToLongBits(double value);

    @Pure
    @HotSpotIntrinsicCandidate
    public static native long doubleToRawLongBits(double value);

    @Pure
    @HotSpotIntrinsicCandidate
    public static native double longBitsToDouble(long bits);

    @Pure
    public int compareTo(Double anotherDouble);

    @Pure
    public static int compare(double d1, double d2);

    public static double sum(double a, double b);

    public static double max(double a, double b);

    public static double min(double a, double b);
}
