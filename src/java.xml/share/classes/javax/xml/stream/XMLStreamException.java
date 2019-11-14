package javax.xml.stream;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public class XMLStreamException extends Exception {

    private static final long serialVersionUID = 2018819321811497362L;

    @Nullable
    protected Throwable nested;

    @Nullable
    protected Location location;

    @SideEffectFree
    public XMLStreamException() {
        super();
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg) {
        super(msg);
    }

    @SideEffectFree
    public XMLStreamException(@Nullable Throwable th) {
        super(th);
        nested = th;
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg, @Nullable Throwable th) {
        super(msg, th);
        nested = th;
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg, Location location, @Nullable Throwable th) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\n" + "Message: " + msg);
        nested = th;
        this.location = location;
    }

    @SideEffectFree
    public XMLStreamException(@Nullable String msg, Location location) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\n" + "Message: " + msg);
        this.location = location;
    }

    @Pure
    @Nullable
    public Throwable getNestedException();

    @Pure
    @Nullable
    public Location getLocation();
}
