/*
 * Copyright (c) 2000, 2016, Oracle and/or its affiliates. All rights reserved.
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
package java.nio;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.misc.JavaNioAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.Unsafe;
import java.util.Spliterator;

@AnnotatedFor({ "index" })
public abstract class Buffer {

    @NonNegative
    public final int capacity();

    @NonNegative
    public final int position();

    public Buffer position(@NonNegative int newPosition);

    @NonNegative
    public final int limit();

    public Buffer limit(@NonNegative int newLimit);

    public Buffer mark();

    public Buffer reset();

    public Buffer clear();

    public Buffer flip();

    public Buffer rewind();

    @NonNegative
    public final int remaining();

    public final boolean hasRemaining();

    public abstract boolean isReadOnly();

    public abstract boolean hasArray();

    public abstract Object array();

    @NonNegative
    public abstract int arrayOffset();

    public abstract boolean isDirect();

    public abstract Buffer slice();

    public abstract Buffer duplicate();
}
