package java.nio.file.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.Path;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FileTypeDetector {

    private static Void checkPermission();

    private FileTypeDetector(Void ignore) {
    }

    protected FileTypeDetector() {
        this(checkPermission());
    }

    public abstract String probeContentType(Path path) throws IOException;
}
