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

    static final JMX proof = new JMX();

    private JMX() {
    }

    @Interned
    public static final String DEFAULT_VALUE_FIELD = "defaultValue";

    @Interned
    public static final String IMMUTABLE_INFO_FIELD = "immutableInfo";

    @Interned
    public static final String INTERFACE_CLASS_NAME_FIELD = "interfaceClassName";

    @Interned
    public static final String LEGAL_VALUES_FIELD = "legalValues";

    @Interned
    public static final String MAX_VALUE_FIELD = "maxValue";

    @Interned
    public static final String MIN_VALUE_FIELD = "minValue";

    @Interned
    public static final String MXBEAN_FIELD = "mxbean";

    @Interned
    public static final String OPEN_TYPE_FIELD = "openType";

    @Interned
    public static final String ORIGINAL_TYPE_FIELD = "originalType";

    public static <T> T newMBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass);

    public static <T> T newMBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass, boolean notificationEmitter);

    public static <T> T newMXBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass);

    public static <T> T newMXBeanProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass, boolean notificationEmitter);

    public static boolean isMXBeanInterface(Class<?> interfaceClass);

    private static <T> T createProxy(MBeanServerConnection connection, ObjectName objectName, Class<T> interfaceClass, boolean notificationEmitter, boolean isMXBean);
}
