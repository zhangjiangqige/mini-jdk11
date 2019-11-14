package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class StandardWatchEventKinds {

    private StandardWatchEventKinds() {
    }

    public static final WatchEvent.Kind<Object> OVERFLOW = new StdWatchEventKind<Object>("OVERFLOW", Object.class);

    public static final WatchEvent.Kind<Path> ENTRY_CREATE = new StdWatchEventKind<Path>("ENTRY_CREATE", Path.class);

    public static final WatchEvent.Kind<Path> ENTRY_DELETE = new StdWatchEventKind<Path>("ENTRY_DELETE", Path.class);

    public static final WatchEvent.Kind<Path> ENTRY_MODIFY = new StdWatchEventKind<Path>("ENTRY_MODIFY", Path.class);

    private static class StdWatchEventKind<T> implements WatchEvent.Kind<T> {

        private final String name;

        private final Class<T> type;

        StdWatchEventKind(String name, Class<T> type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String name();

        @Override
        public Class<T> type();

        @Override
        public String toString();
    }
}
