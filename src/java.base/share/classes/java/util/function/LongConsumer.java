package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface LongConsumer {

    void accept(long value);

    default LongConsumer andThen(LongConsumer after);
}
