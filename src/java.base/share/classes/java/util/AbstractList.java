package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    protected AbstractList() {
    }

    public boolean add(@GuardSatisfied AbstractList<E> this, E e);

    @Pure
    public abstract E get(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index);

    public E set(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index, E element);

    public void add(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int index, E element);

    public E remove(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @Nullable Object o);

    public void clear(@GuardSatisfied AbstractList<E> this);

    public boolean addAll(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int index, Collection<? extends E> c);

    @SideEffectFree
    public Iterator<E> iterator();

    public ListIterator<E> listIterator();

    public ListIterator<E> listIterator(@IndexOrHigh({ "this" }) final int index);

    private class Itr implements Iterator<E> {

        int cursor = 0;

        int lastRet = -1;

        int expectedModCount = modCount;

        public boolean hasNext();

        public E next();

        public void remove();

        final void checkForComodification();
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void set(E e);

        public void add(E e);
    }

    @SideEffectFree
    public List<E> subList(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    static void subListRangeCheck(int fromIndex, int toIndex, int size);

    @Pure
    public boolean equals(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public int hashCode(@GuardSatisfied AbstractList<E> this);

    protected void removeRange(@IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    protected transient int modCount = 0;

    private void rangeCheckForAdd(int index);

    private String outOfBoundsMsg(int index);

    static final class RandomAccessSpliterator<E> implements Spliterator<E> {

        private final List<E> list;

        private int index;

        private int fence;

        private final AbstractList<E> alist;

        private int expectedModCount;

        RandomAccessSpliterator(List<E> list) {
            assert list instanceof RandomAccess;
            this.list = list;
            this.index = 0;
            this.fence = -1;
            this.alist = list instanceof AbstractList ? (AbstractList<E>) list : null;
            this.expectedModCount = alist != null ? alist.modCount : 0;
        }

        private RandomAccessSpliterator(RandomAccessSpliterator<E> parent, int origin, int fence) {
            this.list = parent.list;
            this.index = origin;
            this.fence = fence;
            this.alist = parent.alist;
            this.expectedModCount = parent.expectedModCount;
        }

        private int getFence();

        public Spliterator<E> trySplit();

        public boolean tryAdvance(Consumer<? super E> action);

        public void forEachRemaining(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();

        private static <E> E get(List<E> list, int i);

        static void checkAbstractListModCount(AbstractList<?> alist, int expectedModCount);
    }

    private static class SubList<E> extends AbstractList<E> {

        private final AbstractList<E> root;

        private final SubList<E> parent;

        private final int offset;

        protected int size;

        public SubList(AbstractList<E> root, int fromIndex, int toIndex) {
            this.root = root;
            this.parent = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        protected SubList(SubList<E> parent, int fromIndex, int toIndex) {
            this.root = parent.root;
            this.parent = parent;
            this.offset = parent.offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        public E set(int index, E element);

        public E get(int index);

        public int size();

        public void add(int index, E element);

        public E remove(int index);

        protected void removeRange(int fromIndex, int toIndex);

        public boolean addAll(Collection<? extends E> c);

        public boolean addAll(int index, Collection<? extends E> c);

        public Iterator<E> iterator();

        public ListIterator<E> listIterator(int index);

        public List<E> subList(int fromIndex, int toIndex);

        private void rangeCheckForAdd(int index);

        private String outOfBoundsMsg(int index);

        private void checkForComodification();

        private void updateSizeAndModCount(int sizeChange);
    }

    private static class RandomAccessSubList<E> extends SubList<E> implements RandomAccess {

        RandomAccessSubList(AbstractList<E> root, int fromIndex, int toIndex) {
            super(root, fromIndex, toIndex);
        }

        RandomAccessSubList(RandomAccessSubList<E> parent, int fromIndex, int toIndex) {
            super(parent, fromIndex, toIndex);
        }

        public List<E> subList(int fromIndex, int toIndex);
    }
}
