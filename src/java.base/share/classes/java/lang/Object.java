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

import org.checkerframework.checker.guieffect.qual.PolyUI;
import org.checkerframework.checker.guieffect.qual.PolyUIType;
import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "guieffect", "index", "lock", "nullness" })
@PolyUIType
public class Object {

    @HotSpotIntrinsicCandidate
    public Object() {
    }

    @SafeEffect
    @Pure
    @HotSpotIntrinsicCandidate
    public final native Class<?> getClass(@PolyUI @GuardSatisfied @UnknownInitialization Object this);

    @Pure
    @HotSpotIntrinsicCandidate
    public native int hashCode(@GuardSatisfied Object this);

    @Pure
    public boolean equals(@GuardSatisfied Object this, @GuardSatisfied @Nullable Object obj);

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    protected native Object clone(@GuardSatisfied Object this) throws CloneNotSupportedException;

    @CFComment({ "nullness: toString() is @SideEffectFree rather than @Pure because it returns a string", "that differs according to ==, and @Deterministic requires that the results of", "two calls of the method are ==." })
    @SideEffectFree
    public String toString(@GuardSatisfied Object this);

    @HotSpotIntrinsicCandidate
    public final native void notify();

    @HotSpotIntrinsicCandidate
    public final native void notifyAll();

    public final void wait(@UnknownInitialization Object this) throws InterruptedException;

    public final native void wait(@NonNegative long timeoutMillis) throws InterruptedException;

    public final void wait(@UnknownInitialization Object this, long timeoutMillis, @NonNegative int nanos) throws InterruptedException;

    @Deprecated()
    protected void finalize() throws Throwable;
}
