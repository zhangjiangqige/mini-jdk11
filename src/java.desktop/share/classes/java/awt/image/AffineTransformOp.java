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

    private AffineTransform xform;

    RenderingHints hints;

    @Native
    public static final int TYPE_NEAREST_NEIGHBOR = 1;

    @Native
    public static final int TYPE_BILINEAR = 2;

    @Native
    public static final int TYPE_BICUBIC = 3;

    int interpolationType = TYPE_NEAREST_NEIGHBOR;

    public AffineTransformOp(AffineTransform xform, RenderingHints hints) {
        validateTransform(xform);
        this.xform = (AffineTransform) xform.clone();
        this.hints = hints;
        if (hints != null) {
            Object value = hints.get(RenderingHints.KEY_INTERPOLATION);
            if (value == null) {
                value = hints.get(RenderingHints.KEY_RENDERING);
                if (value == RenderingHints.VALUE_RENDER_SPEED) {
                    interpolationType = TYPE_NEAREST_NEIGHBOR;
                } else if (value == RenderingHints.VALUE_RENDER_QUALITY) {
                    interpolationType = TYPE_BILINEAR;
                }
            } else if (value == RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR) {
                interpolationType = TYPE_NEAREST_NEIGHBOR;
            } else if (value == RenderingHints.VALUE_INTERPOLATION_BILINEAR) {
                interpolationType = TYPE_BILINEAR;
            } else if (value == RenderingHints.VALUE_INTERPOLATION_BICUBIC) {
                interpolationType = TYPE_BICUBIC;
            }
        } else {
            interpolationType = TYPE_NEAREST_NEIGHBOR;
        }
    }

    public AffineTransformOp(AffineTransform xform, int interpolationType) {
        validateTransform(xform);
        this.xform = (AffineTransform) xform.clone();
        switch(interpolationType) {
            case TYPE_NEAREST_NEIGHBOR:
            case TYPE_BILINEAR:
            case TYPE_BICUBIC:
                break;
            default:
                throw new IllegalArgumentException("Unknown interpolation type: " + interpolationType);
        }
        this.interpolationType = interpolationType;
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

    void validateTransform(AffineTransform xform);
}
