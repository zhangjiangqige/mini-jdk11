package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.util.Preconditions;
import jdk.internal.vm.annotation.ForceInline;
import java.util.function.Supplier;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public final class Objects {

    @Pure
    public static boolean equals(@GuardSatisfied @Nullable Object a, @GuardSatisfied @Nullable Object b);

    @Pure
    public static boolean deepEquals(@GuardSatisfied @Nullable Object a, @GuardSatisfied @Nullable Object b);

    @Pure
    public static int hashCode(@GuardSatisfied @Nullable Object o);

    @Pure
    public static int hash(@GuardSatisfied @Nullable Object... values);

    @SideEffectFree
    public static String toString(@GuardSatisfied @Nullable Object o);

    @SideEffectFree
    public static String toString(@GuardSatisfied @Nullable Object o, String nullDefault);

    @Pure
    public static <T> int compare(@GuardSatisfied @Nullable T a, @GuardSatisfied @Nullable T b, @GuardSatisfied Comparator<? super T> c);

    @CFComment({ "lock: TODO: treat like other nullness assertion methods in the Checker Framework." })
    public static <T> T requireNonNull(@Nullable T obj);

    @SideEffectFree
    public static <T> T requireNonNull(@GuardSatisfied @Nullable T obj, @Nullable String message);

    @EnsuresNonNullIf(expression = { "#1" }, result = false)
    @Pure
    public static boolean isNull(@GuardSatisfied @Nullable Object obj);

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @Pure
    public static boolean nonNull(@GuardSatisfied @Nullable Object obj);

    public static <T> T requireNonNullElse(T obj, T defaultObj);

    public static <T> T requireNonNullElseGet(T obj, Supplier<? extends T> supplier);

    @CFComment({ "nullness: TODO: treat like other nullness assertion methods in the Checker Framework." })
    @Pure
    public static <T> T requireNonNull(@GuardSatisfied @Nullable T obj, @GuardSatisfied Supplier<String> messageSupplier);

    @ForceInline
    public static int checkIndex(int index, int length);

    public static int checkFromToIndex(int fromIndex, int toIndex, int length);

    public static int checkFromIndexSize(int fromIndex, int size, int length);
}
