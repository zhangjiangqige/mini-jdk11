package javax.xml.stream;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public class XMLStreamException extends Exception {

    @Nullable
    protected Throwable nested;

    @Nullable
    protected Location location;

    @SideEffectFree
    public XMLStreamException() {
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg) {
    }

    @SideEffectFree
    public XMLStreamException(@Nullable Throwable th) {
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg, @Nullable Throwable th) {
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg, Location location, @Nullable Throwable th) {
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg, Location location) {
    }

    @Pure
    @Nullable
    public Throwable getNestedException();

    @Pure
    @Nullable
    public Location getLocation();
}
