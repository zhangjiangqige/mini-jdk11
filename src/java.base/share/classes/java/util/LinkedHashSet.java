package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: This class permits null elements" })
@AnnotatedFor({ "lock", "nullness" })
public class LinkedHashSet<E> extends HashSet<E> implements Set<E>, Cloneable, java.io.Serializable {

    public LinkedHashSet(int initialCapacity, float loadFactor) {
    }

    public LinkedHashSet(int initialCapacity) {
    }

    public LinkedHashSet() {
    }

    public LinkedHashSet(Collection<? extends E> c) {
    }

    @Override
    public Spliterator<E> spliterator();
}
