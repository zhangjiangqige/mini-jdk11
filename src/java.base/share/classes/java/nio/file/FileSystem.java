package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.attribute.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;
import java.io.Closeable;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FileSystem implements Closeable {

    protected FileSystem() {
    }

    public abstract FileSystemProvider provider();

    @Override
    public abstract void close() throws IOException;

    public abstract boolean isOpen();

    public abstract boolean isReadOnly();

    public abstract String getSeparator();

    public abstract Iterable<Path> getRootDirectories();

    public abstract Iterable<FileStore> getFileStores();

    public abstract Set<String> supportedFileAttributeViews();

    public abstract Path getPath(String first, String... more);

    public abstract PathMatcher getPathMatcher(String syntaxAndPattern);

    public abstract UserPrincipalLookupService getUserPrincipalLookupService();

    public abstract WatchService newWatchService() throws IOException;
}
