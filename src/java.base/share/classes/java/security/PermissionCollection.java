package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class PermissionCollection implements java.io.Serializable {

    private static final long serialVersionUID = -6727011328946861783L;

    private volatile boolean readOnly;

    public abstract void add(Permission permission);

    public abstract boolean implies(Permission permission);

    public abstract Enumeration<Permission> elements();

    public Stream<Permission> elementsAsStream();

    public void setReadOnly();

    public boolean isReadOnly();

    public String toString();
}
