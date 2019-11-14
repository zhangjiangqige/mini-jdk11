package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface DoublePredicate {

    boolean test(double value);

    default DoublePredicate and(DoublePredicate other);

    default DoublePredicate negate();

    default DoublePredicate or(DoublePredicate other);
}
