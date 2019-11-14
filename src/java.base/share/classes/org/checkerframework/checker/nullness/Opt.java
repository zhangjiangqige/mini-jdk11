package org.checkerframework.checker.nullness;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class Opt {

    private Opt() {
        throw new AssertionError("shouldn't be instantiated");
    }

    public static <T extends @NonNull Object> T get(T primary);

    @EnsuresNonNullIf(expression = "#1", result = true)
    public static boolean isPresent(@Nullable Object primary);

    public static <T> void ifPresent(T primary, Consumer<@NonNull ? super @NonNull T> consumer);

    @Nullable
    public static <T> T filter(T primary, Predicate<@NonNull ? super @NonNull T> predicate);

    @Nullable
    public static <T, U> U map(T primary, Function<@NonNull ? super @NonNull T, ? extends U> mapper);

    @NonNull
    public static <T> T orElse(T primary, @NonNull T other);

    @NonNull
    public static <T> T orElseGet(T primary, Supplier<? extends @NonNull T> other);

    public static <T extends @NonNull Object, X extends @NonNull Throwable> T orElseThrow(T primary, Supplier<? extends X> exceptionSupplier) throws X;
}
