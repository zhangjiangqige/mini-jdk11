package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public abstract class TimerTask implements Runnable {

    final Object lock = new Object();

    int state = VIRGIN;

    static final int VIRGIN = 0;

    static final int SCHEDULED = 1;

    static final int EXECUTED = 2;

    static final int CANCELLED = 3;

    long nextExecutionTime;

    long period = 0;

    protected TimerTask() {
    }

    public abstract void run();

    public boolean cancel();

    public long scheduledExecutionTime();
}
