package javax.management;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.jmx.mbeanserver.Introspector;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning" })
public class JMX {

    @Interned
    public static final String DEFAULT_VALUE_FIELD;

    @Interned
    public static final String IMMUTABLE_INFO_FIELD;

    @Interned
    public static final String INTERFACE_CLASS_NAME_FIELD;

    @Interned
    public static final String LEGAL_VALUES_FIELD;

    @Interned
    public static final String MAX_VALUE_FIELD;

    @Interned
    public static final String MIN_VALUE_FIELD;

    @Interned
    public static final String MXBEAN_FIELD;

    @Interned
    public static final String OPEN_TYPE_FIELD;

    @Interned
    public static final String ORIGINAL_TYPE_FIELD;

    public static <T> T newMBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass);

    public static <T> T newMBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass, boolean notificationEmitter);

    public static <T> T newMXBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass);

    public static <T> T newMXBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass, boolean notificationEmitter);

    public static boolean isMXBeanInterface(Class<?> interfaceClass);
}
