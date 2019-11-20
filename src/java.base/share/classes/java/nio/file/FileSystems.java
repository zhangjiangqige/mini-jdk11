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

    public static FileSystem getDefault();

    public static FileSystem getFileSystem(URI uri);

    public static FileSystem newFileSystem(URI uri, Map<String, ?> env) throws IOException;

    public static FileSystem newFileSystem(URI uri, Map<String, ?> env, ClassLoader loader) throws IOException;

    public static FileSystem newFileSystem(Path path, ClassLoader loader) throws IOException;
}
