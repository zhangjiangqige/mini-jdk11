package java.nio.file;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor("nullness")
public class FileSystemException extends IOException {

    static final long serialVersionUID = -3055425747967319812L;

    @Nullable
    private final String file;

    @Nullable
    private final String other;

    public FileSystemException(@Nullable String file) {
        super((String) null);
        this.file = file;
        this.other = null;
    }

    public FileSystemException(@Nullable String file, @Nullable String other, @Nullable String reason) {
        super(reason);
        this.file = file;
        this.other = other;
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
