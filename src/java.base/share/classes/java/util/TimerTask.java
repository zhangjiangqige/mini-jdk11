package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public abstract class TimerTask implements Runnable {

    protected TimerTask() {
    }

    public abstract void run();

    public boolean cancel();

    public long scheduledExecutionTime();
}
