package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.WeakHashMap;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.ClassValue.ClassValueMap.probeHomeLocation;
import static java.lang.ClassValue.ClassValueMap.probeBackupLocations;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ClassValue<T> {

    protected ClassValue() {
    }

    protected abstract T computeValue(Class<?> type);

    public T get(Class<?> type);

    public void remove(Class<?> type);
}
