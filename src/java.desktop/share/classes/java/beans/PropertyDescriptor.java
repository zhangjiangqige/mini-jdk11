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

    @SideEffectFree
    public PropertyDescriptor(String propertyName, Class<?> beanClass) throws IntrospectionException {
    }

    @SideEffectFree
    public PropertyDescriptor(String propertyName, Class<?> beanClass, @Nullable String readMethodName, @Nullable String writeMethodName) throws IntrospectionException {
    }

    @SideEffectFree
    public PropertyDescriptor(String propertyName, @Nullable Method readMethod, @Nullable Method writeMethod) throws IntrospectionException {
    }

    @Pure
    @Nullable
    public synchronized Class<?> getPropertyType();

    @Pure
    @Nullable
    public synchronized Method getReadMethod();

    public synchronized void setReadMethod(@Nullable Method readMethod) throws IntrospectionException;

    @Pure
    @Nullable
    public synchronized Method getWriteMethod();

    public synchronized void setWriteMethod(@Nullable Method writeMethod) throws IntrospectionException;

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

    @Pure
    public int hashCode();
}
