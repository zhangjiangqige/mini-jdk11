package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiers;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Objects;
import jdk.internal.loader.BootLoader;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;

@AnnotatedFor({ "interning", "lock", "nullness", "signature" })
@UsesObjectEquals
public class Package extends NamedPackage implements java.lang.reflect.AnnotatedElement {

    @DotSeparatedIdentifiers
    public String getName();

    @Nullable
    public String getSpecificationTitle();

    @Nullable
    public String getSpecificationVersion();

    @Nullable
    public String getSpecificationVendor();

    @Nullable
    public String getImplementationTitle();

    @Nullable
    public String getImplementationVersion();

    @Nullable
    public String getImplementationVendor();

    @Pure
    public boolean isSealed(@GuardSatisfied Package this);

    @Pure
    public boolean isSealed(@GuardSatisfied Package this, @GuardSatisfied URL url);

    @Pure
    public boolean isCompatibleWith(@GuardSatisfied Package this, String desired) throws NumberFormatException;

    @Pure
    @CallerSensitive
    @Deprecated()
    @SuppressWarnings("deprecation")
    @Nullable
    public static Package getPackage(String name);

    @Pure
    @CallerSensitive
    public static Package[] getPackages();

    @Pure
    @Override
    public int hashCode(@GuardSatisfied Package this);

    @SideEffectFree
    @Override
    public String toString(@GuardSatisfied Package this);

    @Nullable
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass);

    @Pure
    @Override
    public boolean isAnnotationPresent(@GuardSatisfied Package this, @GuardSatisfied Class<? extends Annotation> annotationClass);

    @Override
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass);

    public Annotation[] getAnnotations();

    @Override
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass);

    @Override
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass);

    public Annotation[] getDeclaredAnnotations();
}
