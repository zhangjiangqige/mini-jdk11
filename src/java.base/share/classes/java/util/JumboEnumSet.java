package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index" })
class JumboEnumSet<E extends Enum<E>> extends EnumSet<E> {

    private static final long serialVersionUID = 334349849919042784L;

    private long[] elements;

    private int size = 0;

    JumboEnumSet(Class<E> elementType, Enum<?>[] universe) {
        super(elementType, universe);
        elements = new long[(universe.length + 63) >>> 6];
    }

    void addRange(E from, E to);

    void addAll();

    void complement();

    public Iterator<E> iterator();

    private class EnumSetIterator<E extends Enum<E>> implements Iterator<E> {

        long unseen;

        int unseenIndex = 0;

        long lastReturned = 0;

        int lastReturnedIndex = 0;

        EnumSetIterator() {
            unseen = elements[0];
        }

        @Override
        public boolean hasNext();

        @Override
        @SuppressWarnings("unchecked")
        public E next();

        @Override
        public void remove();
    }

    @NonNegative
    public int size();

    public boolean isEmpty();

    public boolean contains(Object e);

    public boolean add(E e);

    public boolean remove(Object e);

    public boolean containsAll(Collection<?> c);

    public boolean addAll(Collection<? extends E> c);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public void clear();

    public boolean equals(Object o);

    private boolean recalculateSize();

    public EnumSet<E> clone();
}
