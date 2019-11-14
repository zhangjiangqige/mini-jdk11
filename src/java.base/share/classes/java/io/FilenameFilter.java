package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
@FunctionalInterface
public interface FilenameFilter {

    boolean accept(File dir, String name);
}
