package java.nio.file.attribute;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index" })
public interface BasicFileAttributes {

    FileTime lastModifiedTime();

    FileTime lastAccessTime();

    FileTime creationTime();

    boolean isRegularFile();

    boolean isDirectory();

    boolean isSymbolicLink();

    boolean isOther();

    @NonNegative
    long size();

    Object fileKey();
}
