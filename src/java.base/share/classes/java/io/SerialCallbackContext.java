package java.io;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class SerialCallbackContext {

    private final Object obj;

    private final ObjectStreamClass desc;

    private Thread thread;

    public SerialCallbackContext(Object obj, ObjectStreamClass desc) {
        this.obj = obj;
        this.desc = desc;
        this.thread = Thread.currentThread();
    }

    public Object getObj() throws NotActiveException;

    public ObjectStreamClass getDesc();

    public void check() throws NotActiveException;

    public void checkAndSetUsed() throws NotActiveException;

    public void setUsed();
}
