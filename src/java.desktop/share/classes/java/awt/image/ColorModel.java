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

    private long pData;

    protected int pixel_bits;

    int[] nBits;

    int transparency = Transparency.TRANSLUCENT;

    boolean supportsAlpha = true;

    boolean isAlphaPremultiplied = false;

    int numComponents = -1;

    int numColorComponents = -1;

    ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB);

    int colorSpaceType = ColorSpace.TYPE_RGB;

    int maxBits;

    boolean is_sRGB = true;

    protected int transferType;

    private static boolean loaded = false;

    static void loadLibraries();

    private static native void initIDs();

    static {
        loadLibraries();
        initIDs();
    }

    private static ColorModel RGBdefault;

    public static ColorModel getRGBdefault();

    public ColorModel(@Positive int bits) {
        pixel_bits = bits;
        if (bits < 1) {
            throw new IllegalArgumentException("Number of bits must be > 0");
        }
        numComponents = 4;
        numColorComponents = 3;
        maxBits = bits;
        transferType = ColorModel.getDefaultTransferType(bits);
    }

    protected ColorModel(@Positive int pixel_bits, @NonNegative int[] bits, ColorSpace cspace, boolean hasAlpha, boolean isAlphaPremultiplied, int transparency, int transferType) {
        colorSpace = cspace;
        colorSpaceType = cspace.getType();
        numColorComponents = cspace.getNumComponents();
        numComponents = numColorComponents + (hasAlpha ? 1 : 0);
        supportsAlpha = hasAlpha;
        if (bits.length < numComponents) {
            throw new IllegalArgumentException("Number of color/alpha " + "components should be " + numComponents + " but length of bits array is " + bits.length);
        }
        if (transparency < Transparency.OPAQUE || transparency > Transparency.TRANSLUCENT) {
            throw new IllegalArgumentException("Unknown transparency: " + transparency);
        }
        if (supportsAlpha == false) {
            this.isAlphaPremultiplied = false;
            this.transparency = Transparency.OPAQUE;
        } else {
            this.isAlphaPremultiplied = isAlphaPremultiplied;
            this.transparency = transparency;
        }
        nBits = Arrays.copyOf(bits, numComponents);
        this.pixel_bits = pixel_bits;
        if (pixel_bits <= 0) {
            throw new IllegalArgumentException("Number of pixel bits must " + "be > 0");
        }
        maxBits = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] < 0) {
                throw new IllegalArgumentException("Number of bits must be >= 0");
            }
            if (maxBits < bits[i]) {
                maxBits = bits[i];
            }
        }
        if (maxBits == 0) {
            throw new IllegalArgumentException("There must be at least " + "one component with > 0 " + "pixel bits.");
        }
        if (cspace != ColorSpace.getInstance(ColorSpace.CS_sRGB)) {
            is_sRGB = false;
        }
        this.transferType = transferType;
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

    @Deprecated(since = "9")
    public void finalize();

    public WritableRaster getAlphaRaster(WritableRaster raster);

    public String toString();

    static int getDefaultTransferType(int pixel_bits);

    static byte[] l8Tos8 = null;

    static byte[] s8Tol8 = null;

    static byte[] l16Tos8 = null;

    static short[] s8Tol16 = null;

    static Map<ICC_ColorSpace, byte[]> g8Tos8Map = null;

    static Map<ICC_ColorSpace, byte[]> lg16Toog8Map = null;

    static Map<ICC_ColorSpace, byte[]> g16Tos8Map = null;

    static Map<ICC_ColorSpace, short[]> lg16Toog16Map = null;

    static boolean isLinearRGBspace(ColorSpace cs);

    static boolean isLinearGRAYspace(ColorSpace cs);

    static byte[] getLinearRGB8TosRGB8LUT();

    static byte[] getsRGB8ToLinearRGB8LUT();

    static byte[] getLinearRGB16TosRGB8LUT();

    static short[] getsRGB8ToLinearRGB16LUT();

    static byte[] getGray8TosRGB8LUT(ICC_ColorSpace grayCS);

    static byte[] getLinearGray16ToOtherGray8LUT(ICC_ColorSpace grayCS);

    static byte[] getGray16TosRGB8LUT(ICC_ColorSpace grayCS);

    static short[] getLinearGray16ToOtherGray16LUT(ICC_ColorSpace grayCS);
}
