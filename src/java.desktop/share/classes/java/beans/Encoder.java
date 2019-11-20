package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.beans.finder.PersistenceDelegateFinder;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Encoder {

    protected void writeObject(Object o);

    public void setExceptionListener(ExceptionListener exceptionListener);

    public ExceptionListener getExceptionListener();

    public PersistenceDelegate getPersistenceDelegate(Class<?> type);

    public void setPersistenceDelegate(Class<?> type, PersistenceDelegate delegate);

    public Object remove(Object oldInstance);

    public Object get(Object oldInstance);

    public void writeStatement(Statement oldStm);

    public void writeExpression(Expression oldExp);
}
