package java.io;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public abstract class ObjectStreamException extends IOException {

    protected ObjectStreamException(@Nullable String message) {
    }

    protected ObjectStreamException() {
    }
}
