package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Kernel implements Cloneable {

    public Kernel(int width, int height, float[] data) {
    }

    public final int getXOrigin();

    public final int getYOrigin();

    public final int getWidth();

    public final int getHeight();

    public final float[] getKernelData(float[] data);

    public Object clone();
}
