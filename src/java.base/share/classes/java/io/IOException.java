package java.io;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IOException extends Exception {

    static final long serialVersionUID = 7818375828146090155L;

    public IOException() {
        super();
    }

    public IOException(@Nullable String message) {
        super(message);
    }

    public IOException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    public IOException(@Nullable Throwable cause) {
        super(cause);
    }
}
