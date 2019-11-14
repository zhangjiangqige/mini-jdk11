package java.io;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public abstract class ObjectStreamException extends IOException {

    private static final long serialVersionUID = 7260898174833392607L;

    protected ObjectStreamException(@Nullable String message) {
        super(message);
    }

    protected ObjectStreamException() {
        super();
    }
}
