package java.lang.ref;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import jdk.internal.misc.VM;

@AnnotatedFor({ "interning", "nullness" })
@SuppressWarnings({ "rawtypes" })
@UsesObjectEquals
public class ReferenceQueue<T> {

    public ReferenceQueue() {
    }

    public Reference<? extends T> poll();

    public Reference<? extends T> remove(long timeout) throws IllegalArgumentException, InterruptedException;

    public Reference<? extends T> remove() throws InterruptedException;
}
