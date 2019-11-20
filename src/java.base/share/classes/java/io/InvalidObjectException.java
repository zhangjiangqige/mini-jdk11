package java.io;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class InvalidObjectException extends ObjectStreamException {

    public InvalidObjectException(@Nullable String reason) {
    }
}
