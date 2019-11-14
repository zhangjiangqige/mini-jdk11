package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import sun.nio.fs.BasicFileAttributesHolder;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class FileTreeWalker implements Closeable {

    private final boolean followLinks;

    private final LinkOption[] linkOptions;

    private final int maxDepth;

    private final ArrayDeque<DirectoryNode> stack = new ArrayDeque<>();

    private boolean closed;

    private static class DirectoryNode {

        private final Path dir;

        private final Object key;

        private final DirectoryStream<Path> stream;

        private final Iterator<Path> iterator;

        private boolean skipped;

        DirectoryNode(Path dir, Object key, DirectoryStream<Path> stream) {
            this.dir = dir;
            this.key = key;
            this.stream = stream;
            this.iterator = stream.iterator();
        }

        Path directory();

        Object key();

        DirectoryStream<Path> stream();

        Iterator<Path> iterator();

        void skip();

        boolean skipped();
    }

    static enum EventType {

        START_DIRECTORY, END_DIRECTORY, ENTRY
    }

    static class Event {

        private final EventType type;

        private final Path file;

        private final BasicFileAttributes attrs;

        private final IOException ioe;

        private Event(EventType type, Path file, BasicFileAttributes attrs, IOException ioe) {
            this.type = type;
            this.file = file;
            this.attrs = attrs;
            this.ioe = ioe;
        }

        Event(EventType type, Path file, BasicFileAttributes attrs) {
            this(type, file, attrs, null);
        }

        Event(EventType type, Path file, IOException ioe) {
            this(type, file, null, ioe);
        }

        EventType type();

        Path file();

        BasicFileAttributes attributes();

        IOException ioeException();
    }

    FileTreeWalker(Collection<FileVisitOption> options, int maxDepth) {
        boolean fl = false;
        for (FileVisitOption option : options) {
            switch(option) {
                case FOLLOW_LINKS:
                    fl = true;
                    break;
                default:
                    throw new AssertionError("Should not get here");
            }
        }
        if (maxDepth < 0)
            throw new IllegalArgumentException("'maxDepth' is negative");
        this.followLinks = fl;
        this.linkOptions = (fl) ? new LinkOption[0] : new LinkOption[] { LinkOption.NOFOLLOW_LINKS };
        this.maxDepth = maxDepth;
    }

    private BasicFileAttributes getAttributes(Path file, boolean canUseCached) throws IOException;

    private boolean wouldLoop(Path dir, Object key);

    private Event visit(Path entry, boolean ignoreSecurityException, boolean canUseCached);

    Event walk(Path file);

    Event next();

    void pop();

    void skipRemainingSiblings();

    boolean isOpen();

    @Override
    public void close();
}
