package java.lang.reflect;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.AccessController;
import java.util.StringJoiner;
import jdk.internal.reflect.LangReflectAccess;
import jdk.internal.reflect.ReflectionFactory;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Modifier {

    static {
        ReflectionFactory factory = AccessController.doPrivileged(new ReflectionFactory.GetReflectionFactoryAction());
        factory.setLangReflectAccess(new java.lang.reflect.ReflectAccess());
    }

    public static boolean isPublic(int mod);

    public static boolean isPrivate(int mod);

    public static boolean isProtected(int mod);

    public static boolean isStatic(int mod);

    public static boolean isFinal(int mod);

    public static boolean isSynchronized(int mod);

    public static boolean isVolatile(int mod);

    public static boolean isTransient(int mod);

    public static boolean isNative(int mod);

    public static boolean isInterface(int mod);

    public static boolean isAbstract(int mod);

    public static boolean isStrict(int mod);

    public static String toString(int mod);

    public static final int PUBLIC = 0x00000001;

    public static final int PRIVATE = 0x00000002;

    public static final int PROTECTED = 0x00000004;

    public static final int STATIC = 0x00000008;

    public static final int FINAL = 0x00000010;

    public static final int SYNCHRONIZED = 0x00000020;

    public static final int VOLATILE = 0x00000040;

    public static final int TRANSIENT = 0x00000080;

    public static final int NATIVE = 0x00000100;

    public static final int INTERFACE = 0x00000200;

    public static final int ABSTRACT = 0x00000400;

    public static final int STRICT = 0x00000800;

    static final int BRIDGE = 0x00000040;

    static final int VARARGS = 0x00000080;

    static final int SYNTHETIC = 0x00001000;

    static final int ANNOTATION = 0x00002000;

    static final int ENUM = 0x00004000;

    static final int MANDATED = 0x00008000;

    static boolean isSynthetic(int mod);

    static boolean isMandated(int mod);

    private static final int CLASS_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE | Modifier.ABSTRACT | Modifier.STATIC | Modifier.FINAL | Modifier.STRICT;

    private static final int INTERFACE_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE | Modifier.ABSTRACT | Modifier.STATIC | Modifier.STRICT;

    private static final int CONSTRUCTOR_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    private static final int METHOD_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE | Modifier.ABSTRACT | Modifier.STATIC | Modifier.FINAL | Modifier.SYNCHRONIZED | Modifier.NATIVE | Modifier.STRICT;

    private static final int FIELD_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL | Modifier.TRANSIENT | Modifier.VOLATILE;

    private static final int PARAMETER_MODIFIERS = Modifier.FINAL;

    static final int ACCESS_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    public static int classModifiers();

    public static int interfaceModifiers();

    public static int constructorModifiers();

    public static int methodModifiers();

    public static int fieldModifiers();

    public static int parameterModifiers();
}
