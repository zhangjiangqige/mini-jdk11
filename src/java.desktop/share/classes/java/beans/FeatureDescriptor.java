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

    public String toString();
}
