/*
 * Copyright (c) 2003, 2017, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.GuardedByUnknown;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;

@AnnotatedFor({ "lock", "nullness", "index" })
@SuppressWarnings("serial")
public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {

    public final String name(@GuardedByUnknown @UnknownInitialization(java.lang.Enum.class) Enum<E> this);

    @NonNegative
    public final int ordinal();

    protected Enum(String name, @NonNegative int ordinal) {
    }

    @SideEffectFree
    public String toString(@GuardSatisfied Enum<E> this);

    @Pure
    public final boolean equals(@GuardSatisfied Enum<E> this, @GuardSatisfied @Nullable Object other);

    @Pure
    public final int hashCode(@GuardSatisfied Enum<E> this);

    @SideEffectFree
    protected final Object clone(@GuardSatisfied Enum<E> this) throws CloneNotSupportedException;

    @SuppressWarnings({ "rawtypes" })
    public final int compareTo(E o);

    @SuppressWarnings("unchecked")
    public final Class<E> getDeclaringClass();

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name);

    @SuppressWarnings("deprecation")
    protected final void finalize();
}
