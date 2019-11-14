package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "nullness" })
public class DelayQueue<E extends @NonNull Delayed> extends AbstractQueue<E> implements BlockingQueue<E> {

    private final transient ReentrantLock lock = new ReentrantLock();

    private final PriorityQueue<E> q = new PriorityQueue<E>();

    @Nullable
    private Thread leader;

    private final Condition available = lock.newCondition();

    public DelayQueue() {
    }

    public DelayQueue(Collection<? extends E> c) {
        this.addAll(c);
    }

    public boolean add(E e);

    public boolean offer(E e);

    public void put(E e);

    public boolean offer(E e, long timeout, TimeUnit unit);

    @Nullable
    public E poll();

    public E take() throws InterruptedException;

    @Nullable
    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    @Nullable
    public E peek();

    @Pure
    public int size();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    public void clear();

    public int remainingCapacity();

    @SideEffectFree
    @PolyNull
    public Object[] toArray(DelayQueue<@PolyNull E> this);

    @SideEffectFree
    public <T> T[] toArray(T[] a);

    public boolean remove(Object o);

    void removeEQ(Object o);

    @SideEffectFree
    public Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        final Object[] array;

        int cursor;

        int lastRet;

        Itr(Object[] array) {
            lastRet = -1;
            this.array = array;
        }

        public boolean hasNext();

        @SuppressWarnings("unchecked")
        public E next();

        public void remove();
    }
}
