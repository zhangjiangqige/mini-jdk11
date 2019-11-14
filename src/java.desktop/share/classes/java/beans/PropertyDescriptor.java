package java.beans;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.Reference;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.Map.Entry;
import com.sun.beans.introspect.PropertyInfo;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "nullness" })
public class PropertyDescriptor extends FeatureDescriptor {

    @Nullable
    private Reference<? extends Class<?>> propertyTypeRef;

    private final MethodRef readMethodRef = new MethodRef();

    private final MethodRef writeMethodRef = new MethodRef();

    @Nullable
    private Reference<? extends Class<?>> propertyEditorClassRef;

    private boolean bound;

    private boolean constrained;

    @Nullable
    private String baseName;

    @Nullable
    private String writeMethodName;

    @Nullable
    private String readMethodName;

    @SideEffectFree
    public PropertyDescriptor(String propertyName, Class<?> beanClass) throws IntrospectionException {
        this(propertyName, beanClass, Introspector.IS_PREFIX + NameGenerator.capitalize(propertyName), Introspector.SET_PREFIX + NameGenerator.capitalize(propertyName));
    }

    @SideEffectFree
    public PropertyDescriptor(String propertyName, Class<?> beanClass, @Nullable String readMethodName, @Nullable String writeMethodName) throws IntrospectionException {
        if (beanClass == null) {
            throw new IntrospectionException("Target Bean class is null");
        }
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException("bad property name");
        }
        if ("".equals(readMethodName) || "".equals(writeMethodName)) {
            throw new IntrospectionException("read or write method name should not be the empty string");
        }
        setName(propertyName);
        setClass0(beanClass);
        this.readMethodName = readMethodName;
        if (readMethodName != null && getReadMethod() == null) {
            throw new IntrospectionException("Method not found: " + readMethodName);
        }
        this.writeMethodName = writeMethodName;
        if (writeMethodName != null && getWriteMethod() == null) {
            throw new IntrospectionException("Method not found: " + writeMethodName);
        }
        Class<?>[] args = { PropertyChangeListener.class };
        this.bound = null != Introspector.findMethod(beanClass, "addPropertyChangeListener", args.length, args);
    }

    @SideEffectFree
    public PropertyDescriptor(String propertyName, @Nullable Method readMethod, @Nullable Method writeMethod) throws IntrospectionException {
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException("bad property name");
        }
        setName(propertyName);
        setReadMethod(readMethod);
        setWriteMethod(writeMethod);
    }

    PropertyDescriptor(Entry<String, PropertyInfo> entry, boolean bound) {
        String base = entry.getKey();
        PropertyInfo info = entry.getValue();
        setName(Introspector.decapitalize(base));
        setReadMethod0(info.getReadMethod());
        setWriteMethod0(info.getWriteMethod());
        setPropertyType(info.getPropertyType());
        setConstrained(info.isConstrained());
        setBound(bound && info.is(PropertyInfo.Name.bound));
        boolean isExpert = info.is(PropertyInfo.Name.expert);
        setValue(PropertyInfo.Name.expert.name(), isExpert);
        setExpert(isExpert);
        boolean isHidden = info.is(PropertyInfo.Name.hidden);
        setValue(PropertyInfo.Name.hidden.name(), isHidden);
        setHidden(isHidden);
        setPreferred(info.is(PropertyInfo.Name.preferred));
        boolean isRequired = info.is(PropertyInfo.Name.required);
        setValue(PropertyInfo.Name.required.name(), isRequired);
        boolean visual = info.is(PropertyInfo.Name.visualUpdate);
        setValue(PropertyInfo.Name.visualUpdate.name(), visual);
        Object description = info.get(PropertyInfo.Name.description);
        if (description != null) {
            setShortDescription(description.toString());
        }
        Object values = info.get(PropertyInfo.Name.enumerationValues);
        if (values == null) {
            values = new Object[0];
        }
        setValue(PropertyInfo.Name.enumerationValues.name(), values);
        this.baseName = base;
    }

    @Pure
    @Nullable
    public synchronized Class<?> getPropertyType();

    private void setPropertyType(@Nullable Class<?> type);

    @Nullable
    private Class<?> getPropertyType0();

    @Pure
    @Nullable
    public synchronized Method getReadMethod();

    public synchronized void setReadMethod(@Nullable Method readMethod) throws IntrospectionException;

    private void setReadMethod0(Method readMethod);

    @Pure
    @Nullable
    public synchronized Method getWriteMethod();

    public synchronized void setWriteMethod(@Nullable Method writeMethod) throws IntrospectionException;

    private void setWriteMethod0(Method writeMethod);

    void setClass0(Class<?> clz);

    @Pure
    public boolean isBound();

    public void setBound(boolean bound);

    @Pure
    public boolean isConstrained();

    public void setConstrained(boolean constrained);

    public void setPropertyEditorClass(@Nullable Class<?> propertyEditorClass);

    @Pure
    @Nullable
    public Class<?> getPropertyEditorClass();

    @SideEffectFree
    @SuppressWarnings("deprecation")
    @Nullable
    public PropertyEditor createPropertyEditor(@Nullable Object bean);

    @Pure
    public boolean equals(@Nullable Object obj);

    boolean compareMethods(@Nullable Method a, @Nullable Method b);

    PropertyDescriptor(PropertyDescriptor x, PropertyDescriptor y) {
        super(x, y);
        if (y.baseName != null) {
            baseName = y.baseName;
        } else {
            baseName = x.baseName;
        }
        if (y.readMethodName != null) {
            readMethodName = y.readMethodName;
        } else {
            readMethodName = x.readMethodName;
        }
        if (y.writeMethodName != null) {
            writeMethodName = y.writeMethodName;
        } else {
            writeMethodName = x.writeMethodName;
        }
        if (y.propertyTypeRef != null) {
            propertyTypeRef = y.propertyTypeRef;
        } else {
            propertyTypeRef = x.propertyTypeRef;
        }
        Method xr = x.getReadMethod();
        Method yr = y.getReadMethod();
        try {
            if (isAssignable(xr, yr)) {
                setReadMethod(yr);
            } else {
                setReadMethod(xr);
            }
        } catch (IntrospectionException ex) {
        }
        if (xr != null && yr != null && xr.getDeclaringClass() == yr.getDeclaringClass() && getReturnType(getClass0(), xr) == boolean.class && getReturnType(getClass0(), yr) == boolean.class && xr.getName().indexOf(Introspector.IS_PREFIX) == 0 && yr.getName().indexOf(Introspector.GET_PREFIX) == 0) {
            try {
                setReadMethod(xr);
            } catch (IntrospectionException ex) {
            }
        }
        Method xw = x.getWriteMethod();
        Method yw = y.getWriteMethod();
        try {
            if (yw != null) {
                setWriteMethod(yw);
            } else {
                setWriteMethod(xw);
            }
        } catch (IntrospectionException ex) {
        }
        if (y.getPropertyEditorClass() != null) {
            setPropertyEditorClass(y.getPropertyEditorClass());
        } else {
            setPropertyEditorClass(x.getPropertyEditorClass());
        }
        bound = x.bound | y.bound;
        constrained = x.constrained | y.constrained;
    }

    PropertyDescriptor(PropertyDescriptor old) {
        super(old);
        propertyTypeRef = old.propertyTypeRef;
        this.readMethodRef.set(old.readMethodRef.get());
        this.writeMethodRef.set(old.writeMethodRef.get());
        propertyEditorClassRef = old.propertyEditorClassRef;
        writeMethodName = old.writeMethodName;
        readMethodName = old.readMethodName;
        baseName = old.baseName;
        bound = old.bound;
        constrained = old.constrained;
    }

    void updateGenericsFor(Class<?> type);

    @Nullable
    private Class<?> findPropertyType(@Nullable Method readMethod, @Nullable Method writeMethod) throws IntrospectionException;

    @Pure
    public int hashCode();

    String getBaseName();

    void appendTo(StringBuilder sb);

    boolean isAssignable(@Nullable Method m1, @Nullable Method m2);
}
