package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.GraphicsEnvironment;
import java.awt.color.ICC_Profile;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.RenderingHints;
import sun.awt.image.ImagingLib;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class BandCombineOp implements RasterOp {

    public BandCombineOp(float[][] matrix, RenderingHints hints) {
    }

    public final float[][] getMatrix();

    public WritableRaster filter(Raster src, WritableRaster dst);

    public final Rectangle2D getBounds2D(Raster src);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    public final RenderingHints getRenderingHints();
}
