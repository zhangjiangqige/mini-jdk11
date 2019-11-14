package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: permit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public class Stack<E> extends Vector<E> {

    public Stack() {
    }

    public E push(@GuardSatisfied Stack<E> this, E item);

    public synchronized E pop(@GuardSatisfied Stack<E> this);

    public synchronized E peek();

    public boolean empty();

    public synchronized int search(Object o);

    private static final long serialVersionUID = 1224463164541339165L;
}
