package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public interface Enumeration<E> {

    boolean hasMoreElements();

    E nextElement();

    default Iterator<E> asIterator();
}
