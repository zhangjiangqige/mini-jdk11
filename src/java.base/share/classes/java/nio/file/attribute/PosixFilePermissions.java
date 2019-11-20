package java.nio.file.attribute;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.nio.file.attribute.PosixFilePermission.*;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class PosixFilePermissions {

    public static String toString(Set<PosixFilePermission> perms);

    public static Set<PosixFilePermission> fromString(String perms);

    public static FileAttribute<Set<PosixFilePermission>> asFileAttribute(Set<PosixFilePermission> perms);
}
