package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ImageCapabilities implements Cloneable {

    public ImageCapabilities(boolean accelerated) {
    }

    public boolean isAccelerated();

    public boolean isTrueVolatile();

    public Object clone();
}
