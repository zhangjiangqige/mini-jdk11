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

    float[][] matrix;

    int nrows = 0;

    int ncols = 0;

    RenderingHints hints;

    public BandCombineOp(float[][] matrix, RenderingHints hints) {
        nrows = matrix.length;
        ncols = matrix[0].length;
        this.matrix = new float[nrows][];
        for (int i = 0; i < nrows; i++) {
            if (ncols > matrix[i].length) {
                throw new IndexOutOfBoundsException("row " + i + " too short");
            }
            this.matrix[i] = Arrays.copyOf(matrix[i], ncols);
        }
        this.hints = hints;
    }

    public final float[][] getMatrix();

    public WritableRaster filter(Raster src, WritableRaster dst);

    public final Rectangle2D getBounds2D(Raster src);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    public final RenderingHints getRenderingHints();
}
