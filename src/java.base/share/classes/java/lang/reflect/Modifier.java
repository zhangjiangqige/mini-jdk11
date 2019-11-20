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

    public static final int PUBLIC;

    public static final int PRIVATE;

    public static final int PROTECTED;

    public static final int STATIC;

    public static final int FINAL;

    public static final int SYNCHRONIZED;

    public static final int VOLATILE;

    public static final int TRANSIENT;

    public static final int NATIVE;

    public static final int INTERFACE;

    public static final int ABSTRACT;

    public static final int STRICT;

    public static int classModifiers();

    public static int interfaceModifiers();

    public static int constructorModifiers();

    public static int methodModifiers();

    public static int fieldModifiers();

    public static int parameterModifiers();
}
