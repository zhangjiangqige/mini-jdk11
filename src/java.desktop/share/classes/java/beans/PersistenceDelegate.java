package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class PersistenceDelegate {

    public void writeObject(Object oldInstance, Encoder out);

    protected boolean mutatesTo(Object oldInstance, Object newInstance);

    protected abstract Expression instantiate(Object oldInstance, Encoder out);

    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out);
}
