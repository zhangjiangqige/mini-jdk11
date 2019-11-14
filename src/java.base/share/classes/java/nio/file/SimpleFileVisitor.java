package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.Objects;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class SimpleFileVisitor<T> implements FileVisitor<T> {

    protected SimpleFileVisitor() {
    }

    @Override
    public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) throws IOException;

    @Override
    public FileVisitResult visitFile(T file, BasicFileAttributes attrs) throws IOException;

    @Override
    public FileVisitResult visitFileFailed(T file, IOException exc) throws IOException;

    @Override
    public FileVisitResult postVisitDirectory(T dir, IOException exc) throws IOException;
}
