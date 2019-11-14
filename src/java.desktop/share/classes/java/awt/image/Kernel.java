package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Kernel implements Cloneable {

    private int width;

    private int height;

    private int xOrigin;

    private int yOrigin;

    private float[] data;

    private static native void initIDs();

    static {
        ColorModel.loadLibraries();
        initIDs();
    }

    public Kernel(int width, int height, float[] data) {
        this.width = width;
        this.height = height;
        this.xOrigin = (width - 1) >> 1;
        this.yOrigin = (height - 1) >> 1;
        int len = width * height;
        if (data.length < len) {
            throw new IllegalArgumentException("Data array too small " + "(is " + data.length + " and should be " + len);
        }
        this.data = new float[len];
        System.arraycopy(data, 0, this.data, 0, len);
    }

    public final int getXOrigin();

    public final int getYOrigin();

    public final int getWidth();

    public final int getHeight();

    public final float[] getKernelData(float[] data);

    public Object clone();
}
