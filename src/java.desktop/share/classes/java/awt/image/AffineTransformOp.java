package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.AlphaComposite;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.lang.annotation.Native;
import sun.awt.image.ImagingLib;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AffineTransformOp implements BufferedImageOp, RasterOp {

    @Native
    public static final int TYPE_NEAREST_NEIGHBOR;

    @Native
    public static final int TYPE_BILINEAR;

    @Native
    public static final int TYPE_BICUBIC;

    public AffineTransformOp(AffineTransform xform, RenderingHints hints) {
    }

    public AffineTransformOp(AffineTransform xform, int interpolationType) {
    }

    public final int getInterpolationType();

    public final BufferedImage filter(BufferedImage src, BufferedImage dst);

    public final WritableRaster filter(Raster src, WritableRaster dst);

    public final Rectangle2D getBounds2D(BufferedImage src);

    public final Rectangle2D getBounds2D(Raster src);

    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    public final AffineTransform getTransform();

    public final RenderingHints getRenderingHints();
}
