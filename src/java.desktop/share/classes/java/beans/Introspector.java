package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.beans.TypeResolver;
import com.sun.beans.WeakCache;
import com.sun.beans.finder.ClassFinder;
import com.sun.beans.introspect.ClassInfo;
import com.sun.beans.introspect.EventSetInfo;
import com.sun.beans.introspect.PropertyInfo;
import java.awt.Component;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.EventObject;
import java.util.List;
import java.util.TreeMap;
import jdk.internal.misc.JavaBeansAccess;
import jdk.internal.misc.SharedSecrets;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Introspector {

    public static final int USE_ALL_BEANINFO = 1;

    public static final int IGNORE_IMMEDIATE_BEANINFO = 2;

    public static final int IGNORE_ALL_BEANINFO = 3;

    private static final WeakCache<Class<?>, Method[]> declaredMethodCache = new WeakCache<>();

    private Class<?> beanClass;

    private BeanInfo explicitBeanInfo;

    private BeanInfo superBeanInfo;

    private BeanInfo[] additionalBeanInfo;

    private boolean propertyChangeSource = false;

    private String defaultEventName;

    private String defaultPropertyName;

    private int defaultEventIndex = -1;

    private int defaultPropertyIndex = -1;

    private Map<String, MethodDescriptor> methods;

    private Map<String, PropertyDescriptor> properties;

    private Map<String, EventSetDescriptor> events;

    private static final EventSetDescriptor[] EMPTY_EVENTSETDESCRIPTORS = new EventSetDescriptor[0];

    static final String ADD_PREFIX = "add";

    static final String REMOVE_PREFIX = "remove";

    static final String GET_PREFIX = "get";

    static final String SET_PREFIX = "set";

    static final String IS_PREFIX = "is";

    static {
        SharedSecrets.setJavaBeansAccess(new JavaBeansAccess() {

            @Override
            public Method getReadMethod(Class<?> clazz, String property) throws Exception {
                BeanInfo bi = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] pds = bi.getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(property)) {
                        return pd.getReadMethod();
                    }
                }
                return null;
            }

            @Override
            public String[] getConstructorPropertiesValue(Constructor<?> ctr) {
                ConstructorProperties cp = ctr.getAnnotation(ConstructorProperties.class);
                String[] ret = cp != null ? cp.value() : null;
                return ret;
            }
        });
    }

    public static BeanInfo getBeanInfo(Class<?> beanClass) throws IntrospectionException;

    public static BeanInfo getBeanInfo(Class<?> beanClass, int flags) throws IntrospectionException;

    public static BeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass) throws IntrospectionException;

    public static BeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass, int flags) throws IntrospectionException;

    public static String decapitalize(String name);

    public static String[] getBeanInfoSearchPath();

    public static void setBeanInfoSearchPath(String[] path);

    public static void flushCaches();

    public static void flushFromCaches(Class<?> clz);

    private Introspector(Class<?> beanClass, Class<?> stopClass, int flags) throws IntrospectionException {
        this.beanClass = beanClass;
        if (stopClass != null) {
            boolean isSuper = false;
            for (Class<?> c = beanClass.getSuperclass(); c != null; c = c.getSuperclass()) {
                if (c == stopClass) {
                    isSuper = true;
                }
            }
            if (!isSuper) {
                throw new IntrospectionException(stopClass.getName() + " not superclass of " + beanClass.getName());
            }
        }
        if (flags == USE_ALL_BEANINFO) {
            explicitBeanInfo = findExplicitBeanInfo(beanClass);
        }
        Class<?> superClass = beanClass.getSuperclass();
        if (superClass != stopClass) {
            int newFlags = flags;
            if (newFlags == IGNORE_IMMEDIATE_BEANINFO) {
                newFlags = USE_ALL_BEANINFO;
            }
            superBeanInfo = getBeanInfo(superClass, stopClass, newFlags);
        }
        if (explicitBeanInfo != null) {
            additionalBeanInfo = explicitBeanInfo.getAdditionalBeanInfo();
        }
        if (additionalBeanInfo == null) {
            additionalBeanInfo = new BeanInfo[0];
        }
    }

    private BeanInfo getBeanInfo() throws IntrospectionException;

    private static BeanInfo findExplicitBeanInfo(Class<?> beanClass);

    private PropertyDescriptor[] getTargetPropertyInfo();

    private HashMap<String, List<PropertyDescriptor>> pdStore = new HashMap<>();

    private void addPropertyDescriptor(PropertyDescriptor pd);

    private void addPropertyDescriptors(PropertyDescriptor[] descriptors);

    private PropertyDescriptor[] getPropertyDescriptors(BeanInfo info);

    private void processPropertyDescriptors();

    private static boolean isAssignable(Class<?> current, Class<?> candidate);

    private PropertyDescriptor mergePropertyWithIndexedProperty(PropertyDescriptor pd, IndexedPropertyDescriptor ipd);

    private PropertyDescriptor mergePropertyDescriptor(IndexedPropertyDescriptor ipd, PropertyDescriptor pd);

    private PropertyDescriptor mergePropertyDescriptor(PropertyDescriptor pd1, PropertyDescriptor pd2);

    private IndexedPropertyDescriptor mergePropertyDescriptor(IndexedPropertyDescriptor ipd1, IndexedPropertyDescriptor ipd2);

    private EventSetDescriptor[] getTargetEventInfo() throws IntrospectionException;

    private void addEvent(EventSetDescriptor esd);

    private MethodDescriptor[] getTargetMethodInfo();

    private void addMethod(MethodDescriptor md);

    private static String makeQualifiedMethodName(String name, String[] params);

    private int getTargetDefaultEventIndex();

    private int getTargetDefaultPropertyIndex();

    private BeanDescriptor getTargetBeanDescriptor();

    private static Class<?> findCustomizerClass(Class<?> type);

    private boolean isEventHandler(Method m);

    private static Method internalFindMethod(Class<?> start, String methodName, int argCount, Class<?>[] args);

    static Method findMethod(Class<?> cls, String methodName, int argCount);

    static Method findMethod(Class<?> cls, String methodName, int argCount, Class<?>[] args);

    static boolean isSubclass(Class<?> a, Class<?> b);

    @SuppressWarnings("deprecation")
    static Object instantiate(Class<?> sibling, String className) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException;
}

