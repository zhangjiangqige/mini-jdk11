package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.lang.ref.SoftReference;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class MultipleGradientPaint implements Paint {

    public static enum CycleMethod {

        NO_CYCLE, REFLECT, REPEAT
    }

    public static enum ColorSpaceType {

        SRGB, LINEAR_RGB
    }

    public final float[] getFractions();

    public final Color[] getColors();

    public final CycleMethod getCycleMethod();

    public final ColorSpaceType getColorSpace();

    public final AffineTransform getTransform();

    public final int getTransparency();
}
