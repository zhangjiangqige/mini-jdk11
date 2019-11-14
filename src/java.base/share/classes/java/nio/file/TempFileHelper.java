package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Set;
import java.util.EnumSet;
import java.security.SecureRandom;
import java.io.IOException;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import static java.nio.file.attribute.PosixFilePermission.*;
import sun.security.action.GetPropertyAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class TempFileHelper {

    private TempFileHelper() {
    }

    private static final Path tmpdir = Path.of(GetPropertyAction.privilegedGetProperty("java.io.tmpdir"));

    private static final boolean isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");

    private static final SecureRandom random = new SecureRandom();

    private static Path generatePath(String prefix, String suffix, Path dir);

    private static class PosixPermissions {

        static final FileAttribute<Set<PosixFilePermission>> filePermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(OWNER_READ, OWNER_WRITE));

        static final FileAttribute<Set<PosixFilePermission>> dirPermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE));
    }

    private static Path create(Path dir, String prefix, String suffix, boolean createDirectory, FileAttribute<?>[] attrs) throws IOException;

    static Path createTempFile(Path dir, String prefix, String suffix, FileAttribute<?>[] attrs) throws IOException;

    static Path createTempDirectory(Path dir, String prefix, FileAttribute<?>[] attrs) throws IOException;
}
