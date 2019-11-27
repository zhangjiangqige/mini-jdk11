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
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "index", "interning", "nullness" })
public final class Byte extends Number implements Comparable<Byte> {

    public static final byte MIN_VALUE;

    @Positive
    public static final byte MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static final Class<Byte> TYPE;

    public static String toString(byte b);

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    @Interned
    public static Byte valueOf(@PolyIndex byte b);

    @Pure
    public static byte parseByte(String s, @Positive int radix) throws NumberFormatException;

    @Pure
    public static byte parseByte(String s) throws NumberFormatException;

    @Pure
    @Interned
    public static Byte valueOf(String s, @Positive int radix) throws NumberFormatException;

    @Pure
    @Interned
    public static Byte valueOf(String s) throws NumberFormatException;

    @Pure
    public static Byte decode(String nm) throws NumberFormatException;

    @Deprecated()
    @PolyIndex
    public Byte(@PolyIndex byte value) {
    }

    @Deprecated()
    public Byte(String s) throws NumberFormatException {
    }

    @Pure
    @HotSpotIntrinsicCandidate
    @PolyIndex
    public byte byteValue(@PolyIndex Byte this);

    @Pure
    @PolyIndex
    public short shortValue(@PolyIndex Byte this);

    @Pure
    @PolyIndex
    public int intValue(@PolyIndex Byte this);

    @Pure
    @PolyIndex
    public long longValue(@PolyIndex Byte this);

    @Pure
    public float floatValue();

    @Pure
    public double doubleValue();

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(byte value);

    public boolean equals(@Nullable Object obj);

    public int compareTo(Byte anotherByte);

    public static int compare(byte x, byte y);

    public static int compareUnsigned(byte x, byte y);

    public static int toUnsignedInt(byte x);

    public static long toUnsignedLong(byte x);

    @Positive
    public static final int SIZE;

    public static final int BYTES;
}
