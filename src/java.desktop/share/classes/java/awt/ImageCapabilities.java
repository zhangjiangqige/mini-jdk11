package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ImageCapabilities implements Cloneable {

    private boolean accelerated = false;

    public ImageCapabilities(boolean accelerated) {
        this.accelerated = accelerated;
    }

    public boolean isAccelerated();

    public boolean isTrueVolatile();

    public Object clone();
}
