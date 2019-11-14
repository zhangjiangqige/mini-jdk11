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

    private transient Class<?> declaringClassObject;

    private String classLoaderName;

    private String moduleName;

    private String moduleVersion;

    private String declaringClass;

    private String methodName;

    private String fileName;

    private int lineNumber;

    private byte format = 0;

    public StackTraceElement(@FullyQualifiedName String declaringClass, String methodName, @Nullable String fileName, int lineNumber) {
        this(null, null, null, declaringClass, methodName, fileName, lineNumber);
    }

    public StackTraceElement(String classLoaderName, String moduleName, String moduleVersion, String declaringClass, String methodName, String fileName, int lineNumber) {
        this.classLoaderName = classLoaderName;
        this.moduleName = moduleName;
        this.moduleVersion = moduleVersion;
        this.declaringClass = Objects.requireNonNull(declaringClass, "Declaring class is null");
        this.methodName = Objects.requireNonNull(methodName, "Method name is null");
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    private StackTraceElement() {
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

    private synchronized void computeFormat();

    private static final byte BUILTIN_CLASS_LOADER = 0x1;

    private static final byte JDK_NON_UPGRADEABLE_MODULE = 0x2;

    private boolean dropClassLoaderName();

    private boolean dropModuleVersion();

    private static boolean isHashedInJavaBase(Module m);

    private static class HashedModules {

        static Set<String> HASHED_MODULES = hashedModules();

        static Set<String> hashedModules();

        static boolean contains(Module m);
    }

    static StackTraceElement[] of(Throwable x, int depth);

    static StackTraceElement of(StackFrameInfo sfi);

    private static native void initStackTraceElements(StackTraceElement[] elements, Throwable x);

    private static native void initStackTraceElement(StackTraceElement element, StackFrameInfo sfi);

    private static final long serialVersionUID = 6992337162326171013L;
}
