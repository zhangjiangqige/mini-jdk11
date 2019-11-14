package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
abstract class MultipleGradientPaintContext implements PaintContext {

    protected ColorModel model;

    private static ColorModel xrgbmodel = new DirectColorModel(24, 0x00ff0000, 0x0000ff00, 0x000000ff);

    protected static ColorModel cachedModel;

    protected static WeakReference<Raster> cached;

    protected Raster saved;

    protected CycleMethod cycleMethod;

    protected ColorSpaceType colorSpace;

    protected float a00, a01, a10, a11, a02, a12;

    protected boolean isSimpleLookup;

    protected int fastGradientArraySize;

    protected int[] gradient;

    private int[][] gradients;

    private float[] normalizedIntervals;

    private float[] fractions;

    private int transparencyTest;

    private static final int[] SRGBtoLinearRGB = new int[256];

    private static final int[] LinearRGBtoSRGB = new int[256];

    static {
        for (int k = 0; k < 256; k++) {
            SRGBtoLinearRGB[k] = convertSRGBtoLinearRGB(k);
            LinearRGBtoSRGB[k] = convertLinearRGBtoSRGB(k);
        }
    }

    protected static final int GRADIENT_SIZE = 256;

    protected static final int GRADIENT_SIZE_INDEX = GRADIENT_SIZE - 1;

    private static final int MAX_GRADIENT_ARRAY_SIZE = 5000;

    protected MultipleGradientPaintContext(MultipleGradientPaint mgp, ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform t, RenderingHints hints, float[] fractions, Color[] colors, CycleMethod cycleMethod, ColorSpaceType colorSpace) {
        if (deviceBounds == null) {
            throw new NullPointerException("Device bounds cannot be null");
        }
        if (userBounds == null) {
            throw new NullPointerException("User bounds cannot be null");
        }
        if (t == null) {
            throw new NullPointerException("Transform cannot be null");
        }
        if (hints == null) {
            throw new NullPointerException("RenderingHints cannot be null");
        }
        AffineTransform tInv;
        try {
            t.invert();
            tInv = t;
        } catch (NoninvertibleTransformException e) {
            tInv = new AffineTransform();
        }
        double[] m = new double[6];
        tInv.getMatrix(m);
        a00 = (float) m[0];
        a10 = (float) m[1];
        a01 = (float) m[2];
        a11 = (float) m[3];
        a02 = (float) m[4];
        a12 = (float) m[5];
        this.cycleMethod = cycleMethod;
        this.colorSpace = colorSpace;
        this.fractions = fractions;
        int[] gradient = (mgp.gradient != null) ? mgp.gradient.get() : null;
        int[][] gradients = (mgp.gradients != null) ? mgp.gradients.get() : null;
        if (gradient == null && gradients == null) {
            calculateLookupData(colors);
            mgp.model = this.model;
            mgp.normalizedIntervals = this.normalizedIntervals;
            mgp.isSimpleLookup = this.isSimpleLookup;
            if (isSimpleLookup) {
                mgp.fastGradientArraySize = this.fastGradientArraySize;
                mgp.gradient = new SoftReference<int[]>(this.gradient);
            } else {
                mgp.gradients = new SoftReference<int[][]>(this.gradients);
            }
        } else {
            this.model = mgp.model;
            this.normalizedIntervals = mgp.normalizedIntervals;
            this.isSimpleLookup = mgp.isSimpleLookup;
            this.gradient = gradient;
            this.fastGradientArraySize = mgp.fastGradientArraySize;
            this.gradients = gradients;
        }
    }

    private void calculateLookupData(Color[] colors);

    private void calculateSingleArrayGradient(Color[] colors, float Imin);

    private void calculateMultipleArrayGradient(Color[] colors);

    private void interpolate(int rgb1, int rgb2, int[] output);

    private int convertEntireColorLinearRGBtoSRGB(int rgb);

    protected final int indexIntoGradientsArrays(float position);

    private static int convertSRGBtoLinearRGB(int color);

    private static int convertLinearRGBtoSRGB(int color);

    public final Raster getRaster(int x, int y, int w, int h);

    protected abstract void fillRaster(int[] pixels, int off, int adjust, int x, int y, int w, int h);

    private static synchronized Raster getCachedRaster(ColorModel cm, int w, int h);

    private static synchronized void putCachedRaster(ColorModel cm, Raster ras);

    public final void dispose();

    public final ColorModel getColorModel();
}
