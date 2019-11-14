package javax.naming.directory;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.naming.NamingException;
import javax.naming.NamingEnumeration;

@AnnotatedFor("nullness")
public interface Attributes extends Cloneable, java.io.Serializable {

    @Pure
    boolean isCaseIgnored();

    @Pure
    int size();

    @Nullable
    @Pure
    Attribute get(String attrID);

    @Pure
    NamingEnumeration<? extends Attribute> getAll();

    @Pure
    NamingEnumeration<String> getIDs();

    @Nullable
    Attribute put(String attrID, @Nullable Object val);

    @Nullable
    Attribute put(Attribute attr);

    @Nullable
    Attribute remove(String attrID);

    Object clone();
}
