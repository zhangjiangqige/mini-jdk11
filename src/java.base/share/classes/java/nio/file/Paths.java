package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.spi.FileSystemProvider;
import java.net.URI;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Paths {

    private Paths() {
    }

    public static Path get(String first, String... more);

    public static Path get(URI uri);
}
