package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.color.ColorSpace;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import sun.awt.image.ImagingLib;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class LookupOp implements BufferedImageOp, RasterOp {

    private LookupTable ltable;

    private int numComponents;

    RenderingHints hints;

    public LookupOp(LookupTable lookup, RenderingHints hints) {
        this.ltable = lookup;
        this.hints = hints;
        numComponents = ltable.getNumComponents();
    }

    public final LookupTable getTable();

    public final BufferedImage filter(BufferedImage src, BufferedImage dst);

    public final WritableRaster filter(Raster src, WritableRaster dst);

    public final Rectangle2D getBounds2D(BufferedImage src);

    public final Rectangle2D getBounds2D(Raster src);

    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    public final RenderingHints getRenderingHints();

    private void byteFilter(ByteLookupTable lookup, Raster src, WritableRaster dst, int width, int height, int numBands);

    private void shortFilter(ShortLookupTable lookup, Raster src, WritableRaster dst, int width, int height, int numBands);
}
