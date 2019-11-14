package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface LongPredicate {

    boolean test(long value);

    default LongPredicate and(LongPredicate other);

    default LongPredicate negate();

    default LongPredicate or(LongPredicate other);
}
