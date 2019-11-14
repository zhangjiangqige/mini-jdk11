package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning", "nullness" })
@Deprecated(since = "9", forRemoval = true)
@UsesObjectEquals
public final class Compiler {

    private Compiler() {
    }

    public static boolean compileClass(Class<?> clazz);

    public static boolean compileClasses(String string);

    @Nullable
    public static Object command(Object any);

    public static void enable();

    public static void disable();
}
