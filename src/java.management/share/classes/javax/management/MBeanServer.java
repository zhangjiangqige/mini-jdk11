package javax.management;

import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.Set;
import java.io.ObjectInputStream;
import javax.management.loading.ClassLoaderRepository;

@AnnotatedFor("nullness")
public interface MBeanServer extends MBeanServerConnection {

    public ObjectInstance createMBean(String className, @Nullable ObjectName name) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException;

    public ObjectInstance createMBean(String className, @Nullable ObjectName name, ObjectName loaderName) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException, InstanceNotFoundException;

    public ObjectInstance createMBean(String className, @Nullable ObjectName name, Object @Nullable [] params, String @Nullable [] signature) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException;

    public ObjectInstance createMBean(String className, @Nullable ObjectName name, ObjectName loaderName, Object @Nullable [] params, String @Nullable [] signature) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException, InstanceNotFoundException;

    public ObjectInstance registerMBean(Object object, @Nullable ObjectName name) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException;

    public void unregisterMBean(@Nullable ObjectName name) throws InstanceNotFoundException, MBeanRegistrationException;

    public ObjectInstance getObjectInstance(@Nullable ObjectName name) throws InstanceNotFoundException;

    public Set<ObjectInstance> queryMBeans(@Nullable ObjectName name, @Nullable QueryExp query);

    public Set<ObjectName> queryNames(@Nullable ObjectName name, @Nullable QueryExp query);

    public boolean isRegistered(ObjectName name);

    public Integer getMBeanCount();

    public Object getAttribute(ObjectName name, String attribute) throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException;

    public AttributeList getAttributes(ObjectName name, String[] attributes) throws InstanceNotFoundException, ReflectionException;

    public void setAttribute(ObjectName name, Attribute attribute) throws InstanceNotFoundException, AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException;

    public AttributeList setAttributes(ObjectName name, AttributeList attributes) throws InstanceNotFoundException, ReflectionException;

    public Object invoke(@Nullable ObjectName name, String operationName, Object @Nullable [] params, String @Nullable [] signature) throws InstanceNotFoundException, MBeanException, ReflectionException;

    public String getDefaultDomain();

    public String[] getDomains();

    public void addNotificationListener(@Nullable ObjectName name, NotificationListener listener, @Nullable NotificationFilter filter, Object handback) throws InstanceNotFoundException;

    public void addNotificationListener(@Nullable ObjectName name, ObjectName listener, @Nullable NotificationFilter filter, Object handback) throws InstanceNotFoundException;

    public void removeNotificationListener(@Nullable ObjectName name, ObjectName listener) throws InstanceNotFoundException, ListenerNotFoundException;

    public void removeNotificationListener(@Nullable ObjectName name, ObjectName listener, @Nullable NotificationFilter filter, @Nullable Object handback) throws InstanceNotFoundException, ListenerNotFoundException;

    public void removeNotificationListener(@Nullable ObjectName name, @Nullable NotificationListener listener) throws InstanceNotFoundException, ListenerNotFoundException;

    public void removeNotificationListener(@Nullable ObjectName name, NotificationListener listener, @Nullable NotificationFilter filter, @Nullable Object handback) throws InstanceNotFoundException, ListenerNotFoundException;

    public MBeanInfo getMBeanInfo(@Nullable ObjectName name) throws InstanceNotFoundException, IntrospectionException, ReflectionException;

    public boolean isInstanceOf(@Nullable ObjectName name, String className) throws InstanceNotFoundException;

    public Object instantiate(String className) throws ReflectionException, MBeanException;

    public Object instantiate(String className, @Nullable ObjectName loaderName) throws ReflectionException, MBeanException, InstanceNotFoundException;

    public Object instantiate(String className, Object @Nullable [] params, String @Nullable [] signature) throws ReflectionException, MBeanException;

    public Object instantiate(String className, @Nullable ObjectName loaderName, Object @Nullable [] params, String @Nullable [] signature) throws ReflectionException, MBeanException, InstanceNotFoundException;

    @Deprecated()
    default public ObjectInputStream deserialize(@Nullable ObjectName name, byte[] data) throws InstanceNotFoundException, OperationsException;

    @Deprecated()
    default public ObjectInputStream deserialize(String className, byte[] data) throws OperationsException, ReflectionException;

    @Deprecated()
    default public ObjectInputStream deserialize(String className, @Nullable ObjectName loaderName, byte[] data) throws InstanceNotFoundException, OperationsException, ReflectionException;

    public ClassLoader getClassLoaderFor(ObjectName mbeanName) throws InstanceNotFoundException;

    public ClassLoader getClassLoader(@Nullable ObjectName loaderName) throws InstanceNotFoundException;

    public ClassLoaderRepository getClassLoaderRepository();
}
