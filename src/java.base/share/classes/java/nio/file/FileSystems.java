package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.spi.FileSystemProvider;
import java.net.URI;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import jdk.internal.misc.VM;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class FileSystems {

    private FileSystems() {
    }

    private static final FileSystemProvider builtinFileSystemProvider = sun.nio.fs.DefaultFileSystemProvider.create();

    private static class BuiltinFileSystemHolder {

        static final FileSystem builtinFileSystem = builtinFileSystemProvider.getFileSystem(URI.create("file:///"));
    }

    private static class DefaultFileSystemHolder {

        static final FileSystem defaultFileSystem = defaultFileSystem();

        private static FileSystem defaultFileSystem();

        private static FileSystemProvider getDefaultProvider();
    }

    public static FileSystem getDefault();

    public static FileSystem getFileSystem(URI uri);

    public static FileSystem newFileSystem(URI uri, Map<String, ?> env) throws IOException;

    public static FileSystem newFileSystem(URI uri, Map<String, ?> env, ClassLoader loader) throws IOException;

    public static FileSystem newFileSystem(Path path, ClassLoader loader) throws IOException;
}
