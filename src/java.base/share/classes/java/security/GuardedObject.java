package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GuardedObject implements java.io.Serializable {

    public GuardedObject(Object object, Guard guard) {
    }

    public Object getObject() throws SecurityException;
}
