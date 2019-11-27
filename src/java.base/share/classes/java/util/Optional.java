/*
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.Covariant;
import org.checkerframework.framework.qual.CFComment;
import org.checkerframework.checker.optional.qual.Present;
import org.checkerframework.framework.qual.EnsuresQualifierIf;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@CFComment({ "nullness :", "The @NonNull annotation on the class makes the type \"@Nullable Optional<T>\" illegal and enforces", "\"Rule #1: Never, ever, use null for an Optional variable or return value.\" from", "https://stuartmarks.files.wordpress.com/2016/09/optionalmotherofallbikesheds3.pdf, which is", "generally accepted practice.  If you wish to permit the type \"@Nullable Optional\", you may do so", "by writing a stub file that overrides this class in the annotated JDK.", "The type argument to Optional is meaningless.", "Optional<@NonNull String> and Optional<@Nullable String> have the same", "meaning, but are unrelated by the Java type hierarchy.", "@Covariant makes Optional<@NonNull String> a subtype of Optional<@Nullable String>." })
@AnnotatedFor({ "lock", "nullness", "optional" })
@Covariant(0)
@NonNull
public final class Optional<T> {

    public static <T> Optional<T> empty();

    @Present
    public static <T> Optional<T> of(@NonNull T value);

    public static <T> Optional<T> ofNullable(@Nullable T value);

    @NonNull
    public T get(@Present Optional<T> this);

    @EnsuresQualifierIf(result = true, expression = "this", qualifier = Present.class)
    public boolean isPresent();

    public boolean isEmpty();

    public void ifPresent(Consumer<? super T> action);

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction);

    public Optional<T> filter(Predicate<? super T> predicate);

    public <U> Optional<U> map(Function<? super T, ? extends @Nullable U> mapper);

    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper);

    public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier);

    public Stream<T> stream();

    @PolyNull
    public T orElse(@PolyNull T other);

    @PolyNull
    public T orElseGet(Supplier<? extends @PolyNull T> supplier);

    public T orElseThrow();

    public <X extends Throwable> T orElseThrow(@Present Optional<T> this, Supplier<? extends X> exceptionSupplier) throws X;

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
