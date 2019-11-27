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
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "nullness" })
public final class Float extends Number implements Comparable<Float> {

    public static final float POSITIVE_INFINITY;

    public static final float NEGATIVE_INFINITY;

    public static final float NaN;

    public static final float MAX_VALUE;

    public static final float MIN_NORMAL;

    public static final float MIN_VALUE;

    public static final int MAX_EXPONENT;

    public static final int MIN_EXPONENT;

    public static final int SIZE;

    public static final int BYTES;

    @SuppressWarnings("unchecked")
    public static final Class<Float> TYPE;

    @SideEffectFree
    public static String toString(float f);

    @SideEffectFree
    public static String toHexString(float f);

    @SideEffectFree
    public static Float valueOf(String s) throws NumberFormatException;

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public static Float valueOf(float f);

    @Pure
    public static float parseFloat(String s) throws NumberFormatException;

    @Pure
    public static boolean isNaN(float v);

    @Pure
    public static boolean isInfinite(float v);

    public static boolean isFinite(float f);

    @Deprecated()
    public Float(float value) {
    }

    @Deprecated()
    public Float(double value) {
    }

    @Deprecated()
    public Float(String s) throws NumberFormatException {
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
    @HotSpotIntrinsicCandidate
    public float floatValue();

    @Pure
    public double doubleValue();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(float value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    @HotSpotIntrinsicCandidate
    public static int floatToIntBits(float value);

    @HotSpotIntrinsicCandidate
    public static native int floatToRawIntBits(float value);

    @HotSpotIntrinsicCandidate
    public static native float intBitsToFloat(int bits);

    @Pure
    public int compareTo(Float anotherFloat);

    @Pure
    public static int compare(float f1, float f2);

    public static float sum(float a, float b);

    public static float max(float a, float b);

    public static float min(float a, float b);
}
