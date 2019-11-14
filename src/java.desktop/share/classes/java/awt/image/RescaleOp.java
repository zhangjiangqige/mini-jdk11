package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.color.ColorSpace;
import java.awt.geom.Rectangle2D;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.RenderingHints;
import sun.awt.image.ImagingLib;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RescaleOp implements BufferedImageOp, RasterOp {

    float[] scaleFactors;

    float[] offsets;

    int length = 0;

    RenderingHints hints;

    private int srcNbits;

    private int dstNbits;

    public RescaleOp(float[] scaleFactors, float[] offsets, RenderingHints hints) {
        length = scaleFactors.length;
        if (length > offsets.length)
            length = offsets.length;
        this.scaleFactors = new float[length];
        this.offsets = new float[length];
        for (int i = 0; i < length; i++) {
            this.scaleFactors[i] = scaleFactors[i];
            this.offsets[i] = offsets[i];
        }
        this.hints = hints;
    }

    public RescaleOp(float scaleFactor, float offset, RenderingHints hints) {
        length = 1;
        this.scaleFactors = new float[1];
        this.offsets = new float[1];
        this.scaleFactors[0] = scaleFactor;
        this.offsets[0] = offset;
        this.hints = hints;
    }

    public final float[] getScaleFactors(float[] scaleFactors);

    public final float[] getOffsets(float[] offsets);

    public final int getNumFactors();

    private ByteLookupTable createByteLut(float[] scale, float[] off, int nBands, int nElems);

    private ShortLookupTable createShortLut(float[] scale, float[] off, int nBands, int nElems);

    private boolean canUseLookup(Raster src, Raster dst);

    public final BufferedImage filter(BufferedImage src, BufferedImage dst);

    public final WritableRaster filter(Raster src, WritableRaster dst);

    private WritableRaster filterRasterImpl(Raster src, WritableRaster dst, int scaleConst, boolean sCheck);

    public final Rectangle2D getBounds2D(BufferedImage src);

    public final Rectangle2D getBounds2D(Raster src);

    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    public final RenderingHints getRenderingHints();
}
