package java.lang.management;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FilePermission;
import java.io.IOException;
import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerFactory;
import javax.management.MBeanServerPermission;
import javax.management.NotificationEmitter;
import javax.management.ObjectName;
import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.StandardEmitterMBean;
import javax.management.StandardMBean;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import java.util.stream.Stream;
import javax.management.JMX;
import sun.management.Util;
import sun.management.spi.PlatformMBeanProvider;
import sun.management.spi.PlatformMBeanProvider.PlatformComponent;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ManagementFactory {

    public final static String CLASS_LOADING_MXBEAN_NAME;

    public final static String COMPILATION_MXBEAN_NAME;

    public final static String MEMORY_MXBEAN_NAME;

    public final static String OPERATING_SYSTEM_MXBEAN_NAME;

    public final static String RUNTIME_MXBEAN_NAME;

    public final static String THREAD_MXBEAN_NAME;

    public final static String GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE;

    public final static String MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE;

    public final static String MEMORY_POOL_MXBEAN_DOMAIN_TYPE;

    public static ClassLoadingMXBean getClassLoadingMXBean();

    public static MemoryMXBean getMemoryMXBean();

    public static ThreadMXBean getThreadMXBean();

    public static RuntimeMXBean getRuntimeMXBean();

    public static CompilationMXBean getCompilationMXBean();

    public static OperatingSystemMXBean getOperatingSystemMXBean();

    public static List<MemoryPoolMXBean> getMemoryPoolMXBeans();

    public static List<MemoryManagerMXBean> getMemoryManagerMXBeans();

    public static List<GarbageCollectorMXBean> getGarbageCollectorMXBeans();

    public static synchronized MBeanServer getPlatformMBeanServer();

    public static <T> T newPlatformMXBeanProxy(MBeanServerConnection connection, String mxbeanName, Class<T> mxbeanInterface) throws java.io.IOException;

    public static <T extends PlatformManagedObject> T getPlatformMXBean(Class<T> mxbeanInterface);

    public static <T extends PlatformManagedObject> List<T> getPlatformMXBeans(Class<T> mxbeanInterface);

    public static <T extends PlatformManagedObject> T getPlatformMXBean(MBeanServerConnection connection, Class<T> mxbeanInterface) throws java.io.IOException;

    public static <T extends PlatformManagedObject> List<T> getPlatformMXBeans(MBeanServerConnection connection, Class<T> mxbeanInterface) throws java.io.IOException;

    public static Set<Class<? extends PlatformManagedObject>> getPlatformManagementInterfaces();
}
