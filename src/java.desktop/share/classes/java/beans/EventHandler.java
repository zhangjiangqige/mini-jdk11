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

    private Object target;

    private String action;

    private final String eventPropertyName;

    private final String listenerMethodName;

    private final AccessControlContext acc = AccessController.getContext();

    @ConstructorProperties({ "target", "action", "eventPropertyName", "listenerMethodName" })
    public EventHandler(Object target, String action, String eventPropertyName, String listenerMethodName) {
        this.target = target;
        this.action = action;
        if (target == null) {
            throw new NullPointerException("target must be non-null");
        }
        if (action == null) {
            throw new NullPointerException("action must be non-null");
        }
        this.eventPropertyName = eventPropertyName;
        this.listenerMethodName = listenerMethodName;
    }

    public Object getTarget();

    public String getAction();

    public String getEventPropertyName();

    public String getListenerMethodName();

    private Object applyGetters(Object target, String getters);

    public Object invoke(final Object proxy, final Method method, final Object[] arguments);

    private Object invokeInternal(Object proxy, Method method, Object[] arguments);

    public static <T> T create(Class<T> listenerInterface, Object target, String action);

    public static <T> T create(Class<T> listenerInterface, Object target, String action, String eventPropertyName);

    public static <T> T create(Class<T> listenerInterface, Object target, String action, String eventPropertyName, String listenerMethodName);

    private static ClassLoader getClassLoader(Class<?> type);
}
