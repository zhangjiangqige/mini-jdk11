package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class StandardWatchEventKinds {

    public static final WatchEvent.Kind<Object> OVERFLOW;

    public static final WatchEvent.Kind<Path> ENTRY_CREATE;

    public static final WatchEvent.Kind<Path> ENTRY_DELETE;

    public static final WatchEvent.Kind<Path> ENTRY_MODIFY;
}
