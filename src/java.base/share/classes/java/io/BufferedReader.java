/*
 * Copyright (c) 1996, 2017, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "lock", "nullness", "index" })
public class BufferedReader extends Reader {

    public BufferedReader(Reader in, @Positive int sz) {
    }

    public BufferedReader(Reader in) {
    }

    @GTENegativeOne
    public int read(@GuardSatisfied BufferedReader this) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(@GuardSatisfied BufferedReader this, char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Nullable
    public String readLine(@GuardSatisfied BufferedReader this) throws IOException;

    @NonNegative
    public long skip(@GuardSatisfied BufferedReader this, @NonNegative long n) throws IOException;

    @EnsuresNonNullIf(expression = { "readLine()" }, result = true)
    @Pure
    public boolean ready(@GuardSatisfied BufferedReader this) throws IOException;

    public boolean markSupported();

    public void mark(@GuardSatisfied BufferedReader this, @NonNegative int readAheadLimit) throws IOException;

    public void reset(@GuardSatisfied BufferedReader this) throws IOException;

    public void close(@GuardSatisfied BufferedReader this) throws IOException;

    public Stream<String> lines(@GuardSatisfied BufferedReader this);
}
