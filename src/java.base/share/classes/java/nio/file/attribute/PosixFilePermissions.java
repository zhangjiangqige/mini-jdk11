package java.nio.file.attribute;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.nio.file.attribute.PosixFilePermission.*;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class PosixFilePermissions {

    private PosixFilePermissions() {
    }

    private static void writeBits(StringBuilder sb, boolean r, boolean w, boolean x);

    public static String toString(Set<PosixFilePermission> perms);

    private static boolean isSet(char c, char setValue);

    private static boolean isR(char c);

    private static boolean isW(char c);

    private static boolean isX(char c);

    public static Set<PosixFilePermission> fromString(String perms);

    public static FileAttribute<Set<PosixFilePermission>> asFileAttribute(Set<PosixFilePermission> perms);
}
