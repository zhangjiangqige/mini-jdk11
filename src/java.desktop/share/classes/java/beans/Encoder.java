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

    private final PersistenceDelegateFinder finder = new PersistenceDelegateFinder();

    private Map<Object, Expression> bindings = new IdentityHashMap<>();

    private ExceptionListener exceptionListener;

    boolean executeStatements = true;

    private Map<Object, Object> attributes;

    protected void writeObject(Object o);

    public void setExceptionListener(ExceptionListener exceptionListener);

    public ExceptionListener getExceptionListener();

    Object getValue(Expression exp);

    public PersistenceDelegate getPersistenceDelegate(Class<?> type);

    public void setPersistenceDelegate(Class<?> type, PersistenceDelegate delegate);

    public Object remove(Object oldInstance);

    public Object get(Object oldInstance);

    private Object writeObject1(Object oldInstance);

    private Statement cloneStatement(Statement oldExp);

    public void writeStatement(Statement oldStm);

    public void writeExpression(Expression oldExp);

    void clear();

    void setAttribute(Object key, Object value);

    Object getAttribute(Object key);
}
