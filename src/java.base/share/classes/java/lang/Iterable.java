package java.lang;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

@AnnotatedFor({ "lock", "nullness" })
public interface Iterable<T> {

    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action);

    default Spliterator<T> spliterator();
}
