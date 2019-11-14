package java.io;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
class ExpiringCache {

    private long millisUntilExpiration;

    private Map<String, Entry> map;

    private int queryCount;

    private int queryOverflow = 300;

    private int MAX_ENTRIES = 200;

    static class Entry {

        private long timestamp;

        private String val;

        Entry(long timestamp, String val) {
            this.timestamp = timestamp;
            this.val = val;
        }

        long timestamp();

        void setTimestamp(long timestamp);

        String val();

        void setVal(String val);
    }

    ExpiringCache() {
        this(30000);
    }

    @SuppressWarnings("serial")
    ExpiringCache(long millisUntilExpiration) {
        this.millisUntilExpiration = millisUntilExpiration;
        map = new LinkedHashMap<>() {

            protected boolean removeEldestEntry(Map.Entry<String, Entry> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    synchronized String get(String key);

    synchronized void put(String key, String val);

    synchronized void clear();

    private Entry entryFor(String key);

    private void cleanup();
}
