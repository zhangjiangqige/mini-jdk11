/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
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
package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "index", "interning", "lock", "nullness", "signedness" })
@UsesObjectEquals
public class Random implements java.io.Serializable {

    public Random() {
    }

    public Random(long seed) {
    }

    public synchronized void setSeed(@GuardSatisfied Random this, long seed);

    protected int next(int bits);

    public void nextBytes(@PolySigned byte[] bytes);

    public int nextInt();

    @NonNegative
    public int nextInt(@Positive int bound);

    public long nextLong();

    public boolean nextBoolean();

    public float nextFloat();

    public double nextDouble();

    public synchronized double nextGaussian();

    public IntStream ints(long streamSize);

    public IntStream ints();

    public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound);

    public IntStream ints(int randomNumberOrigin, int randomNumberBound);

    public LongStream longs(long streamSize);

    public LongStream longs();

    public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound);

    public LongStream longs(long randomNumberOrigin, long randomNumberBound);

    public DoubleStream doubles(long streamSize);

    public DoubleStream doubles();

    public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound);

    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound);
}
