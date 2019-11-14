package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@AnnotatedFor({ "lock", "nullness" })
public final class OptionalInt {

    private static final OptionalInt EMPTY = new OptionalInt();

    private final boolean isPresent;

    private final int value;

    private OptionalInt() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalInt empty();

    private OptionalInt(int value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalInt of(int value);

    public int getAsInt();

    public boolean isPresent();

    public boolean isEmpty();

    public void ifPresent(IntConsumer action);

    public void ifPresentOrElse(IntConsumer action, Runnable emptyAction);

    public IntStream stream();

    public int orElse(int other);

    public int orElseGet(IntSupplier supplier);

    public int orElseThrow();

    public <X extends Throwable> int orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
