package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GuardedObject implements java.io.Serializable {

    private static final long serialVersionUID = -5240450096227834308L;

    private Object object;

    private Guard guard;

    public GuardedObject(Object object, Guard guard) {
        this.guard = guard;
        this.object = object;
    }

    public Object getObject() throws SecurityException;

    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException;
}
