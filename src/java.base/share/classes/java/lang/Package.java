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
    @Deprecated(since = "9")
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

    private Class<?> getPackageInfo();

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

    Package(@DotSeparatedIdentifiers String name, String spectitle, String specversion, String specvendor, String impltitle, String implversion, String implvendor, URL sealbase, ClassLoader loader) {
        super(Objects.requireNonNull(name), loader != null ? loader.getUnnamedModule() : BootLoader.getUnnamedModule());
        this.versionInfo = VersionInfo.getInstance(spectitle, specversion, specvendor, impltitle, implversion, implvendor, sealbase);
    }

    Package(String name, Module module) {
        super(name, module);
        this.versionInfo = VersionInfo.NULL_VERSION_INFO;
    }

    static class VersionInfo {

        static final VersionInfo NULL_VERSION_INFO = new VersionInfo(null, null, null, null, null, null, null);

        private final String specTitle;

        private final String specVersion;

        private final String specVendor;

        private final String implTitle;

        private final String implVersion;

        private final String implVendor;

        private final URL sealBase;

        static VersionInfo getInstance(String spectitle, String specversion, String specvendor, String impltitle, String implversion, String implvendor, URL sealbase);

        private VersionInfo(String spectitle, String specversion, String specvendor, String impltitle, String implversion, String implvendor, URL sealbase) {
            this.implTitle = impltitle;
            this.implVersion = implversion;
            this.implVendor = implvendor;
            this.specTitle = spectitle;
            this.specVersion = specversion;
            this.specVendor = specvendor;
            this.sealBase = sealbase;
        }
    }

    private final VersionInfo versionInfo;

    private Class<?> packageInfo;
}