class GenericBeanInfo extends SimpleBeanInfo {

    private BeanDescriptor beanDescriptor;

    private EventSetDescriptor[] events;

    private int defaultEvent;

    private PropertyDescriptor[] properties;

    private int defaultProperty;

    private MethodDescriptor[] methods;

    private Reference<BeanInfo> targetBeanInfoRef;

    public GenericBeanInfo(BeanDescriptor beanDescriptor, EventSetDescriptor[] events, int defaultEvent, PropertyDescriptor[] properties, int defaultProperty, MethodDescriptor[] methods, BeanInfo targetBeanInfo) {
        this.beanDescriptor = beanDescriptor;
        this.events = events;
        this.defaultEvent = defaultEvent;
        this.properties = properties;
        this.defaultProperty = defaultProperty;
        this.methods = methods;
        this.targetBeanInfoRef = (targetBeanInfo != null) ? new SoftReference<>(targetBeanInfo) : null;
    }

    GenericBeanInfo(GenericBeanInfo old) {
        beanDescriptor = new BeanDescriptor(old.beanDescriptor);
        if (old.events != null) {
            int len = old.events.length;
            events = new EventSetDescriptor[len];
            for (int i = 0; i < len; i++) {
                events[i] = new EventSetDescriptor(old.events[i]);
            }
        }
        defaultEvent = old.defaultEvent;
        if (old.properties != null) {
            int len = old.properties.length;
            properties = new PropertyDescriptor[len];
            for (int i = 0; i < len; i++) {
                PropertyDescriptor oldp = old.properties[i];
                if (oldp instanceof IndexedPropertyDescriptor) {
                    properties[i] = new IndexedPropertyDescriptor((IndexedPropertyDescriptor) oldp);
                } else {
                    properties[i] = new PropertyDescriptor(oldp);
                }
            }
        }
        defaultProperty = old.defaultProperty;
        if (old.methods != null) {
            int len = old.methods.length;
            methods = new MethodDescriptor[len];
            for (int i = 0; i < len; i++) {
                methods[i] = new MethodDescriptor(old.methods[i]);
            }
        }
        this.targetBeanInfoRef = old.targetBeanInfoRef;
    }

    public PropertyDescriptor[] getPropertyDescriptors();

    public int getDefaultPropertyIndex();

    public EventSetDescriptor[] getEventSetDescriptors();

    public int getDefaultEventIndex();

    public MethodDescriptor[] getMethodDescriptors();

    public BeanDescriptor getBeanDescriptor();

    public java.awt.Image getIcon(int iconKind);

    private BeanInfo getTargetBeanInfo();
}
