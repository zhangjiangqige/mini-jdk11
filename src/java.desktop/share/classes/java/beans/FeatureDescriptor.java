package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.beans.TypeResolver;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map.Entry;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public class FeatureDescriptor {

    private static final String TRANSIENT = "transient";

    @Nullable
    private Reference<? extends Class<?>> classRef;

    public FeatureDescriptor() {
    }

    public String getName();

    public void setName(String name);

    public String getDisplayName();

    public void setDisplayName(String displayName);

    public boolean isExpert();

    public void setExpert(boolean expert);

    public boolean isHidden();

    public void setHidden(boolean hidden);

    public boolean isPreferred();

    public void setPreferred(boolean preferred);

    public String getShortDescription();

    public void setShortDescription(String text);

    public void setValue(String attributeName, Object value);

    @Nullable
    public Object getValue(String attributeName);

    public Enumeration<String> attributeNames();

    FeatureDescriptor(FeatureDescriptor x, FeatureDescriptor y) {
        expert = x.expert | y.expert;
        hidden = x.hidden | y.hidden;
        preferred = x.preferred | y.preferred;
        name = y.name;
        shortDescription = x.shortDescription;
        if (y.shortDescription != null) {
            shortDescription = y.shortDescription;
        }
        displayName = x.displayName;
        if (y.displayName != null) {
            displayName = y.displayName;
        }
        classRef = x.classRef;
        if (y.classRef != null) {
            classRef = y.classRef;
        }
        addTable(x.table);
        addTable(y.table);
    }

    FeatureDescriptor(FeatureDescriptor old) {
        expert = old.expert;
        hidden = old.hidden;
        preferred = old.preferred;
        name = old.name;
        shortDescription = old.shortDescription;
        displayName = old.displayName;
        classRef = old.classRef;
        addTable(old.table);
    }

    private void addTable(Hashtable<String, Object> table);

    @EnsuresNonNull({ "this.table" })
    private Hashtable<String, Object> getTable();

    void setTransient(@Nullable Transient annotation);

    boolean isTransient();

    void setClass0(Class<?> cls);

    @Nullable
    Class<?> getClass0();

    @Nullable
    static <T> Reference<T> getSoftReference(@Nullable T object);

    @Nullable
    static <T> Reference<T> getWeakReference(@Nullable T object);

    static Class<?> getReturnType(@Nullable Class<?> base, Method method);

    static Class<?>[] getParameterTypes(@Nullable Class<?> base, Method method);

    private boolean expert;

    private boolean hidden;

    private boolean preferred;

    @Nullable
    private String shortDescription;

    private String name;

    @Nullable
    private String displayName;

    @Nullable
    private Hashtable<String, Object> table;

    public String toString();

    void appendTo(StringBuilder sb);

    static void appendTo(StringBuilder sb, String name, @Nullable Reference<?> reference);

    static void appendTo(StringBuilder sb, String name, @Nullable Object value);

    static void appendTo(StringBuilder sb, String name, boolean value);
}
