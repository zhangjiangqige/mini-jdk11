package java.nio.file.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.Path;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FileTypeDetector {

    protected FileTypeDetector() {
    }

    public abstract String probeContentType(Path path) throws IOException;
}
