package java.awt.image;

import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.index.qual.SameLen;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import sun.java2d.cmm.CMSManager;
import sun.java2d.cmm.ColorTransform;
import sun.java2d.cmm.PCMM;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Arrays;

@AnnotatedFor({ "index" })
public abstract class ColorModel implements Transparency {

    protected int pixel_bits;

    protected int transferType;

    public static ColorModel getRGBdefault();

    public ColorModel(@Positive int bits) {
    }

    protected ColorModel(@Positive int pixel_bits, @NonNegative int[] bits, ColorSpace cspace, boolean hasAlpha, boolean isAlphaPremultiplied, int transparency, int transferType) {
    }

    public final boolean hasAlpha();

    public final boolean isAlphaPremultiplied();

    public final int getTransferType();

    @NonNegative
    public int getPixelSize();

    @NonNegative
    public int getComponentSize(@IndexFor({ "this" }) int componentIdx);

    @NonNegative
    public int[] getComponentSize();

    public int getTransparency();

    @LengthOf({ "this" })
    public int getNumComponents();

    @IndexOrHigh({ "this" })
    public int getNumColorComponents();

    public abstract int getRed(int pixel);

    public abstract int getGreen(int pixel);

    public abstract int getBlue(int pixel);

    public abstract int getAlpha(int pixel);

    public int getRGB(int pixel);

    public int getRed(Object inData);

    public int getGreen(Object inData);

    public int getBlue(Object inData);

    public int getAlpha(Object inData);

    public int getRGB(Object inData);

    public Object getDataElements(int rgb, Object pixel);

    @NonNegative
    public int @SameLen({ "#2" }) [] getComponents(int pixel, int[] components, @IndexFor({ "#2" }) int offset);

    @NonNegative
    public int @SameLen({ "#2" }) [] getComponents(Object pixel, int[] components, @IndexFor({ "#2" }) int offset);

    public int @SameLen({ "#1", "#3" }) [] getUnnormalizedComponents(float @SameLen({ "#1", "#3" }) [] normComponents, @IndexFor({ "#1" }) int normOffset, int @SameLen({ "#1", "#3" }) [] components, @IndexFor({ "#3" }) int offset);

    public float @SameLen({ "#1", "#3" }) [] getNormalizedComponents(int @SameLen({ "#1", "#3" }) [] components, @IndexFor({ "#1" }) int offset, float @SameLen({ "#1", "#3" }) [] normComponents, @IndexFor({ "#3" }) int normOffset);

    public int getDataElement(int[] components, @IndexFor({ "#1" }) int offset);

    public Object getDataElements(int[] components, @IndexFor({ "#1" }) int offset, Object obj);

    public int getDataElement(float[] normComponents, @IndexFor({ "#1" }) int normOffset);

    public Object getDataElements(float[] normComponents, @IndexFor({ "#1" }) int normOffset, Object obj);

    public float[] getNormalizedComponents(Object pixel, float[] normComponents, @IndexFor({ "#2" }) int normOffset);

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    public final ColorSpace getColorSpace();

    public ColorModel coerceData(WritableRaster raster, boolean isAlphaPremultiplied);

    public boolean isCompatibleRaster(Raster raster);

    public WritableRaster createCompatibleWritableRaster(int w, int h);

    public SampleModel createCompatibleSampleModel(int w, int h);

    public boolean isCompatibleSampleModel(SampleModel sm);

    @Deprecated()
    public void finalize();

    public WritableRaster getAlphaRaster(WritableRaster raster);

    public String toString();
}
