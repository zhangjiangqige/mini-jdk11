package org.checkerframework.checker.nullness;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings({ "nullness", "cast" })
public final class NullnessUtil {

    private NullnessUtil() {
        throw new AssertionError("shouldn't be instantiated");
    }

    @EnsuresNonNull("#1")
    @NonNull
    public static <T extends @Nullable Object> T castNonNull(@Nullable T ref);

    @EnsuresNonNull("#1")
    @NonNull
    public static <T extends @Nullable Object> T @NonNull [] castNonNullDeep(T @Nullable [] arr);

    @EnsuresNonNull("#1")
    @NonNull
    public static <T extends @Nullable Object> T @NonNull [][] castNonNullDeep(T @Nullable [] @Nullable [] arr);

    @EnsuresNonNull("#1")
    @NonNull
    public static <T extends @Nullable Object> T @NonNull [][][] castNonNullDeep(T @Nullable [] @Nullable [] @Nullable [] arr);

    @EnsuresNonNull("#1")
    @NonNull
    public static <T extends @Nullable Object> T @NonNull [][][][] castNonNullDeep(T @Nullable [] @Nullable [] @Nullable [] @Nullable [] arr);

    @EnsuresNonNull("#1")
    @NonNull
    public static <T extends @Nullable Object> T @NonNull [][][][][] castNonNullDeep(T @Nullable [] @Nullable [] @Nullable [] @Nullable [] @Nullable [] arr);

    @NonNull
    private static <T extends @Nullable Object> T @NonNull [] castNonNullArray(T @Nullable [] arr);

    private static void checkIfArray(@NonNull Object ref);
}
