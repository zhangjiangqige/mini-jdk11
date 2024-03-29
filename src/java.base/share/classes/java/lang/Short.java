/*
 * Copyright (c) 1996, 2013, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "nullness", "index" })
public final class Short extends Number implements Comparable<Short> {

    public static final short MIN_VALUE;

    @Positive
    public static final short MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static final Class<Short> TYPE;

    @SideEffectFree
    public static String toString(short s);

    @Pure
    public static short parseShort(String s, @Positive int radix) throws NumberFormatException;

    @Pure
    public static short parseShort(String s) throws NumberFormatException;

    @SideEffectFree
    public static Short valueOf(String s, @Positive int radix) throws NumberFormatException;

    @SideEffectFree
    public static Short valueOf(String s) throws NumberFormatException;

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public static Short valueOf(@PolyIndex short s);

    @SideEffectFree
    public static Short decode(String nm) throws NumberFormatException;

    @SideEffectFree
    @Deprecated()
    @PolyIndex
    public Short(@PolyIndex short value) {
    }

    @SideEffectFree
    @Deprecated()
    public Short(String s) throws NumberFormatException {
    }

    @Pure
    @PolyIndex
    public byte byteValue(@PolyIndex Short this);

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public short shortValue(@PolyIndex Short this);

    @Pure
    @PolyIndex
    public int intValue(@PolyIndex Short this);

    @Pure
    @PolyIndex
    public long longValue(@PolyIndex Short this);

    @Pure
    public float floatValue();

    @Pure
    public double doubleValue();

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(short value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    public int compareTo(Short anotherShort);

    public static int compare(short x, short y);

    public static int compareUnsigned(short x, short y);

    @Positive
    public static final int SIZE;

    public static final int BYTES;

    @Pure
    @HotSpotIntrinsicCandidate
    public static short reverseBytes(short i);

    public static int toUnsignedInt(short x);

    public static long toUnsignedLong(short x);
}
