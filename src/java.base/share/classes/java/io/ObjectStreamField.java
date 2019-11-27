/*
 * Copyright (c) 1996, 2015, Oracle and/or its affiliates. All rights reserved.
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
package java.io;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.Field;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public class ObjectStreamField implements Comparable<Object> {

    public ObjectStreamField(String name, Class<?> type) {
    }

    public ObjectStreamField(String name, Class<?> type, boolean unshared) {
    }

    public String getName();

    @CallerSensitive
    public Class<?> getType();

    public char getTypeCode();

    @Nullable
    @Interned
    public String getTypeString();

    public int getOffset();

    protected void setOffset(int offset);

    @Pure
    public boolean isPrimitive(@GuardSatisfied ObjectStreamField this);

    @Pure
    public boolean isUnshared(@GuardSatisfied ObjectStreamField this);

    @Pure
    public int compareTo(@GuardSatisfied ObjectStreamField this, @GuardSatisfied Object obj);

    @SideEffectFree
    public String toString(@GuardSatisfied ObjectStreamField this);
}
