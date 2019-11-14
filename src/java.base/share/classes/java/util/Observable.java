package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "guieffect:", "@PolyUIType class Observable {", "@SafeEffect void addObserver(@PolyUI Observable this, @PolyUI Observer o);", "@SafeEffect void deleteObserver(@PolyUI Observable this, @PolyUI Observer o);", "@PolyUIEffect void notifyObservers(@PolyUI Observable this);", "@PolyUIEffect void notifyObservers(@PolyUI Observable this, Object arg);}" })
@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@Deprecated(since = "9")
@UsesObjectEquals
public class Observable {

    private boolean changed = false;

    private Vector<Observer> obs;

    public Observable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(@GuardSatisfied Observable this, Observer o);

    public synchronized void deleteObserver(@GuardSatisfied Observable this, @Nullable Observer o);

    public void notifyObservers();

    public void notifyObservers(@Nullable Object arg);

    public synchronized void deleteObservers(@GuardSatisfied Observable this);

    protected synchronized void setChanged();

    protected synchronized void clearChanged();

    public synchronized boolean hasChanged();

    @NonNegative
    public synchronized int countObservers();
}
