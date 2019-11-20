package java.nio.file;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor("nullness")
public class FileSystemException extends IOException {

    public FileSystemException(@Nullable String file) {
    }

    public FileSystemException(@Nullable String file, @Nullable String other, @Nullable String reason) {
    }

    @Nullable
    public String getFile();

    @Nullable
    public String getOtherFile();

    @Nullable
    public String getReason();

    @Override
    @Nullable
    public String getMessage();
}
