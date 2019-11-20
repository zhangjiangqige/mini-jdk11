package java.lang;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.loader.BuiltinClassLoader;
import jdk.internal.misc.VM;
import jdk.internal.module.ModuleHashes;
import jdk.internal.module.ModuleReferenceImpl;
import java.lang.module.ModuleDescriptor.Version;
import java.lang.module.ModuleReference;
import java.lang.module.ResolvedModule;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@AnnotatedFor({ "lock", "nullness", "signature" })
public final class StackTraceElement implements java.io.Serializable {

    public StackTraceElement(@FullyQualifiedName String declaringClass, String methodName, @Nullable String fileName, int lineNumber) {
    }

    public StackTraceElement(String classLoaderName, String moduleName, String moduleVersion, String declaringClass, String methodName, String fileName, int lineNumber) {
    }

    @Nullable
    public String getFileName(@GuardSatisfied StackTraceElement this);

    public int getLineNumber(@GuardSatisfied StackTraceElement this);

    public String getModuleName();

    public String getModuleVersion();

    public String getClassLoaderName();

    @FullyQualifiedName
    public String getClassName(@GuardSatisfied StackTraceElement this);

    public String getMethodName(@GuardSatisfied StackTraceElement this);

    @Pure
    public boolean isNativeMethod(@GuardSatisfied StackTraceElement this);

    @SideEffectFree
    public String toString(@GuardSatisfied StackTraceElement this);

    @Pure
    public boolean equals(@GuardSatisfied StackTraceElement this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int hashCode(@GuardSatisfied StackTraceElement this);
}
