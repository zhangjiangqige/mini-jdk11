/*
 * Copyright (c) 1994, 2013, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "interning", "nullness" })
public final class Boolean implements java.io.Serializable, Comparable<Boolean> {

    @Interned
    public static final Boolean TRUE;

    @Interned
    public static final Boolean FALSE;

    @SuppressWarnings("unchecked")
    public static final Class<Boolean> TYPE;

    @Deprecated()
    public Boolean(boolean value) {
    }

    @Deprecated()
    public Boolean(@Nullable String s) {
    }

    @Pure
    public static boolean parseBoolean(@Nullable String s);

    @Pure
    @HotSpotIntrinsicCandidate
    public boolean booleanValue();

    @Pure
    @HotSpotIntrinsicCandidate
    @Interned
    public static Boolean valueOf(boolean b);

    @Pure
    @Interned
    public static Boolean valueOf(@Nullable String s);

    @Pure
    public static String toString(boolean b);

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(boolean value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    public static boolean getBoolean(@Nullable String name);

    @Pure
    public int compareTo(Boolean b);

    public static int compare(boolean x, boolean y);

    public static boolean logicalAnd(boolean a, boolean b);

    public static boolean logicalOr(boolean a, boolean b);

    public static boolean logicalXor(boolean a, boolean b);
}
