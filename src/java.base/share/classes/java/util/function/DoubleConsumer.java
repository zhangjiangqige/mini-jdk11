package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface DoubleConsumer {

    void accept(double value);

    default DoubleConsumer andThen(DoubleConsumer after);
}