package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.attribute.*;
import java.io.InputStream;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class CopyMoveHelper {

    private CopyMoveHelper() {
    }

    private static class CopyOptions {

        boolean replaceExisting = false;

        boolean copyAttributes = false;

        boolean followLinks = true;

        private CopyOptions() {
        }

        static CopyOptions parse(CopyOption... options);
    }

    private static CopyOption[] convertMoveToCopyOptions(CopyOption... options) throws AtomicMoveNotSupportedException;

    static void copyToForeignTarget(Path source, Path target, CopyOption... options) throws IOException;

    static void moveToForeignTarget(Path source, Path target, CopyOption... options) throws IOException;
}
