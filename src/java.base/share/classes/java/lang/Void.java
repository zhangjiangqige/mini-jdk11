package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public final class Void {

    @SuppressWarnings("unchecked")
    public static final Class<Void> TYPE;
}
