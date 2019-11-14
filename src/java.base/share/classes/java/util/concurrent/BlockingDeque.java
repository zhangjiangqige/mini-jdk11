package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

@AnnotatedFor({ "nullness" })
public interface BlockingDeque<E extends @NonNull Object> extends BlockingQueue<E>, Deque<E> {

    void addFirst(E e);

    void addLast(E e);

    boolean offerFirst(E e);

    boolean offerLast(E e);

    void putFirst(E e) throws InterruptedException;

    void putLast(E e) throws InterruptedException;

    boolean offerFirst(E e, long timeout, TimeUnit unit) throws InterruptedException;

    boolean offerLast(E e, long timeout, TimeUnit unit) throws InterruptedException;

    E takeFirst() throws InterruptedException;

    E takeLast() throws InterruptedException;

    @Nullable
    E pollFirst(long timeout, TimeUnit unit) throws InterruptedException;

    @Nullable
    E pollLast(long timeout, TimeUnit unit) throws InterruptedException;

    boolean removeFirstOccurrence(Object o);

    boolean removeLastOccurrence(Object o);

    boolean add(E e);

    boolean offer(E e);

    void put(E e) throws InterruptedException;

    boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    E remove();

    @Nullable
    E poll();

    E take() throws InterruptedException;

    @Nullable
    E poll(long timeout, TimeUnit unit) throws InterruptedException;

    E element();

    @Nullable
    E peek();

    boolean remove(Object o);

    @Pure
    boolean contains(Object o);

    @Pure
    int size();

    @SideEffectFree
    Iterator<E> iterator();

    void push(E e);
}
