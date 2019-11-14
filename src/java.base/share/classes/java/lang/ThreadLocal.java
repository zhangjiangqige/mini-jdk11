package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.misc.TerminatingThreadLocal;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@CFComment({ "nullness: It is permitted to write a subclass that extends ThreadLocal<@NonNull MyType>", "but in such a case:", "* the subclass must override initialValue to return a non-null value", "* the subclass needs to suppress a warning:", "@SuppressWarnings(\"nullness:type.argument.type.incompatible\") // initialValue returns non-null" })
@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public class ThreadLocal<@Nullable T> {

    private final int threadLocalHashCode = nextHashCode();

    private static AtomicInteger nextHashCode = new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode();

    protected T initialValue();

    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier);

    public ThreadLocal() {
    }

    public T get();

    boolean isPresent();

    private T setInitialValue();

    public void set(T value);

    public void remove();

    ThreadLocalMap getMap(Thread t);

    void createMap(Thread t, T firstValue);

    static ThreadLocalMap createInheritedMap(ThreadLocalMap parentMap);

    T childValue(T parentValue);

    static final class SuppliedThreadLocal<T> extends ThreadLocal<T> {

        private final Supplier<? extends T> supplier;

        SuppliedThreadLocal(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue();
    }

    static class ThreadLocalMap {

        static class Entry extends WeakReference<ThreadLocal<?>> {

            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }

        private static final int INITIAL_CAPACITY = 16;

        private Entry[] table;

        private int size = 0;

        private int threshold;

        private void setThreshold(int len);

        private static int nextIndex(int i, int len);

        private static int prevIndex(int i, int len);

        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
            table = new Entry[INITIAL_CAPACITY];
            int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
            table[i] = new Entry(firstKey, firstValue);
            size = 1;
            setThreshold(INITIAL_CAPACITY);
        }

        private ThreadLocalMap(ThreadLocalMap parentMap) {
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];
            for (Entry e : parentTable) {
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                    if (key != null) {
                        Object value = key.childValue(e.value);
                        Entry c = new Entry(key, value);
                        int h = key.threadLocalHashCode & (len - 1);
                        while (table[h] != null) h = nextIndex(h, len);
                        table[h] = c;
                        size++;
                    }
                }
            }
        }

        private Entry getEntry(ThreadLocal<?> key);

        private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e);

        private void set(ThreadLocal<?> key, Object value);

        private void remove(ThreadLocal<?> key);

        private void replaceStaleEntry(ThreadLocal<?> key, Object value, int staleSlot);

        private int expungeStaleEntry(int staleSlot);

        private boolean cleanSomeSlots(int i, int n);

        private void rehash();

        private void resize();

        private void expungeStaleEntries();
    }
}
