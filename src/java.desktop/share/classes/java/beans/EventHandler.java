package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class EventHandler implements InvocationHandler {

    @ConstructorProperties({ "target", "action", "eventPropertyName", "listenerMethodName" })
    public EventHandler(Object target, String action, String eventPropertyName, String listenerMethodName) {
    }

    public Object getTarget();

    public String getAction();

    public String getEventPropertyName();

    public String getListenerMethodName();

    public Object invoke(final Object proxy, final Method method, final Object[] arguments);

    public static <T> T create(Class<T> listenerInterface, Object target, String action);

    public static <T> T create(Class<T> listenerInterface, Object target, String action, String eventPropertyName);

    public static <T> T create(Class<T> listenerInterface, Object target, String action, String eventPropertyName, String listenerMethodName);
}
