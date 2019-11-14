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

    void put(Class<?> type, T value);

    private static Entry<?>[] getCacheCarefully(Class<?> type);

    private static final Entry<?>[] EMPTY_CACHE = { null };

    private T getFromBackup(Entry<?>[] cache, Class<?> type);

    @SuppressWarnings("unchecked")
    Entry<T> castEntry(Entry<?> e);

    private T getFromHashMap(Class<?> type);

    boolean match(Entry<?> e);

    final int hashCodeForCache = nextHashCode.getAndAdd(HASH_INCREMENT) & HASH_MASK;

    private static final AtomicInteger nextHashCode = new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    static final int HASH_MASK = (-1 >>> 2);

    static class Identity {
    }

    final Identity identity = new Identity();

    private volatile Version<T> version = new Version<>(this);

    Version<T> version();

    void bumpVersion();

    static class Version<T> {

        private final ClassValue<T> classValue;

        private final Entry<T> promise = new Entry<>(this);

        Version(ClassValue<T> classValue) {
            this.classValue = classValue;
        }

        ClassValue<T> classValue();

        Entry<T> promise();

        boolean isLive();
    }

    static class Entry<T> extends WeakReference<Version<T>> {

        final Object value;

        Entry(Version<T> version, T value) {
            super(version);
            this.value = value;
        }

        private void assertNotPromise();

        Entry(Version<T> version) {
            super(version);
            this.value = this;
        }

        @SuppressWarnings("unchecked")
        T value();

        boolean isPromise();

        Version<T> version();

        ClassValue<T> classValueOrNull();

        boolean isLive();

        Entry<T> refreshVersion(Version<T> v2);

        static final Entry<?> DEAD_ENTRY = new Entry<>(null, null);
    }

    private static ClassValueMap getMap(Class<?> type);

    private static final Object CRITICAL_SECTION = new Object();

    private static ClassValueMap initializeMap(Class<?> type);

    static <T> Entry<T> makeEntry(Version<T> explicitVersion, T value);

    static class ClassValueMap extends WeakHashMap<ClassValue.Identity, Entry<?>> {

        private Entry<?>[] cacheArray;

        private int cacheLoad, cacheLoadLimit;

        private static final int INITIAL_ENTRIES = 32;

        ClassValueMap() {
            sizeCache(INITIAL_ENTRIES);
        }

        Entry<?>[] getCache();

        synchronized <T> Entry<T> startEntry(ClassValue<T> classValue);

        synchronized <T> Entry<T> finishEntry(ClassValue<T> classValue, Entry<T> e);

        synchronized void removeEntry(ClassValue<?> classValue);

        synchronized <T> void changeEntry(ClassValue<T> classValue, T value);

        static Entry<?> loadFromCache(Entry<?>[] cache, int i);

        static <T> Entry<T> probeHomeLocation(Entry<?>[] cache, ClassValue<T> classValue);

        static <T> Entry<T> probeBackupLocations(Entry<?>[] cache, ClassValue<T> classValue);

        private static int entryDislocation(Entry<?>[] cache, int pos, Entry<?> e);

        private void sizeCache(int length);

        private void checkCacheLoad();

        private void reduceCacheLoad();

        private void removeStaleEntries(Entry<?>[] cache, int begin, int count);

        private Entry<?> findReplacement(Entry<?>[] cache, int home1);

        private void removeStaleEntries(ClassValue<?> classValue);

        private void removeStaleEntries();

        private <T> void addToCache(Entry<T> e);

        private <T> void addToCache(ClassValue<T> classValue, Entry<T> e);

        private Entry<?> placeInCache(Entry<?>[] cache, int pos, Entry<?> e, boolean gently);

        private <T> Entry<T> overwrittenEntry(Entry<T> e2);

        private static final int CACHE_LOAD_LIMIT = 67;

        private static final int PROBE_LIMIT = 6;
    }
}
