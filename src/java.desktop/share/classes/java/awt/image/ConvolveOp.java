package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.color.ICC_Profile;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.lang.annotation.Native;
import sun.awt.image.ImagingLib;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ConvolveOp implements BufferedImageOp, RasterOp {

    Kernel kernel;

    int edgeHint;

    RenderingHints hints;

    @Native
    public static final int EDGE_ZERO_FILL = 0;

    @Native
    public static final int EDGE_NO_OP = 1;

    public ConvolveOp(Kernel kernel, int edgeCondition, RenderingHints hints) {
        this.kernel = kernel;
        this.edgeHint = edgeCondition;
        this.hints = hints;
    }

    public ConvolveOp(Kernel kernel) {
        this.kernel = kernel;
        this.edgeHint = EDGE_ZERO_FILL;
    }

    public int getEdgeCondition();

    public final Kernel getKernel();

    public final BufferedImage filter(BufferedImage src, BufferedImage dst);

    public final WritableRaster filter(Raster src, WritableRaster dst);

    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Rectangle2D getBounds2D(BufferedImage src);

    public final Rectangle2D getBounds2D(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    public final RenderingHints getRenderingHints();
}
