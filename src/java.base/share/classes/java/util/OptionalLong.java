package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.LongStream;

@AnnotatedFor({ "lock", "nullness" })
public final class OptionalLong {

    private static final OptionalLong EMPTY = new OptionalLong();

    private final boolean isPresent;

    private final long value;

    private OptionalLong() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalLong empty();

    private OptionalLong(long value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalLong of(long value);

    public long getAsLong();

    public boolean isPresent();

    public boolean isEmpty();

    public void ifPresent(LongConsumer action);

    public void ifPresentOrElse(LongConsumer action, Runnable emptyAction);

    public LongStream stream();

    public long orElse(long other);

    public long orElseGet(LongSupplier supplier);

    public long orElseThrow();

    public <X extends Throwable> long orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
