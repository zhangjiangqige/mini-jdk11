package java.io;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class IOException extends Exception {

    public IOException() {
    }

    public IOException(@Nullable String message) {
    }

    public IOException(@Nullable String message, @Nullable Throwable cause) {
    }

    public IOException(@Nullable Throwable cause) {
    }
}
