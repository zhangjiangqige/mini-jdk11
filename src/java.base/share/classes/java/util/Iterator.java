package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import org.checkerframework.framework.qual.Covariant;
import java.util.function.Consumer;

@CFComment({ "nullness: This @Covariant annotation is sound, but it would not be sound on", "ListIterator (a subclass of Iterator), which supports a set operation." })
@AnnotatedFor({ "lock" })
@Covariant({ 0 })
public interface Iterator<E> {

    boolean hasNext(@GuardSatisfied Iterator<E> this);

    E next(@GuardSatisfied Iterator<E> this);

    default void remove(@GuardSatisfied Iterator<E> this);

    default void forEachRemaining(Consumer<? super E> action);
}
